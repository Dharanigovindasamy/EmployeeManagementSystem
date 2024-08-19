package com.ideas2it.ems.service;

import java.util.Set;

import com.ideas2it.ems.dao.CertificateDao;
import com.ideas2it.ems.model.Certificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>Service class for managing certificates-related operations.</p>
 *
 * @author Dharani G
 */

@Service
public class CertificateServiceImpl implements CertificateService {
    @Autowired
    private CertificateDao certificateDao ;

    public CertificateServiceImpl(CertificateDao certificateDao) {
        this.certificateDao = certificateDao;
    }

    @Override
    public Certificate addCertificate(Certificate certificate)  {
        return certificateDao.save(certificate);
    }

    @Override
    public Set<Certificate> getAllCertificates() {
        Set<Certificate> certificates = certificateDao.findByIsRemovedFalse();
        return certificates;
    }

    @Override 
    public Certificate getCertificateById(int certificateId)  {
       return certificateDao.findByCertificateIdAndIsRemovedFalse(certificateId);
    }



}

