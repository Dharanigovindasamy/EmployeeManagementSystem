package com.ideas2it.ems.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ideas2it.ems.dto.CertificateDto;
import com.ideas2it.ems.dto.EmployeeDto;
import com.ideas2it.ems.model.Certificate;
import com.ideas2it.ems.model.Employee;
import com.ideas2it.ems.service.CertificateService;

@ExtendWith(MockitoExtension.class)
class CertificateControllerTest {

    @InjectMocks
    private CertificateController certificateController;

    @Mock
    private CertificateService certificateService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddCertificate() {
        CertificateDto certificateDto = new CertificateDto();
        certificateDto.setCertificateName("php master");
        Certificate certificate = new Certificate();
      //  certificate.setCertificateName("php master");
        when(certificateService.addCertificate(any(Certificate.class))).thenReturn(certificate);

        ResponseEntity<CertificateDto> response = certificateController.addCertificate(certificateDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(certificate.getCertificateName(), response.getBody().getCertificateName());
        verify(certificateService, times(1)).addCertificate(any(Certificate.class));
    }

    @Test
    void testDisplayCertificates() {
        Set<Certificate> certificates = new HashSet<>();
        certificates.add(new Certificate());

        when(certificateService.getAllCertificates()).thenReturn(certificates);

        ResponseEntity<Set<CertificateDto>> response = certificateController.displayCertificates();

        assertEquals(HttpStatus.OK, response.getStatusCode());
       // assertEquals(certificates, response.getBody());
        assertFalse(response.getBody().isEmpty());
        verify(certificateService, times(1)).getAllCertificates();
    }

    @Test
    void testDisplayCertificates_Empty() {
        when(certificateService.getAllCertificates()).thenReturn(new HashSet<>());

        ResponseEntity<Set<CertificateDto>> response = certificateController.displayCertificates();

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(certificateService, times(1)).getAllCertificates();
    }

    @Test
    void testDisplayCertificateById() {
        int certificateId = 1;
        Certificate certificate = new Certificate();

        when(certificateService.getCertificateById(certificateId)).thenReturn(certificate);

        ResponseEntity<CertificateDto> response = certificateController.displayCertificateById(certificateId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(certificate.getCertificateId(), response.getBody().getCertificateId());
        verify(certificateService, times(1)).getCertificateById(certificateId);
    }

    @Test
    void testDisplayCertificateById_NotFound() {
        int certificateId = 1;

        when(certificateService.getCertificateById(certificateId)).thenReturn(null);

        NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> certificateController.displayCertificateById(certificateId));

        assertEquals("Can't display. certificate not found {}" + certificateId, thrown.getMessage());
        verify(certificateService, times(1)).getCertificateById(certificateId);
    }

    @Test
    void testUpdatedCertificate() {
        int certificateId = 1;
        CertificateDto certificateDto = new CertificateDto();
        Certificate certificate = new Certificate();

        when(certificateService.getCertificateById(certificateId)).thenReturn(certificate);
        when(certificateService.addCertificate(any(Certificate.class))).thenReturn(certificate);

        ResponseEntity<CertificateDto> response = certificateController.updatedCertificate(certificateId, certificateDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(certificateService, times(1)).getCertificateById(certificateId);
        verify(certificateService, times(1)).addCertificate(any(Certificate.class));
    }

    @Test
    void testUpdatedCertificate_NotFound() {
        int certificateId = 1;
        CertificateDto certificateDto = new CertificateDto();

        when(certificateService.getCertificateById(certificateId)).thenReturn(null);

        NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> certificateController.updatedCertificate(certificateId, certificateDto));

        assertEquals("Can't update. Certificate not found {} " + certificateId, thrown.getMessage());
        verify(certificateService, times(1)).getCertificateById(certificateId);
    }

    @Test
    void testDeletedCertificate() {
        int certificateId = 1;
        Certificate certificate = new Certificate();

        when(certificateService.getCertificateById(certificateId)).thenReturn(certificate);

        ResponseEntity<CertificateDto> response = certificateController.deletedCertificate(certificateId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(certificateService, times(1)).getCertificateById(certificateId);
        verify(certificateService, times(1)).addCertificate(any(Certificate.class));
    }

    @Test
    void testDeletedCertificate_NotFound() {
        int certificateId = 1;

        when(certificateService.getCertificateById(certificateId)).thenReturn(null);

        NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> certificateController.deletedCertificate(certificateId));

        assertEquals("Cant delete. Certificate not found" + certificateId, thrown.getMessage());
        verify(certificateService, times(1)).getCertificateById(certificateId);
    }

    @Test
    void testGetEmployeesByCertificateId() {
        int certificateId = 1;
        Set<Employee> employees = new HashSet<>();
        employees.add(new Employee());

        Certificate certificate = new Certificate();
        certificate.setEmployees(employees);

        when(certificateService.getCertificateById(certificateId)).thenReturn(certificate);

        ResponseEntity<Set<EmployeeDto>> response = certificateController.getEmployeesByCertificateId(certificateId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(response.getBody().isEmpty());
        verify(certificateService, times(1)).getCertificateById(certificateId);
    }

    @Test
    void testGetEmployeesByCertificateId_NotFound() {
        int certificateId = 1;

        when(certificateService.getCertificateById(certificateId)).thenReturn(null);

        NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> certificateController.getEmployeesByCertificateId(certificateId));

        assertEquals("Can't get employees by certificate Id.Certificates not found" + certificateId, thrown.getMessage());
        verify(certificateService, times(1)).getCertificateById(certificateId);
    }
}
