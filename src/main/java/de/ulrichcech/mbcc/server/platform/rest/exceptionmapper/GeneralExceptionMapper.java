/*
 * Copyright (c) 2024 Ulrich Cech - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Ulrich Cech <ulrich@ulrichcech.de>, 2024
 */
package de.ulrichcech.mbcc.server.platform.rest.exceptionmapper;

import de.ulrichcech.mbcc.server.platform.localization.LocaleThreadLocal;
import de.ulrichcech.mbcc.server.platform.rest.APIException;
import de.ulrichcech.mbcc.server.platform.rest.ApiErrorStructure;
import de.ulrichcech.mbcc.server.platform.rest.GlobalApiErrorCode;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.logging.Level;
import java.util.logging.Logger;

import static de.ulrichcech.mbcc.server.platform.rest.GlobalApiErrorCode.E000_GENERAL_NOTFOUND_ERROR;
import static de.ulrichcech.mbcc.server.platform.rest.GlobalApiErrorCode.E000_GENERAL_SERVER_ERROR;


@Provider
public class GeneralExceptionMapper implements ExceptionMapper<Exception> {

    private static final Logger LOG = Logger.getLogger(GeneralExceptionMapper.class.getName());

    @Override
    public Response toResponse(Exception exception) {
        Response apiResponse = findApiExceptionInHierarchy(exception, 0);
        if (apiResponse != null) {
            return apiResponse;
        }
        GlobalApiErrorCode apiErrorCode = E000_GENERAL_SERVER_ERROR;
        if (exception instanceof WebApplicationException) {
            if (exception instanceof NotFoundException){
                apiErrorCode = E000_GENERAL_NOTFOUND_ERROR;
            } else {
                final int status = ((WebApplicationException) exception).getResponse().getStatus();
                if (Response.Status.INTERNAL_SERVER_ERROR.getStatusCode() != status) {
                    var apiErrorStructure = new ApiErrorStructure();
                    apiErrorStructure.createGenericError("MBCC-Y-000", exception.getLocalizedMessage());
                    final String msg = apiErrorStructure.toJson().toString();
                    LOG.log(Level.WARNING, msg, exception);
                    return Response.status(status).entity(msg).build();
                }
            }
        }
        LOG.log(Level.SEVERE, "MBCC-EXCEPTION", exception);
        return Response.status(apiErrorCode.getResponseStatus()).entity(
                apiErrorCode.toJson(LocaleThreadLocal.get(), exception.getLocalizedMessage()).toString())
                .build();
    }

    Response findApiExceptionInHierarchy(Throwable ex, int level) {
        if (level >= 10 || ex == null) {
            return null;
        }
        if (ex instanceof APIException) {
            return new JAXRSAPIExceptionMapper().toResponse((APIException) ex);
        } else {
            return findApiExceptionInHierarchy(ex.getCause(), ++level);
        }
    }


}