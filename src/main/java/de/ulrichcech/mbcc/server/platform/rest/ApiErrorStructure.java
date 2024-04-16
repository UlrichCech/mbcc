/*
 * Copyright (c) 2024 Ulrich Cech - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Ulrich Cech <ulrich@ulrichcech.de>, 2024
 */
package de.ulrichcech.mbcc.server.platform.rest;

import jakarta.json.Json;
import jakarta.json.JsonObject;

import java.util.List;
import java.util.Map;

/**
 * The general error structure.
 *
 * @author Ulrich Cech
 */
public class ApiErrorStructure {

    private ApiGenericErrorStructure genericError;

    private ApiValidationErrorStructure validationError;


    public ApiErrorStructure() {
        super();
    }


    public void createGenericError(String code, String message) {
        genericError = new ApiGenericErrorStructure();
        genericError.setCode(code);
        genericError.setMessage(message);
        genericError.setxRequestId(XRequestIdThreadLocal.get());
    }

    public void createValidationErrorStructure(Map<String, List<String>> validationErrors) {
        validationError = new ApiValidationErrorStructure();
        for (var entry : validationErrors.entrySet()) {
            validationError.addValidationError(entry.getKey(), (((entry.getValue() != null) && (! entry.getValue().isEmpty())) ? entry.getValue().get(0) : "")); // only first message
        }
    }

    public JsonObject toJson() {
        final var objectBuilder = Json.createObjectBuilder();
        if ((genericError != null) && genericError.hasError()) {
            objectBuilder.add("error", genericError.toJson());
        }
        if ((validationError != null) && validationError.hasError()) {
            objectBuilder.add("x-request-id", XRequestIdThreadLocal.get());
            objectBuilder.add("validationError", validationError.toJson());
        }
        return objectBuilder.build();
    }

}
