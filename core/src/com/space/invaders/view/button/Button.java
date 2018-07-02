package com.space.invaders.view.button;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.space.invaders.Models.Renderable;
import com.space.invaders.Models.Touchable;
import com.space.invaders.controllers.SpaceInvaders;

public abstract class Button implements Touchable, Renderable {

    protected Texture texture;
    protected float x;
    protected float y;
    protected float widht;
    protected float height;
    protected SpaceInvaders g;


    public Button(SpaceInvaders game, float x, float y, float widht, float height) {
        this.g = game;
        this.x = x;
        this.y = y;
        this.widht = widht;
        this.height = height;
        loadTexture();
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
        this.height = height;
        this.widht = widht;
    }

    @Override
    public boolean isTouch(float x, float y) {
        if(x >= this.x && x <= this.x + this.height
                && y >= this.y && y <= this.y + this.widht){
            return true;
        }

        return false;
    }

    @Override
    public void destruct() {
    }
}
