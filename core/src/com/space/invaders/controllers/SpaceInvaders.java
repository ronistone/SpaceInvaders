package com.space.invaders.controllers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
//import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ObjectMap;
import com.space.invaders.Models.ship.Ship;
import com.space.invaders.Models.shot.Shot;
import com.space.invaders.Views.BaseScreen;
import com.space.invaders.Views.GameScreen;
import com.space.invaders.Views.MainMenu;
import com.space.invaders.services.AssetsService;

import java.util.ArrayList;
import java.util.Iterator;

public class SpaceInvaders extends Game {

    private ObjectMap<Class<? extends BaseScreen>, BaseScreen> screens = new ObjectMap<Class<? extends BaseScreen>, BaseScreen>();
    private AssetsService textureManager;


	@Override
	public void create () {
	    textureManager = AssetsService.getInstance();
	    loadScreens();
        changeScreen(MainMenu.class);
	}

	@Override
	public void render () {
        Screen currentScreen = getScreen();
        currentScreen.render(Gdx.graphics.getDeltaTime());
	}

    @Override
	public void dispose () {
        for(ObjectMap.Entry<Class<? extends BaseScreen>, BaseScreen> e: screens){
            e.value.dispose();
        }

        setScreen(null);
        screens.clear();
	}

	public void changeScreen(Class<? extends BaseScreen> screen){
	    this.setScreen(screens.get(screen));
    }

	private void loadScreens(){
	    screens.put(GameScreen.class, GameScreen.getInstance(this));
	    screens.put(MainMenu.class, MainMenu.getInstance(this));
    }


    public void update(Ship player, ArrayList<Shot> shots){
	    float delta = Gdx.graphics.getDeltaTime();

        Iterator<Shot> it = shots.iterator();
	    while(it.hasNext()){
	        Shot s = it.next();
	        s.update(delta);
            if(!s.isLive(BaseScreen.VIRTUAL_WIDHT, BaseScreen.VIRTUAL_HEIGHT)){
                it.remove();
            }
        }

        if(player.getLAST_SHOT() > 0){
	        player.setLAST_SHOT(player.getLAST_SHOT() - delta);
        }
        if(Gdx.input.isTouched() && player.getLAST_SHOT() <= 0){
            shots.add(player.shot());
            player.setLAST_SHOT(1/player.getSHOT_RATE());
        }

        float accelX = Gdx.input.getAccelerometerX();

        if(accelX < 0){
            player.moveRight(Math.abs(accelX)/10, BaseScreen.VIRTUAL_WIDHT);
        }else{
            player.moveLeft(Math.abs(accelX)/10, 0);
        }
    }

    public Texture getTexture(String path){
	    return textureManager.getTexture(path);
    }
}
