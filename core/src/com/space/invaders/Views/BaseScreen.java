package com.space.invaders.Views;

import com.badlogic.gdx.ScreenAdapter;
import com.space.invaders.controllers.SpaceInvaders;

public abstract class BaseScreen extends ScreenAdapter {
    public static final float VIRTUAL_WIDHT = 768, VIRTUAL_HEIGHT = 1024;
    protected SpaceInvaders game;

    public BaseScreen(SpaceInvaders g) {
        game = g;
    }
}
