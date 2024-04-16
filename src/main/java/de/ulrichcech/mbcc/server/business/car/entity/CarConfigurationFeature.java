package de.ulrichcech.mbcc.server.business.car.entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Table(name = "car_configuration_features")
@Entity
public class CarConfigurationFeature implements Serializable {

    @Serial
    private static final long serialVersionUID = -4330827290038720086L;

    @EmbeddedId
    private ConfigurationFeatureId id;

    @ManyToOne
    @MapsId("configId")
    @JoinColumn(name = "car_config_id")
    private CarConfiguration carConfiguration;

    @ManyToOne
    @MapsId("featureId")
    @JoinColumn(name = "car_feature_id")
    private CarSpecialFeature specialFeature;


    public CarConfigurationFeature() {
        // JPA
    }

    public CarConfigurationFeature(ConfigurationFeatureId id, CarConfiguration carConfiguration, CarSpecialFeature specialFeature) {
        this.id = id;
        this.carConfiguration = carConfiguration;
        this.specialFeature = specialFeature;
    }

    public ConfigurationFeatureId getId() {
        return id;
    }

    public void setId(ConfigurationFeatureId id) {
        this.id = id;
    }

    public CarConfiguration getCarConfiguration() {
        return carConfiguration;
    }

    public void setCarConfiguration(CarConfiguration carConfiguration) {
        this.carConfiguration = carConfiguration;
    }

    public CarSpecialFeature getSpecialFeature() {
        return specialFeature;
    }

    public void setSpecialFeature(CarSpecialFeature specialFeature) {
        this.specialFeature = specialFeature;
    }
}
