package com.model;

import java.util.Set;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;

import com.model.BankDetail;
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

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int employeeId;

    @Column(name = "name")
    private String employeeName;

    @Column(name = "employee_dob")
    private LocalDate employeeDOB;

    @Column(name = "contact_number")
    private long contactNumber;

    @Column(name = "mail_id")
    private String mailId;

    @Column(name = "experience")
    private int experience;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(name = "salary")
    private double salary;
  
    @Column(name = "city")
    private String city;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "accountId")
    private BankDetail bankDetail;

    @Column(name = "is_removed")
    private boolean isRemoved;

    @ManyToMany(fetch = FetchType.EAGER , cascade = {CascadeType.ALL})
    @JoinTable(name = "employee_certificate",
        joinColumns = {@JoinColumn(name = "employee_id")},
        inverseJoinColumns = {@JoinColumn(name = "certificate_id")})
    private Set<Certificate> certificates;

    public Employee(String employeeName, LocalDate employeeDOB,
             long contactNumber, String mailId, int experience,
             double salary, String city, Department department, BankDetail bankDetail) {
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
        this.bankDetail = bankDetail;
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

    public BankDetail getBankDetail() {
        return bankDetail;
    }

    public void setBankDetail(BankDetail bankDetail) {
        this.bankDetail = bankDetail;
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
                + ",\n Salary = " + salary 
                + ",\n City = '" + city + '\''
                + ",\n Certificates : = " + getCertificates() 
                + "\n Bank Details : = " + getBankDetail();
    }     
}


