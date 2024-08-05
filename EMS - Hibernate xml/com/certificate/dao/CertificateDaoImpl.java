package com.certificate.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.time.LocalDate;

import com.connectionManager.HibernateConnection;
import com.model.Certificate;
import com.certificate.dao.CertificateDao;
import com.model.Employee;
import com.model.Department;
import com.employeeException.EmployeeException;

/**
* This class used to manage certificate objects.
* Also here, connected with database, store and execute table operations 
*/
public class CertificateDaoImpl implements CertificateDao {
 
    @Override
    public void addCertificate(Certificate certificate) throws EmployeeException {
        Session session = HibernateConnection.getFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(certificate);
            transaction.commit(); 
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new EmployeeException("Unable to add the certificate: " + certificate.getCertificateId() , e);
        } finally {
            session.close();
        }
    }

    @Override
    public void addCertificateToEmployee(int employeeId, int certificateId) throws EmployeeException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateConnection.getFactory().openSession();
            transaction = session.beginTransaction();
            Employee employee = session.get(Employee.class, employeeId);
            Certificate certificate = session.get(Certificate.class, certificateId);
            if (employee == null || certificate == null) {
                System.out.println("Employee or Certificate not found.");
                return;
            }
            Set<Certificate> certificates = employee.getCertificates();
            Set<Employee> employees = certificate.getEmployees();
            certificates.add(certificate);
            employees.add(employee);
            employee.setCertificates(certificates);
            certificate.setEmployees(employees);
            session.saveOrUpdate(employee);
            session.saveOrUpdate(certificate);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
            throw new EmployeeException("Unable to add certificate to employee. Employee ID: " 
                                         + employeeId + ", Certificate ID: " + certificateId, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Certificate> getAllCertificates() throws EmployeeException {
        Session session = HibernateConnection.getFactory().openSession();
        Transaction transaction = null;
        List<Certificate> certificates = null;
        try {
            transaction = session.beginTransaction();
            Query<Certificate> query = session.createQuery("FROM Certificate WHERE is_removed = :isRemoved", Certificate.class)
                                                            .setParameter("isRemoved", false);
            certificates = query.list();
            transaction.commit(); 
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new EmployeeException("Unable to get all the certificates : " , e);
        } finally {
            session.close();
        }
        return certificates;
    }

    @Override
    public Certificate getCertificateById(int certificateId)throws EmployeeException {
        Session session = HibernateConnection.getFactory().openSession();
        Transaction transaction = null;
        Certificate certificate = null;
        try {
            transaction = session.beginTransaction();
            certificate = session.createQuery("FROM Certificate WHERE certificateId = :certificateId and isRemoved = :isRemoved" , Certificate.class)
                                              .setParameter("certificateId" , certificateId);
                                              .setParameter("isRemoved", false).uniqueResult();
            transaction.commit(); 
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new EmployeeException("Unable to get Certificate id: " + certificateId, e);
        } finally {
            session.close();
        }
        return certificate;
    }

    @Override
    public void updateCertificate(Certificate certificate)throws EmployeeException {
        Session session = HibernateConnection.getFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(certificate);
            transaction.commit(); 
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new EmployeeException("Unable to update the certificate : " + certificate.getCertificateId() , e);
        } finally {
            session.close();
        }  
    }
           
    @Override
    public void deleteCertificate(int certificateId) throws EmployeeException {
        Session session = HibernateConnection.getFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query<Certificate> query = session.createQuery("UPDATE Certificate SET is_removed = :isRemoved where id = :certificateId");
                                                            .setParameter("is_removed", false);
                                                            .setParameter("id", certificateId);
            transaction.commit(); 
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new EmployeeException("Unable to delete the employee : " + certificateId , e);
        } finally {
            session.close();
        }     
    }

    @Override
    public List<Employee> getEmployeesByCertificate(int certificateId) throws EmployeeException {
        Session session = null;
        Transaction transaction = null;
        List<Employee> employees = new ArrayList<>();
        try {
            session = HibernateConnection.getFactory().openSession();
            transaction = session.beginTransaction();
            Certificate = session.createQuery("FROM Certificate WHERE certificateId = :certificateId and isRemoved = :isRemoved" , Certificate.class) 
                                               .setParameter("certificateId" , certificateId);
                                               .setParameter("is_removed", false);
            if (certificate != null) {
                for (Employee employee : certificate.getEmployees()) {
                    employees.add(employee);
                }
            }
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new EmployeeException("Unable to get employees by certificate id: " + certificateId, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return employees;
    }
}
