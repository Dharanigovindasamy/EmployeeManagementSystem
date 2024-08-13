package com.ideas2it.ems.model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>
 *     This class contains certificates of the employee ie..certificate id and name.
 *     getter and setter and constructors .
 *     This can create table in the server with column name
 * </p>
 *
 * @author dharani.govindhasamy
 *
 */
@Entity
@Table(name = "certificates")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int certificateId;

    @Column(name = "certificate_name")
    private String certificateName;

    @Column(name = "is_removed")
    private boolean isRemoved;

    @ManyToMany(mappedBy = "certificates", fetch = FetchType.EAGER , cascade = {CascadeType.ALL})
    private Set<Employee> employees;
}