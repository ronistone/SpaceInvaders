package com.space.invaders.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.space.invaders.Models.Renderable;
import com.space.invaders.Models.Touchable;
import com.space.invaders.Models.level.LevelButton;
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

    private Camera camera;
    private Viewport viewport;

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

        camera = new OrthographicCamera();
        camera.position.set(VIRTUAL_WIDHT/2,VIRTUAL_HEIGHT/2,0);
        viewport = new StretchViewport(VIRTUAL_WIDHT, VIRTUAL_HEIGHT, camera);

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
        System.out.println(Gdx.input.getInputProcessor()+ "  ---  " + (Gdx.input.getInputProcessor() instanceof InputService));

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

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height);
    }
}
