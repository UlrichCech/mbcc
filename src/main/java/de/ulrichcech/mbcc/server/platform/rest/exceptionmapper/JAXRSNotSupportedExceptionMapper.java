package de.ulrichcech.mbcc.server.platform.rest.exceptionmapper;

import de.ulrichcech.mbcc.server.platform.rest.GlobalApiErrorCode;
import jakarta.ws.rs.NotSupportedException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
/*
 * Copyright (c) 2024 Ulrich Cech - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Ulrich Cech <ulrich@ulrichcech.de>, 2024
 */
import jakarta.ws.rs.ext.Provider;

import java.util.Locale;


/**
 * Catches the NotSupportedException and converts it into a default JSON error structure.
 *
 * @author Ulrich Cech
 */
@Provider
public class JAXRSNotSupportedExceptionMapper implements ExceptionMapper<NotSupportedException> {

    @Override
    public Response toResponse(NotSupportedException exception) {
        GlobalApiErrorCode errorCode = GlobalApiErrorCode.E415_NOT_SUPPORTED;
        return Response.status(errorCode.getResponseStatus())
                .entity(errorCode.toJson(Locale.ENGLISH).toString())
                .build();
    }
}
