package com.ideas2it.ems.employee.controller;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.InputMismatchException;

import org.apache.logging.log4j.Logger;  
import org.apache.logging.log4j.LogManager;

import com.ideas2it.ems.bankDetail.service.BankDetailService;
import com.ideas2it.ems.bankDetail.service.BankDetailServiceImpl;
import com.ideas2it.ems.certificate.service.CertificateService;
import com.ideas2it.ems.certificate.service.CertificateServiceImpl;
import com.ideas2it.ems.department.service.DepartmentService;
import com.ideas2it.ems.department.service.DepartmentServiceImpl;
import com.ideas2it.ems.employee.service.EmployeeService;
import com.ideas2it.ems.employee.service.EmployeeServiceImpl;
import com.ideas2it.ems.exception.EmployeeException;
import com.ideas2it.ems.model.BankDetail;
import com.ideas2it.ems.model.Certificate;
import com.ideas2it.ems.model.Department;
import com.ideas2it.ems.model.Employee;
import com.ideas2it.ems.util.Validator;

/**
* Displays the Employee menu and handles user input for various employee-related operations.
* The menu includes options to add, display, update, delete employees, and manage certificates.
*
* @author Dharani G
* 
*/
public class EmployeeController {
    Scanner input = new Scanner(System.in);
    EmployeeService employeeService = new EmployeeServiceImpl();
    CertificateService certificateService = new CertificateServiceImpl();
    DepartmentService departmentService = new DepartmentServiceImpl();
    BankDetailService bankDetailService = new BankDetailServiceImpl();

    private static Logger logger = LogManager.getLogger();

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
            System.out.println("6. BankDetails");
            System.out.println("7. Back to Main Menu");
            
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
                viewBankDetails();
                break;
            case 7:
                isExit = true; 
                break;
            default:
                logger.info("Invalid choice. Please try again.");
            }
        }
    }

    /**
    * <p>Add Employee details into employee object getting from user as employee id and employee name
    * if added - successfully added, if not cause error </p>
    */
    public void addEmployee() {
        logger.info("Add Employee Records");
        boolean isFlag = true; 
        String employeeName = " ";
        while(isFlag) {
            System.out.println("Enter the Employee Name");
            employeeName = input.nextLine();
            try {
                if(Validator.isValidAlphabet(employeeName)) {
                    isFlag = false;
                }
            } catch(InputMismatchException e) {
                logger.error("Invalid.Enter valid Name", e.getMessage());
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
                logger.error("Enter valid date of birth ", e.getMessage());
            }
        }
        isActive = true;
        boolean isFound = true;
        long contactNumber = 0;
        while(isFound) {
            System.out.println("Enter the Employee Contact Number");
            contactNumber = input.nextLong();
            String contactNumberString = String.valueOf(contactNumber);
            try {
                if (Validator.isValidContact(contactNumberString)) {
                    isFound = false;
                }
            } catch(InputMismatchException e) {
                logger.error("Enter the valid Contact Number ", e.getMessage());
            }   
        } 
        isFound = true;

        boolean isMail = true;
        input.nextLine();
        String mailId = " ";                
        while(isMail) {
            System.out.println("Enter the Employee Mail ID");
            mailId = input.nextLine();
            try {
                if(Validator.isValidMail(mailId)) {
                    isMail = false;
                }
            } catch(Exception e) {
                logger.error("Enter the valid Employee mail ID", e.getMessage());
                input.nextLine();  
            }
        }
        isMail = true;
        int experience = 0;
        boolean isExperienceCheck = true;
        while (isExperienceCheck) {
            try {
                System.out.println("Enter the Employee Experience (0 to 30):");
                experience = input.nextInt();
                if (experience < 0 || experience > 30) {
                    System.out.println("Invalid input. Please enter a number between 0 and 30.");
                } else {
                    isExperienceCheck = false; 
                }
            } catch (Exception e) {
                logger.error("Invalid input. Please enter a valid integer.", e.getMessage());
                input.next(); 
            }
        }

        double salary = 0.00;
        boolean isSalaryCheck = true;
        while(isSalaryCheck) {
            try{
                System.out.println("Enter the Employee Salary");
                salary = input.nextDouble();
                isSalaryCheck = false;
            } catch (Exception e) {
                logger.error("Invalid.Enter valid salary", e.getMessage());
                input.next();  
            }
        }
        isSalaryCheck = true;
        boolean isCityCheck = true;
        String city = " ";
        while(isCityCheck) {
            System.out.println("Enter the Employee City");
            city = input.nextLine();
            try {
                if(Validator.isValidAlphabet(city)) {
                    isCityCheck = false;
                }
            } catch(InputMismatchException e) {
                logger.error("Enter the valid Employee city", e.getMessage());
            }
        } 
        isCityCheck = true;

        System.out.println("Enter the bank details: ");
        long accountNumber = 0;
        boolean isValidInput = false;
        while (!isValidInput) {
            System.out.println("Enter employee account number:");
            try {
                accountNumber = input.nextLong();
                isValidInput = true; 
            } catch (InputMismatchException e) {
                logger.error("Invalid input. Please enter a valid numeric account number.", e.getMessage());
                input.next(); 
            }
        }
       
        String branch = " ";
        boolean isString = true;
        while(isString) {
            System.out.println("Enter branch details");
            branch = input.nextLine();
            try {
                if(Validator.isValidAlphabet(branch)) {
                    isString = false;
                }
            } catch(InputMismatchException e) {
                logger.error("Invalid.Enter valid branch", e.getMessage());
            }
        } 
        isString = true;
       
        try {
            bankDetailService.addBankDetail(accountNumber, branch);
            logger.info("Bank Details added successfully");
        } catch (EmployeeException e) {
            logger.error(e.getMessage());
        }

       try {
            List<Department> departments = departmentService.getAllDepartments();
            if (departments.isEmpty()) {
                logger.warn("No departments found.");
            } else {
                for(Department departmentObject :departments) {
                    System.out.println(departmentObject);
                }
                System.out.println("Enter Department ID:");
                int departmentId = input.nextInt();
                while(!(departmentService.isPresentDepartment(departmentId))) {
                    logger.warn("Invalid department. Please enter valid department");
                    departmentId = input.nextInt();
                } 
                employeeService.addEmployee(employeeName, employeeDOB, contactNumber, mailId, experience, salary, city, departmentId, accountNumber, branch);
                logger.info("Employee added successfully." + employeeName);
            }
        } catch (EmployeeException e) {
            logger.info("Error while adding employee details" + e.getMessage());
        }
    }

   
    /**
    * <p>Display all employees present in employee list
    * if employee is empty, gives warn,if there is error shows exception
    * if employee occurs, display everything</p>
    */
    public void displayAllEmployees() {
        try {
            List<Employee> employees = employeeService.getAllEmployees();
            if (employees.isEmpty()) {
                logger.warn("No employees found.");
            } else {
                String format = "| %-5s | %-10s | %-3s | %-15s | %-15s | %-10s | %-10s | %-10s | %-15s | %-15s | \n";
                System.out.format(format, "ID", "Name", "Age", "Contact Number", "Mail ID", "Experience", "Salary", "City", "Department", "BankDetails");
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
                                      employee.getDepartment().getDepartmentName(),
                                      employee.getBankDetail());
                    System.out.println(new String(new char[150]).replace("\0", "-"));
                }
                logger.info("Displayed successfully");
            }
        } catch (EmployeeException e) {
            logger.error(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
   }    

    /**
    * <p>Get employee id from user and display the particular employee records
    * if employee is null, shows warn. if it cause exception when error occurs
    * if employee present by given id, it displays </p>
    */
    public void displayEmployeeById() {
        System.out.print("Enter Employee ID: ");
        int employeeId = input.nextInt();
        try {
            Employee employee = employeeService.getEmployeeById(employeeId);
            if (employee == null) {
                logger.warn("Employee with ID not found.");
            } else {
                String format = "| %-5s | %-10s | %-3s | %-15s | %-15s | %-10s | %-10s | %-10s | %-15s | %-15s | \n";
                System.out.format(format, "ID", "Name", "Age", "Contact Number", "Mail ID", "Experience", "Salary", "City", "Department","BankDetail");
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
                                  employee.getDepartment().getDepartmentName(),
                                  employee.getBankDetail());
                System.out.println(new String(new char[150]).replace("\0", "-"));
                logger.info("Display employee detils by employee id");
            }
        } catch (EmployeeException e) {
            logger.error("Error while displaying employee details", e.getMessage());
        }
    }

    
    /**
    * <p> Update employee records by checking the employee id present in list
    * If error occurs while updating, shows exception otherwise, it updates</p>
    */
    public void updateEmployee() {
        System.out.print("Enter Employee ID: ");
        int employeeId = input.nextInt();
        try {
            Employee employee = employeeService.getEmployeeById(employeeId);
            if(employee == null) {
                logger.warn("Empty list");  
            } else {
                System.out.println("Employee Id exists ");
                updateOperations(employee);
                employeeService.updateEmployee(employee);
                logger.info("Employee updated successfully.");
                System.out.println("-----------------------------");
            }
        } catch (EmployeeException e) {
            logger.error("Error while updating employee details", e.getMessage());
        }
    }


    /**
    * <p>Delete employee record by checking the employee id contains in employee list
    * If error occurs while deleting, shows exception otherwise, it deletes</p>
    */
    public void deleteEmployee() {
        System.out.print("Enter Employee ID: ");
        int employeeId = input.nextInt();
        try {
            employeeService.deleteEmployee(employeeId);
            logger.info("Employee deleted successfully.");
            System.out.println("-----------------------------");
        } catch (EmployeeException e) {
            logger.error("Error while deleting employee details", e.getMessage());
        }
    }
   
    /**
    * Updating options for employee details
    */
    public void updateOperations(Employee employee) {
        logger.info("Updating records: ");
        System.out.println("1. Update name");
        System.out.println("2. Update contact number ");
        System.out.println("3. Update mail Id");
        System.out.println("4. Update experience");
        System.out.println("5. Update salary");
        System.out.println("6. Update city");
        System.out.println("7. Update bank details");
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
        case 7 :
            updateBankDetail(employee);
            break;
        default :
            System.out.println("choose valid option");           
        }
    }   
    
    /**
    *<p> Bank Details Menu
    * 1. Display bank details
    * 2. Display bank details by id
    * 3. Update bank details</p>
    */
    public void viewBankDetails() {
        System.out.println("1. Display bank details");
        System.out.println("2. Display bank details by id");
        System.out.println("3. Back Menu");
        int choice = input.nextInt();
        switch(choice) {
        case 1 : displayBankDetail();
                 break;
        case 2 : displayBankDetailById(); 
                 break;
        case 3 : return;
        default :System.out.println("choose valid option");
                 break;   
        }
    }

    /**
    * <p>Display all bank Details
    * </p>
    */
    public void displayBankDetail() {
        try {
            List<BankDetail> bankDetails = bankDetailService.getAllBankDetail();
            if(bankDetails.isEmpty()) {
                logger.warn("Bank Details are empty");
            } else {
                String format = "| %-5s | %-10s | %-10s \n";
                System.out.format(format, "Account Id", "Account Number", "Branch");
                for(BankDetail bankDetail : bankDetails) {
                    System.out.println(new String(new char[50]).replace("\0", "-"));
                    System.out.format(format, bankDetail.getAccountId(), bankDetail.getAccountNumber(), bankDetail.getBranch());
                }
                System.out.println(new String(new char[50]).replace("\0", "-"));   
                logger.info("Bank details displayed successfully");
                
            }
        } catch (EmployeeException e) {
            logger.error("Error while display bank details", e.getMessage());
        }
    }

    /**
    * <p>Display bank Details by account id</p>
    */
    public void displayBankDetailById() {
        try {
            System.out.println("Enter account id ");
            int accountId = input.nextInt();
            BankDetail bankDetail = bankDetailService.getBankDetailById(accountId);
            if(bankDetail == null) {
                logger.warn("Account id doesnot exist");
            } else {
                String format = "| %-5s | %-10s | %-10s \n";
                System.out.format(format, "Account Id", "Account Number", "Branch");
                System.out.println(new String(new char[50]).replace("\0", "-"));
                System.out.format(format, bankDetail.getAccountId(), bankDetail.getAccountNumber(), bankDetail.getBranch());
                System.out.println(new String(new char[50]).replace("\0", "-"));
                logger.info("Bank details displayed by account id successfully");
            }
        }  catch (EmployeeException e) {
            logger.error("Error while displayingbank details by id", e.getMessage());
        }
    }

    /**
    * <p>Bank details updation 
    * @param employee - bank details updation in employee object <p>
    */
    public void updateBankDetail(Employee employee) {
        BankDetail bankDetail  = new BankDetail(); 
        try {
            System.out.println("1. Update account number");
            System.out.println("2. Update branch");
            int updateChoice = input.nextInt(); 
            switch(updateChoice) {
            case 1 : 
                System.out.println("Enter updated account number");
                long accountNumber = input.nextLong();
                bankDetail.setAccountNumber(accountNumber);
                employee.getBankDetail().setAccountNumber(accountNumber);
                break;
            case 2 :
                System.out.println("Enter updated branch");
                input.nextLine();
                String branch = input.nextLine();
                bankDetail.setBranch(branch);
                employee.getBankDetail().setBranch(branch);
                break;
            default:
                System.out.println("Invalid choice updation");
                break;
            }
            bankDetailService.updateBankDetail(bankDetail);
            logger.info("Employee bank details successfully updated"); 
        } catch (EmployeeException e) {
            logger.error("Error while updating bank details", e.getMessage()); 
        }
    }  
}

    