package com.space.invaders.models.shot;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.space.invaders.models.ship.Ship;
import com.space.invaders.controllers.SpaceInvaders;
import com.space.invaders.services.factory.BodyFactory;

public class DefaultBullet extends Bullet {

    private Texture shotTexture;

    public DefaultBullet(float x, float y, Vector2 v, SpaceInvaders game, World world, Ship ship){
        super(x,y, v, game, world, ship);

        setDamage(30);
        loadTexture();
    }

    public void loadTexture() {
        shotTexture = g.getTexture("shoot.png");
    }

    public void render(SpriteBatch batch){
        update();
        batch.draw(shotTexture, getX(), getY(), SHOT_WIDHT, SHOT_HEIGHT);
    }

    public void update(){
        setX(body.getPosition().x);
        setY(body.getPosition().y);
    }

    @Override
    public void destruct() {
        isAlive = false;
        if(body!=null) {
            world.destroyBody(body);
            body = null;
        }
    }

    @Override
    public float getWidht() {
        return this.SHOT_WIDHT;
    }

    @Override
    public float getHeight() {
        return this.SHOT_HEIGHT;
    }

    @Override
    public void setSize(float widht, float height) {
        SHOT_WIDHT = widht;
        SHOT_HEIGHT = height;
    }


    @Override
    public void collide(Object a) {
        if(a instanceof Ship && owner.isPlayer() != ((Ship) a).isPlayer()){
            Ship c = (Ship) a;
            isAlive = false;
            c.hit(getDamage());
            if(!c.isAlive()){
                this.owner.addPoints(c.getCreateTime(), c.getPOINT());
            }
        }
    }
}
