package com.ideas2it.ems.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

/**
 * <p>
 *     This class used for global exception which handles exception for all controllers and
 *     throws the exception to controller with HTTP status, if the condition is unmatched
 * </p>
 *
 * @author dharani.govindhasamy
 */

@ControllerAdvice
public class GlobalException {

    /**
     * This exception method will handle while entering the same data again into it.
     *
     * @param employeeException - custom exception in employeeException
     * @return employeeException message with http status
     */
    @ExceptionHandler({EmployeeException.class})
    public ResponseEntity<String> handleAlreadyExistException(EmployeeException employeeException) {
        return new ResponseEntity<>(employeeException.getMessage(), HttpStatus.ALREADY_REPORTED);
    }

    /**
     * This method will handle no such element found by giving id
     * @param noSuchElementException -
     * @return noSuchElementException message with http status
     */
    @ExceptionHandler({NoSuchElementException.class})
    public  ResponseEntity<String> handleNoSuchElementException(NoSuchElementException noSuchElementException) {
        return new ResponseEntity<>(noSuchElementException.getMessage(), HttpStatus.NOT_FOUND);
    }
}
