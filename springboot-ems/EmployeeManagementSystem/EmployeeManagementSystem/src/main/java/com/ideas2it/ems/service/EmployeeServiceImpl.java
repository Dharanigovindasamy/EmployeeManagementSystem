package com.ideas2it.ems.service;

import java.util.NoSuchElementException;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ideas2it.ems.dao.EmployeeDao;
import com.ideas2it.ems.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>Service class for managing employee-related operations.</p>
 *
 * @author Dharani G
 */

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private EmployeeDao employeeDao ;
    private DepartmentService departmentService;
    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public Employee addEmployee(Employee employee)  {
        return employeeDao.save(employee);
    }

    @Override
    public Set<Employee> getAllEmployees() {
        Set<Employee> employees = (Set<Employee>) employeeDao.findByIsRemovedFalse();
        return employees;
    }

    @Override
    public Employee getEmployeeById(int employeeId)  {
        Employee employee = employeeDao.findByEmployeeIdAndIsRemovedFalse(employeeId);
        if (null == employee) {
            logger.error("Employee not found {}", employeeId);
            throw new NoSuchElementException("Can't display. Employee not found" + employeeId);
        }
        return employee;
    }
}

