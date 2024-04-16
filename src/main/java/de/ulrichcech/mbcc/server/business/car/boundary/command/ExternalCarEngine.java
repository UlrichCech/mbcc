package de.ulrichcech.mbcc.server.business.car.boundary.command;

import de.ulrichcech.mbcc.server.business.car.entity.CarEngine;
import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.math.BigDecimal;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ExternalCarEngine {

    @JsonbProperty("id")
    private String id;

    @JsonbProperty("label")
    private String label;

    @JsonbProperty("priceIncrement")
    private BigDecimal priceIncrement;

    @JsonbProperty("kw")
    private Integer kw;

    @JsonbProperty("ps")
    private Integer ps;


    public ExternalCarEngine() {
        // JSON
    }

    public ExternalCarEngine(CarEngine carEngine) {
        this.id = carEngine.getIdAsString();
        this.label = carEngine.getEngineLabel();
        this.priceIncrement = carEngine.getPriceIncrement();
        this.kw = carEngine.getKw();
        this.ps = carEngine.getPs();
    }


    public String getLabel() {
        return label;
    }

    public BigDecimal getPriceIncrement() {
        return priceIncrement;
    }

    public Integer getKw() {
        return kw;
    }

    public Integer getPs() {
        return ps;
    }
}
