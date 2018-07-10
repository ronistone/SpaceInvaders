package com.space.invaders.view.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.space.invaders.models.Renderable;
import com.space.invaders.models.Touchable;
import com.space.invaders.view.button.Button;
import com.space.invaders.view.button.InfiniteLevelButton;
import com.space.invaders.view.button.LevelButton;
import com.space.invaders.controllers.SpaceInvaders;
import com.space.invaders.services.InputService;
import com.space.invaders.services.factory.LevelsFactory;

import java.util.ArrayList;
import java.util.List;

public class LevelScreen extends BaseScreen {

    private static LevelScreen instance;

    private SpriteBatch batch;
    private List<Button> renderables;
    private List<Touchable> levels;
    private Texture background;


    private LevelScreen(SpaceInvaders g) {
        super(g);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        batch.draw(background, 0, 0, VIRTUAL_WIDHT, VIRTUAL_HEIGHT);

        for ( Renderable r: renderables ){
            r.render(batch);
        }


        batch.end();

    }

    @Override
    public void show() {

        renderables = new ArrayList<>();
        LevelsFactory levelsFactory = new LevelsFactory();
        background = game.getTexture("background.jpg");

        float xh = convertToPPM(200);
        float yh = convertToPPM(220);
        float y = VIRTUAL_HEIGHT - (VIRTUAL_HEIGHT/3);
        float x = (VIRTUAL_WIDHT/4) - (xh/2);


        renderables.add(new LevelButton(game, levelsFactory.createFirstLevel(), x, y - yh, xh, xh, "Level 1"));
        renderables.add(new LevelButton(game, levelsFactory.createSecondLevel(), x, y - (2*yh), xh, xh, "Level 2"));
        renderables.add(new InfiniteLevelButton(game, x, y - (3 * yh), xh, xh, "Arcade"));

        levels = new ArrayList<>();
        for(Button l: renderables){
            levels.add(l);
        }

        Gdx.input.setInputProcessor(new InputService(levels));

        batch = new SpriteBatch();

    }

    public static LevelScreen getInstance(SpaceInvaders g){
        if(instance==null){
            synchronized (LevelScreen.class){
                if(instance==null){
                    instance = new LevelScreen(g);
                }
            }
        }

        return instance;
    }

    @Override
    public void dispose() {
        Gdx.input.setInputProcessor(null);
    }
}
