package com.ideas2it.ems.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.ideas2it.ems.model.Employee;

/**
* This class contains certificate entity ie. certificate id, certifcate name and employees
* who are done that certificate
* This is th model class of certificate 
* Represents the certificates object contains id and name and list of employees
*
* Created constructor and getter ,setter method 
* toString method -> give format of output to the end user
* 
* @author DHARANI G
* 
*/

@Entity
@Table (name = "certificate")
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "certificate_id")
    private int certificateId;

    @Column(name = "certificate_name")
    private String certificateName;

    @Column(name = "is_removed")
    private boolean isRemoved = false;

    @ManyToMany(mappedBy = "certificates", fetch = FetchType.EAGER , cascade = {CascadeType.ALL})
    private Set<Employee> employees;
 
    public Certificate(String certificateName) {
        this.certificateName = certificateName;
    }

    public Certificate(int certificateId, String certificateName) {
        this.certificateId = certificateId;
        this.certificateName = certificateName;
        this.isRemoved = isRemoved;
    }

    public Certificate() {}

    public int getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(int certificateId) {
        this.certificateId = certificateId;
    }

    public String getCertificateName() {
        return certificateName;
    }

    public void setCertificateName(String certificateName) {
        this.certificateName = certificateName;
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
        return "Certificates :" + 
               " Certificate Id= " + certificateId +
               " certificate Name= " + certificateName;
              
    } 
}
