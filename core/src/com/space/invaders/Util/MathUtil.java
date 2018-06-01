package com.space.invaders.Util;

public class MathUtil {

    public static double VELOCITY_EPS = 0.5;
    public static double LIFE_EPS = 0.3;

    public static float toRadians(float degrees){
        return (float) (degrees*(Math.PI/180.0));
    }

}
