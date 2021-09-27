package com.rx.reactive.temperature.reactive;

import com.rx.reactive.temperature.Temperature;
import io.reactivex.Observable;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component("temperatureSensorRx")
public class RxTemperatureSensor {
    private final Random rnd = new Random();

    private final Observable<Temperature> dataStream = Observable
            .range(0, Integer.MAX_VALUE)
            // concatMap ->
            .concatMap(tick -> Observable
                    .just(tick)
                    .delay(rnd.nextInt(5000), TimeUnit.MILLISECONDS)
                    .map(tickValue -> this.probe()))
            .publish()  // рассылает из 1 потока события во все подключённые потоки
            .refCount();  // не читает данные, если нет ни однго подписчика

    private Temperature probe() {
        return new Temperature(16 + rnd.nextGaussian() * 10);
    }

    public Observable<Temperature> temperatureStream() {
        return dataStream;
    }
}
