/*
 * Copyright (c) 2024 Ulrich Cech - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Ulrich Cech <ulrich@ulrichcech.de>, 2024
 */
package de.ulrichcech.mbcc.server.platform.rest;

/**
 * @author Ulrich Cech
 */
public class XRequestIdThreadLocal {

    public static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();

    private XRequestIdThreadLocal() {
    }

    public static String get() {
        return (THREAD_LOCAL.get() == null) ? "" : THREAD_LOCAL.get();
    }

    public static void set(String xRequestId) {
        THREAD_LOCAL.set(xRequestId);
    }

    public static void destroy() {
        THREAD_LOCAL.remove();
    }

}
