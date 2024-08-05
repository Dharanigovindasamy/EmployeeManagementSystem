package com.util;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.*;
import java.text.ParseException;
import java.text.SimpleDateFormat; 
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException; 

/**
* This class is used for validation 
* validates the employee entity
*/
public class Validator {

    /**
    * Method that validates the alphabets only in string
    * 
    */
    public static boolean isValidAlphabet(String employeeName) {
        for (char c : employeeName.toCharArray()) {
            if (!(Character.isLetter(c))) {
                return false; 
            }
        }
        return true;
    }

    /**
    * The method that validates the date of birth and calculate the age 
    */
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static LocalDate parseDate(String dateString) throws IllegalArgumentException {
        try {
            return LocalDate.parse(dateString, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use yyyy-MM-dd.");
        }
    }

    public static int calculateAge(LocalDate dob) {
        return Period.between(dob, LocalDate.now()).getYears();
    }

    
    public static java.sql.Date toSqlDate(LocalDate localDate) {
        return java.sql.Date.valueOf(localDate);
    }
 
    /**
    * Validates contact number accepts only 10 digits and the number starts with 6 or 7 or 8 or  9
    *
    */
    public static boolean isValidContact(String contactNumberString) {  
        Pattern ptrn = Pattern.compile("(0/91)?[6-9][0-9]{9}");  
        Matcher match = ptrn.matcher(contactNumberString);  
        return (match.find() && match.group().equals(contactNumberString));  
    } 

    /**
    * Validate mail id with regrex pattern, if matches the pattern returns as true or false
    */
    public static boolean isValidMail(String emailId) {
        String regex = "^[a-z0-9_+&*]+@(.+)$"; 
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(emailId);
        return matcher.matches(); 
    }
}