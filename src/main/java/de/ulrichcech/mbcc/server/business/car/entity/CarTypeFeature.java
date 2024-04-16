package de.ulrichcech.mbcc.server.business.car.entity;

import de.ulrichcech.mbcc.server.platform.persistence.PersistentEntity;
import jakarta.persistence.*;

import java.io.Serial;
import java.math.BigDecimal;

@Entity
@Table(name = "car_type_feature")
public class CarTypeFeature extends PersistentEntity {

    @Serial
    private static final long serialVersionUID = 8126297688248792019L;


    @ManyToOne
    @JoinColumn(name = "car_type_id", nullable = false)
    private CarType carType;

    @ManyToOne
    @JoinColumn(name = "car_feature_id", nullable = false)
    private CarSpecialFeature specialFeature;

    @Column(name = "car_type_feature_price_increment", precision = 11, scale = 2)
    private BigDecimal priceIncrement = BigDecimal.ZERO;



    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public CarSpecialFeature getSpecialFeature() {
        return specialFeature;
    }

    public void setSpecialFeature(CarSpecialFeature specialFeature) {
        this.specialFeature = specialFeature;
    }

    public BigDecimal getPriceIncrement() {
        return priceIncrement;
    }

    public void setPriceIncrement(BigDecimal priceIncrement) {
        this.priceIncrement = priceIncrement;
    }
}