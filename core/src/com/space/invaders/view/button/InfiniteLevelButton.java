package com.space.invaders.view.button;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.space.invaders.controllers.SpaceInvaders;
import com.space.invaders.services.factory.LevelsFactory;
import com.space.invaders.services.level.impl.LevelServiceImpl;
import com.space.invaders.services.level.impl.SingleLevelService;
import com.space.invaders.view.screen.BaseScreen;
import com.space.invaders.view.screen.GameScreen;

public class InfiniteLevelButton extends Button {

    public InfiniteLevelButton(SpaceInvaders game, float x, float y, float widht, float height, String msg) {
        super(game, x, y, widht, height);
        this.msg = msg;
        glyphLayout.setText(font, msg);
        font.getData().setScale(0.7f);
        finalWidht = widht + glyphLayout.width;
        //game.setLevelService(new SingleLevelService(game));
        LevelsFactory factory = new LevelsFactory();
        l = factory.createUniqueLevel();
    }

    @Override
    public void loadTexture() {
        texture = g.getTexture("logo.png");
    }

    @Override
    public void render(SpriteBatch batch) {
        super.render(batch);
        font.draw(batch, msg, (x + widht + BaseScreen.convertToPPM(10)), y + (height/2) + glyphLayout.height/2);
    }

    @Override
    public void doAction() {
//        Gdx.app.log("CLICOU NO BOTÃO", msg);
        g.setLevel(l);
        g.setLevelService(new SingleLevelService(g));
        g.changeScreen(GameScreen.class);
    }
}
