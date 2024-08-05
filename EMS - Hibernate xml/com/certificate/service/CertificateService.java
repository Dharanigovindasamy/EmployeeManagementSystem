
package com.certificate.service;

import java.util.ArrayList;
import java.util.List;

import com.certificate.dao.CertificateDao;
import com.model.Certificate;
import com.certificate.service.CertificateService;
import com.model.Employee;
import com.employeeException.EmployeeException;

/**
 * Service class for managing certificate-related operations.
 */
public interface CertificateService {
    
    /**
    * Add a certificate
    * @param certificateName -> certificateName from user
    * @throws EmployeeException if there is an error adding the certificate
    */
    public void addCertificate(String certificateName) throws EmployeeException;

    /**
    * Get all certificates
    * @return a list of all certificates
    * @throws EmployeeException if doesn't display all certificates
    */
    public List<Certificate> getAllCertificates() throws EmployeeException;

    /**
    * Get a certificate by ID
    *
    * @param certificateId the ID of the certificate
    * @return the certificate object
    * @throws EmployeeException if the certificateId is not found
    */
    public Certificate getCertificateById(int certificateId) throws EmployeeException;

    /**
    * Update a certificate
    * @param certificateId   the ID of the certificate to update
    * @param certificateName the new name of the certificate
    * @throws EmployeeException if the certificate is not found
    */
    public void updateCertificate(int certificateId, String certificateName) throws EmployeeException;

    /** Delete a certificate
    *
    * @param certificateId   the ID of the certificate to update
    * @throws EmployeeException if the certificate is not found
    */
    public void deleteCertificate(int certificateId) throws EmployeeException;

    /** Add employee to certificate
    *
    * @param certificateId   the ID of the certificate
    * @param employeeId         add certifcateId into employee object 
    * @throws EmployeeException if the certificate is not found
    */
    public void addCertificateToEmployee(int employeeId, int certificateId) throws EmployeeException;

    /**
    * Get employees by certificate ID
    * 
    * @param certificateId ->the ID of the certificate
    * @return the list of employee
    * @throws EmployeeException if the cause exception
    */
    public List<Employee> getEmployeesByCertificate(int certificateId) throws EmployeeException;

    
    /**
    * Get certificates by employee ID
    *
    * @param employeeId -> Get certificates by employee ID
    * @return the list of certificate
    * @throws EmployeeException if the cause exception
    */
    public List<Certificate> getCertificatesByEmployeeId(int employeeId) throws EmployeeException;
}
