package com.bankDetail.dao;

import java.util.ArrayList;
import java.util.List;

import com.model.BankDetail;
import com.model.Employee;

import com.employeeException.EmployeeException;

/**
* This interface performs the bank detail operations like add, display, update
* Storing and updating any changes in database. It can interact with hibernate and reflect it database table
*
* @author Dharani G
*/
public interface BankDetailDao {

    /**
    * Add bank details in bank detail object
    * @param bankDetail - bank detail (account number, branch)of the bank detail
    * @throws EmployeeException , error occurs while adding bank details
    */
    public void addBankDetail(BankDetail bankDetail) throws EmployeeException; 
     
    /**
    * Display bank details
    * @return List<BankDetail> list of bank details
    * @throws EmployeeException , error occurs while displaying bank details
    */
    public List<BankDetail> getAllBankDetail() throws EmployeeException; 

    /**
    * Display bank details by giving accountId
    * @return BankDetail bank record of bank details
    * @throws EmployeeException , error occurs while displaying bank detail of account id
    *
    */
    public BankDetail getBankDetailById(int accountId) throws EmployeeException; 
    
    /**
    * Updating bankd detail
    * @param bank detail - bank detail of updation
    * @throws EmployeeException , error occurs while updating bank detail
    */
    public void updateBankDetail(BankDetail bankDetail) throws EmployeeException;

    // public void deleteDepartment(int departmentId) throws EmployeeException;
}

