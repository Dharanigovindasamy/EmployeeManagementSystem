package com.ideas2it.ems.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

/**
 * <p>
 *     This class contains bank details of the employee ie..account number, branch.
 *     getter and setter and constructors .
 *     This can create table in the server with column name
 * </p>
 *
 * @author dharani.govindhasamy
 *
 */
@Entity
@Table(name = "bank_details")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int accountId;

    @Column(name = "account_number", nullable = false, unique = true)
    private long accountNumber;

    @Column(name = "branch", nullable = false)
    private String branch;

    @Column(name = "isRemoved")
    private boolean isRemoved;

    public BankDetail(long accountNumber, String branch) {
        this.accountNumber = accountNumber;
        this.branch = branch;
    }
}