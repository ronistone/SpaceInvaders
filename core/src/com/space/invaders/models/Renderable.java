package com.space.invaders.models;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Renderable {

    public void loadTexture();
    public void render(SpriteBatch batch);

    public boolean isAlive();
    public void destruct();

    public float getX();
    public float getY();
    public float getWidht();
    public float getHeight();
    public void setX(float x);
    public void setY(float y);
    public void setSize(float widht, float height);

}
