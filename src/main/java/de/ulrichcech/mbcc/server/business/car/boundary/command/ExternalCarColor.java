package de.ulrichcech.mbcc.server.business.car.boundary.command;

import de.ulrichcech.mbcc.server.business.car.entity.CarColor;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.math.BigDecimal;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ExternalCarColor {

    private String id;

    private String color;

    private String colorHexValue;

    private BigDecimal priceIncrement;


    public ExternalCarColor() {
        // JSON
    }

    public ExternalCarColor(CarColor carColor) {
        this.id = carColor.getIdAsString();
        this.color = carColor.getColorLabel();
        this.colorHexValue = carColor.getColorHexValue();
        this.priceIncrement = carColor.getPriceIncrement();
    }


    public String getId() {
        return id;
    }

    public String getColor() {
        return color;
    }

    public String getColorHexValue() {
        return colorHexValue;
    }

    public BigDecimal getPriceIncrement() {
        return priceIncrement;
    }
}
