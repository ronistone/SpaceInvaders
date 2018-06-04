package com.space.invaders.Models.item;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.space.invaders.Models.ship.Ship;
import com.space.invaders.Views.BaseScreen;
import com.space.invaders.controllers.SpaceInvaders;

public class Heal implements Item {

    private final float heal = 100;
    private float widht = BaseScreen.convertToPPM(100);
    private float height = BaseScreen.convertToPPM(100);
    private SpaceInvaders g;
    private Sprite sprite;
    private Body body;
    private Texture texture;
    private boolean isAlive;

    public Heal(){}

    public Heal(float x, float speed, SpaceInvaders game){
        init(game,speed, x);
    }

    public void init(SpaceInvaders game, float speed, float x){
        this.g = game;
        isAlive = true;
        loadTexture();

        sprite = new Sprite(texture);

        sprite.setSize(widht, height);
        sprite.setPosition(x, BaseScreen.VIRTUAL_HEIGHT);

        createBody();

        body.setLinearVelocity(0, -speed);
    }

    @Override
    public void render(SpriteBatch batch) {
        sprite.setPosition(body.getPosition().x - getWidht()/2,
                           body.getPosition().y - getHeight()/2);
        sprite.draw(batch);
    }

    @Override
    public boolean isAlive() {
        if(sprite.getY() < 0) return false;
        if(sprite.getX() < 0 || sprite.getX() > BaseScreen.VIRTUAL_WIDHT) return false;
        return isAlive;
    }

    @Override
    public void destruct() {
        g.getWorld().destroyBody(body);
    }

    @Override
    public float getX() {
        return sprite.getX();
    }

    @Override
    public float getY() {
        return sprite.getY();
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
        sprite.setX(x);
    }

    @Override
    public void setY(float y) {
        sprite.setY(y);
    }

    @Override
    public void setSize(float widht, float height) {
        this.widht = widht;
        this.height = height;
        sprite.setSize(widht,height);
    }

    public void createBody() {
        body = g.getBodyFactory().createItemBody(this,g.getWorld());
    }

    public void loadTexture() {
        this.texture = g.getTexture("items/heart.png");
    }


    @Override
    public void collide(Object a) {
        if(a instanceof Ship && ((Ship) a).isPlayer()){
            Ship s = (Ship) a;
            s.setCurrentLife(s.getCurrentLife() + heal);
            isAlive = false;
        }
    }
}
