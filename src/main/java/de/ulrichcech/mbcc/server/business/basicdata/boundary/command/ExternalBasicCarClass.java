package de.ulrichcech.mbcc.server.business.basicdata.boundary.command;

import de.ulrichcech.mbcc.server.business.car.entity.CarClass;
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
public class ExternalBasicCarClass implements Serializable {

    @Serial
    private static final long serialVersionUID = 6514128317278728071L;


    @JsonbProperty("classId")
    private String classId;

    @JsonbProperty("classLabel")
    private String classLabel;

    @JsonbProperty("priceIncrement")
    private BigDecimal priceIncrement;


    public ExternalBasicCarClass() {
        // JSON
    }

    public ExternalBasicCarClass(CarClass carClass) {
        this.classId = carClass.getIdAsString();
        this.classLabel = carClass.getClassLabel();
        this.priceIncrement = carClass.getPriceIncrement();
    }


    public static List<ExternalBasicCarClass> createList(List<CarClass> carClassList) {
        List<ExternalBasicCarClass> resultList = new LinkedList<>();
        for (CarClass carClass : carClassList) {
            resultList.add(new ExternalBasicCarClass(carClass));
        }
        return resultList;
    }


    public String getClassId() {
        return classId;
    }

    public String getClassLabel() {
        return classLabel;
    }

    public BigDecimal getPriceIncrement() {
        return priceIncrement;
    }
}
