package com.ideas2it.ems.employee.service;

import java.util.List;
import java.time.LocalDate;

import com.ideas2it.ems.model.BankDetail;
import com.ideas2it.ems.bankDetail.service.BankDetailService;
import com.ideas2it.ems.bankDetail.service.BankDetailServiceImpl;
import com.ideas2it.ems.certificate.service.CertificateService;
import com.ideas2it.ems.certificate.service.CertificateServiceImpl;
import com.ideas2it.ems.model.Certificate;
import com.ideas2it.ems.model.Department;
import com.ideas2it.ems.department.service.DepartmentService;
import com.ideas2it.ems.department.service.DepartmentServiceImpl;
import com.ideas2it.ems.employee.dao.EmployeeDao;
import com.ideas2it.ems.employee.dao.EmployeeDaoImpl;
import com.ideas2it.ems.model.Employee;
import com.ideas2it.ems.employee.service.EmployeeService;
import com.ideas2it.ems.exception.EmployeeException;

/**
 * Service class for managing employee-related operations.
 *
 * @author Dharani G
 * 
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
