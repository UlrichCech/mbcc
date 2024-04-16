package de.ulrichcech.mbcc.server.business.car.boundary.command;

import de.ulrichcech.mbcc.server.business.car.entity.CarConfiguration;
import de.ulrichcech.mbcc.server.platform.persistence.PersistenceAware;
import de.ulrichcech.mbcc.server.platform.rest.APIException;
import de.ulrichcech.mbcc.server.platform.rest.GlobalApiErrorCode;
import jakarta.ejb.Stateless;

import java.util.List;

import static de.ulrichcech.mbcc.server.business.car.entity.CarConfiguration.QUERY_PARAMETER_USERID;

@Stateless
public class CommandFetchCarConfiguration extends PersistenceAware {

    public ExternalCarConfiguration execute(String configId) {
        final var carConfigurationId = convertId(configId, GlobalApiErrorCode.E400_INVALID_CAR_CONFIGURATION_ID);
        final var carConfiguration = fetchEntityById(CarConfiguration.class, carConfigurationId);
        if (carConfiguration == null) {
            throw new APIException(GlobalApiErrorCode.E404_CAR_CONFIGURATION_NOT_FOUND, carConfigurationId);
        }
        return new ExternalCarConfiguration(carConfiguration);
    }

    public List<ExternalCarConfiguration> executeByUser(String userIdAsString) {
        final var userId = convertId(userIdAsString, GlobalApiErrorCode.E400_INVALID_USER_ID);
        final List<CarConfiguration> resultList = getEntityManager().createNamedQuery(CarConfiguration.FETCH_ALL_BY_USERID, CarConfiguration.class).setParameter(QUERY_PARAMETER_USERID, userId).getResultList();
        if ((resultList != null) && (! resultList.isEmpty())) {
            return ExternalCarConfiguration.createList(resultList);
        } else {
            throw new APIException(GlobalApiErrorCode.E404_CAR_CONFIGURATIONS_FOR_USERID_NOT_FOUND, userIdAsString);
        }
    }

}
