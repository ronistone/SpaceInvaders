package com.space.invaders.services;

import com.badlogic.gdx.InputProcessor;
import com.space.invaders.models.Touchable;
import com.space.invaders.view.screen.BaseScreen;

import java.util.List;

public class InputService implements InputProcessor {

    private List<Touchable> touchables;

    public InputService(List<Touchable> touchables) {
        this.touchables = touchables;
    }

    public List<Touchable> getTouchables() {
        return touchables;
    }

    public void setTouchables(List<Touchable> touchables) {
        this.touchables = touchables;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        float x = BaseScreen.convertToPPM(screenX);
        float y = BaseScreen.convertToPPM(screenY);
        for( Touchable t: touchables){
            if(t.isTouch(x, y)) {
                t.doAction();
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

}
