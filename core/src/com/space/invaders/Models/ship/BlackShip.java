package com.space.invaders.Models.ship;

import com.badlogic.gdx.physics.box2d.*;
import com.space.invaders.Models.shot.Bullet;
import com.space.invaders.controllers.SpaceInvaders;
import com.space.invaders.services.factory.BodyFactory;

import java.util.Map;

public class BlackShip extends Ship {

    public BlackShip(float X, float Y, SpaceInvaders game, Map<String, Object> args) {
        super(X, Y, game);
        this.args = args;
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
    public void destruct() {
        World world = (World) args.get("world");
        world.destroyBody(body);
    }

    @Override
    public void createBody() {
        BodyFactory bodyFactory = (BodyFactory) args.get("bodyFactory");
        World world = (World) args.get("world");
        this.body = bodyFactory.createSimpleShipBody(this, world);
    }

    @Override
    public Bullet shoot() {
        return null;
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
}
