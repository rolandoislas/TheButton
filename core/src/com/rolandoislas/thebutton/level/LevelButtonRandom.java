package com.rolandoislas.thebutton.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.rolandoislas.thebutton.entity.Button;

/**
 * Created by Rolando on 5/4/2016.
 */
public class LevelButtonRandom extends Level {
    private final Button button;
    private int clickIncrement = 0;

    public LevelButtonRandom() {
        // Button
        button = new Button();
        setButtonRandomLocation();
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                clickIncrement++;
                if (clickIncrement == 3)
                    nextLevel(true, 300);
                else
                    setButtonRandomLocation();
            }
        });
        addActor(button);
    }

    private void setButtonRandomLocation() {
        float x = (float) Math.floor(Math.random() * (Gdx.graphics.getWidth() - button.getWidth()));
        float y = (float) Math.floor(Math.random() * (Gdx.graphics.getHeight() - button.getHeight()));
        button.setPosition(x, y);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        super.touchDown(screenX, screenY, pointer, button);
        if (!this.button.contains(screenX, screenY))
            this.nextLevel(false);
        return true;
    }
}
