package de.ulrichcech.mbcc.server.business.car.entity;

import de.ulrichcech.mbcc.server.platform.persistence.PersistentEntity;
import jakarta.persistence.*;

import java.io.Serial;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static de.ulrichcech.mbcc.server.business.car.entity.CarSpecialFeature.FIND_ALL;


@Table(name = "car_specialfeature")
@Entity
@NamedQuery(name = FIND_ALL, query = "select csf from CarSpecialFeature csf")
public class CarSpecialFeature extends PersistentEntity {

    @Serial
    private static final long serialVersionUID = -4211261302326468551L;


    public static final String FIND_ALL = "carSpecialFeature.findAll";


    @Column(name = "car_specialfeature_feature_label")
    private String featureLabel;

    @Column(name = "car_specialfeature_price_increment", scale = 2, precision = 11)
    private BigDecimal priceIncrement = BigDecimal.ZERO;

    @OneToMany(mappedBy = "specialFeature", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarTypeFeature> carTypeFeatureList = new ArrayList<>();


    public String getFeatureLabel() {
        return featureLabel;
    }

    public void setFeatureLabel(String featureLabel) {
        this.featureLabel = featureLabel;
    }

    public BigDecimal getPriceIncrement() {
        return priceIncrement;
    }

    public void setPriceIncrement(BigDecimal priceIncrement) {
        this.priceIncrement = priceIncrement;
    }

    public List<CarTypeFeature> getCarTypeFeatureList() {
        return carTypeFeatureList;
    }

    public void setCarTypeFeatureList(List<CarTypeFeature> carTypeFeatureList) {
        this.carTypeFeatureList = carTypeFeatureList;
    }
}
