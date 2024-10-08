package com.employee.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.apache.logging.log4j.Logger;  
import org.apache.logging.log4j.LogManager;

import com.connectionManager.HibernateConnection;
import com.model.Certificate;
import com.model.Department;
import com.model.Employee;
import com.employeeException.EmployeeException;

/**
 * <p>This class used for performing bank details operations like adding, displaying 
 * and updating in database by opening connections through session factory, and open sessions
 * and perform the operations </p>
 * This class manages employee data.
 * Connects employee to the database and performs operations like adding, viewing, updating, and deleting.
 *
 * @author Dharani G
 * @version 1.0
 */
public class EmployeeDaoImpl implements EmployeeDao {

    private static Logger logger = LogManager.getLogger();
    @Override
    public void addEmployee(Employee employee) throws EmployeeException {
        logger.debug("Starts adding employee details: {}" , employee);
        Session session = HibernateConnection.getFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Integer id = (Integer) session.save(employee);
            logger.debug("adding employee details in database: {}", employee);
            transaction.commit(); 
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Error while adding employee details", e.getMessage());
            throw new EmployeeException("Unable to add the employee : " + employee.getEmployeeId() , e);
        } finally {
            session.close();
        }
    }

    @Override
    public List<Employee> getAllEmployees() throws EmployeeException {
        logger.debug("Starts displaying all employee details: {}");
        Session session = HibernateConnection.getFactory().openSession();
        Transaction transaction = null;
        List<Employee> employees = null;
        try {
            transaction = session.beginTransaction();
            Query<Employee> query = session.createQuery("FROM Employee WHERE isRemoved = :isRemoved", Employee.class)
                                                         .setParameter("isRemoved", false);
            employees = query.list();
            logger.debug("Successfully displaying all employee details: {}");
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Error while displaying employee details", e.getMessage());
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
        Employee employee = null;
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
            logger.error("Error while displaying employee details by employee Id", e.getMessage());
            throw new EmployeeException("Unable to get employee by ID: " + employeeId, e);
        } finally {
            session.close();
        }
        return employee;
    }

    @Override
    public void updateEmployee(Employee updatedEmployee) throws EmployeeException {
        logger.debug("Started updating employee details: {}", updatedEmployee);
        Session session = HibernateConnection.getFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(updatedEmployee);
            logger.debug("Successfully updated employee details: {}", updatedEmployee);
            transaction.commit(); 
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Error while updating employee details", e.getMessage());
            throw new EmployeeException("Unable to update the employee : " , e);
        } finally {
            session.close();
        }      
    }

    @Override
    public void deleteEmployee(int employeeId) throws EmployeeException {
        logger.debug("Started deleting employee record", employeeId);
        Session session = HibernateConnection.getFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query<?> query = session.createQuery("UPDATE Employee SET isRemoved = :isRemoved WHERE EmployeeId = :employeeId");
            query.setParameter("isRemoved", true);
            query.setParameter("employeeId", employeeId);
            query.executeUpdate();
            logger.debug("Deleted employee record", employeeId);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Error while deleting employee details", e.getMessage());
            throw new EmployeeException("Unable to delete the employee: " + employeeId, e);
        } finally {
            session.close();
        }
    }

    @Override
    public List<Certificate> getCertificatesByEmployeeId(int employeeId) throws EmployeeException {
        logger.debug("Starts getting certificates by employee id", employeeId);
        Session session = HibernateConnection.getFactory().openSession();
        Transaction transaction = null;
        List<Certificate> certificates = null;
        try {
            transaction = session.beginTransaction();
            Employee employee = session.createQuery("FROM Employee WHERE EmployeeId = :EmployeeId AND isRemoved = :isRemoved", Employee.class)
                                                     .setParameter("EmployeeId" , employeeId)
                                                     .setParameter("isRemoved" , false).uniqueResult();
            certificates = new ArrayList<>(employee.getCertificates());
            logger.debug("Successfully got certificates by employee id", employeeId);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Error while getting certificates by employee id", e.getMessage());
            throw new EmployeeException("Unable to get certificates by employee ID: " + employeeId, e);
        } finally {
            session.close();
        }
        return certificates;
    }
}