package com.space.invaders.models;

public interface SubjectMovement {

    public void register(ObserverMovement observer);
    public void exit(ObserverMovement observer);

}
