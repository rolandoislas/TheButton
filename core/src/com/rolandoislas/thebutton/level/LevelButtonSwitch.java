package com.rolandoislas.thebutton.level;

import com.rolandoislas.thebutton.entity.Button;
import com.rolandoislas.thebutton.entity.Switch;

/**
 * Created by Rolando on 4/24/2016.
 */
public class LevelButtonSwitch extends Level {
    private Button button;
    private Switch switchh;

    public LevelButtonSwitch() {
        // Button
        button = new Button();
        button.positionCenter();
        addActor(button);
        // Switch
        switchh = new Switch();
        switchh.positionCenter();
        switchh.setY(button.getY() - button.getHeight());
        addActor(switchh);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        super.touchDown(screenX, screenY, pointer, button);
        if (switchh.isEnabled() && this.button.contains(screenX, screenY))
            this.nextLevel(true, 200);
        else if (!switchh.contains(screenX, screenY))
            this.nextLevel(false);
        return true;
    }
}
