package com.space.invaders.Models.ship;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.space.invaders.Models.shot.Shot;
import com.space.invaders.controllers.SpaceInvaders;

public abstract class Ship {

    private float X, Y;
    private Texture shipTexture;
    private float WIDTH = 100;
    private float HEIGHT = -90;
    private float LATERAL_SPEED = 20;
    private float SPEED = 6;
    private float SHOT_RATE = 5;
    private float LAST_SHOT;

    protected SpaceInvaders g;

    public Ship(float X, float Y, SpaceInvaders game){
        this.X = X;
        this.Y = Y;
        g = game;
        loadTexture();
    }

    public void render(SpriteBatch sb){
            moveForward();
            sb.draw(shipTexture,X,Y,WIDTH,HEIGHT);
    }

    public abstract void loadTexture();

    private void moveForward(){
        if(Y + HEIGHT> 0) {
            Y -= SPEED;
        }
    }
    public void moveLeft(float scale, float leftLimit){
        if(X > leftLimit) {
            X -= (scale * LATERAL_SPEED);
        }
    }
    public void moveRight(float scale, float rightLimit){
        if(X+WIDTH < rightLimit) {
            X += (scale * LATERAL_SPEED);
        }
    }

    public void dispose(){
        shipTexture.dispose();
    }

    public float getX() {
        return X;
    }

    public void setX(float x) {
        X = x;
    }

    public float getY() {
        return Y;
    }

    public void setY(float y) {
        Y = y;
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

    public abstract Shot shot();
}


