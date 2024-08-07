package com.ideas2it.ems.bankDetail.service;

import java.util.List;

import com.ideas2it.ems.bankDetail.dao.BankDetailDao;
import com.ideas2it.ems.bankDetail.dao.BankDetailDaoImpl;
import com.ideas2it.ems.exception.EmployeeException;
import com.ideas2it.ems.model.BankDetail;

/**
 * <p>Service class for managing bank details-related operations.</p>
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
}
