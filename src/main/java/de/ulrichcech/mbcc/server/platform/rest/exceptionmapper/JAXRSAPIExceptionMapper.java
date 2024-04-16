/*
 * Copyright (c) 2024 Ulrich Cech - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Ulrich Cech <ulrich@ulrichcech.de>, 2024
 */
package de.ulrichcech.mbcc.server.platform.rest.exceptionmapper;

import de.ulrichcech.mbcc.server.platform.rest.APIException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;


/**
 * Catches the APIException and converts it into a default JSON error structure.
 *
 * @author Ulrich Cech
 */
@Provider
public class JAXRSAPIExceptionMapper implements ExceptionMapper<APIException> {

    private static final Logger LOG = getLogger(JAXRSAPIExceptionMapper.class.getName());


    @Override
    public Response toResponse(APIException exception) {
        final String entity = exception.getApiErrorCode().toJson(exception.getLocale(), exception.getArgs()).toString();
        LOG.warning("MBCC-EXCEPTION: " + entity);
        return Response.status(exception.getApiErrorCode().getResponseStatus())
                .entity(entity)
                .build();
    }
}
