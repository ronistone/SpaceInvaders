package com.space.invaders.view.button;

import com.space.invaders.models.level.Level;
import com.space.invaders.view.screen.GameScreen;
import com.space.invaders.controllers.SpaceInvaders;

public class LevelButton extends Button {

    private Level l;

    public LevelButton(SpaceInvaders g, Level l, float x, float y, float height, float widht) {
        super(g, x, y, height, widht);
        this.l = l;
    }

    @Override
    public void doAction() {
        g.setLevel(l);
        g.changeScreen(GameScreen.class);
    }

    @Override
    public void loadTexture() {
        texture = g.getTexture("logo.png");
    }
}
