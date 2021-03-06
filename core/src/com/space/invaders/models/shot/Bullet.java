package com.space.invaders.models.shot;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.space.invaders.models.Collider;
import com.space.invaders.models.Renderable;
import com.space.invaders.models.ship.Ship;
import com.space.invaders.services.factory.BodyFactory;
import com.space.invaders.view.screen.BaseScreen;
import com.space.invaders.controllers.SpaceInvaders;

public abstract class Bullet implements Collider, Renderable {

    protected float SHOT_WIDHT = BaseScreen.convertToPPM(5);
    protected float SHOT_HEIGHT = BaseScreen.convertToPPM(18);
    public Body body;
    protected World world;
    protected Ship owner;
    protected boolean isAlive;
    protected float x;
    protected float y;
    protected Vector2 direction;
    protected float damage = 0;

    protected SpaceInvaders g;

    public Bullet(float x, float y, Vector2 v, SpaceInvaders game, World world, Ship ship){
        setPosition(x,y);
        g = game;
        this.world = world;
        this.owner = ship;
        isAlive = true;
        direction = v;
        createBody();
    }

    public abstract void render(SpriteBatch batch);
    public abstract void update();
    public abstract void destruct();

    private void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void createBody(){
        BodyFactory bodyFactory = new BodyFactory();
        bodyFactory.createBulletBody(this, world);
    }

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

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public Vector2 getDirection() {
        return direction;
    }

    public void setDirection(Vector2 direction) {
        this.direction = direction;
    }
}
