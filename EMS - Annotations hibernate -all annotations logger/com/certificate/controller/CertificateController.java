package com.certificate.controller;

import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.Logger;  
import org.apache.logging.log4j.LogManager;

import com.model.Certificate;
import com.certificate.service.CertificateService;
import com.certificate.service.CertificateServiceImpl;
import com.model.Employee;
import com.employee.service.EmployeeService;
import com.employee.service.EmployeeServiceImpl;
import com.employeeException.EmployeeException;

/** 
* <p> This class hold the certificate details and processing by certificate object 
* like adding, displaying, updating and deleting details </P>
* 
* @author Dharani G
* @version 1.0
*/
public class CertificateController {
    Scanner scanner = new Scanner(System.in);
    CertificateService certificateService = new CertificateServiceImpl();
    EmployeeService employeeService = new EmployeeServiceImpl();

    private static Logger logger = LogManager.getLogger();

    /**
    * Performing the options by certificate details
    */
    public void getCertificateDetails() {
        boolean isExit = false;
        while (!isExit) {
            System.out.println("\nCertificate Menu:");
            System.out.println("1. Add Certificate");
            System.out.println("2. Display All Certificates");
            System.out.println("3. Display Certificate by ID");
            System.out.println("4. Update Certificate");
            System.out.println("5. Delete Certificate");
            System.out.println("6. Add Certificate to Employee");
            System.out.println("7. Display Employees by Certificate");
            System.out.println("8. Display Certificates by Employee");
            System.out.println("9. Back to Main Menu");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
            case 1:
                addCertificate();
                break;
            case 2:
                displayAllCertificates();
                break;
            case 3:
                displayCertificateById();
                break;
            case 4:
                updateCertificate();
                break;
            case 5:
                deleteCertificate();
                break;
            case 6:
                addCertificateToEmployee();
                break;
            case 7:
                displayEmployeesByCertificate();
                break;
            case 8:
                displayCertificatesByEmployee();
                break;
            case 9:
                isExit = true;
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
    * Add certificate details into the certificate 
    */
    public void addCertificate() {
        System.out.print("Enter Certificate Name: ");
        String certificateName = scanner.nextLine();
        try {
            certificateService.addCertificate(certificateName);
            logger.info("Certificate added successfully.");
        } catch (EmployeeException e) {
            logger.error(e.getMessage());
        }
    }

    /**
    * Display all certificates details
    */
    public void displayAllCertificates() {
        try {
            List<Certificate> certificates = certificateService.getAllCertificates();
            if (certificates.isEmpty()) {
                logger.warn("No certificates found.");
            } else {
                for(Certificate certificate : certificates) {
                    System.out.println("certificate Id : " + certificate.getCertificateId() + " Certificate Name : " + certificate.getCertificateName());
                }
                logger.info("Certificates are displayed");
            }
        } catch (EmployeeException e) {
            logger.error(e.getMessage());
        }
    }

    /**
    * Display certificate details by giving certificate ID
    */
    public void displayCertificateById() {
        System.out.print("Enter Certificate ID: ");
        int certificateId = scanner.nextInt();
        try {
            Certificate certificate = certificateService.getCertificateById(certificateId);
            if (certificate != null) {
                System.out.println("certificate Id : " + certificate.getCertificateId() + " Certificate Name : " + certificate.getCertificateName());
                logger.info("Display vertificate by Id", certificate.getCertificateId());
            } else {
                logger.warn("Certificate with ID " + certificateId + " not found.");
            }
        } catch (EmployeeException e) {
            logger.error(e.getMessage());
        }
    }

    /**
    * Update certificates by giving certificate id
    */
    public void updateCertificate() {
        System.out.print("Enter Certificate ID to Update: ");
        int certificateId = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Enter New Certificate Name: ");
        String newName = scanner.nextLine();
        try {
            certificateService.updateCertificate(certificateId, newName);
            logger.info("Certificate updated successfully." + certificateId);
        } catch (EmployeeException e) {
            logger.error(e.getMessage());
        }
    }

    /**
    * Delete certificate details by certificate id 
    */
    public void deleteCertificate() {
        System.out.print("Enter Certificate ID to Delete: ");
        int certificateId = scanner.nextInt();
        try {
            certificateService.deleteCertificate(certificateId);
            logger.info("Certificate deleted successfully." + certificateId);
        } catch (EmployeeException e) {
            logger.error(e.getMessage());
        }
    }

    /**
    * Add certificate details to employee by employee id
    */
    public void addCertificateToEmployee() {
        System.out.print("Enter Employee ID: ");
        int employeeId = scanner.nextInt();
        System.out.print("Enter Certificate ID: ");
        int certificateId = scanner.nextInt();
        try {
            while(!certificateService.isPresentEmployeeAndCertificate(employeeId, certificateId)) {   
                logger.warn("Invalid Id.Please add valid one");
                System.out.print("Enter valid Employee ID: ");
                employeeId = scanner.nextInt();
                System.out.print("Enter valid Certificate ID: ");
                certificateId = scanner.nextInt();
            }
            certificateService.addCertificateToEmployee(employeeId, certificateId);
            logger.info("Certificate added to employee successfully." + certificateId);
        } catch (EmployeeException e) {
            logger.error(e.getMessage());
        }
    }

    /**
    * Display employees by giving certificate id
    */
    public void displayEmployeesByCertificate() {
        System.out.print("Enter Certificate ID: ");
        int certificateId = scanner.nextInt();
        try {
            List<Employee> employees = certificateService.getEmployeesByCertificate(certificateId);
            if (employees.isEmpty()) {
                logger.warn("No employees found for Certificate ID " + certificateId);
            } else {
                String format = "| %-5s | %-10s | %-3s | %-15s | %-15s | %-10s | %-10s | %-10s | %-15s |\n";
                System.out.format(format, "ID", "Name", "Age", "Contact Number", "Mail ID", "Experience", "Salary", "City", "Department");
                System.out.println(new String(new char[150]).replace("\0", "-"));
                for (Employee employee : employees) {
                    System.out.format(format,
                                      employee.getEmployeeId(),
                                      employee.getEmployeeName(),
                                      employee.getAge(),
                                      employee.getContactNumber(),
                                      employee.getMailId(),
                                      employee.getExperience(),
                                      employee.getSalary(),
                                      employee.getCity(),
                                      employee.getDepartment().getDepartmentName());
                }
                System.out.println(new String(new char[150]).replace("\0", "-"));
                logger.info("Display employees by certificate Id", certificateId);
            }
        } catch (EmployeeException e) {
            logger.error(e.getMessage());
        }
    }

    /** 
    * Display certificates by giving employee id
    */
    public void displayCertificatesByEmployee() {
        System.out.print("Enter Employee ID: ");
        int employeeId = scanner.nextInt();
        try {
            List<Certificate> certificates = employeeService.getCertificatesByEmployeeId(employeeId);
            if (certificates.isEmpty()) {
                logger.warn("No certificates found for Employee ID " + employeeId);
            } else {
                for(Certificate certificate : certificates) {
                    System.out.println("Certificates: " + "certificate ID : " + certificate.getCertificateId() +  "certificate Name : " + certificate.getCertificateName());
                    logger.info("Displayed certificate by employee Id", employeeId);
                }
            }
        } catch (EmployeeException e) {
            logger.error(e.getMessage());
        }
    }
}
