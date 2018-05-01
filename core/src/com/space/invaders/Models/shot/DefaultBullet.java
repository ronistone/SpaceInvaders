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
        batch.draw(shotTexture, X, Y, SHOT_WIDHT, SHOT_HEIGHT);
    }

    public void update(float delta){
        Y += SPEED;
        shotRect.x = X;
        shotRect.y = Y;
    }

    public boolean isLive(float widht, float height){
        if(X < 0 || X > widht){
            System.out.println("widht"); return false;}
        if(Y < 0 || Y > height){
            System.out.println("height\n Y:"+Y+ "\nheight: "+ height); return false;}
        return true;
    }

}
