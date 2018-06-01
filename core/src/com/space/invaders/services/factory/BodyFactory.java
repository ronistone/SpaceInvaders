package com.space.invaders.services.factory;

import com.badlogic.gdx.physics.box2d.*;
import com.space.invaders.Models.item.Item;
import com.space.invaders.Models.ship.Ship;
import com.space.invaders.Util.MathUtil;

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
}
