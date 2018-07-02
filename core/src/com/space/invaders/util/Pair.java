package com.space.invaders.util;

import java.io.Serializable;

public class Pair <A,B> implements Serializable {
    private A objectA;
    private B objectB;


    public Pair(){}

    public Pair(A objectA, B objectB) {
        this.objectA = objectA;
        this.objectB = objectB;
    }


    public A getObjectA() {
        return objectA;
    }

    public void setObjectA(A objectA) {
        this.objectA = objectA;
    }

    public B getObjectB() {
        return objectB;
    }

    public void setObjectB(B objectB) {
        this.objectB = objectB;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "objectA=" + objectA +
                ", objectB=" + objectB +
                '}';
    }
}