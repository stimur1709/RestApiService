package ru.safin.restapiservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.safin.restapiservice.models.Measurement;
import ru.safin.restapiservice.models.Sensor;
import ru.safin.restapiservice.repositories.MeasurementRepository;
import ru.safin.restapiservice.repositories.SensorRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Service
public class MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final SensorRepository sensorRepository;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository, SensorRepository sensorRepository) {
        this.measurementRepository = measurementRepository;
        this.sensorRepository = sensorRepository;
    }

    public void addMeasurement(Measurement measurement) {
        bindToSensor(measurement);
        measurementRepository.save(measurement);
    }

    public void bindToSensor(Measurement measurement) {
        Optional<Sensor> sensor = sensorRepository.findByName(measurement.getSensor().getName());
        sensor.ifPresent(measurement::setSensor);
        measurement.setMeasurementTime(LocalDateTime.now());
    }
}
