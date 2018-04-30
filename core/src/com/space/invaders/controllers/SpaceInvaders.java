package com.space.invaders.controllers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ObjectMap;
import com.space.invaders.Models.ship.Ship;
import com.space.invaders.Views.BaseScreen;
import com.space.invaders.Views.GameScreen;
import com.space.invaders.Views.MainMenu;

public class SpaceInvaders extends Game {

    private ObjectMap<Class<? extends BaseScreen>, BaseScreen> screens = new ObjectMap<Class<? extends BaseScreen>, BaseScreen>();

	
	@Override
	public void create () {
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


    public void update(Ship player){
	    if(Gdx.input.isTouched()){
	        player.setX(BaseScreen.VIRTUAL_WIDHT);
        }

        float accelX = Gdx.input.getAccelerometerX();

        if(accelX < 0){
            player.moveRight(Math.abs(accelX)/10, BaseScreen.VIRTUAL_WIDHT);
        }else{
            player.moveLeft(Math.abs(accelX)/10, 0);
        }
    }
}
