package de.ulrichcech.mbcc.server.business.car.boundary.command;

import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.math.BigDecimal;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ExternalCarType {

    @JsonbProperty("id")
    private String id;

    @JsonbProperty("label")
    private String label;

    @JsonbProperty("basePrice")
    private BigDecimal basePrice;

    public ExternalCarType() {
        // JSON
    }

    public ExternalCarType(String id, String label, BigDecimal basePrice) {
        this.id = id;
        this.label = label;
        this.basePrice = basePrice;
    }


    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }
}
