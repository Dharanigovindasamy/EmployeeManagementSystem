package com.department.dao;

import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.Date;
import java.time.LocalDate;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import com.model.Department;
import com.employeeException.EmployeeException;
import com.model.Employee;

/**
* DepartmentDao class used to manage department objects.
*/
public interface DepartmentDao {
   
    /** 
     * Add a department to the list.
     * 
     * @param department The Department object to be added.
     * @throws EmployeeException If an error occurs while adding the department.
     */
    public void addDepartment(Department department) throws EmployeeException; 

    /** 
     * Get all departments.
     * 
     * @return List of all departments.
     */
    public List<Department> getAllDepartments() throws EmployeeException; 

    public Department getDepartmentById(int departmentId) throws EmployeeException;
    
    public String getDepartmentName(int departmentId) throws EmployeeException;
    
    /** 
    * Update department name by ID.
     * 
     * @param departmentId The ID of the department to update.
     * @param newName The new name of the department.
     * @throws EmployeeException If the department does not exist.
     */
    public void updateDepartment(Department department) throws EmployeeException; 
   
    /**
    * Delete a department by ID.
    * 
    * @param departmentId The ID of the department to delete.
    * @throws EmployeeException If the department does not exist.
    */
    public void deleteDepartment(int departmentId) throws EmployeeException; 

    /** 
    * Get a department by ID.
    * 
    * @param departmentId The ID of the department to retrieve.
    * @return The Department object, or null if not found.
    * @throws EmployeeException If an error occurs while retrieving the department.
    */
    public List<Employee> getEmployeesByDepartmentId (int departmentId) throws EmployeeException;
}
