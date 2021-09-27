package com.rx.reactive.temperature.reactive;

import com.rx.reactive.temperature.Temperature;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@Component
public class RxSseEmitter extends SseEmitter {
    static final long SSE_SESSION_TIMEOUT = 30 * 60 * 1000L;

    private final Observer<Temperature> subscriber;

    RxSseEmitter() {
        super(SSE_SESSION_TIMEOUT);

        this.subscriber = new Observer<Temperature>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(Temperature temperature) {
                try {
                    RxSseEmitter.this.send(temperature);
                } catch (IOException ex) {
                    onComplete();
                }
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        };

        onCompletion(subscriber::onComplete);
        onTimeout(subscriber::onComplete);
    }

    Observer<Temperature> getSubscriber() {
        return subscriber;
    }
}
