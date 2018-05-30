package com.space.invaders.services;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.space.invaders.Models.ship.Ship;

public class ContactListenerCustom implements ContactListener {


    @Override
    public void beginContact(Contact contact) {
        InformationBody infoA = (InformationBody) contact.getFixtureA().getUserData();
        InformationBody infoB = (InformationBody) contact.getFixtureB().getUserData();

        if(infoA.getBullet() != null && infoB.getBullet() == null){
            if(infoA.getOwner() != infoB.getOwner()) {
                infoB.getOwner().setAlive(false);
                infoA.getBullet().setAlive(false);
            }
        }else if(infoB.getBullet() != null && infoA.getBullet() == null){
            if(infoA.getOwner() != infoB.getOwner()) {
                infoA.getOwner().setAlive(false);
                infoB.getBullet().setAlive(false);
            }
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
