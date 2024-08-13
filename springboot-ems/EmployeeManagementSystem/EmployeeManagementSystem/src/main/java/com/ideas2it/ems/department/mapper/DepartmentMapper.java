package com.ideas2it.ems.department.mapper;

import com.ideas2it.ems.Dto.DepartmentDto;
import com.ideas2it.ems.model.Department;

/**
 * <p>
 *     This class used for conversion of department Dto to entity and vice versa.
 * </p>
 * @author dharani.govindhasamy
 */
public class DepartmentMapper {

    /**
     * <p>
     * Conversion of department Dto to entity
     * @param departmentDto - {@link DepartmentDto}
     * @return Department - department object of the user
     * </p>
     */
    public static Department convertDtoToEntity(DepartmentDto departmentDto) {
        Department department = new Department();
        department.setDepartmentId(departmentDto.getDepartmentId());
        department.setDepartmentName(departmentDto.getDepartmentName());
        return department;
    }

    /**
     * <p>
     *  Conversion of department entity to Dto
     * @param department - {@link Department}
     * @return departmentDto - departmentDto to the user
     * </p>
     */
    public static DepartmentDto convertEntityToDto(Department department) {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentId(department.getDepartmentId());
        departmentDto.setDepartmentName(department.getDepartmentName());
        return departmentDto;
    }
}
