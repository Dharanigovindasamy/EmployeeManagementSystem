package com.ideas2it.ems.controller;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import jakarta.validation.Valid;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ideas2it.ems.dto.CertificateDto;
import com.ideas2it.ems.dto.EmployeeDto;
import com.ideas2it.ems.service.CertificateService;
import com.ideas2it.ems.model.Certificate;
import com.ideas2it.ems.model.Employee;
import static com.ideas2it.ems.mapper.CertificateMapper.convertDtoToEntity;
import static com.ideas2it.ems.mapper.CertificateMapper.convertEntityToDto;
import static com.ideas2it.ems.mapper.EmployeeMapper.convertEntityToDto;

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
    public ResponseEntity<CertificateDto> addCertificate(@Valid @RequestBody CertificateDto certificateDto) {
        Certificate newCertificate = convertDtoToEntity(certificateDto);
        logger.info("Certificates added successfully {}",newCertificate.getCertificateName());
        return new ResponseEntity<>(convertEntityToDto(certificateService.addCertificate(newCertificate)), HttpStatus.CREATED);
    }

    /**
    * <p>
     *    Display all certificate details
     *  if certificateDto is empty, shows warning, otherwise,
     * @return <List<CertificateDto>> - list of certificateDto from user
    * </p>
    */
    @GetMapping
    public ResponseEntity<Set<CertificateDto>> displayCertificates() {
        Set<CertificateDto> certificateDtos = new HashSet<>();
        Set<Certificate> certificates =  certificateService.getAllCertificates();
        for(Certificate certificate : certificates) {
            CertificateDto certificateDto = convertEntityToDto(certificate);
            certificateDtos.add(certificateDto);
        }
        if (certificateDtos.isEmpty()) {
            logger.warn("Empty certificate list");
            return new ResponseEntity<>(certificateDtos, HttpStatus.NOT_FOUND);
        }
        logger.info("Certificates displayed successfully");
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
        if (null == certificate) {
            logger.warn("No certificate found under this Id {}", certificateId);
            throw new NoSuchElementException("Can't display. certificate not found {}" + certificateId);
        }
        CertificateDto certificateDto =  convertEntityToDto(certificate);

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
        if (null == certificate) {
            logger.warn("No certificate found under this certificate Id {}", certificateId);
            throw new NoSuchElementException("Can't update. Certificate not found {} " + certificateId);
        }
        certificate.setCertificateName(certificateDto.getCertificateName());
        certificate = certificateService.addCertificate(certificate);
        certificateDto = convertEntityToDto(certificate);
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
            throw new NoSuchElementException("Cant delete. Certificate not found" + certificateId);
        }
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
            throw new NoSuchElementException("Can't get employees by certificate Id.Certificates not found" + certificateId);
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
