package com.ideas2it.ems.certificate.service;

import java.util.ArrayList;
import java.util.List;

import com.ideas2it.ems.certificate.dao.CertificateDao;
import com.ideas2it.ems.model.Certificate;
import com.ideas2it.ems.certificate.service.CertificateService;
import com.ideas2it.ems.model.Employee;
import com.ideas2it.ems.exception.EmployeeException;

/**
 * Service class for managing certificate-related operations.
 */
public interface CertificateService {
    
    /**
    * <p>Add a certificate
    * @param certificateName -> certificateName from user
    * @throws EmployeeException if there is an error adding the certificate</p>
    */
    public void addCertificate(String certificateName) throws EmployeeException;

    /**
    * <p>Get all certificates
    * @return a list of all certificates
    * @throws EmployeeException if doesn't display all certificates</p>
    */
    public List<Certificate> getAllCertificates() throws EmployeeException;

    /**
    * <p>Get a certificate by ID
    *
    * @param certificateId the ID of the certificate
    * @return the certificate object
    * @throws EmployeeException if the certificateId is not found</p>
    */
    public Certificate getCertificateById(int certificateId) throws EmployeeException;
 
    
    /**
    * <p>Check if the certificate by ID in certificate list or not
    *
    * @param certificateId the ID of the certificate
    * @return the boolean if certificate id present in the certificte list or not 
    * @throws EmployeeException if the certificateId is not found</p>
    */
    public boolean isPresentCertificate(int certificateId) throws EmployeeException;

     /**
    *  <p>Check if the certificate ID and employee id in the list or not
    * @param employeeId - the ID of the employee 
    * @param certificateId the ID of the certificate
    * @return the boolean if certificate id present in the certificte list or not 
    * @throws EmployeeException if the certificateId is not found</p>
    */
    public boolean isPresentEmployeeAndCertificate(int employeeId, int certificateId) throws EmployeeException;

    /**
    * <p>Update a certificate
    * @param certificateId   the ID of the certificate to update
    * @param certificateName the new name of the certificate
    * @throws EmployeeException if the certificate is not found</p>
    */
    public void updateCertificate(int certificateId, String certificateName) throws EmployeeException;

    /** 
    * <p>Delete a certificate
    *
    * @param certificateId   the ID of the certificate to update
    * @throws EmployeeException if the certificate is not found </p>
    */
    public void deleteCertificate(int certificateId) throws EmployeeException;

    /**
    *<p> Add employee to certificate
    *
    * @param certificateId   the ID of the certificate
    * @param employeeId         add certifcateId into employee object 
    * @throws EmployeeException if the certificate is not found</p>
    */
    public void addCertificateToEmployee(int employeeId, int certificateId) throws EmployeeException;

    /**
    *<p> Get employees by certificate ID
    * 
    * @param certificateId ->the ID of the certificate
    * @return the list of employee
    * @throws EmployeeException if the cause exception </p>
    */
    public List<Employee> getEmployeesByCertificate(int certificateId) throws EmployeeException;

    
    /**
    * <p>Get certificates by employee ID
    *
    * @param employeeId -> Get certificates by employee ID
    * @return the list of certificate
    * @throws EmployeeException if the cause exception</p>
    */
    public List<Certificate> getCertificatesByEmployeeId(int employeeId) throws EmployeeException;
}