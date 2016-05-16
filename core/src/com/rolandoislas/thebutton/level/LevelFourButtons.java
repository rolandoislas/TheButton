package com.rolandoislas.thebutton.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.rolandoislas.thebutton.entity.Button;

/**
 * Created by Rolando on 5/3/2016.
 */
public class LevelFourButtons extends Level {
    private final Button buttonOne;
    private final Button buttonTwo;
    private final Button buttonThree;
    private final Button buttonFour;

    public LevelFourButtons() {
        // Buttons
        ClickListener buttonEvent = new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Color color = new Color(.1f, .1f, .1f, 1);
                if (event.getTarget().getColor().equals(color))
                    nextLevel(false);
                else
                    event.getTarget().setColor(color);
                if (buttonOne.getColor().equals(color) && buttonTwo.getColor().equals(color) &&
                        buttonThree.getColor().equals(color) && buttonFour.getColor().equals(color))
                    nextLevel(true, 400);
            }
        };
        float margin = Gdx.graphics.getWidth() * .2f;
        buttonOne = new Button();
        buttonOne.positionCenter();
        buttonOne.setPosition(buttonOne.getX() - margin, buttonOne.getY() + margin);
        buttonOne.addListener(buttonEvent);
        buttonTwo = new Button();
        buttonTwo.positionCenter();
        buttonTwo.setPosition(buttonTwo.getX() + margin, buttonTwo.getY() + margin);
        buttonTwo.addListener(buttonEvent);
        buttonThree = new Button();
        buttonThree.positionCenter();
        buttonThree.setPosition(buttonThree.getX() - margin, buttonThree.getY() - margin);
        buttonThree.addListener(buttonEvent);
        buttonFour = new Button();
        buttonFour.positionCenter();
        buttonFour.setPosition(buttonFour.getX() + margin, buttonFour.getY() - margin);
        buttonFour.addListener(buttonEvent);
        addActor(buttonOne);
        addActor(buttonTwo);
        addActor(buttonThree);
        addActor(buttonFour);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        super.touchDown(screenX, screenY, pointer, button);
        if (!(buttonOne.contains(screenX, screenY) || buttonTwo.contains(screenX, screenY) ||
                buttonThree.contains(screenX, screenY) || buttonFour.contains(screenX, screenY)))
            this.nextLevel(false);
        return true;
    }
}
