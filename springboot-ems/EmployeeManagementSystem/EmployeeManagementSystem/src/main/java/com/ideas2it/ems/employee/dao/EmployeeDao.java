package com.ideas2it.ems.employee.dao;

import com.ideas2it.ems.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
* <p>This class used for performing operations like adding, displaying
* and updating in crudRepository itself
* This class used to manage Employee objects.
* Also here, connected with database and execute table operations </p>
*
* @author Dharani G
*/
@Repository
public interface EmployeeDao extends CrudRepository<Employee, Integer> {
    Set<Employee> findByIsRemovedFalse();
    Employee findByEmployeeIdAndIsRemovedFalse(int employeeId);

}
