/*
 * Copyright (c) 2024 Ulrich Cech - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Ulrich Cech <ulrich@ulrichcech.de>, 2024
 */
package de.ulrichcech.mbcc.server.platform.rest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * The JAX-RS configuration.
 *
 * @author Ulrich Cech
 */
//@LoginConfig(authMethod = "MP-JWT", realmName = "MP-JWT")
//@DeclareRoles({"ROLE_ALL_USERS","ROLE_ADMIN"})
@ApplicationScoped
@ApplicationPath("/api")
public class RESTApplication extends Application {

}
