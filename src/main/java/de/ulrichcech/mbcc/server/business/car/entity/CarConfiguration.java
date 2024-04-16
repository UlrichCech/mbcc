package de.ulrichcech.mbcc.server.business.car.entity;

import de.ulrichcech.mbcc.server.platform.persistence.PersistentEntity;
import de.ulrichcech.mbcc.server.platform.persistence.UUIDAttributeConverter;
import jakarta.persistence.*;

import java.io.Serial;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static de.ulrichcech.mbcc.server.business.car.entity.CarConfiguration.FETCH_ALL_BY_USERID;

@Table(name = "car_configuration")
@Entity
@NamedQuery(name = FETCH_ALL_BY_USERID, query = "select cc from CarConfiguration cc where cc.userId = :usrId")
public class CarConfiguration extends PersistentEntity {

    public static final String FETCH_ALL_BY_USERID = "carConfiguration.fetchAllByUserId";

    public static final String QUERY_PARAMETER_USERID = "userId";

    @Serial
    private static final long serialVersionUID = -1679069377329674496L;

    @Column(name = "user_id", nullable = false, length = 40)
    @Convert(converter = UUIDAttributeConverter.class)
    private UUID userId;

    @Column(name = "car_configuration_label")
    private String configurationLabel;

    @Column(name = "car_configuration_description")
    private String configurationDescription;

    @Column(name = "car_configuration_overall_price", scale = 2, precision = 11)
    private BigDecimal overallPrice = BigDecimal.ZERO;

    @ManyToOne
    @JoinColumn(name = "car_type_id")
    private CarType carType;

    @ManyToOne
    @JoinColumn(name = "car_class_id")
    private CarClass carClass;

    @ManyToOne
    @JoinColumn(name = "car_engine_id")
    private CarEngine carEngine;

    @ManyToOne
    @JoinColumn(name = "car_color_id")
    private CarColor carColor;

    @OneToMany(mappedBy = "carConfiguration")
    private List<CarConfigurationFeature> features = new ArrayList<>();


    public BigDecimal calculatePrice() {
        if (carType == null){
            return BigDecimal.ZERO;
        }

        // start with base-price of the car-type
        BigDecimal totalPrice = carType.getBasePrice();

        // check for special engine config prices
        boolean specialEnginePriceFound = false;
        for (CarTypeEngine carTypeEngine : carType.getTypeEngines()) {
            if (carTypeEngine.getCarEngine().equals(this.getCarEngine())) {
                totalPrice = totalPrice.add(carTypeEngine.getPriceIncrement());
                specialEnginePriceFound = true;
                break;
            }
        }
        // if no special engine config prices exists, use the default price increment of the engine
        if (!specialEnginePriceFound && (carEngine != null)) {
            totalPrice = totalPrice.add(carEngine.getPriceIncrement());
        }

        // check for special color config prices
        boolean specialColorPriceFound = false;
        for (CarTypeColor carTypeColor : carType.getTypeColors()) {
            if (carTypeColor.getCarColor().equals(this.getCarColor())) {
                totalPrice = totalPrice.add(carTypeColor.getPriceIncrement());
                specialColorPriceFound = true;
                break;
            }
        }
        // if no special color config prices exists, use the default price increment of the color
        if (!specialColorPriceFound && (carColor != null)) {
            totalPrice = totalPrice.add(carColor.getPriceIncrement());
        }

        // calculate the prices for special features
        for (CarConfigurationFeature carConfigurationFeature : features) {
            CarSpecialFeature feature = carConfigurationFeature.getSpecialFeature();
            BigDecimal priceIncrement = feature.getCarTypeFeatureList().stream()
                    .filter(tf -> tf.getCarType().equals(carType))
                    .findFirst()
                    .map(CarTypeFeature::getPriceIncrement)
                    .orElse(feature.getPriceIncrement());
            totalPrice = totalPrice.add(priceIncrement);
        }

        return totalPrice;
    }

    public void updateOverallPrice() {
        this.overallPrice = calculatePrice();
    }

    public void addNewSpecialFeature(CarSpecialFeature newCarSpecialFeature) {
        final var newCarConfigurationFeature =
                new CarConfigurationFeature(new ConfigurationFeatureId(this.getId(), newCarSpecialFeature.getId()),
                                            this,
                                            newCarSpecialFeature);
        getFeatures().add(newCarConfigurationFeature);
    }


    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getConfigurationLabel() {
        return configurationLabel;
    }

    public void setConfigurationLabel(String configurationLabel) {
        this.configurationLabel = configurationLabel;
    }

    public String getConfigurationDescription() {
        return configurationDescription;
    }

    public void setConfigurationDescription(String configurationDescription) {
        this.configurationDescription = configurationDescription;
    }

    public BigDecimal getOverallPrice() {
        return overallPrice;
    }

    public void setOverallPrice(BigDecimal overallPrice) {
        this.overallPrice = overallPrice;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public CarClass getCarClass() {
        return carClass;
    }

    public void setCarClass(CarClass carClass) {
        this.carClass = carClass;
    }

    public CarEngine getCarEngine() {
        return carEngine;
    }

    public void setCarEngine(CarEngine carEngine) {
        this.carEngine = carEngine;
    }

    public CarColor getCarColor() {
        return carColor;
    }

    public void setCarColor(CarColor carColor) {
        this.carColor = carColor;
    }

    public List<CarConfigurationFeature> getFeatures() {
        return features;
    }

    public void setFeatures(List<CarConfigurationFeature> features) {
        this.features = features;
    }

}
