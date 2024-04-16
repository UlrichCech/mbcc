/*
 * Copyright (c) 2024 Ulrich Cech - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Ulrich Cech <ulrich@ulrichcech.de>, 2024
 */
package de.ulrichcech.mbcc.server.platform.rest;

import jakarta.json.Json;
import jakarta.json.JsonObject;

import java.util.HashMap;
import java.util.Map;

/**
 * The error structure for validation errors. All validation errors of the Bean-validation framework
 * are mapped to this error structure.
 *
 * @author Ulrich Cech
 */
public class ApiValidationErrorStructure {

    private final Map<String, String> errors = new HashMap<>();


    public void addValidationError(String fieldName, String message) {
        errors.put(fieldName, message);
    }


    public boolean hasError() {
        return ! errors.isEmpty();
    }


    public JsonObject toJson() {
        var jsonBuilder = Json.createObjectBuilder();
        for (var entry : errors.entrySet()) {
            jsonBuilder.add(entry.getKey(), entry.getValue());
        }
        return jsonBuilder.build();
    }

}
