package com.ideas2it.ems.employee.service;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.util.Set;

import com.ideas2it.ems.model.BankDetail;
import com.ideas2it.ems.model.Certificate;
import com.ideas2it.ems.model.Department;
import com.ideas2it.ems.department.service.DepartmentService;
import com.ideas2it.ems.department.service.DepartmentServiceImpl;
import com.ideas2it.ems.employee.dao.EmployeeDao;
import com.ideas2it.ems.employee.dao.EmployeeDaoImpl;
import com.ideas2it.ems.model.Employee;
import com.ideas2it.ems.exception.EmployeeException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Service class for managing employee-related operations.
 *
 * @author Dharani G
 * 
 */
public class EmployeeServiceImpl implements EmployeeService {
    EmployeeDao employeeDao = new EmployeeDaoImpl();
    DepartmentService departmentService = new DepartmentServiceImpl();

    private static final Logger logger = LogManager.getLogger();

    @Override
    public void addEmployee(String employeeName, LocalDate employeeDOB, long contactNumber, String mailId, int experience, 
                               double salary, String city, int departmentId, long accountNumber, String branch) throws EmployeeException {
        Department department = departmentService.getDepartmentById(departmentId);
        BankDetail bankDetail = new BankDetail(accountNumber, branch);	
        Employee employee = new Employee(employeeName, employeeDOB, contactNumber, mailId, experience, salary, city, department, bankDetail);
        employeeDao.addEmployee(employee);
    }

    @Override
    public List<Employee> getAllEmployees() throws EmployeeException {
        return employeeDao.getAllEmployees();
    }

    @Override
    public Employee getEmployeeById(int employeeId) throws EmployeeException {
        return employeeDao.getEmployeeById(employeeId);
    }

    @Override
    public boolean isPresentEmployee(int employeeId) throws EmployeeException {
        Employee employee = employeeDao.getEmployeeById(employeeId);
        return employee != null;
    }

    @Override
    public void updateEmployee(Employee employee) throws EmployeeException {
        employeeDao.updateEmployee(employee);
    }

    @Override
    public boolean deleteEmployee(int employeeId) throws EmployeeException {
        Employee employee = employeeDao.getEmployeeById(employeeId);
        if(null == employee) {
            logger.warn("Given employee id does not exit {}" , employeeId);
            return false;
        } else {
            employeeDao.deleteEmployee(employeeId);
            return true;
        }
    }

    @Override
    public List<Certificate> getCertificatesByEmployeeId(int employeeId) throws EmployeeException {
        Employee employee = employeeDao.getEmployeeById(employeeId);
        List<Certificate> certificates = null;
        if(null == employee) {
            logger.warn("Given employee id does not exit {}" , employeeId);
        } else {
            certificates = new ArrayList<>(employee.getCertificates());
        }
        return certificates;
    }
 
    @Override
    public List<Employee> getEmployeesByDepartmentId(int departmentId) throws EmployeeException {
        return departmentService.getEmployeesByDepartmentId(departmentId);
    }
}
