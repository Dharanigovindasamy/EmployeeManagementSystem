package com.ideas2it.ems.department.service;

import java.util.List;

import com.ideas2it.ems.department.dao.DepartmentDao;
import com.ideas2it.ems.model.Department;
import com.ideas2it.ems.model.Employee;
import com.ideas2it.ems.exception.EmployeeException;

/**
 * Service class for managing department-related operations.
 */
public interface DepartmentService {

    /** Add a department
    *
    * @ param departmentName -> department name of department from user
    * @ throws EmployeeException, if the function cause any exception
    *
    */
    public void addDepartment(String departmentName) throws EmployeeException;

    /**
    * Get all departments of department
    * @return list of department 
    * @ throws EmployeeException, if the function cause any exception
    */
    public List<Department> getAllDepartments() throws EmployeeException;

    /**
    * Get a department by giving department ID
    *
    * @param departmentId - Id of the department
    * @return department object
    * @ throws EmployeeException, if the function cause any exception
    */
    public Department getDepartmentById(int departmentId) throws EmployeeException;

    /**
    * Update a department name by giving department id
    *
    * @param departmentId - Id of the department
    * @departmentNewName - updatin new name for department
    * @ throws EmployeeException, if the function cause any exception 
    */
    public void updateDepartment(int departmentId, String departmentNewName) throws EmployeeException;

    /**
    * Delete a department by giving department id
    * @param departmentId - Id of the department
    * @ throws EmployeeException, if the function cause any exception 
    */
    public void deleteDepartment(int departmentId) throws EmployeeException;

    /**
    * Getting Employees By Department Id
    * @param departmentId - Id of the department
    * @return list of employees by giving department Id
    * @ throws EmployeeException, if the function cause any exception
    */
    public List<Employee>getEmployeesByDepartmentId(int departmentId) throws EmployeeException;

    /**
    * Department present in the department list or not
    * @param departmentId - Id of the department
    * @return boolean true as when giving department id is in the department object,
    * if it returns false, the id doesnot in the department 
    */
    public boolean isPresentDepartment(int departmentId) throws EmployeeException;
}