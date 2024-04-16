package de.ulrichcech.mbcc.server.business.basicdata.boundary.command;

import de.ulrichcech.mbcc.server.business.car.entity.CarSpecialFeature;
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
public class ExternalBasicCarSpecialFeature implements Serializable {

    @Serial
    private static final long serialVersionUID = 230773075287273638L;


    @JsonbProperty("featureId")
    private String featureId;

    @JsonbProperty("featureLabel")
    private String featureLabel;

    @JsonbProperty("priceIncrement")
    private BigDecimal priceIncrement;


    public ExternalBasicCarSpecialFeature() {
        // JSON
    }

    public ExternalBasicCarSpecialFeature(CarSpecialFeature carSpecialFeature) {
        this.featureId = carSpecialFeature.getIdAsString();
        this.featureLabel = carSpecialFeature.getFeatureLabel();
        this.priceIncrement = carSpecialFeature.getPriceIncrement();
    }


    public static List<ExternalBasicCarSpecialFeature> createList(List<CarSpecialFeature> carSpecialFeatureList) {
        List<ExternalBasicCarSpecialFeature> resultList = new LinkedList<>();
        for (CarSpecialFeature carSpecialFeature : carSpecialFeatureList) {
            resultList.add(new ExternalBasicCarSpecialFeature(carSpecialFeature));
        }
        return resultList;
    }


    public String getFeatureId() {
        return featureId;
    }

    public String getFeatureLabel() {
        return featureLabel;
    }

    public BigDecimal getPriceIncrement() {
        return priceIncrement;
    }
}
