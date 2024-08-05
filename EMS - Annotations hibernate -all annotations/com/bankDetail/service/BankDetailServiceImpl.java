package com.bankDetail.service;

import java.util.List;

import com.bankDetail.dao.BankDetailDao;
import com.bankDetail.dao.BankDetailDaoImpl;
import com.model.BankDetail;
import com.bankDetail.service.BankDetailService;
import com.model.Employee;
import com.employeeException.EmployeeException;

/**
 * Service class for managing bank details-related operations.
 *
 * @author Dharani G
 */
public class BankDetailServiceImpl implements BankDetailService {
    BankDetailDao bankDetailDao = new BankDetailDaoImpl();
   
    @Override
    public void addBankDetail(long accountNumber, String branch) throws EmployeeException {
        BankDetail bankDetail = new BankDetail(accountNumber, branch);
        bankDetailDao.addBankDetail(bankDetail);
    }

    @Override
    public List<BankDetail> getAllBankDetail() throws EmployeeException {
        return bankDetailDao.getAllBankDetail();
    }

    @Override 
    public BankDetail getBankDetailById(int accountId) throws EmployeeException {
        return bankDetailDao.getBankDetailById(accountId);
    }

    @Override
    public void updateBankDetail(BankDetail bankDetail) throws EmployeeException {
        bankDetailDao.updateBankDetail(bankDetail);
    }

 /*   @Override
    public void deleteDepartment(int departmentId) throws EmployeeException {
        departmentDao.deleteDepartment(departmentId);
    }  */
}
