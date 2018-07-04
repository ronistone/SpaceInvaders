package com.space.invaders.view.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.space.invaders.models.Touchable;
import com.space.invaders.controllers.SpaceInvaders;
import com.space.invaders.services.InputService;
import com.space.invaders.view.button.Button;

import java.util.ArrayList;
import java.util.List;

public class MainMenuScreen extends BaseScreen {

    private volatile static MainMenuScreen instance;

    private Texture start;
    private SpriteBatch batch;
    private List<Button> buttons;

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

        camera.update();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        for( Button b: buttons ){
            b.render(batch);
        }

        batch.end();
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        start = game.getTexture("start.png");

        buttons = new ArrayList<>();

        float t = convertToPPM(200);

        buttons.add(new Button(game, (VIRTUAL_WIDHT/2) - (t/2), VIRTUAL_HEIGHT/2, 2*t, t) {
            @Override
            public void loadTexture() {
                texture = game.getTexture("start.png");
            }

            @Override
            public void doAction() {
                game.changeScreen(LevelScreen.class);
            }
        });


        List<Touchable> touchables = new ArrayList<>();
        for ( Touchable touchable: buttons){
            touchables.add(touchable);
        }
        InputService inputService = new InputService(touchables);

        Gdx.input.setInputProcessor(inputService);

    }

    @Override
    public void dispose() {
        Gdx.input.setInputProcessor(null);
    }
}
