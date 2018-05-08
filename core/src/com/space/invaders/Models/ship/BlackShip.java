package com.space.invaders.Models.ship;

import com.badlogic.gdx.physics.box2d.*;
import com.space.invaders.Models.shot.Bullet;
import com.space.invaders.controllers.SpaceInvaders;

public class BlackShip extends Ship {

    public BlackShip(float X, float Y, SpaceInvaders game, World world) {
        super(X, Y, game);
        this.world = world;
        createBody();
    }

    @Override
    public void loadTexture() {
        this.setShipTexture(g.getTexture("ships/blackShip.png"));
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
        fixture.setUserData("BlackShip");

        shape.dispose();

    }

    @Override
    public Bullet shoot() {
        return null;
    }
}
