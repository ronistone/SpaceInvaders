package com.space.invaders.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.space.invaders.controllers.SpaceInvaders;

public abstract class BaseScreen extends ScreenAdapter {
    public static final float PPM = 40;
    public static final float VIRTUAL_WIDHT = Gdx.graphics.getWidth()/ PPM;
    public static final float VIRTUAL_HEIGHT = Gdx.graphics.getHeight()/ PPM;
    protected SpaceInvaders game;

    public BaseScreen(SpaceInvaders g) {
        game = g;
    }

    public static float convertToPPM(float m){
        return m/PPM;
    }
}
