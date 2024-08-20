package com.ideas2it.ems.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import com.ideas2it.ems.dto.DepartmentDto;
import com.ideas2it.ems.exception.EmployeeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ideas2it.ems.dto.CertificateDto;
import com.ideas2it.ems.dto.EmployeeDto;
import com.ideas2it.ems.model.Certificate;
import com.ideas2it.ems.model.Department;
import com.ideas2it.ems.model.Employee;
import com.ideas2it.ems.service.CertificateService;
import com.ideas2it.ems.service.DepartmentService;
import com.ideas2it.ems.service.EmployeeService;
import com.ideas2it.ems.model.BankDetail;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private EmployeeService employeeService;

    @Mock
    private DepartmentService departmentService;

    @Mock
    private CertificateService certificateService;

    EmployeeDto employeeDto = new EmployeeDto();
    DepartmentDto departmentDto = new DepartmentDto();
    CertificateDto certificateDto = new CertificateDto();
    Department department =new Department();
    Employee employee = new Employee();
    Certificate certificate = new Certificate();
    Set<Employee> employees = new HashSet<>();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        employeeDto.setEmployeeId(1);
        employeeDto.setEmployeeName("Dharani");
        employeeDto.setEmployeeDOB(LocalDate.of(2000, 10, 10));
        employeeDto.setContactNumber(9898989898L);
        employeeDto.setDepartmentId(3);
        employeeDto.setDepartmentName("IT");
        employeeDto.setMailId("dharani@gmail.com");
        employeeDto.setExperience(4);
        employeeDto.setSalary(65000);
        employeeDto.setCity("Erode");
        employeeDto.setCertificateId(1);
        employeeDto.setCertificateName("aws master");
        employeeDto.setAccountNumber(145564352L);
        employeeDto.setBranch("Sathy");
        departmentDto.setDepartmentId(3);
        departmentDto.setDepartmentName("IT");
        department.setDepartmentId(3);
        department.setDepartmentName("IT");
        certificate.setCertificateId(1);
        certificate.setCertificateName("aws master");
        Set<Certificate> certificates = new HashSet<>();
        certificates.add(certificate);
        employee.setEmployeeId(1);
        employee.setEmployeeName("Dharani");
        employee.setEmployeeDOB(LocalDate.of(2000, 10, 10));
        employee.setContactNumber(9898989898L);
        employee.setDepartment(department);
        employee.setMailId("dharani@gmail.com");
        employee.setExperience(4);
        employee.setSalary(65000);
        employee.setCity("Erode");
        employee.setCertificates(certificates);
        employee.setBankDetail(new BankDetail(145564352L,"Sathy" ));
        certificateDto.setCertificateId(employeeDto.getCertificateId());
        certificateDto.setCertificateName(employeeDto.getCertificateName());
        employees.add(employee);

    }

    @Test
    void testAddEmployee() {
        when(departmentService.getDepartmentById(anyInt())).thenReturn(departmentDto);
        when(employeeService.addEmployee(any(Employee.class))).thenReturn(new Employee());
        ResponseEntity<EmployeeDto> response = employeeController.addEmployee(employeeDto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(employeeDto.getEmployeeName(), response.getBody().getEmployeeName());
        assertEquals(employeeDto.getDepartmentId(), response.getBody().getDepartmentId());
    }

    @Test
    void testDisplayEmployeesSuccess() {
        when(employeeService.getAllEmployees()).thenReturn(employees);
        ResponseEntity<Set<EmployeeDto>> response = employeeController.displayEmployees();
        assertEquals(1,employees.size());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }


    @Test
    void testDisplayEmployeeById() {
        when(employeeService.getEmployeeById(1)).thenReturn(employee);
        ResponseEntity<EmployeeDto> response = employeeController.displayEmployeeById(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testDisplayEmployeesByIdFailure() {
        when(employeeService.getEmployeeById(1)).thenReturn(null);
        assertThrows(NoSuchElementException.class, () -> employeeController.displayEmployeeById(1));
    }

    @Test
    void testUpdateEmployeeSuccess() {
        when(employeeService.getEmployeeById(1)).thenReturn(employee);
        when(employeeService.addEmployee(any(Employee.class))).thenReturn(employee);
        ResponseEntity<EmployeeDto> response = employeeController.updateEmployee(1, employeeDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testUpdateEmployeeFailure() {
        when(employeeService.getEmployeeById(1)).thenReturn(null);
        assertThrows(NoSuchElementException.class, () -> employeeController.updateEmployee(1, employeeDto));
    }
    @Test
    void testDeleteEmployee() {
        when(employeeService.getEmployeeById(1)).thenReturn(employee);
        when(employeeService.addEmployee(any(Employee.class))).thenReturn(employee);
        ResponseEntity<EmployeeDto> response = employeeController.deleteEmployee(employeeDto.getEmployeeId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testDeleteEmployeeFailure() {
        when(employeeService.getEmployeeById(1)).thenReturn(null);
        assertThrows(NoSuchElementException.class, () -> employeeController.deleteEmployee(employeeDto.getEmployeeId()));
    }

    @Test
    void testAddCertificateToEmployee() {
        Certificate newCertificate = Certificate.builder().certificateId(5).certificateName("android").build();
        when(certificateService.getCertificateById(newCertificate.getCertificateId())).thenReturn(certificate);
        when(employeeService.getEmployeeById(1)).thenReturn(employee);
        when(employeeService.addEmployee(any(Employee.class))).thenReturn(employee);
        ResponseEntity<EmployeeDto> response = employeeController.addCertificateToEmployee(1, newCertificate.getCertificateId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testAddCertificateToEmployeeFailure() {
        when(certificateService.getCertificateById(employeeDto.getCertificateId())).thenReturn(null);
        assertThrows(NoSuchElementException.class,() -> employeeController.addCertificateToEmployee(employeeDto.getEmployeeId(), employeeDto.getCertificateId()));
        when(employeeService.getEmployeeById(employeeDto.getEmployeeId())).thenReturn(null);
        assertThrows(NoSuchElementException.class, () -> employeeController.addCertificateToEmployee(employeeDto.getEmployeeId(), employeeDto.getCertificateId()));
        when(employeeService.addEmployee(employee)).thenReturn(null);
        assertThrows(NoSuchElementException.class,() -> employeeController.addCertificateToEmployee(employeeDto.getEmployeeId(), employeeDto.getCertificateId()));


    }
    @Test
    void testDisplayCertificatesByEmployeeId() {
        when(employeeService.getEmployeeById(anyInt())).thenReturn(employee);
        ResponseEntity<Set<CertificateDto>> response = employeeController.displayCertificatesByEmployeeId(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(response.getBody().isEmpty());
    }

    @Test
    void testDisplayCertificatesByEmployeeIdFailure() {
        when(employeeService.getEmployeeById(employeeDto.getEmployeeId())).thenReturn(null);
        assertThrows(NoSuchElementException.class, () -> employeeController.displayCertificatesByEmployeeId(employeeDto.getEmployeeId()));
    }
}
