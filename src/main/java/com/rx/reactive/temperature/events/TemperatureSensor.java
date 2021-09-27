package com.rx.reactive.temperature.events;

import com.rx.reactive.temperature.Temperature;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component("temperatureSensorEvent")
@RequiredArgsConstructor
public class TemperatureSensor {
    private final ApplicationEventPublisher publisher;
    private final Random rnd = new Random();
    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    @PostConstruct
    public void startProcessing() {
        this.executorService.schedule(this::probe, 1, TimeUnit.SECONDS);
    }

    public void probe() {
        double temperature = 16 + rnd.nextGaussian() * 10;
        publisher.publishEvent(new Temperature(temperature));

        executorService.schedule(this::probe, 1000, TimeUnit.MILLISECONDS);
    }
}
