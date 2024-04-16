package de.ulrichcech.mbcc.server.business.basicdata.boundary.command;

import de.ulrichcech.mbcc.server.business.car.entity.CarEngine;
import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ExternalBasicEngine implements Serializable {

    @Serial
    private static final long serialVersionUID = -736917346763681572L;

    @JsonbProperty("engineId")
    private String engineId;

    @JsonbProperty("engineLabel")
    private String engineLabel;

    @JsonbProperty("kw")
    private Integer kw;

    @JsonbProperty("ps")
    private Integer ps;

    @JsonbProperty("nm")
    private Integer nm;

    @JsonbProperty("umin")
    private Integer umin;

    @JsonbProperty("vmax")
    private Integer vmax;

    @JsonbProperty("priceIncrement")
    private BigDecimal priceIncrement;


    public ExternalBasicEngine() {
        // JSON
    }

    public ExternalBasicEngine(CarEngine carEngine) {
        this.engineId = carEngine.getIdAsString();
        this.engineLabel = carEngine.getEngineLabel();
        this.kw = carEngine.getKw();
        this.ps = carEngine.getPs();
        this.nm = carEngine.getNm();
        this.umin = carEngine.getUmin();
        this.vmax = carEngine.getVmax();
        this.priceIncrement = carEngine.getPriceIncrement();
    }


    public static List<ExternalBasicEngine> createList(List<CarEngine> carEngineList) {
        List<ExternalBasicEngine> resultList = new LinkedList<>();
        for (CarEngine carEngine : carEngineList) {
            resultList.add(new ExternalBasicEngine(carEngine));
        }
        return resultList;
    }


    public String getEngineId() {
        return engineId;
    }

    public String getEngineLabel() {
        return engineLabel;
    }

    public Integer getKw() {
        return kw;
    }

    public Integer getPs() {
        return ps;
    }

    public Integer getNm() {
        return nm;
    }

    public Integer getUmin() {
        return umin;
    }

    public Integer getVmax() {
        return vmax;
    }

    public BigDecimal getPriceIncrement() {
        return priceIncrement;
    }
}
