package com.ideas2it.ems.service;

import java.util.Set;

import com.ideas2it.ems.model.Certificate;
import org.springframework.stereotype.Service;

/**
 * <p>
 *     Service class for managing certificate-related operations.
 * </p>
 * @author dharani.govindhasamy
 */
@Service
public interface CertificateService {

    /**
     * <p>Add a certificate
     *
     * @ param certificate ->  certificate added to the user
     * @return certificate - added certificates return to the user
     *</p>
     */
    public Certificate addCertificate(Certificate certificate) ;

    /**
    * <p>
     *  Get all certificates of certificate
     * @return list of certificates
    * </p>
    *
    */
    public Set<Certificate> getAllCertificates();

    /**
     * <p>
     * Get a certificate by giving certificate ID
     *
     * @param certificateId - certificate Id of the certificate
     * @return certificate object
     *</p>
     */
    public Certificate getCertificateById(int certificateId) ;


}