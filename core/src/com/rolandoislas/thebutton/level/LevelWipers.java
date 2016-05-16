package com.rolandoislas.thebutton.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.rolandoislas.thebutton.entity.Button;

/**
 * Created by Rolando on 5/2/2016.
 */
public class LevelWipers extends Level {
    private final Button button;
    private final ShapeRenderer shapeRenderer;
    private final Polygon wiperLeft;
    private final Polygon wiperRight;
    private final Rectangle wiperLeftRect;
    private final Rectangle wiperRightRect;

    public LevelWipers() {
        // Button
        button =  new Button();
        button.positionCenter();
        addActor(button);
        // Wiper Left
        wiperLeftRect = new Rectangle();
        wiperLeftRect.setWidth(Gdx.graphics.getWidth() / 2);
        wiperLeftRect.setHeight(Gdx.graphics.getHeight() * .1f);
        wiperLeftRect.setX(0);
        wiperLeftRect.setY(Gdx.graphics.getHeight() / 2 - wiperLeftRect.getHeight() / 2);
        wiperLeft = new Polygon(new float[] {
                wiperLeftRect.getX(), wiperLeftRect.getY(),
                wiperLeftRect.getX(), wiperLeftRect.getY() + wiperLeftRect.getHeight(),
                wiperLeftRect.getX() + wiperLeftRect.getWidth(), wiperLeftRect.getY() + wiperLeftRect.getHeight(),
                wiperLeftRect.getX() + wiperLeftRect.getWidth(), wiperLeftRect.getY()
        });
        wiperLeft.setOrigin(wiperLeftRect.getX() + wiperLeftRect.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        wiperLeft.setRotation(0);
        // Wiper Right
        wiperRightRect = new Rectangle();
        wiperRightRect.setWidth(wiperLeftRect.getWidth());
        wiperRightRect.setHeight(wiperLeftRect.getHeight());
        wiperRightRect.setX(Gdx.graphics.getWidth() - wiperRightRect.getWidth());
        wiperRightRect.setY(wiperLeftRect.getY());
        wiperRight = new Polygon(new float[] {
                wiperRightRect.getX(), wiperRightRect.getY(),
                wiperRightRect.getX(), wiperRightRect.getY() + wiperRightRect.getHeight(),
                wiperRightRect.getX() + wiperRightRect.getWidth(), wiperRightRect.getY() + wiperRightRect.getHeight(),
                wiperRightRect.getX() + wiperRightRect.getWidth(), wiperRightRect.getY()
        });
        wiperRight.setOrigin(wiperRightRect.getX() + wiperRightRect.getWidth() / 2 , Gdx.graphics.getHeight() / 2);
        wiperRight.setRotation(0); // negative
        // Shape renderer
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
    }

    @Override
    public void draw() {
        super.draw();
        // Draw shapes
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(.1f, .1f, .1f, 1);
        shapeRenderer.rect(wiperLeftRect.getX(), wiperLeftRect.getY(), wiperLeftRect.getWidth() / 2,
                wiperLeftRect.getHeight() / 2, wiperLeftRect.getWidth(), wiperLeftRect.getHeight(), 1, 1,
                wiperLeft.getRotation());
        shapeRenderer.rect(wiperRightRect.getX(), wiperRightRect.getY(), wiperRightRect.getWidth() / 2,
                wiperRightRect.getHeight() / 2, wiperRightRect.getWidth(), wiperRightRect.getHeight(), 1, 1,
                wiperRight.getRotation());
        shapeRenderer.end();
        // Move wipers
        wiperLeft.setRotation(wiperLeft.getRotation() >= 360 ? 1 : wiperLeft.getRotation() + Gdx.graphics.getDeltaTime() * 30 * 5);
        wiperRight.setRotation(wiperRight.getRotation() <= -360 ? 1 : wiperRight.getRotation() - Gdx.graphics.getDeltaTime() * 30 * 5);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (wiperLeft.contains(screenX, screenY) || wiperRight.contains(screenX, screenY))
            nextLevel(false);
        else if (this.button.contains(screenX, screenY))
            nextLevel(true, 500);
        else
            nextLevel(false);
        return true;
    }
}
