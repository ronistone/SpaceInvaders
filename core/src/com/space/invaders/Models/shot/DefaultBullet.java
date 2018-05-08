package com.space.invaders.Models.shot;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.space.invaders.controllers.SpaceInvaders;

public class DefaultBullet extends Bullet {

    private volatile static Texture shotTexture;

    public DefaultBullet(float x, float y, SpaceInvaders game, World world){
        super(x,y,game, world);
    }

    private void loadTexture() {
        if(shotTexture == null){
            synchronized (DefaultBullet.class){
                if(shotTexture == null){
                    shotTexture = g.getTexture("shoot.png");
                }
            }
        }
    }

    public void render(SpriteBatch batch){
        loadTexture();
        batch.draw(shotTexture, getX(), getY(), SHOT_WIDHT, SHOT_HEIGHT);
    }

    public void update(float delta){
        setX(body.getPosition().x);
        setY(body.getPosition().y);
    }

    @Override
    public void createBody() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(getX()+getSHOT_WIDHT()/2, getY()+(getSHOT_HEIGHT()+6)/2);
        bdef.type = BodyDef.BodyType.DynamicBody;
        body = this.world.createBody(bdef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(getSHOT_WIDHT(),getSHOT_HEIGHT());

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.isSensor = true;

        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData("DefaultBullet");
        shape.dispose();
        body.setLinearVelocity(0, SPEED);
    }


}
