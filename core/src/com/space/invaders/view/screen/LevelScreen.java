package com.space.invaders.view.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.space.invaders.models.Renderable;
import com.space.invaders.models.Touchable;
import com.space.invaders.view.button.LevelButton;
import com.space.invaders.controllers.SpaceInvaders;
import com.space.invaders.services.InputService;
import com.space.invaders.services.factory.LevelsFactory;

import java.util.ArrayList;
import java.util.List;

public class LevelScreen extends BaseScreen {

    private static LevelScreen instance;

    private SpriteBatch batch;
    private List<LevelButton> renderables;
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

        float t = convertToPPM(200);
        float yA = (VIRTUAL_HEIGHT/2);
        float x = (VIRTUAL_WIDHT/2) - (t/2);
        float yB = (yA - convertToPPM(220));


        renderables.add(new LevelButton(game, levelsFactory.createFirstLevel(), x, yA, t, t));
        renderables.add(new LevelButton(game, levelsFactory.createSecondLevel(), x, yB, t, t));

        levels = new ArrayList<>();
        for(LevelButton l: renderables){
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
        System.out.println("Dispose LevelScreen");
        Gdx.input.setInputProcessor(null);
    }
}
