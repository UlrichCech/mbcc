/*
 * Copyright (c) 2024 Ulrich Cech - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Ulrich Cech <ulrich@ulrichcech.de>, 2024
 */
package de.ulrichcech.mbcc.server.platform.rest;

import de.ulrichcech.mbcc.server.platform.cdi.CDIBeanService;
import de.ulrichcech.mbcc.server.platform.localization.ResourceBundleRepository;
import jakarta.json.JsonObject;
import jakarta.ws.rs.core.Response;

import java.util.Arrays;
import java.util.Locale;


/**
 * @author Ulrich Cech
 */
public enum GlobalApiErrorCode {

    E000_GENERAL_SERVER_ERROR(Response.Status.INTERNAL_SERVER_ERROR, "MBCC-X-001", "api.error.base"),
    E000_SERVER_ERROR_NOT_IMPLEMENTED(Response.Status.INTERNAL_SERVER_ERROR, "MBCC-X-002", "api.error.base.not_implemented"),

    E000_GENERAL_VALIDATION_ERROR(Response.Status.INTERNAL_SERVER_ERROR, "MBCC-X-005", "validation.error.base"),
    E000_GENERAL_NOTFOUND_ERROR(Response.Status.NOT_FOUND, "MBCC-X-004", "api.error.notfound"),


    E400_INVALID_CAR_CONFIGURATION_ID(Response.Status.BAD_REQUEST, "MBCC-CC-001", "api.error.invalid_car_configuration_id"),
    E400_INVALID_CAR_TYPE_ID(Response.Status.BAD_REQUEST, "MBCC-CC-002", "api.error.invalid_car_type_id"),
    E400_INVALID_CAR_CLASS_ID(Response.Status.BAD_REQUEST, "MBCC-CC-003", "api.error.invalid_car_class_id"),
    E400_INVALID_CAR_ENGINE_ID(Response.Status.BAD_REQUEST, "MBCC-CC-004", "api.error.invalid_car_engine_id"),
    E400_INVALID_CAR_COLOR_ID(Response.Status.BAD_REQUEST, "MBCC-CC-005", "api.error.invalid_car_color_id"),
    E400_INVALID_USER_ID(Response.Status.BAD_REQUEST, "MBCC-CC-006", "api.error.invalid_user_id"),

    E404_CAR_CONFIGURATION_NOT_FOUND(Response.Status.NOT_FOUND, "MBCC-CC-040", "api.error.car_configuration_not_found"),
    E404_CAR_TYPE_NOT_FOUND(Response.Status.NOT_FOUND, "MBCC-CC-041", "api.error.car_type_not_found"),
    E404_CAR_CLASS_NOT_FOUND(Response.Status.NOT_FOUND, "MBCC-CC-042", "api.error.car_class_not_found"),
    E404_CAR_ENGINE_NOT_FOUND(Response.Status.NOT_FOUND, "MBCC-CC-043", "api.error.car_engine_not_found"),
    E404_CAR_COLOR_NOT_FOUND(Response.Status.NOT_FOUND, "MBCC-CC-044", "api.error.car_color_not_found"),
    E404_CAR_CONFIGURATIONS_FOR_USERID_NOT_FOUND(Response.Status.NOT_FOUND, "MBCC-CC-045", "api.error.car_configuration_for_user_not_found"),
    E404_CAR_SPECIALFEATURE_NOT_FOUND(Response.Status.NOT_FOUND, "MBCC-CC-046", "api.error.specialfeature_not_found"),

    E415_NOT_SUPPORTED(Response.Status.UNSUPPORTED_MEDIA_TYPE, "MBCC-X-010", "api.error.base.not_supported"),
    ;

    private static final ResourceBundleRepository messageBundle = CDIBeanService.getInstance().getCDIBean(ResourceBundleRepository.class);

    private final Response.Status responseStatus;

    private final String code;

    private final String i18nKey;


    GlobalApiErrorCode(Response.Status responseStatus, String code, String i18nKey) {
        this.responseStatus = responseStatus;
        this.code = code;
        this.i18nKey = i18nKey;
    }

    public Response.Status getResponseStatus() {
        return responseStatus;
    }

    public String getCode() {
        return code;
    }

    public String getI18nKey() {
        return i18nKey;
    }

    public String getLocalizedMessage(Locale locale, Object... args) {
        return messageBundle.getLocalized("de.ulrichcech.mbcc.server.business.i18n.GlobalApiError", getI18nKey(), locale, args);
    }

    public JsonObject toJson(Locale locale, Object... args) {
        var apiErrorStructure = new ApiErrorStructure();
        apiErrorStructure.createGenericError(getCode(), getLocalizedMessage(locale, args));
        return apiErrorStructure.toJson();
    }

    public JsonObject toJson(Locale locale, String additionalMessage, Object... args) {
        var apiErrorStructure = new ApiErrorStructure();
        apiErrorStructure.createGenericError(getCode(), combineErrorMessages(getLocalizedMessage(locale, args), additionalMessage));
        return apiErrorStructure.toJson();
    }

    private String combineErrorMessages(String... messages) {
        var sb = new StringBuilder();
        Arrays.stream(messages).filter(m -> m != null && m.trim().length() > 0).forEach(m -> sb.append("\n").append(m));
        return sb.toString();
    }

}
