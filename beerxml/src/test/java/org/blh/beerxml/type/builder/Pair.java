package org.blh.beerxml.type.builder;

/**
 * Created by Erik Larkö at 5/17/13:9:45 PM
 */
public class Pair<T, U> {
    T fst;
    U snd;

    public Pair(T fst, U snd) {
        this.fst = fst;
        this.snd = snd;
    }
}
