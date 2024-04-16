package de.ulrichcech.mbcc.server.business.basicdata.boundary.command;

import de.ulrichcech.mbcc.server.business.car.entity.CarSpecialFeature;
import de.ulrichcech.mbcc.server.platform.persistence.PersistenceAware;
import jakarta.ejb.Stateless;

import java.util.Collections;
import java.util.List;

import static de.ulrichcech.mbcc.server.business.car.entity.CarSpecialFeature.FIND_ALL;


@Stateless
public class CommandGetAllAvailableSpecialFeatures extends PersistenceAware {

    public List<CarSpecialFeature> execute() {
        final List<CarSpecialFeature> resultList = getEntityManager().createNamedQuery(FIND_ALL, CarSpecialFeature.class).getResultList();
        if (resultList == null || resultList.isEmpty()) {
            return Collections.emptyList();
        }
        return resultList;
    }

}
