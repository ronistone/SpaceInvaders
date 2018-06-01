package com.space.invaders.Models.health;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.space.invaders.Views.BaseScreen;

public class Health {

    private ShapeRenderer render;
    private float life;
    private float total;
    private float widht;
    private float height;
    private float progress;

    public Health(float widht, float height, float total){
        render = new ShapeRenderer();
        this.total = total;
        this.life = total;
        this.widht = widht;
        this.height = height;
    }

    public void draw(Vector2 position, Camera camera){
        progress = (life/total) * widht;

        render.setProjectionMatrix(camera.combined);
        render.begin(ShapeRenderer.ShapeType.Filled);

        render.setColor(Color.WHITE);
        render.rect(position.x, position.y,
                        widht + BaseScreen.convertToPPM(2),
                        height + BaseScreen.convertToPPM(2));

        render.setColor(Color.RED);
        render.rect(position.x + BaseScreen.convertToPPM(1),
                        position.y + BaseScreen.convertToPPM(1),
                        progress, height);

        render.end();
    }

    public void updateLife(float life){
        this.life = life;
    }


    public void destruct() {
        render.dispose();
    }
}
