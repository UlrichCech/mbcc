/*
 * Copyright (c) 2024 Ulrich Cech - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Ulrich Cech <ulrich@ulrichcech.de>, 2024
 */
package de.ulrichcech.mbcc.server.platform.rest;


import de.ulrichcech.mbcc.server.platform.localization.LocaleThreadLocal;
import jakarta.ejb.ApplicationException;

import java.io.Serial;
import java.util.Arrays;
import java.util.Locale;

/**
 * Wrapper-Exception which has to be used to wrap error situations in the API-domain.
 *
 * @author Ulrich Cech
 */
@ApplicationException(rollback = true)
public class APIException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 4512441841896536352L;

    private final GlobalApiErrorCode apiErrorCode;
    private final Locale locale;
    private final transient Object[] args; // in this case it is not important to have the args-field de-/serialized

    public APIException(GlobalApiErrorCode apiErrorCode, Locale requestLocale, Object... args) {
        super(apiErrorCode.getLocalizedMessage(requestLocale, args));
        this.apiErrorCode = apiErrorCode;
        this.locale = requestLocale;
        this.args = args;
    }

    public APIException(GlobalApiErrorCode apiErrorCode, Object... args) {
        this(apiErrorCode, LocaleThreadLocal.get(), args);
    }


    public GlobalApiErrorCode getApiErrorCode() {
        return apiErrorCode;
    }

    public Locale getLocale() {
        return locale;
    }

    public Object[] getArgs() {
        return Arrays.copyOf(args, args.length);
    }
}
