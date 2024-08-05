package com.employeeException;

/**
* This class deals with Exception in all my applications
* It calls the employee Exception with message context and throwable message
*/
public class EmployeeException extends Exception {
    public EmployeeException(String message, Throwable throwable) {
        super(message, throwable);    
    }    
}