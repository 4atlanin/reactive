package com.rx.reactive;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observables.ConnectableObservable;

import java.util.concurrent.TimeUnit;

public class IntervalObservable {
    public static void main(String[] args) throws InterruptedException {
        // cold Observer
        // У каждого подписчика свой таймер
/*        Observable<Long> seconds = Observable.interval(1, TimeUnit.SECONDS);

        seconds.subscribe(l -> System.out.println("Observer 1: " + l));
        Thread.sleep(3000);

        seconds.subscribe(l -> System.out.println("Observer 2: " + l));
        Thread.sleep(3000);*/
        coTest();
    }

    public static void coTest() throws InterruptedException {
        ConnectableObservable<Long> seconds =
                Observable.interval(1, TimeUnit.SECONDS).publish();
//observer 1
        seconds.subscribe(l -> System.out.println("Observer 1: " + l));
        seconds.connect();
//sleep 3 seconds
        Thread.sleep(3000);
//observer 2
        seconds.subscribe(l -> System.out.println("Observer 2: " + l));
//sleep 3 seconds
        Thread.sleep(3000);
    }
}
