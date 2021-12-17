package com.rx.reactive.operator.conditional;

import io.reactivex.rxjava3.core.Observable;

public class XXXIfEmpty
{
    public static void main( String[] args )
    {
        Observable<String> items = Observable.just( "Alpha", "Beta" );
        items.filter( s -> s.startsWith( "Z" ) )
             .defaultIfEmpty( "None" )
             .subscribe( System.out::println );

        Observable.just("Alpha", "Beta", "Gamma")
                  .filter(s -> s.startsWith("Z"))
                  .switchIfEmpty(Observable.just("Zeta", "Eta", "Theta"))
                  .subscribe(i -> System.out.println("RECEIVED: " + i),
                      e -> System.out.println("RECEIVED ERROR: " + e));

    }
}
