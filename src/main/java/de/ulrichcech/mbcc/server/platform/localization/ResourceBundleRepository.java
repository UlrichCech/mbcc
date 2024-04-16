/*
 * Copyright (c) 2024 Ulrich Cech - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Ulrich Cech <ulrich@ulrichcech.de>, 2024
 */
package de.ulrichcech.mbcc.server.platform.localization;

import jakarta.ejb.Singleton;

import java.io.Serial;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Ulrich Cech
 */
@Singleton
public class ResourceBundleRepository implements Serializable {

    @Serial
    private static final long serialVersionUID = 3394456474897821919L;



    public String getLocalized(final Enum<?> enumObject, final Locale locale) {
        return getLocalized(enumObject.getClass().getName(), enumObject.name(), locale);
    }

    public String getLocalized(final String resourceBundleName, final String messageKey, final Locale locale) {
        return getLocalized(resourceBundleName, messageKey, locale, (Object[]) null);
    }

    public String getLocalized(final String resourceBundleName, final String messageKey, final Locale locale, Object... messageParams) {
        var bundle = ResourceBundle.getBundle(resourceBundleName, locale);
        if (bundle.containsKey(messageKey)) {
            if ((messageParams != null) && (messageParams.length > 0)) {
                return MessageFormat.format(bundle.getString(messageKey), messageParams);
            } else {
                return bundle.getString(messageKey);
            }
        }
        return "";
    }

    public String getDefaultLocalized(final String messageKey, final Locale locale, Object... messageParams) {
        return getLocalized("de.ulrichcech.mbcc.server.business.i18n.Text", messageKey, locale, messageParams);
    }

}
