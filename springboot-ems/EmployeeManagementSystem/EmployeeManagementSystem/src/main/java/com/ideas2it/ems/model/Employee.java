package com.ideas2it.ems.model;

import java.util.Set;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ideas2it.ems.util.Validator;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "employees")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    @JsonBackReference
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
    @JoinTable(name = "employee_certificates",
            joinColumns = {@JoinColumn(name = "employee_id")},
            inverseJoinColumns = {@JoinColumn(name = "certificate_id")})
    private Set<Certificate> certificates;


    public String getAge() {
        return Validator.calculateAge(employeeDOB);
    }
}
