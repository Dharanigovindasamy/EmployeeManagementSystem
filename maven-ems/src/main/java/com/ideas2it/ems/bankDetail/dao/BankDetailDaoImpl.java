package com.ideas2it.ems.bankDetail.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ideas2it.ems.connectionManager.HibernateConnection;
import com.ideas2it.ems.model.BankDetail;
import com.ideas2it.ems.exception.EmployeeException;

/**
* <p>This class used for performing bank details operations like adding, displaying 
* and updating in database by opening connections through session factory, and open sessions
* and perform the operations </p>
*
* @author Dharani G
*/
public class BankDetailDaoImpl implements BankDetailDao {

    private static final Logger logger = LogManager.getLogger();
    @Override
    public void addBankDetail(BankDetail bankDetail) throws EmployeeException {
        Transaction transaction = null;
        try (Session session = HibernateConnection.getFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(bankDetail);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Error while adding bank details {}", e.getMessage());
            throw new EmployeeException("Unable to add the bankDetails : " + bankDetail.getAccountId(), e);
        }
    }

      @Override
    public List<BankDetail> getAllBankDetail() throws EmployeeException {
        List<BankDetail> bankDetails;
        try (Session session = HibernateConnection.getFactory().openSession()){
            Query<BankDetail> query = session.createQuery("FROM BankDetail WHERE isRemoved = :isRemoved " , BankDetail.class)
                                                          .setParameter("isRemoved", false);
            bankDetails = query.list();
        } catch (HibernateException e) {
            logger.error("Error while displaying all bank details {}", e.getMessage());
            throw new EmployeeException("Unable to get all the bankDetails : " , e);
        }
        return bankDetails;
    }

    @Override
    public BankDetail getBankDetailById(int accountId) throws EmployeeException {
        BankDetail bankDetail;
        try (Session session = HibernateConnection.getFactory().openSession()){
            bankDetail = session.createQuery("FROM BankDetail WHERE accountId = :accountId and isRemoved = :isRemoved" , BankDetail.class)
                                             .setParameter("accountId" , accountId)
                                             .setParameter("isRemoved", false).uniqueResult();
        } catch (HibernateException e) {
            logger.error("Error while getting bank details by accountId {}", e.getMessage());
            throw new EmployeeException("Unable to get bankDetails by: " + accountId , e);
        }
        return bankDetail;
    }

    @Override
    public void updateBankDetail(BankDetail bankDetail) throws EmployeeException {
        Transaction transaction = null;
        try (Session session = HibernateConnection.getFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(bankDetail);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Error while updating bank details {}", e.getMessage());
            throw new EmployeeException("Unable to update the certificate : " + bankDetail.getAccountId(), e);
        }
    }
}

