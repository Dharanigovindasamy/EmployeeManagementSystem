package com.employee.service;

import java.util.List;
import java.time.LocalDate;

import com.model.BankDetail;
import com.bankDetail.service.BankDetailService;
import com.bankDetail.service.BankDetailServiceImpl;
import com.certificate.service.CertificateService;
import com.certificate.service.CertificateServiceImpl;
import com.model.Certificate;
import com.model.Department;
import com.department.service.DepartmentService;
import com.department.service.DepartmentServiceImpl;
import com.employee.dao.EmployeeDao;
import com.employee.dao.EmployeeDaoImpl;
import com.model.Employee;
import com.employeeException.EmployeeException;
import com.employee.service.EmployeeService;

/**
 * Service class for managing employee-related operations.
 *
 * @author Dharani G
 * @Version 1.0
 */
public class EmployeeServiceImpl implements EmployeeService {
    EmployeeDao employeeDao = new EmployeeDaoImpl();
    DepartmentService departmentService = new DepartmentServiceImpl();
    BankDetailService bankDetailService = new BankDetailServiceImpl();

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
        if (employee == null) {
            return false;
        } 
        return true;
    }

    @Override
    public void updateEmployee(Employee employee) throws EmployeeException {
        employeeDao.updateEmployee(employee);
    }

    @Override
    public void deleteEmployee(int employeeId) throws EmployeeException {
        employeeDao.deleteEmployee(employeeId);
    }

    @Override
    public List<Certificate> getCertificatesByEmployeeId(int employeeId) throws EmployeeException {
        return employeeDao.getCertificatesByEmployeeId(employeeId);
    }
 
    @Override
    public List<Employee> getEmployeesByDepartmentId(int departmentId) throws EmployeeException {
        return departmentService.getEmployeesByDepartmentId(departmentId);
    }
}
