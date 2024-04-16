package de.ulrichcech.mbcc.server.business.car.boundary.command;

import de.ulrichcech.mbcc.server.business.car.entity.CarConfiguration;
import de.ulrichcech.mbcc.server.business.car.entity.CarConfigurationFeature;
import de.ulrichcech.mbcc.server.platform.persistence.PersistenceAware;
import de.ulrichcech.mbcc.server.platform.rest.APIException;
import de.ulrichcech.mbcc.server.platform.rest.GlobalApiErrorCode;
import jakarta.ejb.Stateless;

@Stateless
public class CommandDeleteCarConfiguration extends PersistenceAware {

    public void execute(String configId) {
        final var carConfigurationId = convertId(configId, GlobalApiErrorCode.E400_INVALID_CAR_CONFIGURATION_ID);
        final var carConfiguration = fetchEntityById(CarConfiguration.class, carConfigurationId);
        if (carConfiguration == null) {
            throw new APIException(GlobalApiErrorCode.E404_CAR_CONFIGURATION_NOT_FOUND, carConfigurationId);
        }
        if ((carConfiguration.getFeatures() != null) && (! carConfiguration.getFeatures().isEmpty())) {
            for (CarConfigurationFeature feature : carConfiguration.getFeatures()) {
                getEntityManager().remove(feature);
            }
        }
        remove(carConfiguration);
    }
}
