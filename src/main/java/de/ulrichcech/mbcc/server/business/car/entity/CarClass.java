package de.ulrichcech.mbcc.server.business.car.entity;


import de.ulrichcech.mbcc.server.platform.persistence.PersistentEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

import java.io.Serial;
import java.math.BigDecimal;

import static de.ulrichcech.mbcc.server.business.car.entity.CarClass.FIND_ALL;


@Table(name = "car_class")
@Entity
@NamedQuery(name = FIND_ALL, query = "select cc from CarClass cc order by cc.classLabel")
public class CarClass extends PersistentEntity {

    @Serial
    private static final long serialVersionUID = -1527813997437207672L;


    public static final String FIND_ALL = "carClass.findAll";


    @Column(name = "car_class_label")
    private String classLabel;

    @Column(name = "car_class_price_increment", scale = 2, precision = 11)
    private BigDecimal priceIncrement = BigDecimal.ZERO;


    public String getClassLabel() {
        return classLabel;
    }

    public void setClassLabel(String classLabel) {
        this.classLabel = classLabel;
    }

    public BigDecimal getPriceIncrement() {
        return priceIncrement;
    }

    public void setPriceIncrement(BigDecimal priceIncrement) {
        this.priceIncrement = priceIncrement;
    }
}
