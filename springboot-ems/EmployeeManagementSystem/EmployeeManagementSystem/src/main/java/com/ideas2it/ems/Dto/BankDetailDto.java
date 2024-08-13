package com.ideas2it.ems.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>
 *     This is the class of bank details how to enter data ,and display to user format ,
 *     contains getter, setter, constructors
 * </p>
 * @author dharani.govindhasamy
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BankDetailDto {
    private int accountId;
    private long accountNumber;
    private String branch;

}
