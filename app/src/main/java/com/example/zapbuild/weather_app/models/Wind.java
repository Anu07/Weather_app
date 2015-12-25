package com.example.zapbuild.weather_app.models;

/**
 * Created by zapbuild on 24/12/15.
 */

import java.util.HashMap;
import java.util.Map;

public class Wind {

    private Double speed;
    private Integer deg;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The speed
     */
    public Double getSpeed() {
        return speed;
    }

    /**
     * @param speed The speed
     */
    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    /**
     * @return The deg
     */
    public Integer getDeg() {
        return deg;
    }

    /**
     * @param deg The deg
     */
    public void setDeg(Integer deg) {
        this.deg = deg;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
