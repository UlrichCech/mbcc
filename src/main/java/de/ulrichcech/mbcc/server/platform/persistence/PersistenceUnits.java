/*
 * Copyright (c) 2024 Ulrich Cech - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Ulrich Cech <ulrich@ulrichcech.de>, 2024
 */
package de.ulrichcech.mbcc.server.platform.persistence;

/**
 * The available Persistent-Units and the current active one.<br/>
 * TODO: should be improved to use environment-variables to configure the
 *
 * @author Ulrich Cech
 */
public class PersistenceUnits {

    public static final String PERSISTENCE_UNIT_PROD = "mbcc-pu-prod";
    public static final String PERSISTENCE_UNIT_TEST = "mbcc-pu-test";
    public static final String PERSISTENCE_UNIT_DEV  = "mbcc-pu-dev";
    public static final String PERSISTENCE_UNIT_GENERATE_SCHEMA = "mbcc-pu-generate_schema";

    public static final String CURRENT_PERSISTENCE_UNIT = PERSISTENCE_UNIT_DEV;


    private PersistenceUnits() {
        // private for utility classes
    }
}
