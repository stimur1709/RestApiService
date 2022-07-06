package ru.safin.restapiservice.models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "value")
    @NotNull(message = "Значение не может быть пустым!")
    @Min(value = -100, message = "Значение должно быть от -100!")
    @Max(value = 100, message = "Значение должно быть до 100 градусов")
    private double value;

    @NotNull(message = "Значение не может быть пустым")
    @Column(name = "raining")
    private boolean raining;

    @NotNull(message = "Значение не может быть пустым")
    @Column(name = "measurement_time")
    private LocalDateTime measurementTime;

    @NotNull(message = "Значение не может быть пустым")
    @ManyToOne
    @JoinColumn(name = "sensor", referencedColumnName = "name")
    private Sensor sensor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public LocalDateTime getMeasurementTime() {
        return measurementTime;
    }

    public void setMeasurementTime(LocalDateTime measurementTime) {
        this.measurementTime = measurementTime;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
