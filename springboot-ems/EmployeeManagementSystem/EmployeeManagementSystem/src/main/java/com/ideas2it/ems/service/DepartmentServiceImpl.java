package com.ideas2it.ems.service;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.ideas2it.ems.dto.EmployeeDto;
import com.ideas2it.ems.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideas2it.ems.dto.DepartmentDto;
import com.ideas2it.ems.dao.DepartmentDao;
import com.ideas2it.ems.model.Department;
import static com.ideas2it.ems.mapper.DepartmentMapper.convertDtoToEntity;
import static com.ideas2it.ems.mapper.DepartmentMapper.convertEntityToDto;
import static com.ideas2it.ems.mapper.EmployeeMapper.convertEntityToDto;

/**
 * <p>
 *     Service class for managing department-related operations.
 * </p>
 *
 * @author Dharani G
 */

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentDao departmentDao ;

    private static final Logger logger = LogManager.getLogger();

    public DepartmentServiceImpl(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Override
    public DepartmentDto addDepartment(DepartmentDto departmentDto)  {
        Department department = convertDtoToEntity(departmentDto);
        return convertEntityToDto(departmentDao.save(department));
    }

    @Override
    public Set<DepartmentDto> getAllDepartments() {
        Set<DepartmentDto> departmentDtos = new HashSet<>();
        Set<Department> departments =  departmentDao.findByIsRemovedFalse();
        for(Department department : departments) {
            DepartmentDto departmentDto = convertEntityToDto(department);
            departmentDtos.add(departmentDto);
        }
        return departmentDtos;
    }

    @Override 
    public DepartmentDto getDepartmentById(int departmentId) {
       return convertEntityToDto(departmentDao.findByDepartmentIdAndIsRemovedFalse(departmentId));
    }

    @Override
    public DepartmentDto updateDepartment(int departmentId, DepartmentDto departmentDto) {
        Department department = departmentDao.findByDepartmentIdAndIsRemovedFalse(departmentId);
        if (department == null) {
            logger.warn("No department found under this department Id {}" , departmentId);
            throw new NoSuchElementException("Can't update. Department not found with id: " + departmentId);
        }
        department.setDepartmentName(departmentDto.getDepartmentName());
        departmentDao.save(department);
        return convertEntityToDto(department);
    }

    @Override
    public void deleteDepartment(int departmentId) {
        Department department = departmentDao.findByDepartmentIdAndIsRemovedFalse(departmentId);
        department.setRemoved(true);
        convertEntityToDto(departmentDao.save(department));
    }

    @Override
    public Set<EmployeeDto> getEmployeesByDepartmentId(int departmentId) {
        Department department = departmentDao.findByDepartmentIdAndIsRemovedFalse(departmentId);
        Set<EmployeeDto> employeeDtos = new HashSet<>();
        if (null == department) {
            logger.warn("Department does not exist {}", departmentId);
        } else {
            Set<Employee> employees = department.getEmployees();
            for (Employee employee : employees) {
                EmployeeDto employeeDto = convertEntityToDto(employee);
                employeeDtos.add(employeeDto);
            }
        }
        return employeeDtos;
    }
}

