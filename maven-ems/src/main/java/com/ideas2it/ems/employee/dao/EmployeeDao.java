package com.ideas2it.ems.employee.dao;

import java.util.ArrayList;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.List;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

import com.ideas2it.ems.model.Certificate;
import com.ideas2it.ems.model.Department;
import com.ideas2it.ems.model.Employee;
import com.ideas2it.ems.exception.EmployeeException;
import com.ideas2it.ems.util.Validator;

/**
 * This interface manages employee data operations.
 * Implementation in employeeDao class where connect with database
 */
public interface EmployeeDao {
    
    /** 
     * <p>Add the employee records and store them in the employee list.
     * 
     * @param employeeName - > The Employee name to be added.
     * @param employeeDOB  -> Employee DOB to be added 
     * @param contact number - > contact number to be added 
     * @param mailId - > mailId to be added 
     * @param experience -> experience to be added 
     * @param salary ->  salary to be added 
     * @param city -> city to be added 
     * @param departmentId -> department id to be added
     *
     * @throws EmployeeException If an error occurs while adding the employee.</p>
     */
    public void addEmployee(Employee employee) throws EmployeeException;    

    /** 
     * <p>Get all employee records.
     * @throws EmployeeException If an error occurs while retrieving the employee.
     * @return List of all employees. </p>
     */
     public List<Employee> getAllEmployees() throws EmployeeException;

     /** 
     * <p>Get employee by ID.
     * 
     * @param employeeId The ID of the employee to retrieve.
     * @return The Employee object, or null if not found.
     * @throws EmployeeException If an error occurs while retrieving the employee.</p>
     */
    public Employee getEmployeeById(int employeeId) throws EmployeeException;

    /** 
     *<p> Update employee details.
     * 
     * @param updatedEmployee The Employee object with updated details.
     * @throws EmployeeException If the employee to be updated does not exist.</p>
     */
    public void updateEmployee(Employee updatedEmployee) throws EmployeeException; 

   /** 
     * <p>Delete an employee by ID.
     * 
     * @param employeeId The ID of the employee to delete.
     * @throws EmployeeException If the employee to be deleted does not exist.</p>
     */
    public void deleteEmployee(int employeeId) throws EmployeeException; 
   
    /**
     * <p>Get employees by certificate ID.
     * 
     * @param employeeId - get certificate details by the employee ID 
     * @return a list of Certificate with the specified employee id
     * @throws EmployeeException If the getting Certificates by employee id does not exist.</p>
     */
    public List<Certificate> getCertificatesByEmployeeId(int employeeId) throws EmployeeException;
}
