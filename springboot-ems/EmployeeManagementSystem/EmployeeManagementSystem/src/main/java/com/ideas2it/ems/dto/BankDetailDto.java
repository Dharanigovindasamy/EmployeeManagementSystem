package com.ideas2it.ems.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

/**
 * <p>
 *     This is the class of bank details how to enter data ,and display to user format ,
 *     contains getter, setter, constructors and validate the user input
 * </p>
 * @author dharani.govindhasamy
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BankDetailDto {
    private int accountId;
    @NotNull(message = "Account number is mandatory")
    private long accountNumber;
    @NotBlank(message = "branch is mandatory")
    @Pattern(regexp = "^[A-Za-z\\s]+$", message = "Enter valid branch")
    private String branch;
}
