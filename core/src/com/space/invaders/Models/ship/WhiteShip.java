package com.space.invaders.Models.ship;

import com.badlogic.gdx.physics.box2d.*;
import com.space.invaders.Models.shot.DefaultBullet;
import com.space.invaders.Models.shot.Bullet;
import com.space.invaders.controllers.SpaceInvaders;
import com.space.invaders.services.factory.BodyFactory;

import java.util.Map;

public class WhiteShip extends Ship {

    public WhiteShip(float X, float Y, SpaceInvaders game, Map<String, Object> args) {
        super(X, 0, game);
        this.setHEIGHT(90);
        this.args = args;
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
        World world = (World) args.get("world");
        return new DefaultBullet(getX()+ (getWIDTH()/2),getY()+getHEIGHT(), g, world, this);
    }

}

