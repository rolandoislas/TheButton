package com.rolandoislas.thebutton.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.rolandoislas.thebutton.Assets;

import java.util.ArrayList;

/**
 * Created by Rolando on 5/13/2016.
 */
public class Ball extends Entity {
    private final Texture texture;
    private ArrayList<Entity> entities = new ArrayList<Entity>();
    private float fallSpeed = 20;

    public Ball() {
        texture = Assets.instance.getManager().get("img/ball.png", Texture.class);
        this.setWidth(Gdx.graphics.getWidth() * .1f);
        this.setHeight(this.getWidth());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.setColor(this.getColor());
        batch.draw(texture, this.getX(), this.getY(), this.getWidth(), this.getHeight());
        batch.setColor(Color.WHITE);
        // Gravity update
        if (!isColliding())
            this.setY(this.getY() - Gdx.graphics.getDeltaTime() * 30 * fallSpeed);
    }

    public boolean isColliding() {
        for (Entity entity : entities)
            if (entity.contains(this))
                return true;
        return false;
    }

    public void addCollidingEntity(Entity entity) {
        entities.add(entity);
    }

    public void setFallSpeed(int fallSpeed) {
        this.fallSpeed = fallSpeed;
    }
}
