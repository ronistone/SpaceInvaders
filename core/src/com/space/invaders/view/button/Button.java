package com.space.invaders.view.button;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.space.invaders.models.Renderable;
import com.space.invaders.models.Touchable;
import com.space.invaders.controllers.SpaceInvaders;
import com.space.invaders.models.level.Level;

public abstract class Button implements Touchable, Renderable {

    protected Texture texture;
    protected float x;
    protected float y;
    protected float widht;
    protected float height;
    protected float finalWidht;
    protected SpaceInvaders g;
    protected Level l;
    protected BitmapFont font;
    protected GlyphLayout glyphLayout;
    protected String msg;


    public Button(SpaceInvaders game, float x, float y, float widht, float height) {
        this.g = game;
        this.x = x;
        this.y = y;
        this.widht = widht;
        this.height = height;
        finalWidht = widht;
        loadTexture();

        font = new BitmapFont();
        glyphLayout = new GlyphLayout();
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
        if(x >= this.x && x <= this.x + this.finalWidht
                && y >= this.y && y <= this.y + this.height){
            return true;
        }

        return false;
    }

    @Override
    public void destruct() {
    }
}
