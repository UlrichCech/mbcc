package de.ulrichcech.mbcc.server.business.car.boundary.command;

import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class UpdateCarConfigurationRequest {

    @JsonbProperty("userId")
    private String userId;

    @JsonbProperty("configurationId")
    private String configurationId;

    @JsonbProperty("configurationLabel")
    private String configurationLabel;

    @JsonbProperty("configurationDescription")
    private String configurationDescription;

    @JsonbProperty("carTypeId")
    private String carTypeId;

    @JsonbProperty("carClassId")
    private String carClassId;

    @JsonbProperty("carEngineId")
    private String carEngineId;

    @JsonbProperty("carColorId")
    private String carColorId;

    @JsonbProperty("specialFeaturesList")
    private List<String> specialFeaturesList = new ArrayList<>();


    public UpdateCarConfigurationRequest() {
        // JSON
    }


    public String getConfigurationId() {
        return configurationId;
    }

    public void setConfigurationId(String configurationId) {
        this.configurationId = configurationId;
    }

    public String getConfigurationLabel() {
        return configurationLabel;
    }

    public void setConfigurationLabel(String configurationLabel) {
        this.configurationLabel = configurationLabel;
    }

    public String getConfigurationDescription() {
        return configurationDescription;
    }

    public void setConfigurationDescription(String configurationDescription) {
        this.configurationDescription = configurationDescription;
    }

    public String getCarTypeId() {
        return carTypeId;
    }

    public void setCarTypeId(String carTypeId) {
        this.carTypeId = carTypeId;
    }

    public String getCarClassId() {
        return carClassId;
    }

    public void setCarClassId(String carClassId) {
        this.carClassId = carClassId;
    }

    public String getCarEngineId() {
        return carEngineId;
    }

    public void setCarEngineId(String carEngineId) {
        this.carEngineId = carEngineId;
    }

    public String getCarColorId() {
        return carColorId;
    }

    public void setCarColorId(String carColorId) {
        this.carColorId = carColorId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getSpecialFeaturesList() {
        return specialFeaturesList;
    }

    public void setSpecialFeaturesList(List<String> specialFeaturesList) {
        this.specialFeaturesList = specialFeaturesList;
    }
}
