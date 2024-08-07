package com.ideas2it.ems.employee.service;

import java.util.List;
import java.time.LocalDate;

import com.ideas2it.ems.model.Certificate;
import com.ideas2it.ems.model.Employee;
import com.ideas2it.ems.exception.EmployeeException;

public interface EmployeeService {
  
    /*
    * <p> Adding the employee data into the employee table which contains
    * 
    * @param employeeName    the name of the employee
    * @param employeeDOB     the date of birth of the employee
    * @param contactNumber   the contact number of the employee
    * @param mailId          the email ID of the employee
    * @param experience      the experience of the employee in years
    * @param salary          the salary of the employee
    * @param city            the city of the employee
    * @param departmentId - > department id of department
    * @param accountNumber - account Number of the bank details
    * @param branch - account branch details
    * @throws EmployeeException if there is an error adding the employee </p>
    */
    void addEmployee(String employeeName, LocalDate employeeDOB, long contactNumber, String mailId, int experience, double salary, String city, int departmentId, long accountNumber, String branch) throws EmployeeException;

    /**
    * Getting all employees in employee table
    * @return list of employee - all employee list of the employee
    * @throws EmployeeException - while occurs error
    */
    List<Employee> getAllEmployees() throws EmployeeException;
 
    /** 
    * Getting employee details from employee table by giving employee id 
    * @param employeeId - fetch employee data when matches employee id
    * @return employee - employee object matched employee id
    * @throws EmployeeException - while occurs error
    */
    Employee getEmployeeById(int employeeId) throws EmployeeException;

    /*
    * Updating employee table by giving as employee object that mentioned the options for updating
    * @param employee - fetch employee data
    * @throws EmployeeException - while occurs error
    */
    void updateEmployee(Employee employee) throws EmployeeException;
 
    /** 
    * Check from employee table by giving employee id is there present or not
    * @param employeeId - fetch employee data when matches employee id
    * @return boolean - if employee id present in the list or not
    * @throws EmployeeException - while occurs error
    */
    boolean isPresentEmployee(int employeeId) throws EmployeeException;

    /**
    * Delete the data from employee
    * @param employeeId - employee id from user
    * @throws EmployeeException - while occurs error
    * Deleting the data from employee that matched with employee id
    */
    boolean deleteEmployee(int employeeId) throws EmployeeException;

    /**
    * Get certificates by employee I'd
    * @param employeeId - employee ID of the employee
    * @return list of certificate - list of certificates done by employee id
    * @throws EmployeeException - while occurs error
    * By giving employee I'd, list out all the certificates the employee can occur
    */
    List<Certificate> getCertificatesByEmployeeId(int employeeId)throws EmployeeException;

    /**
    * Get employees by department id
    * @param departmentId - department id of department 
    * @return list of employee - get employee list by department id
    * @throws EmployeeException - while occurs error
    * <p> By passing department id, fetch in employee object , is the employee are under the given department or not,
    * if yes, fetch it as list of employee details in the employee table </p>
    */
    List<Employee> getEmployeesByDepartmentId(int departmentId) throws EmployeeException;
}