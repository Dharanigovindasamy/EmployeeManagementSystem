package com.ideas2it.ems.mapper;

import com.ideas2it.ems.dto.CertificateDto;
import com.ideas2it.ems.model.Certificate;

/**
 * <p>
 * This class used for convert certificate Dto to certificate entity and vice versa
 * </p>
 * @author dharani.govindhasamy
 */
public class CertificateMapper {

    /**
     * <p>
     *   Conversion of Certificate Dto To Entity
     *
     * @param certificateDto - {@link CertificateDto}
     * @return Certificate - set CertificateDto data to Certificate
     * </p>
     */
    public static Certificate convertDtoToEntity(CertificateDto certificateDto) {
        Certificate certificate = new Certificate();
        certificate.setCertificateId(certificateDto.getCertificateId());
        certificate.setCertificateName(certificateDto.getCertificateName());
        return certificate;
    }

    /**
     * <p>
     *    conversion of certificate Entity To Dto
     * @param certificate - {@link Certificate}
     * @return certificateDto - set certificate entity to dto
     * </p>
     */
    public static CertificateDto convertEntityToDto(Certificate certificate) {
        CertificateDto certificateDto = new CertificateDto();
        certificateDto.setCertificateId(certificate.getCertificateId());
        certificateDto.setCertificateName(certificate.getCertificateName());
        return certificateDto;
    }
}
