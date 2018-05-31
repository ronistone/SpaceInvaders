package com.space.invaders.Views;

import com.badlogic.gdx.ScreenAdapter;
import com.space.invaders.controllers.SpaceInvaders;

public abstract class BaseScreen extends ScreenAdapter {
    public static final float PPM = 40;
    public static final float VIRTUAL_WIDHT = 768/ PPM;
    public static final float VIRTUAL_HEIGHT = 1024/ PPM;
    protected SpaceInvaders game;

    public BaseScreen(SpaceInvaders g) {
        game = g;
    }

    public static float convertToPPM(float m){
        return m/PPM;
    }
}
