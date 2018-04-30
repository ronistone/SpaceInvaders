package com.space.invaders.Models.shot;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class DefaultShot extends Shot{

    private volatile static Texture shotTexture;

    public DefaultShot(float x, float y){
        super(x,y);
        this.setShotRect(new Rectangle(x,y,SHOT_WIDHT,SHOT_HEIGHT));
    }

    private void loadTexture() {
        if(shotTexture == null){
            synchronized (DefaultShot.class){
                if(shotTexture == null){
                    shotTexture = new Texture(Gdx.files.internal("shoot.png"));
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
