package com.ideas2it.ems.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

/**
 * <p>
 *     This is the department details how to add the data into the table and
 *     display the details by this paramaters
 *      contains getter, setter, constructors and added validation to user input
 * </p>
 * @author dharani.govindhasamy
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {
    private int departmentId;
    @NotBlank(message = "Enter valid department name")
    @Pattern(regexp = "^[A-Za-z\\s]+$", message = "Enter valid department")
    @Column(unique = true)
    private String departmentName;
}
