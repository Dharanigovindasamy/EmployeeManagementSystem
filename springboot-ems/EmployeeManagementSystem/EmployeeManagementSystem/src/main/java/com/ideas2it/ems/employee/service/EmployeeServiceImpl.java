package com.ideas2it.ems.employee.service;

import java.util.List;
import java.util.Set;

import com.ideas2it.ems.Dto.BankDetailDto;
import com.ideas2it.ems.Dto.DepartmentDto;
import com.ideas2it.ems.Dto.EmployeeDto;
import com.ideas2it.ems.department.service.DepartmentService;
import com.ideas2it.ems.employee.dao.EmployeeDao;
import com.ideas2it.ems.model.BankDetail;
import com.ideas2it.ems.model.Department;
import com.ideas2it.ems.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.ideas2it.ems.employee.mapper.EmployeeMapper.convertDtoToEntity;
import static com.ideas2it.ems.employee.mapper.EmployeeMapper.convertEntityToDto;
import static com.ideas2it.ems.department.mapper.DepartmentMapper.convertDtoToEntity;
import static com.ideas2it.ems.department.mapper.DepartmentMapper.convertEntityToDto;

/**
 * <p>Service class for managing employee-related operations.</p>
 *
 * @author Dharani G
 */

@Service
public class EmployeeServiceImpl implements EmployeeService {
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
        return employeeDao.findByEmployeeIdAndIsRemovedFalse(employeeId);
    }
}

