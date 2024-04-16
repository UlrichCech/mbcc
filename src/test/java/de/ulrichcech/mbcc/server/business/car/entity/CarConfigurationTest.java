package de.ulrichcech.mbcc.server.business.car.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class CarConfigurationTest {

    @Test
    void calculatePriceWithCarTypeAndCarClassAndColor() {
        CarType carType = new CarType();
        carType.setId(UUID.randomUUID());
        carType.setTypeLabel("A-Klasse");
        carType.setBasePrice(new BigDecimal("30000.00"));

        CarClass carClass = new CarClass();
        carClass.setId(UUID.randomUUID());
        carClass.setClassLabel("Limousine");
        carClass.setPriceIncrement(new BigDecimal("0.00"));

        CarEngine carEngine = new CarEngine();
        carEngine.setId(UUID.randomUUID());
        carEngine.setEngineLabel("150 KW");
        carEngine.setKw(150);
        carEngine.setPs(204);
        carEngine.setPriceIncrement(new BigDecimal("5000.00"));

        CarColor carColor = new CarColor();
        carColor.setId(UUID.randomUUID());
        carColor.setColorLabel("Schwarz");
        carColor.setColorHexValue("#000000");
        carColor.setPriceIncrement(new BigDecimal("750.00"));

        CarSpecialFeature specialFeaturePanoramadach = new CarSpecialFeature();
        specialFeaturePanoramadach.setId(UUID.randomUUID());
        specialFeaturePanoramadach.setFeatureLabel("Panoramadach");
        specialFeaturePanoramadach.setPriceIncrement(new BigDecimal("980.00"));

        CarConfiguration carConfiguration = new CarConfiguration();

        // base price (= 30.000,-)
        carConfiguration.setCarType(carType);
        carConfiguration.updateOverallPrice();
        assertThat(carConfiguration.getOverallPrice(), is(new BigDecimal("30000.00")));

        // add carClass (no extra cost)
        carConfiguration.setCarClass(carClass);
        carConfiguration.updateOverallPrice();
        assertThat(carConfiguration.getOverallPrice(), is(new BigDecimal("30000.00")));

        // add engine (plus 5000,-)
        carConfiguration.setCarEngine(carEngine);
        carConfiguration.updateOverallPrice();
        assertThat(carConfiguration.getOverallPrice(), is(new BigDecimal("35000.00")));

        // add color (plus 750,-)
        carConfiguration.setCarColor(carColor);
        carConfiguration.updateOverallPrice();
        assertThat(carConfiguration.getOverallPrice(), is(new BigDecimal("35750.00")));

        // add Panoramadach (plus 980,-)
        carConfiguration.addNewSpecialFeature(specialFeaturePanoramadach);
        carConfiguration.updateOverallPrice();
        assertThat(carConfiguration.getOverallPrice(), is(new BigDecimal("36730.00")));
    }
}