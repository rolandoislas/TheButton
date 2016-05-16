package com.rolandoislas.thebutton.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.rolandoislas.thebutton.Assets;

/**
 * Created by Rolando on 4/24/2016.
 */
public class Switch extends Entity {
    private Texture textureOn;
    private Texture textureOff;
    private boolean enabled = false;

    public Switch() {
        textureOn = Assets.instance.getManager().get("img/switch_on.png", Texture.class);
        textureOff = Assets.instance.getManager().get("img/switch_off.png", Texture.class);
        this.setWidth(Gdx.graphics.getWidth() * .33f);
        this.setHeight(textureOn.getHeight() * this.getWidth() / textureOn.getWidth());
        this.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                toggle();
            }
        });
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(enabled ? textureOn : textureOff, this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

    private void toggle() {
        enabled = !enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
