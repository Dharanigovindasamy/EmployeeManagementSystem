package com.ideas2it.ems.Dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>
 *     This is the employee details how to add the data into the table and
 *     display the details by this paramaters
 *     contains getter, setter, constructors
 * </p>
 * @author dharani.govindhasamy
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private int employeeId;
    private String employeeName;
    private LocalDate employeeDOB;
    private String age;
    private long contactNumber;
    private String mailId;
    private int experience;
    private int departmentId;
    private String departmentName;
    private double salary;
    private String city;
    private long accountNumber;
    private String branch;
    private int certificateId;
    private String certificateName;
}