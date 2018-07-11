package com.space.invaders.services.factory;

import com.badlogic.gdx.physics.box2d.*;
import com.space.invaders.models.item.Item;
import com.space.invaders.models.ship.Ship;
import com.space.invaders.models.shot.Bullet;
import com.space.invaders.util.MathUtil;

public class BodyFactory {



    public Body createSimpleShipBody(Ship ship, World world){

        BodyDef bdef = new BodyDef();
        bdef.position.set(ship.getX()+ship.getWIDTH()/2, ship.getY()+(ship.getHEIGHT()+6)/2);
        bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.linearDamping = 5;
        Body body = world.createBody(bdef);

        CircleShape shape = new CircleShape();
        shape.setRadius(ship.getWIDTH()/2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.isSensor = true;

        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData(ship);

        shape.dispose();

        if(ship.isPlayer()) {
            body.setTransform(body.getPosition(), MathUtil.toRadians(90));
        }else{
            body.setTransform(body.getPosition(), MathUtil.toRadians(-90));
        }
        return body;
    }

    public Body createItemBody(Item item, World world){

        BodyDef bdef = new BodyDef();
        bdef.position.set(item.getX()+item.getWidht()/2, item.getY()+(item.getHeight()+6)/2);
        bdef.type = BodyDef.BodyType.DynamicBody;
        Body body = world.createBody(bdef);

        CircleShape shape = new CircleShape();
        shape.setRadius(item.getWidht()/2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.isSensor = true;

        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData(item);

        shape.dispose();

        return body;
    }

    public Body createBulletBody(Bullet bullet, World world){
        BodyDef bdef = new BodyDef();
        bdef.position.set(bullet.getX()+bullet.getSHOT_WIDHT()/2, bullet.getY()+(bullet.getSHOT_HEIGHT()+6)/2);
        bdef.type = BodyDef.BodyType.DynamicBody;
        bullet.body = world.createBody(bdef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(bullet.getSHOT_WIDHT(), bullet.getSHOT_HEIGHT());

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.isSensor = true;

        Fixture fixture = bullet.body.createFixture(fixtureDef);
        fixture.setUserData(bullet);
        shape.dispose();
        bullet.body.setLinearVelocity(bullet.getDirection());

        return bullet.body;
    }
}
