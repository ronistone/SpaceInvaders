package com.space.invaders.view.button;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.space.invaders.models.level.Level;
import com.space.invaders.services.level.impl.LevelServiceImpl;
import com.space.invaders.view.screen.BaseScreen;
import com.space.invaders.view.screen.GameScreen;
import com.space.invaders.controllers.SpaceInvaders;

public class LevelButton extends Button {

    public LevelButton(SpaceInvaders g, Level l, float x, float y, float height, float widht, String msg) {
        super(g, x, y, height, widht);
        this.l = l;
        this.msg = msg;
        glyphLayout.setText(font, msg);
        font.getData().setScale(0.7f);
        finalWidht = widht + glyphLayout.width;
    }

    @Override
    public void doAction() {
//        Gdx.app.log("CLICOU NO BOTÃO", msg);
        g.setLevel(l);
        g.setLevelService(new LevelServiceImpl(g));
        g.changeScreen(GameScreen.class);
    }

    @Override
    public void render(SpriteBatch batch) {
        super.render(batch);
        font.draw(batch, msg, (x + widht + BaseScreen.convertToPPM(10)), y + (height/2) + glyphLayout.height/2);
    }

    @Override
    public void loadTexture() {
        texture = g.getTexture("logo.png");
    }
}
