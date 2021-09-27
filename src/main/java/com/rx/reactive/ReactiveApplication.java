package com.rx.reactive;

import io.reactivex.rxjava3.core.Observable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReactiveApplication {

    public static void main(String[] args) {
        Observable<String> myStrings =
                Observable.just("Alpha", "Beta", "Gamma");
        myStrings.subscribe(s -> System.out.println(s));

        SpringApplication.run(ReactiveApplication.class, args);
    }
}
