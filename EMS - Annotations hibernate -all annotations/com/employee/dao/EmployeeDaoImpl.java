package com.employee.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

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

    @Override
    public void addEmployee(Employee employee) throws EmployeeException {
        Session session = HibernateConnection.getFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Integer id = (Integer) session.save(employee);
            transaction.commit(); 
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new EmployeeException("Unable to add the employee : " + employee.getEmployeeId() , e);
        } finally {
            session.close();
        }
    }

    @Override
    public List<Employee> getAllEmployees() throws EmployeeException {
        Session session = HibernateConnection.getFactory().openSession();
        Transaction transaction = null;
        List<Employee> employees = null;
        try {
            transaction = session.beginTransaction();
            Query<Employee> query = session.createQuery("FROM Employee WHERE isRemoved = :isRemoved", Employee.class)
                                                         .setParameter("isRemoved", false);
            employees = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
            throw new EmployeeException("Unable to get all the employees", e);
        } finally {
            session.close();
        }
        return employees;
    }

    @Override
    public Employee getEmployeeById(int employeeId) throws EmployeeException {
        Session session = HibernateConnection.getFactory().openSession();
        Transaction transaction = null;
        Employee employee = null;
        try {
            transaction = session.beginTransaction();
            employee = session.createQuery("FROM Employee WHERE employeeId = :EmployeeId AND isRemoved = :isRemoved" , Employee.class)
                                            .setParameter("EmployeeId" , employeeId)
                                            .setParameter("isRemoved",false).uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new EmployeeException("Unable to get employee by ID: " + employeeId, e);
        } finally {
            session.close();
        }
        return employee;
    }

    @Override
    public void updateEmployee(Employee updatedEmployee) throws EmployeeException {
        Session session = HibernateConnection.getFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(updatedEmployee);
            transaction.commit(); 
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new EmployeeException("Unable to update the employee : " , e);
        } finally {
            session.close();
        }      
    }

    @Override
    public void deleteEmployee(int employeeId) throws EmployeeException {
        Session session = HibernateConnection.getFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query<?> query = session.createQuery("UPDATE Employee SET isRemoved = :isRemoved WHERE EmployeeId = :employeeId");
            query.setParameter("isRemoved", true);
            query.setParameter("employeeId", employeeId);
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new EmployeeException("Unable to delete the employee: " + employeeId, e);
        } finally {
            session.close();
        }
    }

    @Override
    public List<Certificate> getCertificatesByEmployeeId(int employeeId) throws EmployeeException {
        Session session = HibernateConnection.getFactory().openSession();
        Transaction transaction = null;
        List<Certificate> certificates = null;
        try {
            transaction = session.beginTransaction();
            Employee employee = session.createQuery("FROM Employee WHERE EmployeeId = :EmployeeId AND isRemoved = :isRemoved", Employee.class)
                                                     .setParameter("EmployeeId" , employeeId)
                                                     .setParameter("isRemoved" , false).uniqueResult();
            certificates = new ArrayList<>(employee.getCertificates());
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new EmployeeException("Unable to get certificates by employee ID: " + employeeId, e);
        } finally {
            session.close();
        }
        return certificates;
    }
}