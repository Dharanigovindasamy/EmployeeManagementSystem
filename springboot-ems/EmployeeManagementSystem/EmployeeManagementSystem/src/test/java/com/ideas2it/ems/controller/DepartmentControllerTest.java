package com.ideas2it.ems.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ideas2it.ems.dto.DepartmentDto;
import com.ideas2it.ems.dto.EmployeeDto;
import com.ideas2it.ems.service.DepartmentService;

@ExtendWith(MockitoExtension.class)
class DepartmentControllerTest {

    @InjectMocks
    private DepartmentController departmentController;

    @Mock
    private DepartmentService departmentService;

    DepartmentDto departmentDto = new DepartmentDto();
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        departmentDto.setDepartmentId(3);
        departmentDto.setDepartmentName("HR");
    }

    @Test
    void testAddDepartment() {
        when(departmentService.addDepartment(any(DepartmentDto.class))).thenReturn(departmentDto);
        ResponseEntity<DepartmentDto> response = departmentController.addDepartment(departmentDto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(departmentDto, response.getBody());
    }

    @Test
    void testDisplayDepartments() {
        Set<DepartmentDto> departments = new HashSet<>();
        departments.add(new DepartmentDto());
        when(departmentService.getAllDepartments()).thenReturn(departments);
        ResponseEntity<Set<DepartmentDto>> response = departmentController.displayDepartments();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(departments, response.getBody());
    }

    @Test
    void testDisplayDepartmentsEmpty() {
        when(departmentService.getAllDepartments()).thenReturn(new HashSet<>());
        ResponseEntity<Set<DepartmentDto>> response = departmentController.displayDepartments();
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testDisplayDepartmentById() {
        int departmentId = 10;
        DepartmentDto departmentDto = new DepartmentDto();
        when(departmentService.getDepartmentById(departmentId)).thenReturn(departmentDto);
        ResponseEntity<DepartmentDto> response = departmentController.displayDepartmentById(departmentId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(departmentDto, response.getBody());
        verify(departmentService, times(1)).getDepartmentById(departmentId);
    }

    @Test
    void testDisplayDepartmentByIdNotFound() {
        int departmentId = 1;
        when(departmentService.getDepartmentById(departmentId)).thenReturn(null);
        NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> departmentController.displayDepartmentById(departmentId));
        assertEquals("Can't display.Department not found" + departmentId, thrown.getMessage());
        verify(departmentService, times(1)).getDepartmentById(departmentId);
    }

    @Test
    void testUpdateDepartment() {
        int departmentId = 1;
        DepartmentDto departmentDto = new DepartmentDto();
        when(departmentService.updateDepartment((departmentId),any(DepartmentDto.class))).thenReturn(departmentDto);
        ResponseEntity<DepartmentDto> response = departmentController.updateDepartment(departmentId, departmentDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(departmentDto, response.getBody());
        verify(departmentService, times(1)).updateDepartment(eq(departmentId), any(DepartmentDto.class));
    }

    @Test
    void testDeleteDepartment() {
        int departmentId = 1;
        DepartmentDto departmentDto = new DepartmentDto();
        when(departmentService.getDepartmentById(departmentId)).thenReturn(departmentDto);
        ResponseEntity<Void> response = departmentController.deleteDepartment(departmentId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(departmentService, times(1)).getDepartmentById(departmentId);
    }

    @Test
    void testDeleteDepartmentNotFound() {
        int departmentId = 1;
        when(departmentService.getDepartmentById(departmentId)).thenReturn(null);
        NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> departmentController.deleteDepartment(departmentId));
        assertEquals("Can't Delete.Department not found" + departmentId, thrown.getMessage());
        verify(departmentService, times(1)).getDepartmentById(departmentId);
    }

    @Test
    void testGetEmployeesByDepartmentId() {
        int departmentId = 1;
        Set<EmployeeDto> employeeDtos = new HashSet<>();
        employeeDtos.add(new EmployeeDto());
        when(departmentService.getEmployeesByDepartmentId(departmentId)).thenReturn(employeeDtos);
        ResponseEntity<Set<EmployeeDto>> response = departmentController.getEmployeesByDepartmentId(departmentId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employeeDtos, response.getBody());
        verify(departmentService, times(1)).getEmployeesByDepartmentId(departmentId);
    }

    @Test
    void testGetEmployeesByDepartmentIdNotFound() {
        int departmentId = 1;
        when(departmentService.getEmployeesByDepartmentId(departmentId)).thenReturn(null);
        NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> departmentController.getEmployeesByDepartmentId(departmentId));
        assertEquals("Can't get employees by department Id.Department not found" +   departmentId, thrown.getMessage());
        verify(departmentService, times(1)).getEmployeesByDepartmentId(departmentId);
    }
}
