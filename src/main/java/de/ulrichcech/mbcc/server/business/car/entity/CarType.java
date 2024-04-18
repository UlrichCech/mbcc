package de.ulrichcech.mbcc.server.business.car.entity;

import de.ulrichcech.mbcc.server.platform.persistence.PersistentEntity;
import jakarta.persistence.*;

import java.io.Serial;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static de.ulrichcech.mbcc.server.business.car.entity.CarType.FIND_ALL;


@Table(name = "car_type")
@Entity
@NamedQuery(name = FIND_ALL, query = "select ct from CarType ct")
public class CarType extends PersistentEntity {

    @Serial
    private static final long serialVersionUID = -7084491266485895374L;


    public static final String FIND_ALL = "carType.findAll";


    @Column(name = "car_type_label")
    private String typeLabel;

    @Column(name = "car_type_base_price", scale = 2, precision = 11)
    private BigDecimal basePrice = BigDecimal.ZERO;

    @OneToMany(mappedBy = "carType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarTypeColor> typeColors = new ArrayList<>();

    @OneToMany(mappedBy = "carType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarTypeEngine> typeEngines = new ArrayList<>();


    public CarType() {
        // JPA
    }

    public CarType(String typeLabel, BigDecimal basePrice) {
        this.typeLabel = typeLabel;
        this.basePrice = basePrice;
    }


    public String getTypeLabel() {
        return typeLabel;
    }

    public void setTypeLabel(String typeLabel) {
        this.typeLabel = typeLabel;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public List<CarTypeColor> getTypeColors() {
        return typeColors;
    }

    public void setTypeColors(List<CarTypeColor> typeColors) {
        this.typeColors = typeColors;
    }

    public List<CarTypeEngine> getTypeEngines() {
        return typeEngines;
    }

    public void setTypeEngines(List<CarTypeEngine> typeEngines) {
        this.typeEngines = typeEngines;
    }
}
