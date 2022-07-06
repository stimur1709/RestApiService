import dto.MeasurementDTO;
import dto.MeasurementResponse;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SensorClient {
    public static void main(String[] args) {
        Random random = new Random();
        registrationSensor("sensor1");
        for(int i = 0; i < 100; i++) {
            sendMeasurement(Math.random() * (-100 - 100) + 100, random.nextBoolean(), "sensor1");
        }

        List<Double> temperatures = getMeasurement();
        drawChart(temperatures);
    }

    public static void registrationSensor(String name) {
        String url = "http://localhost:8080/sensors/registration";
        Map<String, Object> jsonData  = new HashMap<>();
        jsonData.put("name", name);
        makeRequest(url, jsonData);
    }

    public static void sendMeasurement(double value, boolean raining, String name) {
        String url = "http://localhost:8080/measurements/add";
        Map<String, Object> jsonData = new HashMap<>();
        jsonData.put("value", value);
        jsonData.put("raining", raining);
        jsonData.put("sensor", Map.of("name", name));
        makeRequest(url, jsonData);
    }

    public static void makeRequest(String url, Map<String, Object> jsonData) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> request = new HttpEntity<>(jsonData, headers);
        try {
            restTemplate.postForObject(url, request, String.class);
        } catch (HttpClientErrorException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<Double> getMeasurement() {
        String url = "http://localhost:8080/measurements";
        RestTemplate restTemplate = new RestTemplate();
        MeasurementResponse measurements = restTemplate.getForObject(url, MeasurementResponse.class);

        if (measurements == null)
            return Collections.emptyList();

        return measurements.getMeasurements().stream().map(MeasurementDTO::getValue).collect(Collectors.toList());
    }

    public static void drawChart(List<Double> temperatures) {
        double[] xData = IntStream.range(0, temperatures.size()).asDoubleStream().toArray();
        double[] yData = temperatures.stream().mapToDouble(x -> x).toArray();

        XYChart xyChart = QuickChart.getChart("Temperatures", "X", "Y", "temperature",
                xData, yData);

        new SwingWrapper(xyChart).displayChart();
    }
}
