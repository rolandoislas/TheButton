package com.rolandoislas.thebutton.level;

import com.rolandoislas.thebutton.entity.Switch;

/**
 * Created by Rolando on 5/2/2016.
 */
public class LevelSingleSwitch extends Level {
    private final Switch switchh;

    public LevelSingleSwitch() {
        // Switch
        switchh = new Switch();
        switchh.positionCenter();
        addActor(switchh);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (switchh.contains(screenX, screenY))
            this.nextLevel(true, 100);
        else
            this.nextLevel(false);
        return true;
    }
}
