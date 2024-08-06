package com.ideas2it.ems.bankDetail.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ideas2it.ems.connectionManager.HibernateConnection;
import com.ideas2it.ems.model.BankDetail;
import com.ideas2it.ems.bankDetail.dao.BankDetailDao;
import com.ideas2it.ems.exception.EmployeeException;
import com.ideas2it.ems.model.Employee;


/**
* <p>This class used for performing bank details operations like adding, displaying 
* and updating in database by opening connections through session factory, and open sessions
* and perform the operations </p>
*
* @author Dharani G
*/
public class BankDetailDaoImpl implements BankDetailDao {

    @Override
    public void addBankDetail(BankDetail bankDetail) throws EmployeeException {
        Session session = HibernateConnection.getFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(bankDetail);
            transaction.commit(); 
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new EmployeeException("Unable to add the bankDetails : " + bankDetail.getAccountId() , e);
        } finally {
            session.close();
        }
    }

      @Override
    public List<BankDetail> getAllBankDetail() throws EmployeeException {
        Session session = HibernateConnection.getFactory().openSession();
        Transaction transaction = null;
        List<BankDetail> bankDetails = null;
        try {
            transaction = session.beginTransaction();
            Query<BankDetail> query = session.createQuery("FROM BankDetail WHERE isRemoved = :isRemoved " , BankDetail.class)
                                                          .setParameter("isRemoved", false);
            bankDetails = query.list();
            transaction.commit(); 
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new EmployeeException("Unable to get all the bankDetails : " , e);
        } finally {
            session.close();
        }    
        return bankDetails;
    }

    @Override
    public BankDetail getBankDetailById(int accountId) throws EmployeeException {
        Session session = HibernateConnection.getFactory().openSession();
        Transaction transaction = null;
        BankDetail bankDetail = null;
        try {
            transaction = session.beginTransaction();
            bankDetail = session.createQuery("FROM BankDetail WHERE accountId = :accountId and isRemoved = :isRemoved" , BankDetail.class)
                                             .setParameter("accountId" , accountId)
                                             .setParameter("isRemoved", false).uniqueResult();
            transaction.commit(); 
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
            throw new EmployeeException("Unable to get bankDetails by: " + accountId , e);
        } finally {
            session.close();
        }
        return bankDetail;
    }

    @Override
    public void updateBankDetail(BankDetail bankDetail) throws EmployeeException {
        Session session = HibernateConnection.getFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(bankDetail);
            transaction.commit(); 
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new EmployeeException("Unable to update the certificate : " + bankDetail.getAccountId() , e);
        } finally {
            session.close();
        }  
    }
}

