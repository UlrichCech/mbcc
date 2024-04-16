package de.ulrichcech.mbcc.server.business.basicdata.boundary.command;

import de.ulrichcech.mbcc.server.business.car.entity.CarColor;
import de.ulrichcech.mbcc.server.platform.persistence.PersistenceAware;
import jakarta.ejb.Stateless;

import java.util.Collections;
import java.util.List;

import static de.ulrichcech.mbcc.server.business.car.entity.CarColor.FIND_ALL;

@Stateless
public class CommandGetAllAvailableColors extends PersistenceAware {

    public List<CarColor> execute() {
        final List<CarColor> resultList = getEntityManager().createNamedQuery(FIND_ALL, CarColor.class).getResultList();
        if (resultList == null || resultList.isEmpty()) {
            return Collections.emptyList();
        }
        return resultList;
    }

}
