package com.ideas2it.ems.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** 
* <p>This class consists of department entity as Object
 * Entitites which contains in Department object
 * are defined as private specifier for secure
 *  Represents the department which contains id & name,
 *  Created constructor and getter ,setter method
 *  This class can create the database table by column name </p>
* 
* @author DHARANI G
*/

@Entity
@Table(name = "departments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int departmentId;

    @Column(name = "department_name")
    private String departmentName;

    @Column(name = "is_removed")
    private boolean isRemoved = false;

    @OneToMany(mappedBy = "department", fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Employee> employees;


    public Department(String departmentName) {
        this.departmentName = departmentName;
    }
}