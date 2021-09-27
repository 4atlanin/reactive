package com.rx.reactive.temperature.events;

import com.rx.reactive.temperature.Temperature;
import org.springframework.context.event.EventListener;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@RestController
public class SseTemperatureController {
    private final Set<SseEmitter> clients = new CopyOnWriteArraySet<>();

    // тут таймаут 30 cекунд
    @GetMapping(value = "/temperature-stream")
    public SseEmitter events(HttpServletRequest request) {
        SseEmitter emitter = new SseEmitter();
        clients.add(emitter);

        Runnable dropEmitterFromClient = () -> clients.remove(emitter);

        emitter.onTimeout(dropEmitterFromClient);
        emitter.onCompletion(dropEmitterFromClient);

        return emitter;
    }

    @Async
    @EventListener
    public void handleMessage(Temperature temperature) {
        System.out.println(Thread.currentThread().getId());
        System.out.println("Size: " + clients.size());
        List<SseEmitter> deadEmitters = new ArrayList<>();
        clients.forEach(sseEmitter -> {
            try {
                sseEmitter.send(temperature, MediaType.APPLICATION_JSON);
            } catch (Exception ex) {
                deadEmitters.add(sseEmitter);
            }
        });

        clients.removeAll(deadEmitters);
    }
}