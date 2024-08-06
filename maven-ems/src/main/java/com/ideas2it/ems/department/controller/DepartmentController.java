package com.ideas2it.ems.department.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.Logger;  
import org.apache.logging.log4j.LogManager;

import com.ideas2it.ems.department.service.DepartmentService;
import com.ideas2it.ems.department.service.DepartmentServiceImpl;
import com.ideas2it.ems.exception.EmployeeException;
import com.ideas2it.ems.model.Employee;
import com.ideas2it.ems.model.Department;

/**
* The class used for user input and output for department details.
* Adding the departments, displaying, updating and deleting the details by department id
* 
* @author Dharani G
*
*/
public class DepartmentController {
    Scanner scanner = new Scanner(System.in);
    DepartmentService departmentService = new DepartmentServiceImpl();
    
    private static Logger logger = LogManager.getLogger();

    /**
    * Getting department details like deparmtent id, department name 
    * It can do the functionalities like add, display, update and deleting by departments
    */
    
    public void getDepartmentDetails() {
        boolean isExit = false;
        while (!isExit) {
            System.out.println("\nDepartment Menu:");
            System.out.println("1. Add Department");
            System.out.println("2. Display Departments");
            System.out.println("3. Display Department by Id");
            System.out.println("4. Update Department");
            System.out.println("5. Delete Department");
            System.out.println(" 6. Get employee by department id");
            System.out.println("7. Back to Main Menu");

            int departmentChoice = scanner.nextInt();
            
            switch (departmentChoice) {
            case 1:
                addDepartment();
                break;
            case 2:
                displayDepartments();
                break;
            case 3:
                displayDepartmentById();
                break;
            case 4:
                updateDepartment();
                break;
            case 5:
                deleteDepartment();
                break;
            case 6:
                getEmployeeByDepartmentId();
                break;
            case 7:
                isExit = true;
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
    * <p>Add department details into the department object
    * if added - successfully added, if not cause error </p>
    */
    public void addDepartment() {
        scanner.nextLine(); 
        System.out.print("Enter Department Name: ");
        String departmentName = scanner.nextLine();
        try {
            departmentService.addDepartment(departmentName);
            logger.info("Department added successfully");
            System.out.println("-----------------------------");
        } catch (EmployeeException e) {
            logger.error(e.getMessage());
        }
    }

    /**
    * Display all department details
    */
    public void displayDepartments() {
        try {
            List<Department> departments = departmentService.getAllDepartments();
            if (departments.isEmpty()) {
                logger.warn("No departments found.");
            } else {
                String format = "| %-20s | %-25s |\n";
                System.out.format(format, "Department ID", "Department Name");
                System.out.println(new String(new char[50]).replace("\0", "-"));
                for(Department department :departments) {
                    System.out.format(format, department.getDepartmentId() , department.getDepartmentName());
                }
                System.out.println(new String(new char[50]).replace("\0", "-"));
                logger.info("Departments are displayed successfully");
            }
        } catch (EmployeeException e) {
            logger.error(e.getMessage());
        }
    }

    /** 
    * <P>Display Department details By department Id
    * if department is null, shows warn. if it cause exception when error occurs
    * if department present by given id, it displays </p>
    */
    public void displayDepartmentById() {
        displayDepartments();
        System.out.println("Enter department Id: ");
        int departmentId = scanner.nextInt();
        try {
            Department department = departmentService.getDepartmentById(departmentId);
            if(department == null ) {
                logger.warn("Department details is not exists");
            } else {
                System.out.println(department);
                logger.info("Department are displayed successfully" + departmentId);
            }
        } catch (EmployeeException e) {
            logger.error(e.getMessage());
        }
    }

    /**
    * <p>Update department by checking with department id
    * If error occurs while updating, shows exception otherwise, it updates</p>
    */
    public void updateDepartment() {
        System.out.print("Enter Department ID to Update: ");
        int departmentId = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Enter New Department Name: ");
        String departmentNewName = scanner.nextLine();
        try {
            departmentService.updateDepartment(departmentId, departmentNewName);
            logger.info("Department updated successfully." + departmentId );
        } catch (EmployeeException e) {
            logger.error(e.getMessage());
        }
    }

    /**
    * <p>Delete department by giving department id
    * If error occurs while deleting, shows exception otherwise, it deletes</p>
    */
    public void deleteDepartment() {
        System.out.print("Enter Department ID to Delete: ");
        int departmentId = scanner.nextInt();
        try {
            departmentService.deleteDepartment(departmentId);
            logger.info("Department deleted successfully." + departmentId);
        } catch (EmployeeException e) {
            logger.error(e.getMessage());
        }
    }

    /**
    * <p>By giving department id, gets employee details. if it is empty,shows no employees present</p>
    */
    public void getEmployeeByDepartmentId() {
        System.out.println("Enter department id");
        int departmentId = scanner.nextInt();
        try {
            Department department = departmentService.getDepartmentById(departmentId);
            List<Employee> employees = new ArrayList<>(department.getEmployees());
            if(employees.isEmpty()) {
                logger.warn("No employees are under this department");
            } else {
                String format = "| %-5s | %-10s | %-3s | %-15s | %-15s | %-10s | %-10s | %-10s | %-15s |\n";
                System.out.format(format, "ID", "Name", "Age", "Contact Number", "Mail ID", "Experience", "Salary", "City", "Certificate");
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
                                      employee.getCertificates());
                }
                System.out.println(new String(new char[150]).replace("\0", "-"));
                logger.info("Display employee details under the department" + departmentId);
            }
        } catch (EmployeeException e) {
            logger.error(e.getMessage());
        }
    }
}
