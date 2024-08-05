package com.department.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.model.Department;
import com.department.service.DepartmentService;
import com.department.service.DepartmentServiceImpl;
import com.employeeException.EmployeeException;
import com.model.Employee;

/**
* The class used for user input and output for department details.
* Adding the departments, displaying, updating and deleting the details by department id
* 
* @author Dharani G
* @Version 1.0
*/
public class DepartmentController {
    Scanner scanner = new Scanner(System.in);
    DepartmentService departmentService = new DepartmentServiceImpl();
    
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
    * Add department details into the department object
    */
    public void addDepartment() {
        scanner.nextLine(); 
        System.out.print("Enter Department Name: ");
        String departmentName = scanner.nextLine();
        try {
            departmentService.addDepartment(departmentName);
            System.out.println("Department added successfully");
            System.out.println("-----------------------------");
        } catch (EmployeeException e) {
            System.out.println("Unable to add department" + e.getMessage());
        }
    }

    /**
    * Display all department details
    */
    public void displayDepartments() {
        try {
            List<Department> departments = departmentService.getAllDepartments();
            if (departments.isEmpty()) {
                System.out.println("No departments found.");
            } else {
                String format = "| %-20s | %-25s |\n";
                System.out.format(format, "Department ID", "Department Name");
                System.out.println(new String(new char[50]).replace("\0", "-"));
                for(Department department :departments) {
                    System.out.format(format, department.getDepartmentId() , department.getDepartmentName());
                }
                System.out.println(new String(new char[50]).replace("\0", "-"));
            }
        } catch (EmployeeException e) {
            System.out.println("Unable to display department" + e.getMessage());
        }
    }

    /** 
    * Display Department details By department Id
    */
    public void displayDepartmentById() {
        displayDepartments();
        System.out.println("Enter department Id: ");
        int departmentId = scanner.nextInt();
        try {
            Department department = departmentService.getDepartmentById(departmentId);
            if(department == null ) {
                System.out.println("Department details is not exists");
            } else {
                System.out.println(department);
            }
        } catch (EmployeeException e) {
            System.out.println("Unable to display department by department Id" + e.getMessage());
        }
    }

    /**
    * Update department by checking with department id
    */
    public void updateDepartment() {
        System.out.print("Enter Department ID to Update: ");
        int departmentId = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Enter New Department Name: ");
        String departmentNewName = scanner.nextLine();
        try {
            departmentService.updateDepartment(departmentId, departmentNewName);
            System.out.println("Department updated successfully.");
        } catch (EmployeeException e) {
            System.out.println("Unable to update departmentId" + e.getMessage());
        }
    }

    /**
    * Delete department by giving department id
    */
    public void deleteDepartment() {
        System.out.print("Enter Department ID to Delete: ");
        int departmentId = scanner.nextInt();
        try {
            departmentService.deleteDepartment(departmentId);
            System.out.println("Department deleted successfully.");
        } catch (EmployeeException e) {
            System.out.println("Unable to delete the department" + e.getMessage());
        }
    }

    /**
    * By giving department id, gets employee details. if it is empty,shows no employees present
    */
    public void getEmployeeByDepartmentId() {
        System.out.println("Enter department id");
        int departmentId = scanner.nextInt();
        try {
            Department department = departmentService.getDepartmentById(departmentId);
            List<Employee> employees = new ArrayList<>(department.getEmployees());
            if(employees.isEmpty()) {
                System.out.println("No employees are under this department");
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
            }
        } catch (EmployeeException e) {
            System.out.println("Can't get employees by department Id" + e.getMessage());
        }
    }
}
