package com.ideas2it.ems.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.ideas2it.ems.dto.DepartmentDto;
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

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        employeeDto.setEmployeeId(1);
        employeeDto.setEmployeeName("Dharani");
        employeeDto.setEmployeeDOB(LocalDate.of(2000, 10, 10));
        employeeDto.setContactNumber(9898989898L);
        employeeDto.setDepartmentId(3);
        employeeDto.setMailId("dharani@gmail.com");
        employeeDto.setExperience(4);
        employeeDto.setSalary(65000);
        employeeDto.setCity("Erode");
        employeeDto.setCertificateId(1);
        employeeDto.setCertificateName("aws master");
        employeeDto.setAccountNumber(145564352L);
        employeeDto.setBranch("Sathy");


        certificateDto.setCertificateId(employeeDto.getCertificateId());
        certificateDto.setCertificateName(employeeDto.getCertificateName());

        departmentDto.setDepartmentId(3);
        departmentDto.setDepartmentName("IT");
        department.setDepartmentId(3);
        department.setDepartmentName("IT");
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
    void testDisplayEmployees() {
        Set<Employee> employees = new HashSet<>();
        employees.add(new Employee());
        when(employeeService.getAllEmployees()).thenReturn(employees);
        ResponseEntity<Set<EmployeeDto>> response = employeeController.displayEmployees();
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testDisplayEmployeeById() {
        Employee employee = new Employee();
        when(employeeService.getEmployeeById(anyInt())).thenReturn(employee);
        ResponseEntity<EmployeeDto> response = employeeController.displayEmployeeById(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testUpdateEmployee() {
        EmployeeDto employeeDto = new EmployeeDto();
        Employee employee = new Employee();
        when(employeeService.getEmployeeById(anyInt())).thenReturn(employee);
        when(employeeService.addEmployee(any(Employee.class))).thenReturn(employee);
        ResponseEntity<EmployeeDto> response = employeeController.updateEmployee(1, employeeDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testDeleteEmployee() {
        Employee employee = new Employee();
        when(employeeService.getEmployeeById(anyInt())).thenReturn(employee);
        when(employeeService.addEmployee(any(Employee.class))).thenReturn(employee);
        ResponseEntity<EmployeeDto> response = employeeController.deleteEmployee(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testAddCertificateToEmployee() {
        Certificate certificate = new Certificate();
        Employee employee = new Employee();
        when(certificateService.getCertificateById(anyInt())).thenReturn(certificate);
        when(employeeService.getEmployeeById(anyInt())).thenReturn(employee);
        when(employeeService.addEmployee(any(Employee.class))).thenReturn(employee);
        ResponseEntity<EmployeeDto> response = employeeController.addCertificateToEmployee(1, 1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testDisplayCertificatesByEmployeeId() {
        Employee employee = new Employee();
        Set<Certificate> certificates = new HashSet<>();
        certificates.add(new Certificate());
        employee.setCertificates(certificates);
        when(employeeService.getEmployeeById(anyInt())).thenReturn(employee);
        ResponseEntity<Set<CertificateDto>> response = employeeController.displayCertificatesByEmployeeId(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(response.getBody().isEmpty());
    }
}
