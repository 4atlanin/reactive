package com.rx.reactive.temperature.reactive;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
public class RxSseTemperatureController {
    private final RxTemperatureSensor rxTemperatureSensor;
    private final RxSseEmitter rxSseEmitter;

    @GetMapping(value = "/rx-temperature-stream")
    public SseEmitter events(HttpServletRequest request) {

        final RxTemperatureSensor rxTemperatureSensor = new RxTemperatureSensor();
        rxTemperatureSensor.temperatureStream()
                .subscribe(rxSseEmitter.getSubscriber());

        return rxSseEmitter;
    }
}