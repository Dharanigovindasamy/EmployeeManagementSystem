package com.model;

import java.util.Set;

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

public class Department {
    private int departmentId;
    private String departmentName;
    private boolean isRemoved = false;
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