package com.ideas2it.ems.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>
 *     This is the department details how to add the data into the table and
 *     display the details by this paramaters
 *      contains getter, setter, constructors
 * </p>
 * @author dharani.govindhasamy
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {
    private int departmentId;
    private String departmentName;
}
