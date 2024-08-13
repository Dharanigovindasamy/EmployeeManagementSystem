package com.ideas2it.ems.certificate.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.ideas2it.ems.Dto.CertificateDto;
import com.ideas2it.ems.Dto.EmployeeDto;
import com.ideas2it.ems.certificate.service.CertificateService;
import com.ideas2it.ems.model.Certificate;
import com.ideas2it.ems.model.Employee;
import static com.ideas2it.ems.certificate.mapper.CertificateMapper.convertDtoToEntity;
import static com.ideas2it.ems.certificate.mapper.CertificateMapper.convertEntityToDto;
import static com.ideas2it.ems.employee.mapper.EmployeeMapper.convertEntityToDto;

/**
* <p>
 *  The class used for user input and output for certificate details.
 * Adding the certificate, displaying, updating and deleting the details by certificate id,
 * Fetching employee details by certificate id
 * </p>
 * @author Dharani G
 *
*/
@RestController
@RequestMapping ("/api/v1/certificates")
public class CertificateController {

    private static final Logger logger = LogManager.getLogger();


    @Autowired
    private CertificateService certificateService;

    public CertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }


    /**
    * <p>Add certificate details into the certificate object
     * @param certificateDto - {@link CertificateDto}
     * @return CertificateDto - certificateDto from user
    * if added - successfully added, if not cause error </p>
    */
    @PostMapping
    public ResponseEntity<CertificateDto> addCertificate(@RequestBody CertificateDto certificateDto) {
        Certificate certificate = convertDtoToEntity(certificateDto);
        logger.info("Certificates added successfully {}",certificateDto.getCertificateName());
        return new ResponseEntity<>(convertEntityToDto(certificateService.addCertificate(certificate)), HttpStatus.CREATED);
    }

    /**
    * <p>
     *    Display all certificate details
     *  if certificateDto is empty, shows warning, otherwise,
     * @return <List<CertificateDto>> - list of certificateDto from user
    * </p>
    */
    @GetMapping
    public ResponseEntity<List<CertificateDto>> displayCertificates() {
        List<CertificateDto> certificateDtos = new ArrayList<>();
        List<Certificate> certificates =  certificateService.getAllCertificates();
        for(Certificate certificate : certificates) {
            CertificateDto certificateDto = convertEntityToDto(certificate);
            certificateDtos.add(certificateDto);
        }
        if (certificateDtos.isEmpty()) {
            logger.warn("Empty certificate list");
            return new ResponseEntity<>(certificateDtos, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(certificateDtos, HttpStatus.OK);
    }

    /** 
    * <p>Display certificate details By certificate Id
     * @param certificateId - certificateId of the certificate
    * if certificate is null, shows warn.
    * if certificate present by given id, it displays
     * @return CertificateDto - certificateDto to the user </p>
    */
    @GetMapping("/{id}")
    public ResponseEntity<CertificateDto> displayCertificateById(@PathVariable("id") int certificateId) {
        Certificate certificate = certificateService.getCertificateById(certificateId);
        CertificateDto certificateDto =  convertEntityToDto(certificate);
        if (null == certificateDto) {
            logger.warn("No certificate found under this Id {}", certificateId);
            return new ResponseEntity<>(certificateDto, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(certificateDto, HttpStatus.OK);
    }

    /**
    * <p>Update certificate by checking with certificate id
     * @param certificateId - certificateId of the user
     * @param certificateDto - {@link CertificateDto}
     * If certificate is null,shows warn otherwise, it updates
     * @return CertificateDto - certificateDto to the user </p>
    */
    @PutMapping("/{id}")
    public ResponseEntity<CertificateDto> updatedCertificate(@PathVariable("id") int certificateId, @RequestBody CertificateDto certificateDto) {
        Certificate certificate = certificateService.getCertificateById(certificateId);
        certificateDto = convertEntityToDto(certificate);
        if (null == certificateDto) {
            logger.warn("No certificate found under this certificate Id can be updated {}", certificateId);
            return new ResponseEntity<>(certificateDto, HttpStatus.NOT_FOUND);
        }
        certificate.setCertificateName(certificateDto.getCertificateName());
        certificate = certificateService.addCertificate(certificate);
        logger.info("Certificate updated successfully {}", certificateId);
        return new ResponseEntity<>(certificateDto, HttpStatus.OK);
    }

    /**
    * <p>Delete certificate by giving certificate id
     * @param  certificateId - certificateId of the certificate
     * @return certificateDto - certificateDto of the user
    * If certificate is not found, shows warn, otherwise, it deletes</p>
    */
    @DeleteMapping("/{id}")
    public ResponseEntity<CertificateDto> deletedCertificate(@PathVariable("id") int certificateId) {
        Certificate certificate = certificateService.getCertificateById(certificateId);
        if (null == certificate) {
            logger.warn("Certificate not found {}", certificateId);
        }
        certificate = certificateService.addCertificate(certificate);
        certificate.setRemoved(true);
        Certificate deletedCertificate = certificateService.addCertificate(certificate);
        logger.info("certificate deleted successfully {}", certificateId);
        return new ResponseEntity<>(convertEntityToDto(deletedCertificate), HttpStatus.OK);
    }

    /**
     * <p>
     * Display employee details by giving certificate Id
     *
     * @param certificateId - certificateId of the certificate
     *  if certificate is null, shows warning, otherwise,
     * @return Set<EmployeeDto>- set of employeeDto under the certificate id </p>
     */
    @GetMapping("/employees/{certificateId}")
    public ResponseEntity<Set<EmployeeDto>> getEmployeesByCertificateId(@PathVariable("certificateId")int certificateId) {
        Certificate certificate = certificateService.getCertificateById(certificateId);
        if (null == certificate) {
            logger.warn("certificate not found {}", certificateId);
        }
        Set<Employee> employees = certificate.getEmployees();
        Set<EmployeeDto> employeeDtos = new HashSet<>();
        for(Employee employee : employees) {
            EmployeeDto employeeDto = convertEntityToDto(employee);
            employeeDtos.add(employeeDto);
        }
        return new ResponseEntity<>(employeeDtos, HttpStatus.OK);
    }
}
