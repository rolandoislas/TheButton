package com.rolandoislas.thebutton.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.*;

/**
 * Created by Rolando on 4/24/2016.
 */
public class Entity extends Actor {
    public Entity() {
        this.setTouchable(Touchable.enabled);
    }

    public boolean contains(int x, int y) {
        int x1 = (int) this.getX();
        int y1 = (int) (Gdx.graphics.getHeight() - this.getY() - this.getHeight());
        int x2 = (int) (x1 + this.getWidth());
        int y2 = (int) (y1 + this.getHeight());
        return (x >= x1 && x <= x2) && (y >= y1 && y <= y2);
    }

    public void positionCenter() {
        this.setX(Gdx.graphics.getWidth() / 2 - this.getWidth() / 2);
        this.setY(Gdx.graphics.getHeight() / 2 - this.getHeight() / 2);
    }

    public boolean contains(Entity entity) {
        Rectangle bounds = new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        Rectangle boundsEntity = new Rectangle(entity.getX(), entity.getY(), entity.getWidth(), entity.getHeight());
        return bounds.overlaps(boundsEntity);
    }
}
