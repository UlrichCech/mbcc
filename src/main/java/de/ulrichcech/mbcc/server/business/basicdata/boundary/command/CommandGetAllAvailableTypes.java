package de.ulrichcech.mbcc.server.business.basicdata.boundary.command;

import de.ulrichcech.mbcc.server.business.car.entity.CarType;
import de.ulrichcech.mbcc.server.platform.persistence.PersistenceAware;
import jakarta.ejb.Stateless;

import java.util.Collections;
import java.util.List;

import static de.ulrichcech.mbcc.server.business.car.entity.CarType.FIND_ALL;


@Stateless
public class CommandGetAllAvailableTypes extends PersistenceAware {

    public List<CarType> execute() {
        final List<CarType> resultList = getEntityManager().createNamedQuery(FIND_ALL, CarType.class).getResultList();
        if (resultList == null || resultList.isEmpty()) {
            return Collections.emptyList();
        }
        return resultList;
    }

}
