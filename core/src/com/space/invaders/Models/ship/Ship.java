package com.space.invaders.Models.ship;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.space.invaders.Models.Collider;
import com.space.invaders.Models.shot.Bullet;
import com.space.invaders.Models.weapon.Weapon;
import com.space.invaders.Views.BaseScreen;
import com.space.invaders.controllers.SpaceInvaders;
import com.space.invaders.services.factory.BodyFactory;
import com.space.invaders.services.movement.MovementService;

import java.util.List;
import java.util.Map;

public abstract class Ship implements Collider  {

    private Texture shipTexture;
    private float WIDTH = BaseScreen.convertToPPM(100);
    private float HEIGHT = BaseScreen.convertToPPM(-90);
    private float LATERAL_SPEED = BaseScreen.convertToPPM(100);
    private float SPEED = BaseScreen.convertToPPM(6);
    private float SHOT_RATE = 5;
    private float LAST_SHOT;
    public Body body;
    protected World world;
    protected boolean isAlive;
    protected float x;
    protected float y;

    protected SpaceInvaders g;
    protected MovementService movement;
    protected Weapon weapon;

    public Ship(float x, float y, SpaceInvaders game){
        setX(x);
        setY(y);
        g = game;
        isAlive = true;
        loadTexture();
    }

    public void render(SpriteBatch sb){
//            moveForward();
        setX(body.getPosition().x-getWIDTH()/2);
        setY(body.getPosition().y-getHEIGHT()/2);
        sb.draw(shipTexture,getX(),getY(),WIDTH,HEIGHT);
    }

    public abstract void loadTexture();

    public List<Bullet> shoot(){
        return weapon.shoot();
    }

    @Override
    public void collide(Object a) {
        if(a instanceof Bullet){
            Bullet b = (Bullet) a;
            if(!b.getOwner().equals(this)) {
                setAlive(false);
            }
        }
    }

    public void createBody() {
        this.body = g.getBodyFactory().createSimpleShipBody(this, world);
    }

    public void destruct() {
        world.destroyBody(body);
    }

    public void move(float delta) {
        if(movement != null){
            movement.move(this);
        }
    }

    public void dispose(){
        shipTexture.dispose();
    }

    public Texture getShipTexture() {
        return shipTexture;
    }

    public void setShipTexture(Texture shipTexture) {
        this.shipTexture = shipTexture;
    }

    public float getWIDTH() {
        return WIDTH;
    }

    public void setWIDTH(float WIDTH) {
        this.WIDTH = WIDTH;
    }

    public float getHEIGHT() {
        return HEIGHT;
    }

    public void setHEIGHT(float HEIGHT) {
        this.HEIGHT = HEIGHT;
    }

    public float getLATERAL_SPEED() {
        return LATERAL_SPEED;
    }

    public void setLATERAL_SPEED(float LATERAL_SPEED) {
        this.LATERAL_SPEED = LATERAL_SPEED;
    }

    public float getSPEED() {
        return SPEED;
    }

    public void setSPEED(float SPEED) {
        this.SPEED = SPEED;
    }

    public float getSHOT_RATE() {
        return SHOT_RATE;
    }

    public void setSHOT_RATE(float SHOT_RATE) {
        this.SHOT_RATE = SHOT_RATE;
    }

    public float getLAST_SHOT() {
        return LAST_SHOT;
    }

    public void setLAST_SHOT(float LAST_SHOT) {
        this.LAST_SHOT = LAST_SHOT;
    }

    public MovementService getMovement() {
        return movement;
    }

    public void setMovement(MovementService movement) {
        this.movement = movement;
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

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public SpaceInvaders getGame() {
        return g;
    }
}


