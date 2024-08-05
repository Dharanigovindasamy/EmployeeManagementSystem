package com.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.model.Employee;

/** 
* This class consists of department entity as Object
* Entitites which contains in Department object
* are defined as private specifier for secure 
*
* Represents the department which contains id & name, 
*
* Created constructor and getter ,setter method 
* toString method -> give format of output to the end user
* 
* @author DHARANI G
* @ version 1.0
*/

@Entity
@Table(name = "department")
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
    private Set<Employee> employees;

    public Department(int departmentId, String departmentName) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.employees = employees;	
    }
    
    public Department(String departmentName) {
        this.departmentName = departmentName;
    }
  
    public Department() {}
                  	
    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }
  
    public String getDepartmentName() {
        return departmentName;   
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }
  
    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }  

    public boolean getIsRemoved() {
        return isRemoved;
    }

    public void setIsRemoved(boolean isRemoved) {
        this.isRemoved = isRemoved;
    }

    public String toString() {
        return "Departments :\n department Id = " + departmentId +
               "\n department Name = " + departmentName;   
    }
}