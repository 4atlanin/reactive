package com.rx.reactive;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observables.ConnectableObservable;

public class ConnectableObservableExample {
    public static void main(String[] args) throws InterruptedException {

        // publish вернёт ConnectableObservable
        ConnectableObservable<String> source = Observable.just("One", "Two", "Three").doOnNext(value -> Thread.sleep(3000)).publish();

        // для ConnectableObservable подписка не означает начало отправки значений
        source.subscribe(s -> {
            System.out.println("Observer 1: " + s);
        });
        source.map(String::length).subscribe(s -> System.out.println("Observer 2: " + s));

        // отправка начнётся только после вызова connect().
        // Сделано, чтобы можно было сразу засетапить всех подписчиков, а потом уже начинать рассылку.
        source.connect();

        Thread.sleep(4000);

        // Не отработает, так как just() выплёвывает сразу всё. После конекта не поступает новых значений.
        source.subscribe(s -> System.out.println("Observer 3: " + s));

        Thread.sleep(4000);
    }
}
