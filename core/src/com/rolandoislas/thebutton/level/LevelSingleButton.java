package com.rolandoislas.thebutton.level;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.rolandoislas.thebutton.entity.Button;

/**
 * Created by Rolando on 4/24/2016.
 */
public class LevelSingleButton extends Level {
    private Button button;

    public LevelSingleButton() {
        // Button
        button = new Button();
        button.positionCenter();
        addActor(button);
        button.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                return false;
            }
        });
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (this.button.contains(screenX, screenY))
            this.nextLevel(true, 100);
        else
            this.nextLevel(false);
        return true;
    }
}
