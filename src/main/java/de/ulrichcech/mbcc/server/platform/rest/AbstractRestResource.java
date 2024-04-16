/*
 * Copyright (c) 2024 Ulrich Cech - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Ulrich Cech <ulrich@ulrichcech.de>, 2024
 */
package de.ulrichcech.mbcc.server.platform.rest;

import de.ulrichcech.mbcc.server.platform.ejb.EJBBeanService;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.*;

import java.security.Principal;
import java.util.List;
import java.util.Locale;

/**
 * Abstract superclass for all REST-Resources.
 *
 * @author Ulrich Cech
 */
public abstract class AbstractRestResource {

    public static final String MEDIA_TYPE_JSON_STRING = "application/json;charset=UTF-8"; // have to be public

    private static final MediaType MEDIA_TYPE_JSON = new MediaType("application", "json", "UTF-8");

    private static final List<Variant> acceptableMediaTypes =
            Variant.mediaTypes(MEDIA_TYPE_JSON).build();


    @Context
    protected UriInfo info;

    @Context
    protected HttpHeaders httpHeaders;

    @Context
    protected HttpServletRequest httpServletRequest;

    @Context
    protected HttpServletResponse httpServletResponse;

    @Context
    protected Request request;

    @Inject
    Principal principal;

    protected String getHeaderValue(final String headerKey) {
        return httpHeaders.getHeaderString(headerKey);
    }

    public Locale getRequestLocale() {
        return httpServletRequest.getLocale();
    }

    protected MediaType getNegotiatedMediaType() {
        final var selectedMediaType = request.selectVariant(acceptableMediaTypes);
        if (selectedMediaType == null) {
            return MEDIA_TYPE_JSON;
        }
        return selectedMediaType.getMediaType();
    }

    protected <T> T createCommand(Class<T> commandClass) {
        return EJBBeanService.getInstance().getEJBBean(commandClass);
    }


    public Principal getPrincipal() {
        return principal;
    }

//    public JsonWebToken getCallerPrincipal() {
//        return callerPrincipal;
//    }

    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }
}
