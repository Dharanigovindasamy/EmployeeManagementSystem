package com.ideas2it.ems.service;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Set;

import com.ideas2it.ems.dto.EmployeeDto;
import com.ideas2it.ems.model.BankDetail;
import com.ideas2it.ems.model.Certificate;
import com.ideas2it.ems.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.ideas2it.ems.dao.DepartmentDao;
import com.ideas2it.ems.dto.DepartmentDto;
import com.ideas2it.ems.model.Department;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
    @Mock
    private DepartmentDao departmentDao;

    @InjectMocks
    private DepartmentServiceImpl departmentServiceImpl;

    Set<DepartmentDto> departmentDtos;
    Department department;
    DepartmentDto departmentDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        department = Department.builder().departmentId(3).departmentName("HR")
                     .employees(Set.of(Employee.builder()
                             .employeeId(1)
                             .employeeName("kavi")
                             .employeeDOB(LocalDate.of(2000,11,11))
                             .contactNumber(9876543212L)
                             .mailId("kavi@gmail.com")
                             .experience(3)
                             .salary(54000)
                             .city("erode")
                             .bankDetail(BankDetail.builder()
                                     .accountId(3)
                                     .accountNumber(8765432l)
                                     .branch("sathy")
                             .build())
                             .certificates(Set.of(Certificate.builder()
                                     .certificateId(3)
                                     .certificateName("amazon")
                                     .build()))
                             .build()))
                     .build();
        departmentDto = DepartmentDto.builder()
                .departmentId(3).departmentName("HR").build();
    }

    @Test
    public void testAddDepartment() {
        when(departmentDao.save(any(Department.class))).thenReturn(department);
        DepartmentDto response = departmentServiceImpl.addDepartment(departmentDto);
        assertNotNull(response);
        assertEquals(response.getDepartmentName(),departmentDto.getDepartmentName());
    }

    @Test
    public void testGetAllDepartment() {
        when(departmentDao.findByIsRemovedFalse()).thenReturn(Set.of(department));
        Set<DepartmentDto> responseDepartmentDtos = departmentServiceImpl.getAllDepartments();
        assertNotNull(responseDepartmentDtos);
        assertEquals(1, responseDepartmentDtos.size());
    }

    @Test
    public void testGetDepartmentById() {
        when(departmentDao.findByDepartmentIdAndIsRemovedFalse(3)).thenReturn(department);
        DepartmentDto response = departmentServiceImpl.getDepartmentById(3);
        assertNotNull(response);
        assertEquals(response.getDepartmentName(), departmentDto.getDepartmentName());
    }

    @Test
    public void testUpdateDepartment() {
        when(departmentDao.findByDepartmentIdAndIsRemovedFalse(3)).thenReturn(department);
        when(departmentDao.save(any(Department.class))).thenReturn(department);
        DepartmentDto responseDepartmentDto = departmentServiceImpl.updateDepartment(3, departmentDto);
        assertNotNull(responseDepartmentDto);
        assertEquals(responseDepartmentDto.getDepartmentName(), departmentDto.getDepartmentName());
    }

    @Test
    void testUpdateDepartmentNotFound() {
        when(departmentDao.findByDepartmentIdAndIsRemovedFalse(3)).thenReturn(null);
        NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> departmentServiceImpl.updateDepartment(3, departmentDto));
        assertEquals("Can't update. Department not found with id: " + department.getDepartmentId() , thrown.getMessage());
    }

    @Test
    public void testDeleteDepartment() {
        when(departmentDao.findByDepartmentIdAndIsRemovedFalse(3)).thenReturn(department);
        when(departmentDao.save(any(Department.class))).thenReturn(department);
        departmentServiceImpl.deleteDepartment(3);
        assertEquals(true, department.isRemoved());
    }

    @Test
    public void testGetEmployeesByDepartmentId() {
        when(departmentDao.findByDepartmentIdAndIsRemovedFalse(3)).thenReturn(department);
        Set<EmployeeDto> response = departmentServiceImpl.getEmployeesByDepartmentId(3);
        assertNotNull(response);
        assertEquals(response, department.getEmployees());
    }

    @Test
    public void testGetEmployeesByDepartmentIdNotFound() {
        when(departmentDao.findByDepartmentIdAndIsRemovedFalse(3)).thenReturn(null);
        NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> departmentServiceImpl.getEmployeesByDepartmentId(3));
        assertEquals("Department does not exist {}" + 3 , thrown.getMessage());
    }
}
