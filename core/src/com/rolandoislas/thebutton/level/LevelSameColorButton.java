package com.rolandoislas.thebutton.level;

import com.rolandoislas.thebutton.entity.Button;

/**
 * Created by Rolando on 4/27/2016.
 */
public class LevelSameColorButton extends Level {
    private Button button;

    public LevelSameColorButton() {
        // Button
        button = new Button();
        button.positionCenter();
        button.setColor(Level.BACKGROUND_COLOR);
        addActor(button);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (this.button.contains(screenX, screenY))
            nextLevel(true, 200);
        else
            nextLevel(false);
        return true;
    }
}
