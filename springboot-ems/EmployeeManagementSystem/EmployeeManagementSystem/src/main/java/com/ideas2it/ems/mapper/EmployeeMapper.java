package com.ideas2it.ems.mapper;

import com.ideas2it.ems.dto.EmployeeDto;
import com.ideas2it.ems.model.Department;
import com.ideas2it.ems.model.Employee;

/**
 * <p>
 *     This class used for convert Employee Dto to employee entity
 * and Employee entity to Employee Dto
 * </p>
 * @author dharani.govindhasamy
 */
public class EmployeeMapper {

    /**
     * <p>
     *     Convert EmployeeDto to employee
     *  @param employeeDto - {@link EmployeeDto}
     *  @return Employee - set employeeDto data to employee
     * </p>
     */
    public static Employee convertDtoToEntity(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setEmployeeId(employeeDto.getEmployeeId());
        employee.setEmployeeName(employeeDto.getEmployeeName());
        employee.setEmployeeDOB(employeeDto.getEmployeeDOB());
        employee.setContactNumber(employeeDto.getContactNumber());
        employee.setMailId(employeeDto.getMailId());
        employee.setExperience(employeeDto.getExperience());
        employee.setSalary(employeeDto.getSalary());
        employee.setCity(employeeDto.getCity());
        return employee;
    }

    /**
     * <p>
     * @param employee -{@link Employee}
     * @return EmployeeDto - set employee data to employeeDto
     * </p>This function used for convert Entity To Dto
     */
    public static EmployeeDto convertEntityToDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setEmployeeId(employee.getEmployeeId());
        employeeDto.setEmployeeName(employee.getEmployeeName());
        employeeDto.setEmployeeDOB(employee.getEmployeeDOB());
        employeeDto.setAge(employee.getAge());
        employeeDto.setContactNumber(employee.getContactNumber());
        employeeDto.setMailId(employee.getMailId());
        employeeDto.setExperience(employee.getExperience());
        employeeDto.setSalary(employee.getSalary());
        employeeDto.setCity(employee.getCity());
        employeeDto.setDepartmentId(employee.getDepartment().getDepartmentId());
        employeeDto.setDepartmentName(employee.getDepartment().getDepartmentName());
        return employeeDto;
    }
}
