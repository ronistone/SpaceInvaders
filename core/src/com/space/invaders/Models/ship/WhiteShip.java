package com.space.invaders.Models.ship;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.compression.lzma.Base;
import com.space.invaders.Models.shot.DefaultBullet;
import com.space.invaders.Models.shot.Bullet;
import com.space.invaders.Views.BaseScreen;
import com.space.invaders.controllers.SpaceInvaders;

public class WhiteShip extends Ship {

    public WhiteShip(float X, float Y, SpaceInvaders game, World world) {
        super(X, 0, game);
        this.setHEIGHT(90);
        this.setSPEED(0);
        this.world = world;
        createBody();
    }

    @Override
    public void loadTexture() {
        this.setShipTexture(g.getTexture("ships/whiteShip.png"));
    }

    @Override
    public void move(float delta) {
        if(movement != null){
            movement.move(this);
        }
    }

    @Override
    public void createBody() {

        BodyDef bdef = new BodyDef();
        bdef.position.set(getX()+getWIDTH()/2, getY()+(getHEIGHT()+6)/2);
        bdef.type = BodyDef.BodyType.DynamicBody;
        body = this.world.createBody(bdef);

        CircleShape shape = new CircleShape();
        shape.setRadius(getWIDTH()/2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.isSensor = true;

        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData("WhiteShip");

        shape.dispose();
    }

    @Override
    public Bullet shoot() {
        return new DefaultBullet(getX()+ (getWIDTH()/2),getY()+getHEIGHT(), g, world);
    }

}
