/*
 * Copyright (c) 2024 Ulrich Cech - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Ulrich Cech <ulrich@ulrichcech.de>, 2024
 */
package de.ulrichcech.mbcc.server.platform.ejb;


import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

/**
 * EJB helper service to get or instantiate EJB-components programmatically.
 *
 * @author Ulrich Cech
 */
public class EJBBeanService {

    private static final Logger LOG = getLogger(EJBBeanService.class.getName());

    private static final EJBBeanService INSTANCE = new EJBBeanService();


    InitialContext initialContext;

    private EJBBeanService() {
        super();
        try {
            initialContext = new InitialContext();
        } catch (NamingException ignore) {
            LOG.log(Level.SEVERE, "MBCC-SYS:EJBBeanService could not be initialized!");
        }
    }


    public static EJBBeanService getInstance() {
        return INSTANCE;
    }

    /**
     * ATTENTION: if STATELESS-EJB implements some interface, then they are not accessible via simple classname
     *            therefore they should always EXTEND some other class, then they are accessible via classname.
     */
    public <T> T getEJBBean(final Class<T> clazz) {
        try {
            return clazz.cast(initialContext.lookup("java:global/mb-car-configurator-server/" + clazz.getSimpleName()));
        } catch (NamingException ex) {
            LOG.log(Level.SEVERE, "MBCC-SYS:EJBBeanService", ex);
        }
        return null;
    }

}