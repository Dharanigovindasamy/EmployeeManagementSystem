package com.ideas2it.ems.certificate.dao;

import com.ideas2it.ems.model.Certificate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* <p>This class used for performing operations like adding, displaying
* and updating in crudRepository itself
* This class used to manage certificate objects.
* Also here, connected with database and execute table operations </p>
*
* @author Dharani G
*/
@Repository
public interface CertificateDao extends CrudRepository<Certificate, Integer> {
    List<Certificate> findByIsRemovedFalse();
    Certificate findByCertificateIdAndIsRemovedFalse(int certificateId);
}
