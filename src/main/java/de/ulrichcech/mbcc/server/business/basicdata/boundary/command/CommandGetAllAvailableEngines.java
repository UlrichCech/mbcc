package de.ulrichcech.mbcc.server.business.basicdata.boundary.command;

import de.ulrichcech.mbcc.server.business.car.entity.CarEngine;
import de.ulrichcech.mbcc.server.platform.persistence.PersistenceAware;
import jakarta.ejb.Stateless;

import java.util.Collections;
import java.util.List;

import static de.ulrichcech.mbcc.server.business.car.entity.CarEngine.FIND_ALL;

@Stateless
public class CommandGetAllAvailableEngines extends PersistenceAware {

    public List<CarEngine> execute() {
        final List<CarEngine> resultList = getEntityManager().createNamedQuery(FIND_ALL, CarEngine.class).getResultList();
        if (resultList == null || resultList.isEmpty()) {
            return Collections.emptyList();
        }
        return resultList;
    }

}
