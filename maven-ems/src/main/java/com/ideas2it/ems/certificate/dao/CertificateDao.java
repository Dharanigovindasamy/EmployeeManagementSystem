package com.ideas2it.ems.certificate.dao;

import java.util.List;

import com.ideas2it.ems.model.Certificate;
import com.ideas2it.ems.model.Employee;
import com.ideas2it.ems.exception.EmployeeException;

/**
 * <p> CertificateDao class for managing certificate data.
 * It performs operations like adding, displaying,updating 
 * and get the particular data from object and deleting</p>
 */
public interface CertificateDao {
    
    /**
     * Add the certificate details in certificate list
     *
     * @param certificate The certificate name to be added.
     * @throws EmployeeException If an error occurs while adding the certificate.
     */
    void addCertificate(Certificate certificate) throws EmployeeException;

    /**
    * Add certificates to employee -> add into foreign table
    * @param employee- employee id of the employee
    * @param certificate - certificate id of the certificate
    * @throws EmployeeException if the cause exception
    */
    void addCertificateToEmployee(int employee, int certificate) throws EmployeeException;
    /**
     * Get all the certificates
     * @throws EmployeeException if the cause exception
     * @return List of all certificates
     */
    List<Certificate> getAllCertificates()throws EmployeeException;

    /**
     * Get the certificates by I'd
     * @param certificateId - certificate id of the certificate
     * @return certificate object of certificates
     * @throws EmployeeException if the cause exception
     */
    Certificate getCertificateById(int certificateId)throws EmployeeException;

    /**
     * Update certificate by I'd
     * @param certificate The certificate object of certificates to update.
     * @throws EmployeeException If the certificate does not exist.
     */
    void updateCertificate(Certificate certificate)throws EmployeeException;
    /**
    * Delete the certificates by I'd
    * 
    * @param certificateId The ID of the certificates to delete.
    * @throws EmployeeException If the certificates do not exist.
    */
    void deleteCertificate(int certificateId) throws EmployeeException;

    /** Method to get employees by certificate
    *
    * @param certificateId The ID of the certificate to get employees by certificate
    * @return The list of employee, or null if not found.
    * @throws EmployeeException If an error occurs while retrieving the department.
    */
    List<Employee> getEmployeesByCertificate(int certificateId) throws EmployeeException;
}
