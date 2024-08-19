package com.ideas2it.ems.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

/**
 * <p>
 *     This is the certificate details how to add the data into the table and
 *     display the details by this paramaters
 *     contains getter, setter, constructors and validates the user input
 * </p>
 * @author dharani.govindhasamy
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CertificateDto {
    private int certificateId;
    @NotBlank(message = "Certificate name  is mandatory")
    @Pattern(regexp = "^[A-Za-z]$", message = "Enter valid certificate name")
    @Column(unique = true)
    private String certificateName;
}
