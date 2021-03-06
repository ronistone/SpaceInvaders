package com.space.invaders.controllers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.space.invaders.models.Renderable;
import com.space.invaders.models.level.Level;
import com.space.invaders.models.ship.Ship;
import com.space.invaders.services.level.LevelService;
import com.space.invaders.services.level.impl.LevelServiceImpl;
import com.space.invaders.view.screen.BaseScreen;
import com.space.invaders.view.screen.GameScreen;
import com.space.invaders.view.screen.LevelScreen;
import com.space.invaders.view.screen.MainMenuScreen;
import com.space.invaders.services.AssetsService;
import com.space.invaders.services.ContactListenerCustom;
import com.space.invaders.services.factory.BodyFactory;
import com.space.invaders.services.shot.ShootService;


public class SpaceInvaders extends Game {

    private ObjectMap<Class<? extends BaseScreen>, BaseScreen> screens = new ObjectMap<Class<? extends BaseScreen>, BaseScreen>();
    private AssetsService textureManager;
    private ShootService shootService;
    private LevelService levelService;
    private BodyFactory bodyFactory;
    private World world;
    private Array<Renderable> elements;
    private Array<Ship> ships;
    private Level level;
    private Ship player;


	@Override
	public void create () {
	    elements = new Array<>();
	    ships = new Array<>();

	    textureManager = AssetsService.getInstance();
	    shootService = new ShootService();
	    bodyFactory = new BodyFactory();
        world = new World(new Vector2(0,0), true);
        world.setContactListener(new ContactListenerCustom());
	    loadScreens();
        changeScreen(MainMenuScreen.class);
	}

	@Override
	public void render () {
	    super.render();
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
	    if(this.getScreen() != null) {
            this.getScreen().dispose();
        }
	    this.setScreen(screens.get(screen));
    }

	private void loadScreens(){
	    screens.put(GameScreen.class, GameScreen.getInstance(this));
	    screens.put(MainMenuScreen.class, MainMenuScreen.getInstance(this));
	    screens.put(LevelScreen.class, LevelScreen.getInstance(this));
    }


    public void update(float delta){
        shootService.shoot(player, elements, ships, delta);

        for(Renderable r: elements){
            if(!r.isAlive()){
                r.destruct();
                elements.removeValue(r,true);
                if(r instanceof Ship){
                    ships.removeValue((Ship) r, true);
                }
            }
        }
    }

    public Texture getTexture(String path){
	    return textureManager.getTexture(path);
    }

    public long getPoints(){
	    return player.getPoints();
    }

    public long getTimeCurrent(){
	    return ((GameScreen)screens.get(GameScreen.class)).getInitialTime();
    }

    public World getWorld() {
        return world;
    }

    public BodyFactory getBodyFactory() {
        return bodyFactory;
    }

    public ObjectMap<Class<? extends BaseScreen>, BaseScreen> getScreens() {
        return screens;
    }

    public void setScreens(ObjectMap<Class<? extends BaseScreen>, BaseScreen> screens) {
        this.screens = screens;
    }

    public AssetsService getTextureManager() {
        return textureManager;
    }

    public void setTextureManager(AssetsService textureManager) {
        this.textureManager = textureManager;
    }

    public ShootService getShootService() {
        return shootService;
    }

    public void setShootService(ShootService shootService) {
        this.shootService = shootService;
    }

    public LevelService getLevelService() {
        return levelService;
    }

    public void setLevelService(LevelService levelService) {
        this.levelService = levelService;
    }

    public void setBodyFactory(BodyFactory bodyFactory) {
        this.bodyFactory = bodyFactory;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public Array<Renderable> getElements() {
        return elements;
    }

    public void setElements(Array<Renderable> elements) {
        this.elements = elements;
    }

    public Array<Ship> getShips() {
        return ships;
    }

    public void setShips(Array<Ship> ships) {
        this.ships = ships;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Ship getPlayer() {
        return player;
    }

    public void setPlayer(Ship player) {
        this.player = player;
    }
}
