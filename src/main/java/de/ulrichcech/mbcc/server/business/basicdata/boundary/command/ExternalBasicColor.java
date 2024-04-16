package de.ulrichcech.mbcc.server.business.basicdata.boundary.command;

import de.ulrichcech.mbcc.server.business.car.entity.CarColor;
import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ExternalBasicColor implements Serializable {

    @Serial
    private static final long serialVersionUID = -2647169791804531714L;


    @JsonbProperty("colorId")
    private String colorId;

    @JsonbProperty("colorLabel")
    private String colorLabel;

    @JsonbProperty("colorHexValue")
    private String colorHexValue;

    @JsonbProperty("priceIncrement")
    private BigDecimal priceIncrement;


    public ExternalBasicColor() {
        // JSON
    }

    public ExternalBasicColor(CarColor carColor) {
        this.colorId = carColor.getIdAsString();
        this.colorLabel = carColor.getColorLabel();
        this.colorHexValue = carColor.getColorHexValue();
        this.priceIncrement = carColor.getPriceIncrement();
    }


    public static List<ExternalBasicColor> createList(List<CarColor> carColorList) {
        List<ExternalBasicColor> resultList = new LinkedList<>();
        for (CarColor carColor : carColorList) {
            resultList.add(new ExternalBasicColor(carColor));
        }
        return resultList;
    }


    public String getColorId() {
        return colorId;
    }

    public String getColorLabel() {
        return colorLabel;
    }

    public String getColorHexValue() {
        return colorHexValue;
    }

    public BigDecimal getPriceIncrement() {
        return priceIncrement;
    }
}
