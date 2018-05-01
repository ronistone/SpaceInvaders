package com.space.invaders.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.space.invaders.Models.shot.Shot;
import com.space.invaders.controllers.SpaceInvaders;
import com.space.invaders.Models.ship.DefaultPlayer;
import com.space.invaders.Models.ship.Ship;
import com.space.invaders.Models.ship.SimpleEnemy;

import java.util.ArrayList;

public class GameScreen extends BaseScreen {

    private volatile static GameScreen instance;

    private SpriteBatch batch;
    private Texture background;
    private Ship player,enemy;
    private Camera camera;
    private Viewport viewport;
    private ArrayList<Shot> shots;

    private GameScreen(SpaceInvaders g) {
        super(g);
        shots = new ArrayList<>();
    }

    public static GameScreen getInstance(SpaceInvaders g){
        if(instance==null){
            synchronized (BaseScreen.class){
                if(instance==null){
                    instance = new GameScreen(g);
                }
            }
        }
        return instance;
    }


    @Override
    public void show() {
        batch = new SpriteBatch();

        camera = new OrthographicCamera();
        camera.position.set(VIRTUAL_WIDHT/2,VIRTUAL_HEIGHT/2,0);
        viewport = new StretchViewport(VIRTUAL_WIDHT, VIRTUAL_HEIGHT, camera);

        background = game.getTexture("background.jpg");
        player = new DefaultPlayer(VIRTUAL_WIDHT/2, VIRTUAL_HEIGHT/2, game);
        enemy = new SimpleEnemy(100, VIRTUAL_HEIGHT, game);
    }

    @Override
    public void render(float delta) {

        camera.update();
        game.update(player, shots);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        batch.draw(background,0,0, VIRTUAL_WIDHT, VIRTUAL_HEIGHT);
        enemy.render(batch);
        player.render(batch);
        for(Shot s: shots){
            s.render(batch);
        }

        batch.end();

    }


    @Override
    public void dispose() {
        batch.dispose();
        player.dispose();
        enemy.dispose();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height);
    }
}
