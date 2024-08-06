package com.ideas2it.ems.department.service;

import java.util.List;

import com.ideas2it.ems.department.dao.DepartmentDao;
import com.ideas2it.ems.department.dao.DepartmentDaoImpl;
import com.ideas2it.ems.model.Department;
import com.ideas2it.ems.department.service.DepartmentService;
import com.ideas2it.ems.model.Employee;
import com.ideas2it.ems.exception.EmployeeException;

/**
 * <p>Service class for managing department-related operations.</p>
 *
 * @author Dharani G
 */
public class DepartmentServiceImpl implements DepartmentService {
    DepartmentDao departmentDao = new DepartmentDaoImpl();
   
    @Override
    public void addDepartment(String departmentName) throws EmployeeException {
        Department department = new Department(departmentName);
        departmentDao.addDepartment(department);
    }

    @Override
    public List<Department> getAllDepartments() throws EmployeeException {
        return departmentDao.getAllDepartments();
    }

    @Override 
    public Department getDepartmentById(int departmentId) throws EmployeeException {
        return departmentDao.getDepartmentById(departmentId);
    }

    @Override
    public void updateDepartment(int departmentId, String departmentNewName) throws EmployeeException {
        Department department = new Department(departmentId, departmentNewName);
        departmentDao.updateDepartment(department);
    }

    @Override
    public void deleteDepartment(int departmentId) throws EmployeeException {
        departmentDao.deleteDepartment(departmentId);
    }

    @Override
    public List<Employee> getEmployeesByDepartmentId(int departmentId) throws EmployeeException {
        return departmentDao.getEmployeesByDepartmentId(departmentId);
    }

    @Override
    public boolean isPresentDepartment(int departmentId) throws EmployeeException {
        Department department = departmentDao.getDepartmentById(departmentId);
            if(null == department) {
                return false;
            }  
        return true;
    }
}
