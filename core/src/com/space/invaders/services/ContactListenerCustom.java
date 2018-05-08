package com.space.invaders.services;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

public class ContactListenerCustom implements ContactListener {


    @Override
    public void beginContact(Contact contact) {
        String a = (String) contact.getFixtureA().getUserData();
        String b = (String) contact.getFixtureB().getUserData();
        System.out.println("Contato: "+a+"  ---  "+b);
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
