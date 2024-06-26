/*
 * Copyright (c) 2024 Ulrich Cech - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Ulrich Cech <ulrich@ulrichcech.de>, 2024
 */
package de.ulrichcech.mbcc.server.platform;

import jakarta.ejb.ApplicationException;

import java.io.Serial;

/**
 *
 *
 * @author Ulrich Cech
 */
@ApplicationException
public class PlatformException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 6897463919940826522L;

    public PlatformException(Exception causedException) {
        super(causedException);
    }

    public PlatformException(String message) {
        super(message);
    }

}
