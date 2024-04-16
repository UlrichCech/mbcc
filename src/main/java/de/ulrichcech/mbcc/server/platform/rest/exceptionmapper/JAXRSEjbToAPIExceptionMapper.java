/*
 * Copyright (c) 2024 Ulrich Cech - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Ulrich Cech <ulrich@ulrichcech.de>, 2024
 */
package de.ulrichcech.mbcc.server.platform.rest.exceptionmapper;

import de.ulrichcech.mbcc.server.platform.localization.LocaleThreadLocal;
import de.ulrichcech.mbcc.server.platform.rest.APIException;
import de.ulrichcech.mbcc.server.platform.rest.GlobalApiErrorCode;
import jakarta.ejb.EJBException;
import jakarta.ejb.TransactionRolledbackLocalException;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Catches the EJBException and converts it into a default JSON error structure.
 *
 * @author Ulrich Cech
 */
@Provider
public class JAXRSEjbToAPIExceptionMapper implements ExceptionMapper<EJBException> {

    private static final Logger LOG = Logger.getLogger(JAXRSEjbToAPIExceptionMapper.class.getName());

    @Override
    public Response toResponse(EJBException exception) {
        LOG.log(Level.SEVERE, "", exception);
        if (exception.getCause() instanceof APIException apiException) {
            final String entity = apiException.getApiErrorCode().toJson(apiException.getLocale(), apiException.getArgs()).toString();
            LOG.warning("MBCC-EXCEPTION: " + entity);
            return Response.status(apiException.getApiErrorCode().getResponseStatus())
                    .entity(entity)
                    .build();
        } else if (exception.getCause() instanceof ConstraintViolationException) {
            return new ValidationExceptionMapper().toResponse((ValidationException) exception.getCause());
        } else if (exception.getCause() instanceof TransactionRolledbackLocalException) {
            if (exception.getCause().getCause() instanceof ValidationException) {
                return new ValidationExceptionMapper().toResponse((ValidationException) exception.getCause().getCause());
            } else {
                return Response.serverError().entity(
                        GlobalApiErrorCode.E000_GENERAL_SERVER_ERROR.toJson(LocaleThreadLocal.get()).toString())
                        .build();
            }
        } else {
            return Response.serverError().entity(
                    GlobalApiErrorCode.E000_GENERAL_SERVER_ERROR.toJson(LocaleThreadLocal.get()).toString())
                    .build();
        }
    }
}
