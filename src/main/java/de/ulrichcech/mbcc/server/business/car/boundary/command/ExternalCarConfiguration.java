package de.ulrichcech.mbcc.server.business.car.boundary.command;

import de.ulrichcech.mbcc.server.business.car.entity.CarConfiguration;
import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ExternalCarConfiguration {

    @JsonbProperty("userId")
    private String userId;

    @JsonbProperty("configurationId")
    private String configurationId;

    @JsonbProperty("configurationLabel")
    private String configurationLabel;

    @JsonbProperty("configurationDescription")
    private String configurationDescription;

    @JsonbProperty("overallPrice")
    private BigDecimal overallPrice;

    @JsonbProperty
    private ExternalCarType carType;

    @JsonbProperty
    private ExternalCarClass carClass;

    @JsonbProperty
    private ExternalCarEngine carEngine;

    @JsonbProperty
    private ExternalCarColor carColor;

    @JsonbProperty
    private List<ExternalCarConfigurationFeature> specialFeatureList;


    public ExternalCarConfiguration() {
        // JSON
    }


    public ExternalCarConfiguration(CarConfiguration carConfiguration) {
        this.userId = carConfiguration.getUserId().toString();
        this.configurationId = carConfiguration.getIdAsString();
        this.configurationLabel = carConfiguration.getConfigurationLabel();
        this.configurationDescription = carConfiguration.getConfigurationDescription();
        this.overallPrice = carConfiguration.getOverallPrice();
        if (carConfiguration.getCarType() != null) {
            this.carType = new ExternalCarType(carConfiguration.getCarType().getIdAsString(), carConfiguration.getCarType().getTypeLabel(), carConfiguration.getCarType().getBasePrice());
        }
        if (carConfiguration.getCarClass() != null) {
            this.carClass = new ExternalCarClass(carConfiguration.getCarClass());
        }
        if (carConfiguration.getCarEngine() != null) {
            this.carEngine = new ExternalCarEngine(carConfiguration.getCarEngine());
        }
        if (carConfiguration.getCarColor() != null) {
            this.carColor = new ExternalCarColor(carConfiguration.getCarColor());
        }
        if ((carConfiguration.getFeatures() != null) && (! carConfiguration.getFeatures().isEmpty())) {
            this.specialFeatureList = ExternalCarConfigurationFeature.createList(carConfiguration.getFeatures());
        }
    }

    public static List<ExternalCarConfiguration> createList(List<CarConfiguration> resultList) {
        List<ExternalCarConfiguration> result = new ArrayList<>(resultList.size());
        for (CarConfiguration carConfiguration : resultList) {
            result.add(new ExternalCarConfiguration(carConfiguration));
        }
        return result;
    }


    public String getUserId() {
        return userId;
    }

    public String getConfigurationId() {
        return configurationId;
    }

    public String getConfigurationLabel() {
        return configurationLabel;
    }

    public String getConfigurationDescription() {
        return configurationDescription;
    }

    public BigDecimal getOverallPrice() {
        return overallPrice;
    }

    public ExternalCarType getCarType() {
        return carType;
    }

    public ExternalCarClass getCarClass() {
        return carClass;
    }

    public ExternalCarEngine getCarEngine() {
        return carEngine;
    }

    public ExternalCarColor getCarColor() {
        return carColor;
    }

    public List<ExternalCarConfigurationFeature> getSpecialFeatureList() {
        return specialFeatureList;
    }
}
