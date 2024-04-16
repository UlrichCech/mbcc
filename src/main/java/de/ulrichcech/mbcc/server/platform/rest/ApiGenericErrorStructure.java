/*
 * Copyright (c) 2024 Ulrich Cech - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Ulrich Cech <ulrich@ulrichcech.de>, 2024
 */
package de.ulrichcech.mbcc.server.platform.rest;

import jakarta.json.Json;
import jakarta.json.JsonObject;

/**
 * The error structure for generic errors. All exceptions of the application are mapped to
 * this error structure.
 *
 * @author Ulrich Cech
 */
public class ApiGenericErrorStructure {

    private String code;

    private String message;

    private String xRequestId;


    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("code", code)
                .add("message", message)
                .add("x-request-id", xRequestId)
                .build();
    }

    public boolean hasError() {
        return (code != null) && (message != null);
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getxRequestId() {
        return xRequestId;
    }

    public void setxRequestId(String xRequestId) {
        this.xRequestId = xRequestId;
    }
}
