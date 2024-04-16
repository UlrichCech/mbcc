package de.ulrichcech.mbcc.server.business.car.boundary.command;

import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class NewCarConfigurationRequest {

    @XmlElement(name = "userId")
    @JsonbProperty(value = "userId")
    private String userId;

    @XmlElement(name = "newConfigurationLabel")
    @JsonbProperty(value = "newConfigurationLabel")
    private String label;

    @XmlElement(name = "newConfigurationDescription")
    private String description;


    public NewCarConfigurationRequest() {
        // JSON
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
