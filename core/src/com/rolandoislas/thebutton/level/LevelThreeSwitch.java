package com.rolandoislas.thebutton.level;

import com.badlogic.gdx.Gdx;
import com.rolandoislas.thebutton.entity.Switch;

/**
 * Created by Rolando on 5/3/2016.
 */
public class LevelThreeSwitch extends Level {
    private final Switch switchOne;
    private final Switch switchTwo;
    private final Switch switchThree;

    public LevelThreeSwitch() {
        // Switches
        float margin = Gdx.graphics.getHeight() * .2f;
        switchOne = new Switch();
        switchOne.positionCenter();
        addActor(switchOne);
        switchTwo = new Switch();
        switchTwo.setPosition(switchOne.getX(), switchOne.getY() - margin);
        addActor(switchTwo);
        switchThree = new Switch();
        switchThree.setPosition(switchOne.getX(), switchOne.getY() + margin);
        addActor(switchThree);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        super.touchDown(screenX, screenY, pointer, button);
        if (!(switchOne.contains(screenX, screenY) || switchTwo.contains(screenX, screenY) || switchThree.contains(screenX, screenY)))
            this.nextLevel(false);
        return true;
    }

    @Override
    public void draw() {
        super.draw();
        if (switchOne.isEnabled() && switchTwo.isEnabled() && switchThree.isEnabled())
            nextLevel(true, 300);
    }
}
