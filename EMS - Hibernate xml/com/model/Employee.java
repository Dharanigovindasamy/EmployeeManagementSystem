package com.model;

import java.util.Set;
import java.time.LocalDate;

import com.model.Certificate;
import com.model.Department;
import com.util.Validator;

/**
* This class consists of employee records entity model
*
* <p> Represents the employee object that contains details like id, namen dob,
* contact number, mail id, experience , department which contains id & name,
* salary, city, age & certificates contains id and name 
* This is the model of the employee data
* Created constructor and getter ,setter method 
* toString method -> give format of output to the end user</p>
* 
* @author DHARANI G
* @ version 1.0
*/
public class Employee {
    private int employeeId;
    private String employeeName;
    private LocalDate employeeDOB;
    private long contactNumber;
    private String mailId;
    private int experience;
    private Department department;
    private double salary;
    private String city;
    private boolean isRemoved = false;
    private Set<Certificate> certificates;

    public Employee(String employeeName, LocalDate employeeDOB,
             long contactNumber, String mailId, int experience,
             double salary, String city, Department department) {
        this.employeeId = employeeId;
        this.employeeDOB = employeeDOB;
        this.employeeName = employeeName;
        this.contactNumber = contactNumber;
        this.mailId = mailId;
        this.experience = experience;
        this.salary = salary;
        this.city = city;
        this.isRemoved = isRemoved;
        this.department = department;
        this.certificates = certificates;
    }

    public Employee() {}
  
    public int getEmployeeId() {
        return employeeId;
    }
  
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
  
    public String getEmployeeName() {
        return employeeName;
    }
  
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public LocalDate getEmployeeDOB() {
        return employeeDOB;
     }
  
    public void setEmployeeDOB(LocalDate employeeDOB) {
        this.employeeDOB = employeeDOB;
    }

    public long getContactNumber() {
        return contactNumber;
    }
  
    public void setContactNumber(long contactNumber) {
        this.contactNumber = contactNumber;
    }
 
    public String getMailId() {
        return mailId;
    }

    public void setMailId(String mailId) {
        this.mailId = mailId;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience= experience;
    }
    
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getAge() {
        return Validator.calculateAge(employeeDOB);
    }   

    public boolean getIsRemoved() {
        return isRemoved;
    }

    public void setIsRemoved(boolean isRemoved) {
        this.isRemoved = isRemoved;
    }

    public Set<Certificate> getCertificates() {
        return certificates;
    }

    public void setCertificates(Set<Certificate> certificates) {
        this.certificates = certificates;
    }

    public String toString() {
        return " Employee id = " + employeeId 
                + "\n Employee Name = '" + employeeName 
                + '\'' + ",\n Employee age = '" + getAge()
                + ",\n Contact Number = " + contactNumber 
                + ",\n Mail Id = '" + mailId + '\'' 
                +",\n Experience = " + experience 
                +", \n Department = " + department 
                + ",\n Salary = " + salary + 
                ",\n City = '" + city + '\''
                + "Certificates : = " + getCertificates();
    }

    /**
    * Soft delete
    */ 	
    public void isSetRemoved() {
        this.isRemoved = false;    
    }       
}


