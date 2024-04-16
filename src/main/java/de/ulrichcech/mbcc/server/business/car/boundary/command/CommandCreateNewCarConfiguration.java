package de.ulrichcech.mbcc.server.business.car.boundary.command;

import de.ulrichcech.mbcc.server.business.car.entity.CarConfiguration;
import de.ulrichcech.mbcc.server.platform.persistence.PersistenceAware;
import de.ulrichcech.mbcc.server.platform.rest.GlobalApiErrorCode;
import jakarta.ejb.Stateless;

@Stateless
public class CommandCreateNewCarConfiguration extends PersistenceAware {

    public ExternalCarConfiguration execute(NewCarConfigurationRequest request) {
        CarConfiguration newCarConfiguration = new CarConfiguration();
        var userId = convertId(request.getUserId(), GlobalApiErrorCode.E400_INVALID_USER_ID);
        newCarConfiguration.setUserId(userId);
        newCarConfiguration.setConfigurationLabel(request.getLabel());
        newCarConfiguration.setConfigurationDescription(request.getDescription());
        persist(newCarConfiguration);
        return new ExternalCarConfiguration(newCarConfiguration);
    }
}
