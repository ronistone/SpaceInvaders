package com.space.invaders.Models.shot;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.space.invaders.controllers.SpaceInvaders;

public class DefaultBullet extends Bullet {

    private volatile static Texture shotTexture;

    public DefaultBullet(float x, float y, SpaceInvaders game){
        super(x,y,game);
        this.setShotRect(new Rectangle(x,y,SHOT_WIDHT,SHOT_HEIGHT));
    }

    private void loadTexture() {
        if(shotTexture == null){
            synchronized (DefaultBullet.class){
                if(shotTexture == null){
                    shotTexture = g.getTexture("shoot.png");
                }
            }
        }
    }

    public void render(SpriteBatch batch){
        loadTexture();
        batch.draw(shotTexture, getX(), getY(), SHOT_WIDHT, SHOT_HEIGHT);
    }

    public void update(float delta){
        setY(getY() + SPEED);
        shotRect.x = getX();
        shotRect.y = getY();
    }


}
