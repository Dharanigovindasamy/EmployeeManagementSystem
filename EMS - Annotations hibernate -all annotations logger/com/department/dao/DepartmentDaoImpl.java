package com.department.dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.time.LocalDate;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


import com.connectionManager.HibernateConnection;
import com.model.Department;
import com.department.dao.DepartmentDao;
import com.employeeException.EmployeeException;
import com.model.Employee;

/**
* <p>This class used for performing bank details operations like adding, displaying 
* and updating in database by opening connections through session factory, and open sessions
* and perform the operations.
* This class used to manage department objects.
* Also here, connected with database and execute table operations </p>
*
* @author Dharani G
*/
public class DepartmentDaoImpl implements DepartmentDao {
    
    @Override
    public void addDepartment(Department department) throws EmployeeException {
        Session session = HibernateConnection.getFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(department);
            transaction.commit(); 
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new EmployeeException("Unable to add the department : " + department.getDepartmentId() , e);
        } finally {
            session.close();
        }
    }

    @Override
    public List<Department> getAllDepartments() throws EmployeeException {
        Session session = HibernateConnection.getFactory().openSession();
        Transaction transaction = null;
        List<Department> departments = null;
        try {
            transaction = session.beginTransaction();
            Query<Department> query = session.createQuery("FROM Department WHERE isRemoved = :isRemoved " , Department.class)
                                                          .setParameter("isRemoved", false);
            departments = query.list();
            transaction.commit(); 
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new EmployeeException("Unable to get all the departments : " , e);
        } finally {
            session.close();
        }    
        return departments;
    }

    @Override
    public Department getDepartmentById(int departmentId) throws EmployeeException {
        Session session = HibernateConnection.getFactory().openSession();
        Transaction transaction = null;
        Department department = null;
        try {
            transaction = session.beginTransaction();
            department = session.createQuery("FROM Department WHERE departmentId = :departmentId and isRemoved = :isRemoved" , Department.class)
                                             .setParameter("departmentId" , departmentId)
                                             .setParameter("isRemoved", false).uniqueResult();
            transaction.commit(); 
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
            throw new EmployeeException("Unable to get department by: " + departmentId , e);
        } finally {
            session.close();
        }
        return department;
    }
    
    @Override
    public List<Employee> getEmployeesByDepartmentId(int departmentId) throws EmployeeException {
        Session session = HibernateConnection.getFactory().openSession();
        Transaction transaction = null;
        List<Employee> employees = null;
        try {
            transaction = session.beginTransaction();
            Department department = session.get(Department.class, departmentId);
            employees = new ArrayList<>(department.getEmployees());
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new EmployeeException("Unable to get employees for department: " + departmentId, e);
        } finally {
            session.close();
        }
        return employees;
    }

    @Override
    public void updateDepartment(Department department) throws EmployeeException {
        Session session = HibernateConnection.getFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(department);
            transaction.commit(); 
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new EmployeeException("Unable to update the certificate : " + department , e);
        } finally {
            session.close();
        }  
    }
    
    @Override 
    public void deleteDepartment(int departmentId) throws EmployeeException {
        Session session = HibernateConnection.getFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query<Department> query = session.createQuery("UPDATE Department SET isRemoved = :isRemoved where DepartmentId = :departmentId");
            query.setParameter("isRemoved", true);
            query.executeUpdate();
            transaction.commit(); 
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new EmployeeException("Unable to delete the department : " + departmentId , e);
        } finally {
            session.close();
        }    
    }
}
