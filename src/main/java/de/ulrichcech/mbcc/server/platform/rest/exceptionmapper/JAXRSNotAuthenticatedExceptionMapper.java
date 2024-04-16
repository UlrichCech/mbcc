/*
 * Copyright (c) 2024 Ulrich Cech - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Ulrich Cech <ulrich@ulrichcech.de>, 2024
 */
package de.ulrichcech.mbcc.server.platform.rest.exceptionmapper;

import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;


/**
 * Catches the NotAuthorizedException and converts it into a default JSON error structure.
 *
 * @author Ulrich Cech
 */
@Provider
public class JAXRSNotAuthenticatedExceptionMapper implements ExceptionMapper<NotAuthorizedException> {

    @Override
    public Response toResponse(NotAuthorizedException exception) {
        return Response.status(exception.getResponse().getStatus())
                .entity(exception.getLocalizedMessage())
                .build();
    }
}
