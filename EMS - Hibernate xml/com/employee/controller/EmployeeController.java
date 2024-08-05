package com.employee.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;

import com.model.Certificate;
import com.certificate.service.CertificateService;
import com.certificate.service.CertificateServiceImpl;
import com.model.Department;
import com.department.service.DepartmentService;
import com.department.service.DepartmentServiceImpl;
import com.model.Employee;
import com.employee.service.EmployeeService;
import com.employee.service.EmployeeServiceImpl;
import com.employeeException.EmployeeException;
import com.util.Validator;

/**
* Displays the Employee menu and handles user input for various employee-related operations.
* The menu includes options to add, display, update, delete employees, and manage certificates.
*
* @author Dharani G
* @version 1.0
*/
public class EmployeeController {
    Scanner input = new Scanner(System.in);
    EmployeeService employeeService = new EmployeeServiceImpl();
    CertificateService certificateService = new CertificateServiceImpl();
    DepartmentService departmentService = new DepartmentServiceImpl();

    /**
    * The functions is for employee options
    */
   
    public void getEmployeeDetails() {
        boolean isExit = false;
        while (!isExit) {
            System.out.println("\nEmployee Menu:");
            System.out.println("1. Add Employee");
            System.out.println("2. Display All Employees");
            System.out.println("3. Display Employee by ID");
            System.out.println("4. Update Employee");
            System.out.println("5. Delete Employee");
            System.out.println("6. Back to Main Menu");
            
            int choice = input.nextInt();
            input.nextLine();
            
            switch (choice) {
            case 1:
                addEmployee();
                break;
            case 2:
                displayAllEmployees();
                break;
            case 3:
                displayEmployeeById();
                break;
            case 4:
                updateEmployee();
                break;
            case 5:
                deleteEmployee();
                break;
            case 6:
                isExit = true; 
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
    * Add Employee details into employee object getting from user as employee id and employee name
    */
    public void addEmployee() {
        System.out.println("Add Employee Records");
        boolean isFlag = true; 
        String employeeName = " ";
        while(isFlag) {
            System.out.println("Enter the Employee Name");
            employeeName = input.nextLine();
            if(Validator.isValidAlphabet(employeeName)) {
                isFlag = false;
            } else {
                System.out.println("Invalid.Enter valid Name");
            }
        } 
        isFlag = true;
        boolean isActive = true;
        LocalDate employeeDOB = null; 
        int age = 0;
        while (isActive) {
            System.out.println("Enter DOB in yyyy-mm-dd format.");
            String dobString = input.nextLine();
            try {
                employeeDOB = Validator.parseDate(dobString);
                age = Validator.calculateAge(employeeDOB);
                System.out.println("Employee's age: " + age);
                isActive = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        isActive = true;
        boolean isFound = true;
        long contactNumber = 0;
        while(isFound) {
            System.out.println("Enter the Employee Contact Number");
            contactNumber = input.nextLong();
            String contactNumberString = String.valueOf(contactNumber);
            if (Validator.isValidContact(contactNumberString)) {
                isFound = false;
            } else {
                System.out.println("Enter the valid Contact Number");
            }   
        } 
        isFound = true;

        boolean isMail = true;
        input.nextLine();
        String mailId = " ";                
        while(isMail) {
            System.out.println("Enter the Employee Mail ID");
            mailId = input.nextLine();
            if(Validator.isValidMail(mailId)) {
                isMail = false;
            } else {
                System.out.println("Enter the valid Employee mail ID");
                input.nextLine();  
            }
        }
        isMail = true;
        boolean isExperienceCheck = true;
        int experience = 0;
        while (isExperienceCheck) {
            try{
                System.out.println("Enter the Employee Experience");
                experience = input.nextInt();
                isExperienceCheck = false;
            } catch (Exception e) {
                System.out.println("Invalid.Enter valid Experience");
                input.next();  
            }
        }
        isExperienceCheck = true;
        double salary = 0.00;
        boolean isSalaryCheck = true;
        while(isSalaryCheck) {
            try{
                System.out.println("Enter the Employee Salary");
                salary = input.nextDouble();
                isSalaryCheck = false;
            } catch (Exception e) {
                System.out.println("Invalid.Enter valid salary");
                input.next();  
            }
        }
        isSalaryCheck = true;
        boolean isCityCheck = true;
        String city = " ";
        while(isCityCheck) {
            System.out.println("Enter the Employee City");
            input.nextLine();
            city = input.nextLine();
            if(Validator.isValidAlphabet(city)) {
                isCityCheck = false;
            } else {
                System.out.println("Enter the valid Employee city");
                input.next();  
            }
        } 
        isCityCheck = true;
        
        try {
            List<Department> departments = departmentService.getAllDepartments();
            if (departments.isEmpty()) {
                System.out.println("No departments found.");
            } else {
                for(Department departmentObject :departments) {
                    System.out.println(departmentObject);
                }
            }
        } catch (EmployeeException e) {
            System.out.println("Unable to display department" + e.getMessage());
        }

        System.out.println("Enter Department ID:");
        int departmentId = input.nextInt();

        try {
            employeeService.addEmployee(employeeName, employeeDOB, contactNumber, mailId, experience, salary, city, departmentId);
            System.out.println("Employee added successfully.");
            System.out.println("-----------------------------");
        } catch (EmployeeException e) {
            System.out.println("unable to add " + e.getMessage());
	    e.printStackTrace();
        }
    }

   
    /**
    * Display all employees present in employee list
    */
    public void displayAllEmployees() {
        try {
            List<Employee> employees = employeeService.getAllEmployees();
            if (employees.isEmpty()) {
                System.out.println("No employees found.");
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
                    System.out.println(new String(new char[150]).replace("\0", "-"));
                }
            }
        } catch (EmployeeException e) {
            System.out.println("Unable to get all employees" + e.getMessage());
        }
   }    

    /**
    * Get employee id from user and display the particular employee records
    */
    public void displayEmployeeById() {
        System.out.print("Enter Employee ID: ");
        int employeeId = input.nextInt();
        try {
            Employee employee = employeeService.getEmployeeById(employeeId);
            if (employee!= null) {
                String format = "| %-5s | %-10s | %-3s | %-15s | %-15s | %-10s | %-10s | %-10s | %-15s |\n";
                System.out.format(format, "ID", "Name", "Age", "Contact Number", "Mail ID", "Experience", "Salary", "City", "Department");
                System.out.println(new String(new char[150]).replace("\0", "-"));
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
                System.out.println(new String(new char[150]).replace("\0", "-"));
            } else {
                System.out.println("Employee with ID not found.");
            }
        } catch (EmployeeException e) {
            System.out.println("Unable to display" + e.getMessage());
        }
    }

    
    /**
    * Update employee records by checking the employee id present in list
    */
    public void updateEmployee() {
        System.out.print("Enter Employee ID: ");
        int employeeId = input.nextInt();
        try {
            Employee employee = employeeService.getEmployeeById(employeeId);
            if(employee == null) {
                System.out.println("Empty list");  
            } else {
                System.out.println("Employee Id exists ");
                updateOperations(employee);
                employeeService.updateEmployee(employee);
                System.out.println("Employee updated successfully.");
                System.out.println("-----------------------------");
            }
        } catch (EmployeeException e) {
            System.out.println("Unable to update employee" + e.getMessage());
        }
    }


    /**
    * Delete employee record by checking the employee id contains in employee list
    */
    public void deleteEmployee() {
        System.out.print("Enter Employee ID: ");
        int employeeId = input.nextInt();
        try {
            employeeService.deleteEmployee(employeeId);
            System.out.println("Employee deleted successfully.");
            System.out.println("-----------------------------");
        } catch (EmployeeException e) {
            System.out.println("Unable to delete " + e.getMessage());
        }
    }
   
    /**
    * Updating options for employee details
    */
    public void updateOperations(Employee employee) {
            System.out.println("Updating records: ");
            System.out.println(" 1. Update name");
            System.out.println("2. Update contact number ");
            System.out.println("3. Update mail Id");
            System.out.println("4. Update experience");
            System.out.println("5. Update salary");
            System.out.println("5. Update city");
            int updateOption = input.nextInt();
            
                switch (updateOption) {
                case 1 :
                    input.nextLine(); 
                    System.out.print("Enter Employee Name: ");
                    String employeeName = input.nextLine();
                    employee.setEmployeeName(employeeName);
                    break;
                case 2:
                    System.out.print("Enter Employee Contact Number: ");
                    long contactNumber = input.nextLong();
                    employee.setContactNumber(contactNumber);
                    break;
                case 3 :
                    input.nextLine(); 
                    System.out.print("Enter Employee Mail ID: ");
                    String mailId = input.nextLine();
                    employee.setMailId(mailId);
                    break;
                case 4 :
                    System.out.print("Enter Employee Experience: ");
                    int experience = input.nextInt();
                    employee.setExperience(experience);
                    break;
                case 5 :
                    System.out.print("Enter Employee Salary: ");
                    double salary = input.nextDouble();
                    employee.setSalary(salary); 
                    break;
                case 6 :
                    input.nextLine(); 
                    System.out.print("Enter Employee City: ");
                    String city = input.nextLine();
                    employee.setCity(city);
                    break;
                default :
                    System.out.println("choose valid option");
                    
                }
     }
}

    