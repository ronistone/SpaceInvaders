package com.space.invaders.services;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.space.invaders.Models.Collider;
import com.space.invaders.Models.ship.Ship;

public class ContactListenerCustom implements ContactListener {


    @Override
    public void beginContact(Contact contact) {
        Collider a = (Collider) contact.getFixtureA().getUserData();
        Collider b = (Collider) contact.getFixtureB().getUserData();

        a.collide(b);
        b.collide(a);

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
