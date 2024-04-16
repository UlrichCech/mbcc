package de.ulrichcech.mbcc.server.business.car.entity;

import de.ulrichcech.mbcc.server.platform.persistence.PersistentEntity;
import jakarta.persistence.*;

import java.io.Serial;
import java.math.BigDecimal;

@Entity
@Table(name = "car_type_color")
public class CarTypeColor extends PersistentEntity {

    @Serial
    private static final long serialVersionUID = -575671166746891147L;


    @ManyToOne
    @JoinColumn(name = "car_type_color_type_id", nullable = false)
    private CarType carType;

    @ManyToOne
    @JoinColumn(name = "car_type_color_color_id", nullable = false)
    private CarColor carColor;

    @Column(name = "car_type_color_price_increment", precision = 11, scale = 2)
    private BigDecimal priceIncrement = BigDecimal.ZERO;

    public CarTypeColor() {
        // JPA
    }


    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public CarColor getCarColor() {
        return carColor;
    }

    public void setCarColor(CarColor carColor) {
        this.carColor = carColor;
    }

    public BigDecimal getPriceIncrement() {
        return priceIncrement;
    }

    public void setPriceIncrement(BigDecimal priceIncrement) {
        this.priceIncrement = priceIncrement;
    }
}