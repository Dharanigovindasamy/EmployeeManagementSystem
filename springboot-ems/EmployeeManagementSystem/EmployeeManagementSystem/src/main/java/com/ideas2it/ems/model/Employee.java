package com.ideas2it.ems.model;

import java.util.Set;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ideas2it.ems.util.Validator;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.UniqueElements;

/**
 * <p>
 *     This is the employee class that holds the data
 *  which can validate user data through annotations and store the data in the table
 * </p>
 *
 * @author dharani.govindhasamy
 */
@Entity
@Table(name = "employees")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int employeeId;

    @Column(name = "name", nullable = false, unique = true)
    @NotBlank
    private String employeeName;

    @Column(name = "employee_dob", nullable = false)
    private LocalDate employeeDOB;

    @Column(name = "contact_number",  nullable = false)
    private long contactNumber;

    @Column(name = "mail_id",  nullable = false, unique = true)
    private String mailId;

    @Column(name = "experience", nullable = false)
    private int experience;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id", nullable = false)
    @JsonBackReference
    private Department department;

    @Column(name = "salary", nullable = false)
    private double salary;

    @Column(name = "city", nullable = false)
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
