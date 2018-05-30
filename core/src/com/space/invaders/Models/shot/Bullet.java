package com.space.invaders.Models.shot;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.space.invaders.Models.Collider;
import com.space.invaders.Models.ship.Ship;
import com.space.invaders.controllers.SpaceInvaders;

public abstract class Bullet implements Collider{

    protected float SHOT_WIDHT = 5;
    protected float SHOT_HEIGHT = 18;
    protected float SPEED = 3000;
    public Body body;
    protected World world;
    protected Ship owner;
    protected boolean isAlive;
    protected float x;
    protected float y;

    protected SpaceInvaders g;

    public Bullet(float x, float y, SpaceInvaders game, World world, Ship ship){
        setPosition(x,y);
        g = game;
        this.world = world;
        this.owner = ship;
        isAlive = true;
        createBody();
    }

    private void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public abstract void render(SpriteBatch batch);
    public abstract void update(float delta);
    public abstract void createBody();
    public abstract void destruct();

    public boolean isLive(float widht, float height){
        if(getX() < 0 || getX() > widht) return false;
        if(getY() < 0 || getY() > height) return false;
        return isAlive;
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

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Ship getOwner() {
        return owner;
    }

    public void setOwner(Ship owner) {
        this.owner = owner;
    }
}
