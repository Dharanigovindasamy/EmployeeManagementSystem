package com.ideas2it.ems.employee.dao;

import java.util.List;

import com.ideas2it.ems.model.Certificate;
import com.ideas2it.ems.model.Employee;
import com.ideas2it.ems.exception.EmployeeException;

/**
 * This interface manages employee data operations.
 * Implementation in employeeDao class where connect with database
 */
public interface EmployeeDao {

    /**
     * <p>Add the employee records and store them in the employee list.
     *
     * @param employee - add employee details into employee object
     *
     * @throws EmployeeException If an error occurs while adding the employee.</p>
     */
     void addEmployee(Employee employee) throws EmployeeException;

    /**
     * <p>Get all employee records.
     *
     * @return List of all employees. </p>
     * @throws EmployeeException If an error occurs while retrieving the employee.
     */
    List<Employee> getAllEmployees() throws EmployeeException;

    /**
     * <p>Get employee by ID.
     * 
     * @param employeeId The ID of the employee to retrieve.
     * @return The Employee object, or null if not found.
     * @throws EmployeeException If an error occurs while retrieving the employee.</p>
     */
    Employee getEmployeeById(int employeeId) throws EmployeeException;

    /** 
     *<p> Update employee details.
     * 
     * @param updatedEmployee The Employee object with updated details.
     * @throws EmployeeException If the employee to be updated does not exist.</p>
     */
    void updateEmployee(Employee updatedEmployee) throws EmployeeException;

   /** 
     * <p>Delete an employee by ID.
     * 
     * @param employeeId The ID of the employee to delete.
     * @throws EmployeeException If the employee to be deleted does not exist.</p>
     */
   void deleteEmployee(int employeeId) throws EmployeeException;
   
    /**
     * <p>Get employees by certificate ID.
     * 
     * @param employeeId - get certificate details by the employee ID 
     * @return a list of Certificate with the specified employee id
     * @throws EmployeeException If the getting Certificates by employee id does not exist.</p>
     */
    List<Certificate> getCertificatesByEmployeeId(int employeeId) throws EmployeeException;
}
