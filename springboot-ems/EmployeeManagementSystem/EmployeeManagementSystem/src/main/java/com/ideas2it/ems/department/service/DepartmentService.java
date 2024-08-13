package com.ideas2it.ems.department.service;

import java.util.List;
import java.util.Set;

import com.ideas2it.ems.Dto.DepartmentDto;
import com.ideas2it.ems.Dto.EmployeeDto;
import com.ideas2it.ems.model.Department;
import org.springframework.stereotype.Service;

/**
 * <p>
 *     Service class for managing department-related operations.
 * </p>
 * @author dharani.govindhasamy
 */
@Service
public interface DepartmentService {

    /**
     * <p>Add a department
     *
     * @ param departmentName -> {@link Department} department name of department from user
     * @ return Department - department object
     * @ throws EmployeeException, if the function cause any exception
     * </p>
     */
    public DepartmentDto addDepartment(DepartmentDto departmentDto) ;

    /**
    * <p>Get all departments of department
    * @return List<Department>  - list of department
    * @ throws EmployeeException, if the function cause any exception
     * </p>
    */
    public Set<DepartmentDto>  getAllDepartments();

    /**
     * <p>Get a department by giving department ID
     *
     * @param departmentId - {@link  Department}Id of the department
     * @return Department -  department object
     * @ throws EmployeeException, if the function cause any exception
     * </p>
     */
    public DepartmentDto getDepartmentById(int departmentId) ;

    public DepartmentDto updateDepartment(int departmentId, DepartmentDto departmentDto);

    public DepartmentDto deleteDepartment(int departmentId);

    public Set<EmployeeDto> getEmployeesByDepartmentId(int departmentId);

}