/*
 * Copyright (c) 2024 Ulrich Cech - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Ulrich Cech <ulrich@ulrichcech.de>, 2024
 */
package de.ulrichcech.mbcc.server.platform.persistence;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.UUID;

/**
 * JPA-attribute converter for converting UUID to String and vice-versa.
 *
 * @author Ulrich Cech
 */
@Converter
public class UUIDAttributeConverter implements AttributeConverter<UUID, String> {

    @Override
    public String convertToDatabaseColumn(UUID uuid) {
        if (uuid != null) {
            return uuid.toString();
        } else {
            return null;
        }
    }

    @Override
    public UUID convertToEntityAttribute(String s) {
        if (s != null) {
            try {
                return UUID.fromString(s);
            } catch (IllegalArgumentException ignore) {
                // ignore
            }
        }
        return null;
    }
}
