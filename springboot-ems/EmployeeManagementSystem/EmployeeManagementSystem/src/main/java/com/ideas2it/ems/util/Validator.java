package com.ideas2it.ems.util;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* <p>
 * This class used for validate alphabets, mail id, date of birth
 * and calculate age, contact number
* </p>
 * @author dharani.govindhasamy
*/
public class Validator {

    /**
    * <p>
     *     Method that validates the alphabets only in string
     * @param employeeName {@link com.ideas2it.ems.model.Employee}
     * @return boolean true - is character true,
     * false - is character false
     * </p>
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
    * <p>
     *     The method that validates the date of birth and calculate the age
     * @param dateString
     * if error occurs IllegalArgumentException
     * @return LocalDate
    * </p>
    */
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static LocalDate parseDate(String dateString) throws IllegalArgumentException {
        try {
            return LocalDate.parse(dateString, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use yyyy-MM-dd.");
        }
    }

    public static String calculateAge(LocalDate dob) {
        return Period.between(dob, LocalDate.now()).getYears() + " y " + Period.between(dob, LocalDate.now()).getMonths() + " M ";
    }

 
    /**
    * <p>
     * Validates contact number accepts only 10 digits and the number starts with 6 or 7 or 8 or  9
    * @param contactNumberString -
     * @return if matches pattern, returns true, otherwise false
     * </p>
    */
    public static boolean isValidContact(String contactNumberString) {  
        Pattern pattern = Pattern.compile("(0/91)?[6-9][0-9]{9}");
        Matcher match = pattern.matcher(contactNumberString);
        return (match.find() && match.group().equals(contactNumberString));  
    } 

    /**
    * <p>
     * Validate mail id with regex pattern, if matches the pattern returns as true or false
     * @return emailId - emailID
     * @return  boolean true, if matches pattern, othrwise false
    * </p>
    */
    public static boolean isValidMail(String emailId) {
        String regex = "^[a-z0-9_+&*]+@(.+)$"; 
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(emailId);
        return matcher.matches(); 
    }
}