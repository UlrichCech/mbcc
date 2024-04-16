package de.ulrichcech.mbcc.server.business.car.entity;

import de.ulrichcech.mbcc.server.platform.persistence.PersistentEntity;
import jakarta.persistence.*;

import java.io.Serial;
import java.math.BigDecimal;

import static de.ulrichcech.mbcc.server.business.car.entity.CarColor.FIND_ALL;

@Table(name = "car_color")
@Entity
@NamedQuery(name = FIND_ALL, query = "select cc from CarColor cc")
public class CarColor extends PersistentEntity {

    @Serial
    private static final long serialVersionUID = -1362171380434874509L;


    public static final String FIND_ALL = "carColor.findAll";


    @Column(name = "car_color_label")
    private String colorLabel;

    @Column(name = "car_color_hex_value")
    private String colorHexValue;

    @Column(name = "car_color_price_increment", scale = 2, precision = 11)
    private BigDecimal priceIncrement = BigDecimal.ZERO;


    public String getColorLabel() {
        return colorLabel;
    }

    public void setColorLabel(String colorLabel) {
        this.colorLabel = colorLabel;
    }

    public String getColorHexValue() {
        return colorHexValue;
    }

    public void setColorHexValue(String colorHexValue) {
        this.colorHexValue = colorHexValue;
    }

    public BigDecimal getPriceIncrement() {
        return priceIncrement;
    }

    public void setPriceIncrement(BigDecimal priceIncrement) {
        this.priceIncrement = priceIncrement;
    }
}
