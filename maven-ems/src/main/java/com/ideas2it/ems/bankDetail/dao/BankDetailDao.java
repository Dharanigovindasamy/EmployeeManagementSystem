package com.ideas2it.ems.bankDetail.dao;

import java.util.ArrayList;
import java.util.List;

import com.ideas2it.ems.model.BankDetail;
import com.ideas2it.ems.model.Employee;
import com.ideas2it.ems.exception.EmployeeException;

/**
* <p>This interface performs the bank detail operations like add, display, update
* Storing and updating any changes in database. 
* It can interact with hibernate and reflect it database table </p>
*
* @author Dharani G
*/
public interface BankDetailDao {

    /**
    * <p>Add bank details in bank detail object
    * @param bankDetail - bank detail (account number, branch)of the bank detail
    * @throws EmployeeException , error occurs while adding bank details</p>
    */
    public void addBankDetail(BankDetail bankDetail) throws EmployeeException; 
     
    /**
    *<p> Display bank details
    * @return List<BankDetail> list of bank details
    * @throws EmployeeException , error occurs while displaying bank details</p>
    */
    public List<BankDetail> getAllBankDetail() throws EmployeeException; 

    /**
    * <p>Display bank details by giving accountId
    * @return BankDetail bank record of bank details
    * @throws EmployeeException , error occurs while displaying bank detail of account id
    *</p>
    */
    public BankDetail getBankDetailById(int accountId) throws EmployeeException; 
    
    /**
    * <p>Updating bankd detail
    * @param bank detail - bank detail of updation
    * @throws EmployeeException , error occurs while updating bank detail</p>
    */
    public void updateBankDetail(BankDetail bankDetail) throws EmployeeException;
}

