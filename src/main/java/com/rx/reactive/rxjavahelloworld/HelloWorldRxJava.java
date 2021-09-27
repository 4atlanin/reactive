package com.rx.reactive.rxjavahelloworld;

import io.reactivex.Observable;

public class HelloWorldRxJava {
    public static void main(String[] args) {
        Observable.create(sub -> {
            sub.onNext("Hello world!");   // Observable генерит данные, которые прилетают всем subscriberam
            sub.onComplete();
        }).subscribe(System.out::println,
                System.err::println,
                () -> System.out.println("Done")
        );
    }
}