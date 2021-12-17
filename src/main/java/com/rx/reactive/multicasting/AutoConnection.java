package com.rx.reactive.multicasting;

import io.reactivex.rxjava3.core.Observable;
import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class AutoConnection
{
    public static void main( String[] args )
    {
        refCount();
    }

    @SneakyThrows
    public static void refCount()
    {
        // .publish().refCount() тоже самое что и .share()
        Observable<Long> ints =
            Observable.interval( 1, TimeUnit.SECONDS ).publish().refCount();
//Observer 1
        ints.take( 5 )
            .subscribe( l -> System.out.println( "Observer 1: " + l ) );
        sleep( 3000 );
//Observer 2
        ints.take( 2 )
            .subscribe( l -> System.out.println( "Observer 2: " + l ) );
        sleep( 3000 );
//There should be no more subscribers at this point
//Observer 3
        ints.subscribe( l -> System.out.println( "Observer 3: " + l ) );
        sleep( 3000 );
    }
}
