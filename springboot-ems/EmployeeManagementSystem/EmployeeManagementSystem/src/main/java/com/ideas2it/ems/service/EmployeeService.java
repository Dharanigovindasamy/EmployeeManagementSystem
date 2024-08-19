package com.ideas2it.ems.service;

import java.util.Set;

import com.ideas2it.ems.model.Employee;
import org.springframework.stereotype.Service;

/**
 * <p>
 *     This interface for managing employee-related operations.
 * </p>
 * @author dharani.govindhasamy
 */
@Service
public interface EmployeeService {

    /**
     * Add employee
     *
     * @ param employee ->  {@link Employee}employee from user
      * @return - Employee object
     * @ throws EmployeeException, if the function cause any exception
     */
    public Employee addEmployee(Employee employee) ;

    /**
    * Get all employees of employee
    * @return set of employee
    * @ throws EmployeeException, if the function cause any exception
    */
    public Set<Employee> getAllEmployees();

    /**
     * Get a employee by giving employee ID
     *
     * @param employeeId - Id of the employee {@link Employee}
     * @return employee object
     * @ throws EmployeeException, if the function cause any exception
     */
    public Employee getEmployeeById(int employeeId) ;
}