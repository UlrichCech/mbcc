package de.ulrichcech.mbcc.server.business.basicdata.boundary.command;

import de.ulrichcech.mbcc.server.business.car.entity.CarType;
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
public class ExternalBasicCarType implements Serializable {

    @Serial
    private static final long serialVersionUID = 6177256465553349893L;


    @JsonbProperty("typeId")
    private String typeId;

    @JsonbProperty("typeLabel")
    private String typeLabel;

    @JsonbProperty("basePrice")
    private BigDecimal basePrice;


    public ExternalBasicCarType() {
        // JSON
    }

    public ExternalBasicCarType(CarType carType) {
        this.typeId = carType.getIdAsString();
        this.typeLabel = carType.getTypeLabel();
        this.basePrice = carType.getBasePrice();
    }


    public static List<ExternalBasicCarType> createList(List<CarType> carTypeList) {
        List<ExternalBasicCarType> resultList = new LinkedList<>();
        for (CarType carType : carTypeList) {
            resultList.add(new ExternalBasicCarType(carType));
        }
        return resultList;
    }


    public String getTypeId() {
        return typeId;
    }

    public String getTypeLabel() {
        return typeLabel;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }
}
