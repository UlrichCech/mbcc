/*
 * Copyright (c) 2024 Ulrich Cech - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Ulrich Cech <ulrich@ulrichcech.de>, 2024
 */
package de.ulrichcech.mbcc.server.platform.persistence;

import de.ulrichcech.mbcc.server.platform.ejb.EJBBeanService;
import de.ulrichcech.mbcc.server.platform.localization.LocaleThreadLocal;
import de.ulrichcech.mbcc.server.platform.rest.APIException;
import de.ulrichcech.mbcc.server.platform.rest.GlobalApiErrorCode;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.logging.Logger;

import static de.ulrichcech.mbcc.server.platform.persistence.PersistenceUnits.CURRENT_PERSISTENCE_UNIT;
import static java.util.logging.Logger.getLogger;

/**
 * The superclass for all business command objects, which has to deal with the database.<br/>
 * There are various helper methods for persistence handling.
 *
 * @author Ulrich Cech
 */
public class PersistenceAware {


    @PersistenceContext(unitName = CURRENT_PERSISTENCE_UNIT)
    EntityManager entityManager;


    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager manager) {
        this.entityManager = manager;
    }

    public void persist(PersistentEntity entity) {
        if (entity != null && entity.isNew()) {
            entityManager.persist(entity);
        } else {
            entityManager.merge(entity);
        }
    }

    public void persist(List<? extends PersistentEntity> entityList) {
        for (PersistentEntity persistentEntity : entityList) {
            persist(persistentEntity);
        }
    }

    public void refresh(PersistentEntity entity) {
        entityManager.refresh(entity);
    }

    public void remove(PersistentEntity entity) {
        if (entity != null) {
            entityManager.remove(entity);
        }
    }

    public void flush() {
        entityManager.flush();
    }


    public <T extends PersistentEntity> T fetchEntityById(Class<T> entityClass, UUID id) {
        if (id != null) {
            return entityManager.find(entityClass, id);
        }
        return null;
    }

    public <T extends PersistentEntity> T fetchEntityById(Class<T> entityClass, UUID id, Supplier<? extends APIException> errorFunction) {
        if (id != null) {
            return entityManager.find(entityClass, id);
        }
        throw errorFunction.get();
    }


    protected <T> T createCommand(Class<T> commandClass) {
        return EJBBeanService.getInstance().getEJBBean(commandClass);
    }

    public UUID convertId(String idAsString, GlobalApiErrorCode errorCode) {
        try {
            return UUID.fromString(idAsString);
        } catch (IllegalArgumentException ex) {
            throw new APIException(errorCode, LocaleThreadLocal.get());
        }
    }

}
