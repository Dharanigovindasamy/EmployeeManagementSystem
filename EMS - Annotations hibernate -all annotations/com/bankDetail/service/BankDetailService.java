package com.bankDetail.service;

import java.util.List;

import com.bankDetail.dao.BankDetailDao;
import com.bankDetail.dao.BankDetailDaoImpl;
import com.model.BankDetail;
import com.model.Employee;
import com.employeeException.EmployeeException;

/**
 * Service class for managing bank details-related operations.
 * This interface used for setting bank details property like adding, displaying and updating
 * 
 * @author Dharani G
 */
public interface BankDetailService {
   
    /**
    * Adding bank details to bank detail model
    *
    * @param accountNumber - account number for bank detail
    * @param branch - bank detail branch 
    * @throws EmployeeException causes error while adding bank details
    */
    public void addBankDetail(long accountNumber, String branch) throws EmployeeException;
 
    /**
    * Getting list of bank details in the bank detail table
    * @return List<BankDetail> list of bank details
    * @throws EmployeeException causes error, while displaying bank details
    */
    public List<BankDetail> getAllBankDetail() throws EmployeeException;

    /**
    * Displaying bank detail by giving account id
    * @param accountId  account Id of the bank detail
    * @return bank detail - bank detail of the respective accountId
    * @throws EmployeeException causes error, while displaying bank detail
    */
    public BankDetail getBankDetailById(int accountId) throws EmployeeException;

    /**
    * Updating bank details object
    * @param bankDetail - bank detail object of the banak detail
    * @throws EmployeeException causes error, while updating bank detail
    */
    public void updateBankDetail(BankDetail bankDetail) throws EmployeeException;

}
