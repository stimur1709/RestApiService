package ru.safin.restapiservice.dto;

import ru.safin.restapiservice.models.Sensor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class MeasurementDTO {

    @NotNull(message = "Значение не может быть пустым!")
    @Min(value = -100, message = "Значение должно быть от -100!")
    @Max(value = 100, message = "Значение должно быть до 100 градусов")
    private double value;

    @NotNull(message = "Значение не может быть пустым")
    private boolean raining;

    @NotNull(message = "Значение не может быть пустым")
    private Sensor sensor;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
