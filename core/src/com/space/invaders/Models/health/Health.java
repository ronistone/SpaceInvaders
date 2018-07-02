package com.space.invaders.Models.health;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.space.invaders.view.screen.BaseScreen;
import com.space.invaders.controllers.SpaceInvaders;

public class Health {

    private Texture background;
    private Texture currentLife;
    private float life;
    private float total;
    private float widht;
    private float height;
    private float progress;
    private SpaceInvaders g;

    public Health(float widht, float height, float total, SpaceInvaders game){
        this.total = total;
        this.life = total;
        this.widht = widht;
        this.height = height;
        this.g = game;
        loadTexture();
    }

    public void loadTexture(){
        background = g.getTexture("health/white.png");
        currentLife = g.getTexture("health/red.png");
    }

    public void draw(Vector2 position, SpriteBatch batch){
        progress = (life/total) * widht;
        progress = (progress < 0? 0:progress);

        batch.draw(background,position.x, position.y,
                        widht + BaseScreen.convertToPPM(2),
                        height + BaseScreen.convertToPPM(2));

        batch.draw(currentLife,position.x + BaseScreen.convertToPPM(1),
                        position.y + BaseScreen.convertToPPM(1),
                        progress, height);

    }

    public void updateLife(float life){
        this.life = life;
    }


}
