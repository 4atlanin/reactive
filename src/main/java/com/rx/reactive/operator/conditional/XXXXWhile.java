package com.rx.reactive.operator.conditional;

import io.reactivex.rxjava3.core.Observable;

public class XXXXWhile
{
    public static void main( String[] args )
    {
        Observable.range( 1, 100 )
                  .skipWhile( i -> i < 3 )
                  .takeWhile( i -> i < 5 )
                  .subscribe( i -> System.out.println( "RECEIVED: " + i ) );
    }
}
