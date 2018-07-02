package com.space.invaders.view.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.space.invaders.controllers.SpaceInvaders;

public abstract class BaseScreen extends ScreenAdapter {
    public static final float PPM = 40;
    public static final float VIRTUAL_WIDHT = Gdx.graphics.getWidth()/ PPM;
    public static final float VIRTUAL_HEIGHT = Gdx.graphics.getHeight()/ PPM;
    protected SpaceInvaders game;
    protected Camera camera;
    protected Viewport viewport;

    public BaseScreen(SpaceInvaders g) {
        game = g;
        camera = new OrthographicCamera();
        camera.position.set(VIRTUAL_WIDHT/2,VIRTUAL_HEIGHT/2,0);
        viewport = new StretchViewport(VIRTUAL_WIDHT, VIRTUAL_HEIGHT, camera);
    }

    public static float convertToPPM(float m){
        return m/PPM;
    }
    public static float convertToPixel(float m){
        return m*PPM;
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height);
    }

}
