package com.department.service;

import java.util.List;

import com.department.dao.DepartmentDao;
import com.department.dao.DepartmentDaoImpl;
import com.model.Department;
import com.department.service.DepartmentService;
import com.model.Employee;
import com.employeeException.EmployeeException;

/**
 * Service class for managing department-related operations.
 */
public class DepartmentServiceImpl implements DepartmentService {
    DepartmentDao departmentDao = new DepartmentDaoImpl();
   
    @Override
    public void addDepartment(String departmentName) throws EmployeeException {
        Department department = new Department(departmentName);
        departmentDao.addDepartment(department);
    }

    @Override
    public List<Department> getAllDepartments() throws EmployeeException {
        return departmentDao.getAllDepartments();
    }

    @Override 
    public Department getDepartmentById(int departmentId) throws EmployeeException {
        return departmentDao.getDepartmentById(departmentId);
    }

    @Override
    public void updateDepartment(int departmentId, String departmentNewName) throws EmployeeException {
        Department department = new Department(departmentId, departmentNewName);
        departmentDao.updateDepartment(department);
    }

    @Override
    public void deleteDepartment(int departmentId) throws EmployeeException {
        departmentDao.deleteDepartment(departmentId);
    }

    @Override
    public List<Employee> getEmployeesByDepartmentId(int departmentId) throws EmployeeException {
        return departmentDao.getEmployeesByDepartmentId(departmentId);
    }
}
