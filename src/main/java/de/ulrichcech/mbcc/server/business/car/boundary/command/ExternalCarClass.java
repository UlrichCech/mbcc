package de.ulrichcech.mbcc.server.business.car.boundary.command;

import de.ulrichcech.mbcc.server.business.car.entity.CarClass;
import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.math.BigDecimal;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ExternalCarClass {

    @JsonbProperty("id")
    private String id;

    @JsonbProperty("label")
    private String label;

    @JsonbProperty("priceIncrement")
    private BigDecimal priceIncrement;


    public ExternalCarClass() {
        // JSON
    }

    public ExternalCarClass(CarClass carClass) {
        this.id = carClass.getIdAsString();
        this.label = carClass.getClassLabel();
        this.priceIncrement = carClass.getPriceIncrement();
    }


    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public BigDecimal getPriceIncrement() {
        return priceIncrement;
    }
}
