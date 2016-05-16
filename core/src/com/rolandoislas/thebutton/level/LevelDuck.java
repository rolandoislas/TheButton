package com.rolandoislas.thebutton.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.I18NBundle;
import com.rolandoislas.thebutton.entity.Duck;

/**
 * Created by Rolando on 5/8/2016.
 */
public class LevelDuck extends Level {
    private final Duck duck;
    private int ammo = 3;
    private Label ammoLabel;

    public LevelDuck() {
        // Duck
        duck = new Duck();
        duck.setX(-duck.getWidth());
        setDuckRandomY();
        addActor(duck);
        // Ammo
        I18NBundle lang = I18NBundle.createBundle(Gdx.files.internal("lang/lang"));
        ammoLabel = createLabel(lang.get("ammo") + " " + ammo, (int) (Gdx.graphics.getHeight() * .05f),
                "font/timeburner.ttf");
        ammoLabel.setPosition(ammoLabel.getWidth(), Gdx.graphics.getHeight() - ammoLabel.getHeight());
        addActor(ammoLabel);
        // Background
        this.setBackground(new Color(.39f, .69f, 1, 1));
        // Time
        this.setTimeLimit(5);
    }

    private void setDuckRandomY() {
        duck.setY((float) Math.floor(Math.random() * (Gdx.graphics.getHeight() - duck.getHeight())) - duck.getHeight()
                / 2);
    }

    @Override
    public void draw() {
        super.draw();
        if (duck.getX() <= -duck.getWidth()) {
            setDuckRandomY();
            duck.setX(Gdx.graphics.getWidth() + duck.getWidth());
        }
        else
            duck.setX(duck.getX() - Gdx.graphics.getDeltaTime() * 30 * 50);
        ammoLabel.setText(ammoLabel.getText().substring(0, ammoLabel.getText().length - 1) + ammo);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        super.touchDown(screenX, screenY, pointer, button);
        if (duck.contains(screenX, screenY))
            nextLevel(true, 150);
        else
            ammo--;
        if (ammo == 0)
            nextLevel(false);
        return true;
    }
}
