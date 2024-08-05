package com.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;

import com.model.Employee;

/**
* <p> This class used for the bank Details model 
* contains account number, branch and employee object.
* It contains of constructor , getter and setter method of all entities
* Model of bank details 
* toString method -> give format of output to the end user</p>
*
* @author Dharani G
* @Version 1.0
*/

@Entity
@Table(name = "bank_detail")
public class BankDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int accountId;
 
    @Column(name = "account_number")
    private long accountNumber;

    @Column( name = "branch")
    private String branch;

 /*   @OneToOne(mappedBy = "bankDetail", fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private Employee employee;  */

    @Column(name = "isRemoved")
    private boolean isRemoved;

    public BankDetail(int accountId, long accountNumber, String branch) {
        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.branch = branch;
    }

    public BankDetail(long accountNumber, String branch) {
        this.accountNumber = accountNumber;
        this.branch = branch;
    }

    public BankDetail() {}

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

 /*   public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }  */

    public String toString() {
        return "Bank Details: Account Number = " + accountNumber
                + " Branch = " + branch;
    }
}