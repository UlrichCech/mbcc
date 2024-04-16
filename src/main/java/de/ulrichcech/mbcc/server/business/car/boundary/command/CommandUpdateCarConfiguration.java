package de.ulrichcech.mbcc.server.business.car.boundary.command;

import de.ulrichcech.mbcc.server.business.car.entity.*;
import de.ulrichcech.mbcc.server.platform.StringUtils;
import de.ulrichcech.mbcc.server.platform.persistence.PersistenceAware;
import de.ulrichcech.mbcc.server.platform.rest.APIException;
import de.ulrichcech.mbcc.server.platform.rest.GlobalApiErrorCode;
import jakarta.ejb.Stateless;

import java.util.List;
import java.util.UUID;

@Stateless
public class CommandUpdateCarConfiguration extends PersistenceAware {

    public ExternalCarConfiguration execute(UpdateCarConfigurationRequest updateRequest) {
        final var carConfigurationId = convertId(updateRequest.getConfigurationId(), GlobalApiErrorCode.E400_INVALID_CAR_CONFIGURATION_ID);
        final var carConfiguration = fetchEntityById(CarConfiguration.class, carConfigurationId);
        if (carConfiguration == null) {
            throw new APIException(GlobalApiErrorCode.E404_CAR_CONFIGURATION_NOT_FOUND, carConfigurationId);
        }

        updateConfigurationLabel(carConfiguration, updateRequest);

        updateConfigurationDescription(carConfiguration, updateRequest);

        updateConfigurationCarType(carConfiguration, updateRequest);

        updateConfigurationCarClass(carConfiguration, updateRequest);

        updateConfigurationCarEngine(carConfiguration, updateRequest);

        updateConfigurationCarColor(carConfiguration, updateRequest);

        updateConfigurationSpecialFeatures(carConfiguration, updateRequest);
        // TODO: ???? special feature

        carConfiguration.updateOverallPrice();
        persist(carConfiguration);

        return new ExternalCarConfiguration(carConfiguration);
    }


    void updateConfigurationLabel(CarConfiguration carConfiguration, UpdateCarConfigurationRequest updateRequest) {
        if (StringUtils.isNotBlank(updateRequest.getConfigurationLabel())) {
            carConfiguration.setConfigurationLabel(updateRequest.getConfigurationLabel());
        }
    }

    void updateConfigurationDescription(CarConfiguration carConfiguration, UpdateCarConfigurationRequest updateRequest) {
        if (StringUtils.isNotBlank(updateRequest.getConfigurationDescription())) {
            carConfiguration.setConfigurationDescription(updateRequest.getConfigurationDescription());
        }
    }

    void updateConfigurationCarType(CarConfiguration carConfiguration, UpdateCarConfigurationRequest updateRequest) {
        if (updateRequest.getCarTypeId() != null) {
            var newCarTypeId = convertId(updateRequest.getCarTypeId(), GlobalApiErrorCode.E400_INVALID_CAR_TYPE_ID);
            if ((carConfiguration.getCarType() == null) || (!newCarTypeId.equals(carConfiguration.getCarType().getId()))) {
                var newCarType = fetchEntityById(CarType.class, newCarTypeId);
                if (newCarType == null) {
                    throw new APIException(GlobalApiErrorCode.E404_CAR_TYPE_NOT_FOUND);
                }
                carConfiguration.setCarType(newCarType);
            }
        }
    }

    void updateConfigurationCarClass(CarConfiguration carConfiguration, UpdateCarConfigurationRequest updateRequest) {
        if (updateRequest.getCarClassId() != null) {
            var newCarClassId = convertId(updateRequest.getCarClassId(), GlobalApiErrorCode.E400_INVALID_CAR_CLASS_ID);
            if ((carConfiguration.getCarClass() == null) || (!newCarClassId.equals(carConfiguration.getCarClass().getId()))) {
                var newCarClass = fetchEntityById(CarClass.class, newCarClassId);
                if (newCarClass == null) {
                    throw new APIException(GlobalApiErrorCode.E404_CAR_CLASS_NOT_FOUND);
                }
                carConfiguration.setCarClass(newCarClass);
            }
        }
    }

    void updateConfigurationCarEngine(CarConfiguration carConfiguration, UpdateCarConfigurationRequest updateRequest) {
        if (updateRequest.getCarEngineId() != null) {
            var newCarEngineId = convertId(updateRequest.getCarEngineId(), GlobalApiErrorCode.E400_INVALID_CAR_ENGINE_ID);
            if ((carConfiguration.getCarEngine() == null) || (!newCarEngineId.equals(carConfiguration.getCarEngine().getId()))) {
                var newCarEngine = fetchEntityById(CarEngine.class, newCarEngineId);
                if (newCarEngine == null) {
                    throw new APIException(GlobalApiErrorCode.E404_CAR_ENGINE_NOT_FOUND);
                }
                carConfiguration.setCarEngine(newCarEngine);
            }
        }
    }

    void updateConfigurationCarColor(CarConfiguration carConfiguration, UpdateCarConfigurationRequest updateRequest) {
        if (updateRequest.getCarColorId() != null) {
            var newCarColorId = convertId(updateRequest.getCarColorId(), GlobalApiErrorCode.E400_INVALID_CAR_COLOR_ID);
            if ((carConfiguration.getCarColor() == null) || (!newCarColorId.equals(carConfiguration.getCarColor().getId()))) {
                var newCarColor = fetchEntityById(CarColor.class, newCarColorId);
                if (newCarColor == null) {
                    throw new APIException(GlobalApiErrorCode.E404_CAR_COLOR_NOT_FOUND);
                }
                carConfiguration.setCarColor(newCarColor);
            }
        }
    }

    void updateConfigurationSpecialFeatures(CarConfiguration carConfiguration, UpdateCarConfigurationRequest updateRequest) {
        var newFeatureIdList = updateRequest.getSpecialFeaturesList();
        final List<CarConfigurationFeature> featuresToRemove = carConfiguration.getFeatures().stream()
                .filter(entry -> !newFeatureIdList.contains(entry.getSpecialFeature().getIdAsString()))
                .toList();
        carConfiguration.getFeatures().removeAll(featuresToRemove);

        for (String featureId : newFeatureIdList) {
            if (carConfiguration.getFeatures().stream().noneMatch(featureEntry -> featureEntry.getSpecialFeature().getIdAsString().equals(featureId))) {
                final CarSpecialFeature newCarSpecialFeature = fetchEntityById(CarSpecialFeature.class, UUID.fromString(featureId));
                if (newCarSpecialFeature == null) {
                    throw new APIException(GlobalApiErrorCode.E404_CAR_SPECIALFEATURE_NOT_FOUND);
                }
                carConfiguration.addNewSpecialFeature(newCarSpecialFeature);
            }
        }
    }

}