package com.certificate.service;

import java.util.ArrayList;
import java.util.List;

import com.certificate.dao.CertificateDao;
import com.certificate.dao.CertificateDaoImpl;
import com.model.Certificate;
import com.certificate.service.CertificateService;
import com.model.Employee;
import com.employeeException.EmployeeException;
import com.employee.service.EmployeeService;
import com.employee.service.EmployeeServiceImpl;

/**
 * Service class for managing certificate-related operations.
 */
public class CertificateServiceImpl implements CertificateService {
    CertificateDao certificateDao = new CertificateDaoImpl();
    EmployeeService employeeService = new EmployeeServiceImpl();

    @Override
    public void addCertificate(String certificateName) throws EmployeeException {
        Certificate certificate = new Certificate(certificateName);
        certificateDao.addCertificate(certificate);
    }

    @Override
    public List<Certificate> getAllCertificates() throws EmployeeException {
        return certificateDao.getAllCertificates();
    }

    @Override
    public Certificate getCertificateById(int certificateId) throws EmployeeException {
        return certificateDao.getCertificateById(certificateId);
    }

    @Override
    public void updateCertificate(int certificateId, String certificateName) throws EmployeeException {
        Certificate certificate = new Certificate(certificateId, certificateName);
        certificateDao.updateCertificate(certificate);
    }

    @Override
    public void deleteCertificate(int certificateId) throws EmployeeException {
        certificateDao.deleteCertificate(certificateId);
    }

    @Override
    public void addCertificateToEmployee(int employeeId, int certificateId) throws EmployeeException {
        certificateDao.addCertificateToEmployee(employeeId, certificateId);
    }

    @Override
    public boolean isPresentCertificate(int certificateId) throws EmployeeException {
        Certificate certificate = certificateDao.getCertificateById(certificateId);
        if (null == certificate) {
            return false;
        } 
        return true;
    }

    @Override
    public boolean isPresentEmployeeAndCertificate(int employeeId, int certificateId) throws EmployeeException {
        boolean isPresent = employeeService.isPresentEmployee(employeeId);
        if (isPresent) {
            if (isPresentCertificate(certificateId)) {
                return true;
            } else {
                return false;
            }
        } 
        return false;
    }

    @Override
    public List<Employee> getEmployeesByCertificate(int certificateId) throws EmployeeException {
       return certificateDao.getEmployeesByCertificate(certificateId);
    }

    @Override
    public List<Certificate> getCertificatesByEmployeeId(int employeeId) throws EmployeeException {
        List<Certificate> result = new ArrayList<>();
        for (Certificate certificate : certificateDao.getAllCertificates()) {
            for (Employee employee : certificate.getEmployees()) {
                if (employee.getEmployeeId() == employeeId) {
                    result.add(certificate);
                }
            }
        }
        return result;
    }
}