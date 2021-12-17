package com.rx.reactive;

import io.reactivex.rxjava3.core.Observable;

public class DefferObservable {
    private static int start = 1;
    private static int count = 5;

    public static void main(String[] args) throws InterruptedException {
        // Defer позволяет отслеживать измненение переменных для рэйнджа, и сразу применять их для новых потребителей
        Observable<Integer> source = Observable.defer(() ->
                Observable.range(start, count));
        source.subscribe(i -> System.out.println("Observer 1: " + i));

        count = 10;
        source.subscribe(i -> System.out.println("Observer 2: " + i));
    }
}
