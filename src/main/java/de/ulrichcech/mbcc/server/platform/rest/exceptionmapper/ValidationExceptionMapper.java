/*
 * Copyright (c) 2024 Ulrich Cech - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Ulrich Cech <ulrich@ulrichcech.de>, 2024
 */
package de.ulrichcech.mbcc.server.platform.rest.exceptionmapper;

import de.ulrichcech.mbcc.server.platform.localization.LocaleThreadLocal;
import de.ulrichcech.mbcc.server.platform.localization.ResourceBundleRepository;
import de.ulrichcech.mbcc.server.platform.rest.ApiErrorStructure;
import de.ulrichcech.mbcc.server.platform.rest.GlobalApiErrorCode;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
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
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {

    private static final Logger LOG = Logger.getLogger(ValidationExceptionMapper.class.getName());

    @Inject
    ResourceBundleRepository messageBundle;

    @Override
    public Response toResponse(ValidationException exception) {
        if (exception instanceof ConstraintViolationException cve) {
            LOG.log(Level.FINER, "Following ConstraintViolations has been encountered:", exception);
            Map<String, List<String>> errorStructure = new HashMap<>();
            for (final ConstraintViolation<?> violation : cve.getConstraintViolations()) {
                final var propertyPath = violation.getPropertyPath();
                var propertyPathAsString = propertyPath.toString();
                propertyPathAsString = propertyPathAsString.substring(propertyPathAsString.lastIndexOf(".") + 1);
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
            final String msg = apiErrorStructure.toJson().toString();
            LOG.warning("MBCC-EXCEPTION: " + msg);
            // TODO: the above logic could be optimized !!!
            return Response.status(Response.Status.BAD_REQUEST).entity(msg).build();
        } else {
            LOG.log(Level.SEVERE, exception.getMessage(), exception);
            return Response.serverError().entity(
                    GlobalApiErrorCode.E000_GENERAL_VALIDATION_ERROR.toJson(LocaleThreadLocal.get()).toString())
                    .build();
        }
    }

}