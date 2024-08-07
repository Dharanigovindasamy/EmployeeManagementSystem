package com.ideas2it.ems.employee.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.apache.logging.log4j.Logger;  
import org.apache.logging.log4j.LogManager;

import com.ideas2it.ems.connectionManager.HibernateConnection;
import com.ideas2it.ems.model.Certificate;
import com.ideas2it.ems.model.Employee;
import com.ideas2it.ems.exception.EmployeeException;

/**
 * <p>This class used for performing bank details operations like adding, displaying 
 * and updating in database by opening connections through session factory, and open sessions
 * and perform the operations </p>
 * This class manages employee data.
 * Connects employee to the database and performs operations like adding, viewing, updating, and deleting.
 *
 * @author Dharani G
 *
 */
public class EmployeeDaoImpl implements EmployeeDao {

    private static final Logger logger = LogManager.getLogger();
    @Override
    public void addEmployee(Employee employee) throws EmployeeException {
        logger.debug("Starts adding employee details: {}" , employee);
        Transaction transaction = null;
        try (Session session = HibernateConnection.getFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(employee);
            logger.debug("adding employee details in database: {}", employee);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Error while adding employee details {}", e.getMessage());
            throw new EmployeeException("Unable to add the employee : " + employee.getEmployeeId(), e);
        }
    }

    @Override
    public List<Employee> getAllEmployees() throws EmployeeException {
        logger.debug("Starts displaying all employee details: ");
        Session session = HibernateConnection.getFactory().openSession();
        Transaction transaction = null;
        List<Employee> employees;
        try {
            transaction = session.beginTransaction();
            Query<Employee> query = session.createQuery("FROM Employee WHERE isRemoved = :isRemoved", Employee.class)
                                                         .setParameter("isRemoved", false);
            employees = query.list();
            logger.debug("Successfully displaying all employee details: ");
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Error while displaying all employee details {} ", e.getMessage());
            throw new EmployeeException("Unable to get all the employees", e);
        } finally {
            session.close();
        }
        return employees;
    }

    @Override
    public Employee getEmployeeById(int employeeId) throws EmployeeException {
        logger.debug("Starts displaying employee details by employee id: {}", employeeId);
        Session session = HibernateConnection.getFactory().openSession();
        Transaction transaction = null;
        Employee employee;
        try {
            transaction = session.beginTransaction();
            employee = session.createQuery("FROM Employee WHERE employeeId = :EmployeeId AND isRemoved = :isRemoved" , Employee.class)
                                            .setParameter("EmployeeId" , employeeId)
                                            .setParameter("isRemoved",false).uniqueResult();
            logger.debug("Successfully displaying employee details by employee id: {}", employeeId);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Error while displaying employee details by employee Id {} " , e.getMessage());
            throw new EmployeeException("Unable to get employee by ID: " + employeeId, e);
        } finally {
            session.close();
        }
        return employee;
    }

    @Override
    public void updateEmployee(Employee updatedEmployee) throws EmployeeException {
        logger.debug("Started updating employee details: {}", updatedEmployee);
        Transaction transaction = null;
        try (Session session = HibernateConnection.getFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(updatedEmployee);
            logger.debug("Successfully updated employee details: {}", updatedEmployee);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Error while updating employee details {} ", e.getMessage());
            throw new EmployeeException("Unable to update the employee : ", e);
        }
    }

    @Override
    public void deleteEmployee(int employeeId) throws EmployeeException {
        logger.debug("Started deleting employee record {} ", employeeId);
        Transaction transaction = null;
        try (Session session = HibernateConnection.getFactory().openSession()) {
            transaction = session.beginTransaction();
            Query<?> query = session.createQuery("UPDATE Employee SET isRemoved = :isRemoved WHERE EmployeeId = :employeeId");
            query.setParameter("isRemoved", true);
            query.setParameter("employeeId", employeeId);
            query.executeUpdate();
            logger.debug("Deleted employee record {}", employeeId);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Error while deleting employee details {}", e.getMessage());
            throw new EmployeeException("Unable to delete the employee: " + employeeId, e);
        }
    }

    @Override
    public List<Certificate> getCertificatesByEmployeeId(int employeeId) throws EmployeeException {
        logger.debug("Starts getting certificates by employee id {} ", employeeId);
        Session session = HibernateConnection.getFactory().openSession();
        Transaction transaction = null;
        List<Certificate> certificates ;
        try {
            transaction = session.beginTransaction();
            Employee employee = session.createQuery("FROM Employee WHERE EmployeeId = :EmployeeId AND isRemoved = :isRemoved", Employee.class)
                                                     .setParameter("EmployeeId" , employeeId)
                                                     .setParameter("isRemoved" , false).uniqueResult();
            certificates = new ArrayList<>(employee.getCertificates());
            logger.debug("Successfully got certificates by employee id {}", employeeId);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Error while getting certificates by employee id {}", e.getMessage());
            throw new EmployeeException("Unable to get certificates by employee ID: " + employeeId, e);
        } finally {
            session.close();
        }
        return certificates;
    }
}