package com.ideas2it.ems.bankDetail.service;

import java.util.List;

import com.ideas2it.ems.exception.EmployeeException;
import com.ideas2it.ems.model.BankDetail;

/**
 * Service class for managing bank details-related operations.
 * This interface used for setting bank details property like adding, displaying and updating
 * 
 * @author Dharani G
 */
public interface BankDetailService {
   
    /**
    * <p>Adding bank details to bank detail model
    *
    * @param accountNumber - account number for bank detail
    * @param branch - bank detail branch 
    * @throws EmployeeException causes error while adding bank details</p>
    */
    void addBankDetail(long accountNumber, String branch) throws EmployeeException;
 
    /**
    * <p>Getting list of bank details in the bank detail table
    * @return List<BankDetail> list of bank details
    * @throws EmployeeException causes error, while displaying bank details</p>
    */
    List<BankDetail> getAllBankDetail() throws EmployeeException;

    /**
    * <p>Displaying bank detail by giving account id
    * @param accountId  account I'd of the bank detail
    * @return bank detail - bank detail of the respective accountId
    * @throws EmployeeException causes error, while displaying bank detail</p>
    */
    BankDetail getBankDetailById(int accountId) throws EmployeeException;

    /**
    * <p>Updating bank details object
    * @param bankDetail - bank detail object of the bank detail
    * @throws EmployeeException causes error, while updating bank detail</p>
    */
    void updateBankDetail(BankDetail bankDetail) throws EmployeeException;

}
