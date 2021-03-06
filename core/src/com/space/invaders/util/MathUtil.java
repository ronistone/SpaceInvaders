package com.space.invaders.util;

public class MathUtil {

    public static double VELOCITY_EPS = 0.5;
    public static double LIFE_EPS = 0.3;
    public static double ITEM_PROBABILITY = 0.999123123323223;

    public static float toRadians(float degrees){
        return (float) (degrees*(Math.PI/180.0));
    }

}
