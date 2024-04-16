/*
 * Copyright (c) 2024 Ulrich Cech - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Ulrich Cech <ulrich@ulrichcech.de>, 2024
 */
package de.ulrichcech.mbcc.server.platform.localization;

import java.util.Locale;

/**
 * @author Ulrich Cech
 */
public class LocaleThreadLocal {

    public static final ThreadLocal<Locale> THREAD_LOCAL = new ThreadLocal<>();

    private LocaleThreadLocal() {
    }

    public static Locale get() {
        return (THREAD_LOCAL.get() == null) ? Locale.getDefault() : THREAD_LOCAL.get();
    }

    public static void set(Locale locale) {
        THREAD_LOCAL.set(locale);
    }

}
