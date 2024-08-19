package com.ideas2it.ems.dao;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ideas2it.ems.model.Department;

/**
* <p>This class used for performing department operations like adding, displaying
* and updating in crudRepository
* This class used to manage department objects.
* Also here, connected with database and execute table operations </p>
*
* @author Dharani G
*/
@Repository
public interface DepartmentDao extends CrudRepository<Department, Integer> {
    Set<Department> findByIsRemovedFalse();
    Department findByDepartmentIdAndIsRemovedFalse(int departmentId);
}
