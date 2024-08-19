package com.ideas2it.ems.exception;

/**
* <p>
 *     This class deals with Employee Exception in all my applications
* It calls the employee Exception with message context and throwable message, extends from RumTime exception
 * </p>
 * @author dharani.govindhasamy
*/
public class EmployeeException extends RuntimeException {
    public EmployeeException(String message) {
        super(message);
    }    
}