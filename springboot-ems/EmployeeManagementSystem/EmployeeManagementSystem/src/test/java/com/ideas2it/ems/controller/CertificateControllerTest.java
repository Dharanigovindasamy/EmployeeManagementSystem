package com.ideas2it.ems.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import com.ideas2it.ems.model.BankDetail;
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

    CertificateDto certificateDto = new CertificateDto();
    Certificate certificate = new Certificate();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        certificateDto.setCertificateId(1);
        certificateDto.setCertificateName("php master");
        certificate.setCertificateId(1);
        certificate.setCertificateName("php master");
        certificate = Certificate.builder().certificateId(1)
                            .certificateName("Master in php")
                            .employees(Set.of(Employee.builder().employeeId(1).employeeName("kavi").employeeDOB(LocalDate.of(2000,11,11))
                                .contactNumber(9876543212L)
                                .mailId("kavi@gmail.com")
                                .experience(3)
                                .salary(54000)
                                .city("erode")
                                .bankDetail(BankDetail.builder().accountId(3)
                                            .accountNumber(8765432l)
                                            .branch("sathy")
                                            .build())
                            .build()))
                        .build();

    }

    @Test
    void testAddCertificate() {
        when(certificateService.addCertificate(any(Certificate.class))).thenReturn(certificate);
        ResponseEntity<CertificateDto> response = certificateController.addCertificate(certificateDto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(certificate.getCertificateName(), response.getBody().getCertificateName());
    }

    @Test
    void testDisplayCertificates() {
        Set<Certificate> certificates = new HashSet<>();
        certificates.add(new Certificate());
        when(certificateService.getAllCertificates()).thenReturn(certificates);
        ResponseEntity<Set<CertificateDto>> response = certificateController.displayCertificates();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(response.getBody().isEmpty());
    }

    @Test
    void testDisplayCertificatesEmpty() {
        when(certificateService.getAllCertificates()).thenReturn(new HashSet<>());
        ResponseEntity<Set<CertificateDto>> response = certificateController.displayCertificates();
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testDisplayCertificateById() {
        when(certificateService.getCertificateById(certificateDto.getCertificateId())).thenReturn(certificate);
        ResponseEntity<CertificateDto> response = certificateController.displayCertificateById(certificateDto.getCertificateId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(certificate.getCertificateId(), response.getBody().getCertificateId());
    }

    @Test
    void testDisplayCertificateByIdNotFound() {
        when(certificateService.getCertificateById(certificateDto.getCertificateId())).thenReturn(null);
        assertThrows(NoSuchElementException.class, () -> certificateController.displayCertificateById(certificateDto.getCertificateId()));
    }

    @Test
    void testUpdatedCertificate() {
        when(certificateService.getCertificateById(certificateDto.getCertificateId())).thenReturn(certificate);
        when(certificateService.addCertificate(any(Certificate.class))).thenReturn(certificate);
        ResponseEntity<CertificateDto> response = certificateController.updatedCertificate(certificateDto.getCertificateId(), certificateDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testUpdatedCertificateNotFound() {
        when(certificateService.getCertificateById(certificateDto.getCertificateId())).thenReturn(null);
        NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> certificateController.updatedCertificate(certificateDto.getCertificateId(), certificateDto));
        assertEquals("Can't update. Certificate not found {} " + certificateDto.getCertificateId(), thrown.getMessage());
    }

    @Test
    void testDeletedCertificate() {
        when(certificateService.getCertificateById(certificateDto.getCertificateId())).thenReturn(null);
        assertThrows(NoSuchElementException.class, () -> certificateController.deletedCertificate(certificate.getCertificateId()));
    }

    @Test
    void testDeletedCertificateNotFound() {
        when(certificateService.getCertificateById(certificateDto.getCertificateId())).thenReturn(null);
        NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> certificateController.deletedCertificate(certificateDto.getCertificateId()));
        assertEquals("Cant delete. Certificate not found" + certificateDto.getCertificateId(), thrown.getMessage());
        verify(certificateService, times(1)).getCertificateById(certificateDto.getCertificateId());
    }

    @Test
    void testGetEmployeesByCertificateId() {
        Set<Employee> employees = new HashSet<>();
        employees.add(new Employee());
        certificate.setEmployees(employees);
        when(certificateService.getCertificateById(certificateDto.getCertificateId())).thenReturn(certificate);
        ResponseEntity<Set<EmployeeDto>> response = certificateController.getEmployeesByCertificateId(certificateDto.getCertificateId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testGetEmployeesByCertificateId_NotFound() {
        when(certificateService.getCertificateById(certificateDto.getCertificateId())).thenReturn(null);
        assertThrows(NoSuchElementException.class, () -> certificateController.getEmployeesByCertificateId(certificateDto.getCertificateId()));
    }
}
