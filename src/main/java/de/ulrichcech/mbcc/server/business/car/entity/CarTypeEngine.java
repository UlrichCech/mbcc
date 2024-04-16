package de.ulrichcech.mbcc.server.business.car.entity;

import de.ulrichcech.mbcc.server.platform.persistence.PersistentEntity;
import jakarta.persistence.*;

import java.io.Serial;
import java.math.BigDecimal;

@Entity
@Table(name = "car_type_engine")
public class CarTypeEngine extends PersistentEntity {

    @Serial
    private static final long serialVersionUID = -1612488442623381670L;


    @ManyToOne
    @JoinColumn(name = "car_type_engine_type_id", nullable = false)
    private CarType carType;

    @ManyToOne
    @JoinColumn(name = "car_type_engine_engine_id", nullable = false)
    private CarEngine carEngine;

    @Column(name = "car_type_engine_price_increment", precision = 11, scale = 2)
    private BigDecimal priceIncrement = BigDecimal.ZERO;

    public CarTypeEngine() {
        // JPA
    }


    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public CarEngine getCarEngine() {
        return carEngine;
    }

    public void setCarEngine(CarEngine carEngine) {
        this.carEngine = carEngine;
    }

    public BigDecimal getPriceIncrement() {
        return priceIncrement;
    }

    public void setPriceIncrement(BigDecimal priceIncrement) {
        this.priceIncrement = priceIncrement;
    }
}