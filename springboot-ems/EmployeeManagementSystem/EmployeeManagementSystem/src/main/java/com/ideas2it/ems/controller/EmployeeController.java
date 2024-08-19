package com.ideas2it.ems.controller;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import com.ideas2it.ems.exception.EmployeeException;
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
import com.ideas2it.ems.service.DepartmentService;
import com.ideas2it.ems.service.EmployeeService;
import com.ideas2it.ems.model.BankDetail;
import com.ideas2it.ems.model.Certificate;
import com.ideas2it.ems.model.Department;
import com.ideas2it.ems.model.Employee;

import static com.ideas2it.ems.mapper.CertificateMapper.convertEntityToDto;
import static com.ideas2it.ems.mapper.EmployeeMapper.convertDtoToEntity;
import static com.ideas2it.ems.mapper.EmployeeMapper.convertEntityToDto;
import static com.ideas2it.ems.mapper.DepartmentMapper.convertDtoToEntity;

/**
* <p>The class used for user input and output for employee details.
* Adding the employee, displaying, updating and deleting the details by employee id
* </p>
* @author Dharani G
*
*/
@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private CertificateService certificateService;

    public EmployeeController(EmployeeService employeeService, DepartmentService departmentService, CertificateService certificateService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
        this.certificateService = certificateService;
    }


    /**
    * <p>Add employee details into the employee object
     * @param employeeDto  {@link EmployeeDto}
     * @return employeeDto - given as employeeDto to user
    * if added - successfully added, if not cause error </p>
    */
    @PostMapping
    public ResponseEntity<EmployeeDto> addEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
        Employee employee = convertDtoToEntity(employeeDto);
        Department department = convertDtoToEntity(departmentService.getDepartmentById(employeeDto.getDepartmentId()));
        employee.setDepartment(department);
        BankDetail bankDetail = new BankDetail(employeeDto.getAccountNumber(), employeeDto.getBranch());
        employee.setBankDetail(bankDetail);
        employeeService.addEmployee(employee);
        logger.info("Employee details added successfully {}", employeeDto.getEmployeeName());
        return new ResponseEntity<>(convertEntityToDto(employee), HttpStatus.CREATED);
    }

    /**
    * <p>Display all employee details
     * @return List<EmployeeDto> -  list of employeeDto
     * if employee is empty shows warning</p>
    */
    @GetMapping
    public ResponseEntity<Set<EmployeeDto>> displayEmployees() {
        Set<EmployeeDto> employeeDtos = new HashSet<>();
        Set<Employee> employees =  employeeService.getAllEmployees();
        if (employees.isEmpty()) {
            logger.warn("Empty employee details");
            return  new ResponseEntity<>(employeeDtos, HttpStatus.NOT_FOUND);
        } else {
            for(Employee employee : employees) {
                EmployeeDto employeeDto = convertEntityToDto(employee);
                employeeDtos.add(employeeDto);
            }
        }
        return new ResponseEntity<>(employeeDtos, HttpStatus.OK);
    }

    /** 
    * <p>Display employee details By employee Id
     * @param employeeId - Id of the employee
     * @return employeeDto -
    * </p>
    *
    */
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> displayEmployeeById(@PathVariable("id") int employeeId) {
        EmployeeDto employeeDto = convertEntityToDto(employeeService.getEmployeeById(employeeId));
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }

    /**
    * <p>
     *     Update employee by checking with employee id
     * @param employeeId - employeeId of the employee
     * @param employeeDto - {@link EmployeeDto}
     * @return EmployeeDto -
     * if employee id not contains shows warning, otherwise it updates
    * If error occurs while updating, shows exception otherwise, it updates</p>
    */
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") int employeeId, @RequestBody EmployeeDto employeeDto) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        if (null == employee) {
            logger.warn("employee does not found {}", employeeId);
            throw new NoSuchElementException("Can't update. Employee not found" + employeeId);
        }
        employee.setEmployeeName(employeeDto.getEmployeeName());
        employee.setEmployeeDOB(employeeDto.getEmployeeDOB());
        employee.setContactNumber(employeeDto.getContactNumber());
        employee.setMailId(employeeDto.getMailId());
        employee.setExperience(employeeDto.getExperience());
        employee.setSalary(employeeDto.getSalary());
        employee.setCity(employeeDto.getCity());
        logger.info("Employee details updated successfully {}", employeeId);
        return new ResponseEntity<>(convertEntityToDto(employeeService.addEmployee(employee)), HttpStatus.OK);
        
    }

    /**
    * <p>Delete employee by giving employee id
     * * @param employeeId - employeeId of the employee
     *
    * </p>
    */
    @DeleteMapping("/{id}")
    public ResponseEntity<EmployeeDto> deleteEmployee(@PathVariable("id") int employeeId) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        if (null == employee) {
            logger.warn("employee not found {} for delete", employeeId);
            throw new NoSuchElementException("Can't delete.Employee not found" + employeeId);
        }
        employee.setRemoved(true);
        Employee deletedEmployee = employeeService.addEmployee(employee);
        logger.info("Employee deleted successfully {}", employeeId);
        return new ResponseEntity<>(convertEntityToDto(deletedEmployee), HttpStatus.OK);
    }

    /**
     * <p>
     *     Add certificates to employee
     * @param employeeId - Employee id of the employee
     * @param certificateId - certificate if of the user certificate
     * @return employeeDto - updated employeeDto given to user
     *  if certificate or employee is not found, shows warn, otherwise @return EmployeeDto -
     * </p>
     */
    @PutMapping("/{id}/{certificateId}")
    public ResponseEntity<EmployeeDto> addCertificateToEmployee(@PathVariable("id")int employeeId, @PathVariable("certificateId") int certificateId) {
        Certificate certificate = certificateService.getCertificateById(certificateId);
        EmployeeDto employeeDto = null;
        if (null == certificate) {
            logger.warn("Certificate is not found {}", certificateId);
            throw new NoSuchElementException("Certificate not found {}" + certificateId);
        } else {
            Employee employee = employeeService.getEmployeeById(employeeId);
            if (employee == null) {
                logger.warn("employee not found {}", employeeId);
                throw new NoSuchElementException("Employee not found {}" + employeeId);
            }
            Set<Certificate> existingCertificates = employee.getCertificates();
            for (Certificate existingCertificate : existingCertificates) {
                if (existingCertificate == certificate) {
                    throw new EmployeeException("Certificates already assigned to employee {}" + certificateId);
                }
            }
            employee.getCertificates().add(certificate);
            employeeDto = convertEntityToDto(employeeService.addEmployee(employee));
        }
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }

    /**
     * <p>
     *  Display certificates by employee Id
     *  @param employeeId - employeeId of the employee
     * If employee is null, shows warn,
     * @return <Set<CertificateDto>> - if not null returns set of certificates
     * </p>
     */
    @GetMapping("/certificates/{id}")
    public ResponseEntity<Set<CertificateDto>> displayCertificatesByEmployeeId(@PathVariable("id") int employeeId) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        if (employee == null) {
            logger.warn("employee does not exist{}", employeeId);
            throw new NoSuchElementException("Employee not found" + employeeId);
        }
        Set<Certificate> certificates = employee.getCertificates();
        Set<CertificateDto> certificateDtos = new HashSet<>();
        for (Certificate certificate : certificates) {
            CertificateDto certificateDto = convertEntityToDto(certificate);
            certificateDtos.add(certificateDto);
        }
        return new ResponseEntity<>(certificateDtos, HttpStatus.OK);
    }
}
