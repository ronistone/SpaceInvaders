package com.space.invaders.Models.shot;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.space.invaders.controllers.SpaceInvaders;

public abstract class Bullet extends Sprite {

    protected float SHOT_WIDHT = 5;
    protected float SHOT_HEIGHT = 18;
    protected float SPEED = 3000;
    public Body body;
    protected World world;

    protected SpaceInvaders g;

    public Bullet(float x, float y, SpaceInvaders game, World world){
        setPosition(x,y);
        g = game;
        this.world = world;
        createBody();
    }

    public abstract void render(SpriteBatch batch);
    public abstract void update(float delta);
    public abstract void createBody();

    public boolean isLive(float widht, float height){
        if(getX() < 0 || getX() > widht) return false;
        if(getY() < 0 || getY() > height) return false;
        return true;
    }

    public float getSHOT_WIDHT() {
        return SHOT_WIDHT;
    }

    public void setSHOT_WIDHT(float SHOT_WIDHT) {
        this.SHOT_WIDHT = SHOT_WIDHT;
    }

    public float getSHOT_HEIGHT() {
        return SHOT_HEIGHT;
    }

    public void setSHOT_HEIGHT(float SHOT_HEIGHT) {
        this.SHOT_HEIGHT = SHOT_HEIGHT;
    }

    public float getSPEED() {
        return SPEED;
    }

    public void setSPEED(float SPEED) {
        this.SPEED = SPEED;
    }

}
