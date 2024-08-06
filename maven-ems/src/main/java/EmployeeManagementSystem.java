import java.util.Scanner;

import com.ideas2it.ems.employee.controller.EmployeeController;
import com.ideas2it.ems.department.controller.DepartmentController;
import com.ideas2it.ems.certificate.controller.CertificateController;

/**
* This class is the main application that run Employee Management System 
* contains employee details, certificate and department updation 
*/
public class EmployeeManagementSystem {
    Scanner scanner = new Scanner(System.in);
    EmployeeController employeeController = new EmployeeController();
    DepartmentController departmentController = new DepartmentController();
    CertificateController certificateController = new CertificateController();
    boolean isExit = false;
    public void start() {
        while (!isExit) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Manage Employees");
            System.out.println("2. Manage Departments");
            System.out.println("3. Manage Certificates");
            System.out.println("4. Exit");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
            case 1:
                employeeController.getEmployeeDetails();
                break;
            case 2:
                departmentController.getDepartmentDetails();
                break;
            case 3:
                certificateController.getCertificateDetails();
                break;
            case 4:
                System.out.println("Exiting...");
                isExit = true;
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    public static void main(String[] args) {
        new EmployeeManagementSystem().start();
    }
}
