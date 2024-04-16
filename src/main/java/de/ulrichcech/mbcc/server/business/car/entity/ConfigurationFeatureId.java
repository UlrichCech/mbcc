package de.ulrichcech.mbcc.server.business.car.entity;

import de.ulrichcech.mbcc.server.platform.persistence.UUIDAttributeConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class ConfigurationFeatureId implements Serializable {

    @Serial
    private static final long serialVersionUID = 7807436348331651092L;


    @Column(name = "car_config_id")
    @Convert(converter = UUIDAttributeConverter.class)
    private UUID configId;

    @Column(name = "car_feature_id")
    @Convert(converter = UUIDAttributeConverter.class)
    private UUID featureId;

    public ConfigurationFeatureId() {
        // JPA
    }

    public ConfigurationFeatureId(UUID configId, UUID featureId) {
        this.configId = configId;
        this.featureId = featureId;
    }


    public UUID getConfigId() {
        return configId;
    }

    public void setConfigId(UUID configId) {
        this.configId = configId;
    }

    public UUID getFeatureId() {
        return featureId;
    }

    public void setFeatureId(UUID featureId) {
        this.featureId = featureId;
    }
}