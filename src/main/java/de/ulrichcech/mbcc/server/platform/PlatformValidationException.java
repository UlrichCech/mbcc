/*
 * Copyright (c) 2024 Ulrich Cech - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Ulrich Cech <ulrich@ulrichcech.de>, 2024
 */
package de.ulrichcech.mbcc.server.platform;

import jakarta.ejb.ApplicationException;
import jakarta.validation.ValidationException;

import java.io.Serial;

/**
 * Base exception for handling Bean-Validation exceptions. Throwing these exceptions always
 * rollback the current transaction.
 *
 * @author Ulrich Cech
 */
@ApplicationException(rollback = true)
public class PlatformValidationException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 2127671309657835906L;


    private final ValidationException validationException;

    public PlatformValidationException(ValidationException validationException) {
        this.validationException = validationException;
    }

    public ValidationException getValidationException() {
        return validationException;
    }
}
