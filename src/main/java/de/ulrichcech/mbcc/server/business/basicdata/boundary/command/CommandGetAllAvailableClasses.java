package de.ulrichcech.mbcc.server.business.basicdata.boundary.command;

import de.ulrichcech.mbcc.server.business.car.entity.CarClass;
import de.ulrichcech.mbcc.server.platform.persistence.PersistenceAware;
import jakarta.ejb.Stateless;

import java.util.Collections;
import java.util.List;

import static de.ulrichcech.mbcc.server.business.car.entity.CarClass.FIND_ALL;


@Stateless
public class CommandGetAllAvailableClasses extends PersistenceAware {

    public List<CarClass> execute() {
        final List<CarClass> resultList = getEntityManager().createNamedQuery(FIND_ALL, CarClass.class).getResultList();
        if (resultList == null || resultList.isEmpty()) {
            return Collections.emptyList();
        }
        return resultList;
    }

}
