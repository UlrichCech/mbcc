package de.ulrichcech.mbcc.server.business.car.boundary.command;

import de.ulrichcech.mbcc.server.business.car.entity.CarConfigurationFeature;
import de.ulrichcech.mbcc.server.business.car.entity.CarSpecialFeature;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ExternalCarConfigurationFeature {

    private String id;

    private String specialFeatureLabel;

    private BigDecimal priceIncrement;


    public ExternalCarConfigurationFeature() {
        // JSON
    }

    public ExternalCarConfigurationFeature(CarConfigurationFeature carConfigurationSpecialFeature) {
        final CarSpecialFeature specialFeature = carConfigurationSpecialFeature.getSpecialFeature();
        this.id = specialFeature.getIdAsString();
        this.specialFeatureLabel = specialFeature.getFeatureLabel();
        this.priceIncrement = specialFeature.getPriceIncrement();
    }

    public static final List<ExternalCarConfigurationFeature> createList(List<CarConfigurationFeature> carConfigurationFeatureList) {
        List<ExternalCarConfigurationFeature> result = new ArrayList<>();
        for (CarConfigurationFeature carConfigurationFeature : carConfigurationFeatureList) {
            result.add(new ExternalCarConfigurationFeature(carConfigurationFeature));
        }
        return result;
    }

    public String getId() {
        return id;
    }

    public String getSpecialFeatureLabel() {
        return specialFeatureLabel;
    }

    public BigDecimal getPriceIncrement() {
        return priceIncrement;
    }
}
