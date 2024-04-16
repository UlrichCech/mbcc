/*
 * Copyright (c) 2024 Ulrich Cech - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Ulrich Cech <ulrich@ulrichcech.de>, 2024
 */
package de.ulrichcech.mbcc.server.platform.rest.exceptionmapper;

import de.ulrichcech.mbcc.server.platform.PlatformValidationException;
import de.ulrichcech.mbcc.server.platform.localization.LocaleThreadLocal;
import de.ulrichcech.mbcc.server.platform.rest.ApiErrorStructure;
import de.ulrichcech.mbcc.server.platform.rest.GlobalApiErrorCode;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Provider
public class PlatformValidationExceptionMapper implements ExceptionMapper<PlatformValidationException> {

    private static final Logger LOG = Logger.getLogger(PlatformValidationExceptionMapper.class.getName());


    @Override
    public Response toResponse(PlatformValidationException ex) {
        LOG.log(Level.INFO, "MBCC-EXCEPTION: PlatformValidationExceptionMapper is triggered");
        var exception = ex.getValidationException();
        if (exception instanceof ConstraintViolationException) {
            final ConstraintViolationException cve = (ConstraintViolationException) exception;
            Map<String, List<String>> errorStructure = new HashMap<>();
            for (final ConstraintViolation<?> violation : cve.getConstraintViolations()) {
                final var propertyPath = violation.getPropertyPath();
                var propertyPathAsString = propertyPath.toString();
                if (errorStructure.containsKey(propertyPathAsString)) {
                    errorStructure.get(propertyPathAsString).add(violation.getMessage());
                } else {
                    List<String> newList = new ArrayList<>();
                    newList.add(violation.getMessage());
                    errorStructure.put(propertyPathAsString, newList);
                }
            }
            var apiErrorStructure = new ApiErrorStructure();
            apiErrorStructure.createValidationErrorStructure(errorStructure);
            // TODO: the above logic could be optimized !!!
            final String entity = apiErrorStructure.toJson().toString();
            LOG.log(Level.WARNING, "MBCC-EXCEPTION: Following ConstraintViolations has been encountered: " + entity);
            return Response.status(Response.Status.BAD_REQUEST).entity(entity).build();
        } else {
            LOG.log(Level.SEVERE, exception.getMessage(), exception);
            return Response.serverError().entity(
                    GlobalApiErrorCode.E000_GENERAL_VALIDATION_ERROR.toJson(LocaleThreadLocal.get()).toString())
                    .build();
        }
    }

}