package com.space.invaders.Models.level;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.space.invaders.Models.Renderable;
import com.space.invaders.Models.Touchable;
import com.space.invaders.Views.GameScreen;
import com.space.invaders.controllers.SpaceInvaders;

public class LevelButton implements Touchable, Renderable {

    private Texture texture;
    private SpaceInvaders g;
    private Level l;

    private float x;
    private float y;
    private float height;
    private float widht;


    public LevelButton(SpaceInvaders g, Level l, float x, float y, float height, float widht) {
        this.g = g;
        this.l = l;
        this.x = x;
        this.y = y;
        this.height = height;
        this.widht = widht;
        loadTexture();
    }

    @Override
    public boolean isTouch(float x, float y) {

        System.out.println(x + " -- " + this.x + "  |  " + y + " -- " + this.y );

        if(x >= this.x && x <= this.x + this.height
                && y >= this.y && y <= this.y + this.widht){
            return true;
        }

        return false;
    }

    @Override
    public void doAction() {
        g.setLevel(l);
        g.changeScreen(GameScreen.class);
    }

    @Override
    public void loadTexture() {
        texture = g.getTexture("logo.png");
        System.out.println(texture.getHeight());
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y, widht, height);
    }

    @Override
    public boolean isAlive() {
        return true;
    }

    @Override
    public void destruct() {

    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public float getWidht() {
        return widht;
    }

    @Override
    public float getHeight() {
        return height;
    }

    @Override
    public void setX(float x) {
        this.x = x;
    }

    @Override
    public void setY(float y) {
        this.y = y;
    }

    @Override
    public void setSize(float widht, float height) {
        this.widht = widht;
        this.height = height;
    }
}
