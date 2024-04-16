package de.ulrichcech.mbcc.server.business.car.entity;

import de.ulrichcech.mbcc.server.platform.persistence.PersistentEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

import java.io.Serial;
import java.math.BigDecimal;

import static de.ulrichcech.mbcc.server.business.car.entity.CarEngine.FIND_ALL;


@Table(name = "car_engine")
@Entity
@NamedQuery(name = FIND_ALL, query = "select ce from CarEngine ce order by ce.kw")
public class CarEngine extends PersistentEntity {
    @Serial
    private static final long serialVersionUID = 5193655273982655466L;


    public static final String FIND_ALL = "carEngine.findAll";


    @Column(name = "car_engine_label")
    private String engineLabel;

    @Column(name = "car_engine_kw")
    private Integer kw;

    @Column(name = "car_engine_ps")
    private Integer ps;

    @Column(name = "car_engine_nm")
    private Integer nm;

    @Column(name = "car_engine_umin")
    private Integer umin;

    @Column(name = "car_engine_vmax")
    private Integer vmax;

    @Column(name = "car_engine_price_increment", scale = 2, precision = 11)
    private BigDecimal priceIncrement = BigDecimal.ZERO;



    public String getEngineLabel() {
        return engineLabel;
    }

    public void setEngineLabel(String engineLabel) {
        this.engineLabel = engineLabel;
    }

    public Integer getKw() {
        return kw;
    }

    public void setKw(Integer kw) {
        this.kw = kw;
    }

    public Integer getPs() {
        return ps;
    }

    public void setPs(Integer ps) {
        this.ps = ps;
    }

    public Integer getNm() {
        return nm;
    }

    public void setNm(Integer nm) {
        this.nm = nm;
    }

    public Integer getUmin() {
        return umin;
    }

    public void setUmin(Integer umin) {
        this.umin = umin;
    }

    public Integer getVmax() {
        return vmax;
    }

    public void setVmax(Integer vmax) {
        this.vmax = vmax;
    }

    public BigDecimal getPriceIncrement() {
        return priceIncrement;
    }

    public void setPriceIncrement(BigDecimal priceIncrement) {
        this.priceIncrement = priceIncrement;
    }
}
