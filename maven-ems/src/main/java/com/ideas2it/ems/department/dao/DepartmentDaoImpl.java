package com.ideas2it.ems.department.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


import com.ideas2it.ems.connectionManager.HibernateConnection;
import com.ideas2it.ems.model.Department;
import com.ideas2it.ems.exception.EmployeeException;
import com.ideas2it.ems.model.Employee;

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

    private static final Logger logger = LogManager.getLogger();

    @Override
    public void addDepartment(Department department) throws EmployeeException {
        Transaction transaction = null;
        try (Session session = HibernateConnection.getFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(department);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Error while adding department details {}", e.getMessage());
            throw new EmployeeException("Unable to add the department : " + department.getDepartmentId(), e);
        }
    }

    @Override
    public List<Department> getAllDepartments() throws EmployeeException {
        List<Department> departments;
        try (Session session = HibernateConnection.getFactory().openSession();){
            Query<Department> query = session.createQuery("FROM Department WHERE isRemoved = :isRemoved " , Department.class)
                                                          .setParameter("isRemoved", false);
            departments = query.list();
        } catch (HibernateException e) {
            logger.error("Issue occurs while displaying department details {}", e.getMessage());
            throw new EmployeeException("Unable to get all the departments : " , e);
        }
        return departments;
    }

    @Override
    public Department getDepartmentById(int departmentId) throws EmployeeException {
        Department department ;
        try (Session session = HibernateConnection.getFactory().openSession()){
            department = session.createQuery("FROM Department WHERE departmentId = :departmentId and isRemoved = :isRemoved" , Department.class)
                                             .setParameter("departmentId" , departmentId)
                                             .setParameter("isRemoved", false).uniqueResult();
        } catch (HibernateException e) {
            logger.error("Error while displaying department details by id {}", e.getMessage());
            throw new EmployeeException("Unable to get department by: " + departmentId , e);
        }
        return department;
    }
    
    @Override
    public List<Employee> getEmployeesByDepartmentId(int departmentId) throws EmployeeException {
        List<Employee> employees;
        try (Session session = HibernateConnection.getFactory().openSession()){
            Department department = session.get(Department.class, departmentId);
            employees = new ArrayList<>(department.getEmployees());
        } catch (HibernateException e) {
            logger.error("Error while getting employee details by department Id {}", e.getMessage());
            throw new EmployeeException("Unable to get employees for department: " + departmentId, e);
        }
        return employees;
    }

    @Override
    public void updateDepartment(Department department) throws EmployeeException {
        Transaction transaction = null;
        try (Session session = HibernateConnection.getFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(department);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Error while updating department details {}", e.getMessage());
            throw new EmployeeException("Unable to update the certificate : " + department, e);
        }
    }
    
    @Override 
    public void deleteDepartment(int departmentId) throws EmployeeException {
        Transaction transaction = null;
        try (Session session = HibernateConnection.getFactory().openSession()) {
            transaction = session.beginTransaction();
            Query<?> query = session.createQuery("UPDATE Department SET isRemoved = :isRemoved where DepartmentId = :departmentId");
            query.setParameter("isRemoved", true);
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Error while deleting department details {}", e.getMessage());
            throw new EmployeeException("Unable to delete the department : " + departmentId, e);
        }
    }
}
