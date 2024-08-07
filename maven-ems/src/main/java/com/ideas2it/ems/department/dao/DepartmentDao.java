package com.ideas2it.ems.department.dao;

import java.util.List;

import com.ideas2it.ems.model.Department;
import com.ideas2it.ems.exception.EmployeeException;
import com.ideas2it.ems.model.Employee;

/**
* DepartmentDao class used to manage department objects.
*/
public interface DepartmentDao {
   
    /** 
     * <p>Add a department to the list.
     * 
     * @param department The Department object to be added.
     * @throws EmployeeException If an error occurs while adding the department.</p>
     */
    void addDepartment(Department department) throws EmployeeException;

    /** 
     * <p>Get all departments.
     * 
     * @return List of all departments.
     * @throws EmployeeException If an error occurs while adding the department.</p>
     */
    List<Department> getAllDepartments() throws EmployeeException;

     /** 
    * <p>Get a department by ID.
    * 
    * @param departmentId The ID of the department to retrieve.
    * @return The Department object, or null if not found.
    * @throws EmployeeException If an error occurs while retrieving the department.</p>
    */
     Department getDepartmentById(int departmentId) throws EmployeeException;
    
    /** 
    * <p>Update department name by ID.
     * 
     * @param department The ID of the department to update.
     * @throws EmployeeException If the department does not exist.</p>
     */
    void updateDepartment(Department department) throws EmployeeException;
   
    /**
    * <p>Delete a department by ID.
    * 
    * @param departmentId The ID of the department to delete.
    * @throws EmployeeException If the department does not exist.</p>
    */
    void deleteDepartment(int departmentId) throws EmployeeException;

    /** 
    * <p>Get a department by ID.
    * 
    * @param departmentId The ID of the department to retrieve.
    * @return The Department object, or null if not found.
    * @throws EmployeeException If an error occurs while retrieving the department.</p>
    */
    List<Employee> getEmployeesByDepartmentId(int departmentId) throws EmployeeException;
}
