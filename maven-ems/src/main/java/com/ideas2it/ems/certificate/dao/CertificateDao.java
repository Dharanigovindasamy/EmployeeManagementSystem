package com.ideas2it.ems.certificate.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.time.LocalDate;

import com.ideas2it.ems.model.Certificate;
import com.ideas2it.ems.model.Employee;
import com.ideas2it.ems.model.Department;
import com.ideas2it.ems.exception.EmployeeException;

/**
 * <p> CertificateDao class for managing certificate data.
 * It performs operations like adding, displaying,updating 
 * and retriving the particular data from object and deleting</p>
 */
public interface CertificateDao {
    
    /**
     * Add the certificate details in certificate list
     *
     * @param certificateName The certificate name to be added.
     * @throws EmployeeException If an error occurs while adding the certificate.
     */
    public void addCertificate(Certificate certificate) throws EmployeeException;

    /**
    * Add certificates to employee -> add into foreign table
    * @param employeeId - employee id of the employee
    * @param certificateId - certificate id of the certificate
    * @throws EmployeeException if the cause exception
    */
    public void addCertificateToEmployee(int employee, int certificate) throws EmployeeException; 
    /**
     * Get all the certificates
     * @throws EmployeeException if the cause exception
     * @return List of all certificates
     */
    public List<Certificate> getAllCertificates()throws EmployeeException;

    /**
     * Get the certificates by Id
     * @param certificateId - certificate id of the certificate
     * @return certificate object of certificates
     * @throws EmployeeException if the cause exception
     */
    public Certificate getCertificateById(int certificateId)throws EmployeeException; 

    /**
     * Update certificate by Id
     * @param certificate The certificate object of certificates to update.
     * @throws EmployeeException If the certificate does not exist.
     */
    public void updateCertificate(Certificate certificate)throws EmployeeException;
    /**
    * Delete the certificates by Id
    * 
    * @param certificatesId The ID of the certificates to delete.
    * @throws EmployeeException If the certificates does not exist.
    */
    public void deleteCertificate(int certificateId) throws EmployeeException;

    /** Method to get employees by certificate
    *
    * @param certificateId The ID of the certificate to get employees by certificate
    * @return The lodt of employee, or null if not found.
    * @throws EmployeeException If an error occurs while retrieving the department.
    */
    public List<Employee> getEmployeesByCertificate(int certificateId) throws EmployeeException; 
}
