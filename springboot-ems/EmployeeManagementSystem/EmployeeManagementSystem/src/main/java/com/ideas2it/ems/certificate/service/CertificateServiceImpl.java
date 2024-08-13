package com.ideas2it.ems.certificate.service;

import com.ideas2it.ems.certificate.dao.CertificateDao;
import com.ideas2it.ems.model.Certificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Certificate> getAllCertificates() {
        List<Certificate> certificates = (List<Certificate>) certificateDao.findByIsRemovedFalse();
        return certificates;
    }

    @Override 
    public Certificate getCertificateById(int certificateId)  {
       return certificateDao.findByCertificateIdAndIsRemovedFalse(certificateId);
    }



}

