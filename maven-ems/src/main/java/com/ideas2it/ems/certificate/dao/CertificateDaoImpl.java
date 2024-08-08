package com.ideas2it.ems.certificate.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ideas2it.ems.connectionManager.HibernateConnection;
import com.ideas2it.ems.model.Certificate;
import com.ideas2it.ems.model.Employee;
import com.ideas2it.ems.exception.EmployeeException;

/**
* <p> This class used to manage certificate objects.
* Also here, connected with database, store and execute table operations 
* This class used for performing certificate operations like adding, displaying 
* and updating in database by opening connections through session factory, and open sessions
* and perform the operations </p>
*
* @author Dharani G
*/
public class CertificateDaoImpl implements CertificateDao {

    private static final Logger logger = LogManager.getLogger();
    @Override
    public void addCertificate(Certificate certificate) throws EmployeeException {
        Transaction transaction = null;
        try (Session session = HibernateConnection.getFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(certificate);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Error while adding certificates {}", e.getMessage());
            throw new EmployeeException("Unable to add the certificate: " + certificate.getCertificateId(), e);
        }
    }

    @Override
    public void addCertificateToEmployee(int employeeId, int certificateId) throws EmployeeException {
        try (Session session = HibernateConnection.getFactory().openSession()) {
            Employee employee = session.get(Employee.class, employeeId);
            Certificate certificate = session.get(Certificate.class, certificateId);
            Set<Certificate> certificates = new HashSet<>();
            certificates.add(certificate);
            employee.setCertificates(certificates);
            System.out.println("certificate:" + certificate.getCertificateName());
            session.saveOrUpdate(employee);
            System.out.println(employee.getEmployeeName());
            System.out.println(certificates);
            System.out.println("working");
        } catch (HibernateException e) {
            logger.error("Error while adding certificates to employee {}", e.getMessage());
            throw new EmployeeException("Unable to add certificate to employee. Employee ID: "
                    + employeeId + ", Certificate ID: " + certificateId, e);
        }
    }

    @Override
    public List<Certificate> getAllCertificates() throws EmployeeException {
        List<Certificate> certificates;
        try (Session session = HibernateConnection.getFactory().openSession()){
            Query<Certificate> query = session.createQuery("FROM Certificate WHERE isRemoved = :isRemoved", Certificate.class)
                                                            .setParameter("isRemoved", false);
            certificates = query.list();
        } catch (HibernateException e) {
            logger.error("Error while getting certificates {}", e.getMessage());
            throw new EmployeeException("Unable to get all the certificates : " , e);
        }
        return certificates;
    }

    @Override
    public Certificate getCertificateById(int certificateId)throws EmployeeException {
        Certificate certificate ;
        try ( Session session = HibernateConnection.getFactory().openSession()) {
            certificate = session.createQuery("FROM Certificate WHERE certificateId = :certificateId and isRemoved = :isRemoved" , Certificate.class)
                                              .setParameter("certificateId" , certificateId)
                                              .setParameter("isRemoved", false).uniqueResult();
        } catch (HibernateException e) {
            logger.error("Error while getting certificates by id {}", e.getMessage());
            throw new EmployeeException("Unable to get Certificate id: " + certificateId, e);
        }
        return certificate;
    }

    @Override
    public void updateCertificate(Certificate certificate)throws EmployeeException {
        Transaction transaction = null;
        try (Session session = HibernateConnection.getFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(certificate);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Error while updating certificates {}", e.getMessage());
            throw new EmployeeException("Unable to update the certificate : " + certificate.getCertificateId(), e);
        }
    }
           
    @Override
    public void deleteCertificate(int certificateId) throws EmployeeException {
        Transaction transaction = null;
        try (Session session = HibernateConnection.getFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createQuery("UPDATE Certificate SET isRemoved = :isRemoved where id = :certificateId")
                    .setParameter("isRemoved", false)
                    .setParameter("certificateId", certificateId);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Error while deleting certificates {}", e.getMessage());
            throw new EmployeeException("Unable to delete the employee : " + certificateId, e);
        }
    }

    @Override
    public List<Employee> getEmployeesByCertificate(int certificateId) throws EmployeeException {
        List<Employee> employees = new ArrayList<>();
        try (Session session = HibernateConnection.getFactory().openSession()) {
            Certificate certificate = session.createQuery("FROM Certificate WHERE certificateId = :certificateId and isRemoved = :isRemoved" , Certificate.class) 
                                               .setParameter("certificateId" , certificateId)
                                               .setParameter("isRemoved", false).uniqueResult();
            if (certificate != null) {
                employees.addAll(certificate.getEmployees());
            }
        } catch (HibernateException e) {
            logger.error("Error while getting employee  details certificate id {}", e.getMessage());
            throw new EmployeeException("Unable to get employees by certificate id: " + certificateId, e);
        }
        return employees;
    }
}
