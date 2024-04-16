package de.ulrichcech.mbcc.server.platform.cdi;

import jakarta.enterprise.context.spi.CreationalContext;
import jakarta.enterprise.inject.spi.Bean;
import jakarta.enterprise.inject.spi.BeanManager;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.inject.Named;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Logger;

/**
 * Service for getting CDI-Beans programmatically.
 *
 * @author Ulrich Cech
 */
@Named
public class CDIBeanService {

    private static final Logger LOG = Logger.getLogger(CDIBeanService.class.getName());

    private static final CDIBeanService INSTANCE = new CDIBeanService();

    private CDIBeanService() {
        super();
    }

    public static CDIBeanService getInstance() {
        return INSTANCE;
    }

    public <T> T getCDIBean(final Class<T> clazz) {
        var manager = getBeanManager();
        if (manager != null) {
            Bean<T> bean = (Bean<T>) manager.getBeans(clazz).iterator().next();
            CreationalContext<T> ctx = manager.createCreationalContext(bean);
            return (T) manager.getReference(bean, clazz, ctx);
        } else {
            try {
                return clazz.getDeclaredConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                LOG.warning("Could not create instance of class <" + clazz + ">.");
            }
            return null;
        }
    }

    public <T> T getCDIBean(final Class<T> clazz, Annotation... annotations) {
        var manager = getBeanManager();
        if (manager != null) {
            Bean<T> bean = (Bean<T>) manager.getBeans(clazz, annotations).iterator().next();
            CreationalContext<T> ctx = manager.createCreationalContext(bean);
            return (T) manager.getReference(bean, clazz, ctx);
        } else {
            try {
                return clazz.getDeclaredConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                LOG.warning("Could not create instance of class <" + clazz + ">.");
            }
            return null;
        }
    }

    private BeanManager getBeanManager() {
        CDI<Object> containerAccessor;
        try {
            containerAccessor = CDI.current();
            if (containerAccessor != null) {
                return containerAccessor.getBeanManager();
            } else {
                return getManager();
            }
        } catch (IllegalStateException ignore) {
            // ignore
        }
        return null;
    }

    private BeanManager getManager() {
        try {
            var initialContext = new InitialContext();
            return (BeanManager) initialContext.lookup("java:comp/BeanManager");
        } catch (NamingException e) {
            return null;
        }
    }

}
