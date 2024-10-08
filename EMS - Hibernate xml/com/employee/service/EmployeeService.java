package com.employee.service;

import java.util.List;
import java.time.LocalDate;

import com.model.Certificate;
import com.model.Department;
import com.model.Employee;
import com.employeeException.EmployeeException;
import com.employee.service.EmployeeServiceImpl;

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
    * @return boolean type -> if added , as true, if not added- return false
    * @throws EmployeeException if there is an error adding the employee </p>
    */
    public void addEmployee(String employeeName, LocalDate employeeDOB, long contactNumber, String mailId, int experience, double salary, String city, int departmentId) throws EmployeeException;

    /**
    * Getting all employees in employee table
    * @return list of employee - all employee list of the employee
    * @throws EmployeeException - while occurs error
    */
    public List<Employee> getAllEmployees() throws EmployeeException;
 
    /** 
    * Getting employee details from employee table by giving employee id 
    * @param employeeId - fetch employee data when matches employee id
    * @return employee - employee object matched employee id
    * @throws EmployeeException - while occurs error
    */
    public Employee getEmployeeById(int employeeId) throws EmployeeException;

    /*
    * Updating employee table by giving as employee object that mentioned the options for updating
    * @param employee - fetch employee data
    * @throws EmployeeException - while occurs error
    */
    public void updateEmployee(Employee employee) throws EmployeeException;

    /**
    * Delete the data from employee
    * @param employeeId - employee id from user
    * @throws EmployeeException - while occurs error
    * Deleting the data from employee that matched with employee id 
    */
    public void deleteEmployee(int employeeId) throws EmployeeException;

    /**
    * Get certificates by employee Id
    * @param employeeId - employee ID of the employee
    * @return list of certificate - list of certificates done by employee id
    * @throws EmployeeException - while occurs error
    * By giving employee Id, list out the what are all the certificates the employee can occur
    */
    public List<Certificate> getCertificatesByEmployeeId(int employeeId)throws EmployeeException;

    /**
    * Get employees by department id
    * @param departmentId - department id of department 
    * @return list of employee - get employee list by department id
    * @throws EmployeeException - while occurs error
    * <p> By passing department id, fetch in employee object , is the employee are under the given department or not,
    * if yes, fetch it as list of employee details in the employee table </p>
    */
    public List<Employee> getEmployeesByDepartmentId(int departmentId) throws EmployeeException;
}