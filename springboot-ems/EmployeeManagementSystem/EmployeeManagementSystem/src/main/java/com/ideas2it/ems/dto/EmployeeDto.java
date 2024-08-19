package com.ideas2it.ems.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 *     This is the employee details how to add the data into the table and
 *     display the details by this paramaters ,
 *     contains getter, setter, constructors and validates the entering value
 * </p>
 * @author dharani.govindhasamy
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

    private int employeeId;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Enter valid name")
    private String employeeName;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate employeeDOB;

    private String age;

    @NotNull(message = "Enter valid 10 digits number")
    private long contactNumber;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Please enter a valid email Id")
    private String mailId;

    @Size(min = 0, max = 30,
            message= "Experience should be 0 to 30 years")
    private int experience;
    private int departmentId;
    private String departmentName;

    @NotNull(message = "Enter the salary")
    private double salary;

    @NotBlank(message = "Enter valid city")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Enter valid city")
    private String city;

    @NotNull(message = "Account number is mandatory")
    private long accountNumber;

    @NotBlank(message = "branch is mandatory")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Enter valid branch")
    private String branch;

    private int certificateId;
    private String certificateName;
}