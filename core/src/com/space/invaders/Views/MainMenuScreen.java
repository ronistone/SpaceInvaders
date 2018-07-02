package com.space.invaders.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.space.invaders.controllers.SpaceInvaders;

public class MainMenuScreen extends BaseScreen {

    private volatile static MainMenuScreen instance;

    private Texture start;
    private TextureRegion startRegion;
    private TextureRegionDrawable startDrawable;
    private ImageButton startButton;
    private Stage stage;

    private MainMenuScreen(SpaceInvaders g) {
        super(g);
    }

    public static MainMenuScreen getInstance(SpaceInvaders g){
        if(instance==null){
            synchronized (MainMenuScreen.class){
                if(instance==null){
                    instance = new MainMenuScreen(g);
                }
            }
        }

        return instance;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void show() {
        start = game.getTexture("start.png");
        startRegion = new TextureRegion(start);
        startDrawable = new TextureRegionDrawable(startRegion);
        startButton = new ImageButton(startDrawable);

        stage = new Stage(new ScreenViewport());
        stage.addActor(startButton);
        Gdx.input.setInputProcessor(stage);

        startButton.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                dispose();
                game.changeScreen(LevelScreen.class);
                return true;
            }
        });
    }

    @Override
    public void dispose() {
        super.dispose();
        System.out.println("Dispose MainMenu");
        Gdx.input.setInputProcessor(null);
    }
}
