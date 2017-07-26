package com.rolandoislas.thebutton;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.rolandoislas.thebutton.level.*;
import com.rolandoislas.thebutton.playservices.PlayServices;

import java.util.Arrays;

public class TheButton extends ApplicationAdapter {
	private final PlayServices playServices;
	private Stage stage;
	private Class[] stages = new Class[] {
			LevelSingleButton.class,
			LevelButtonSwitch.class,
			LevelSameColorButton.class,
			//LevelKeypad.class,
			LevelSingleSwitch.class,
			LevelWipers.class,
			LevelFourButtons.class,
			LevelThreeSwitch.class,
			LevelSimon.class,
			LevelButtonRandom.class, // 10
			LevelMillion.class,
			LevelDuck.class,
			LevelCatch.class
	};
	private int score = 0;
	private int random = 0;

	public TheButton(PlayServices playServices) {
		this.playServices = playServices;
	}

	@Override
	public void create () {
		Assets.instance.init(new AssetManager());
		Class[] stagesCopy = Arrays.copyOf(stages, stages.length + 2);
		stagesCopy[stagesCopy.length - 2] = StartScreen.class;
		stagesCopy[stagesCopy.length - 1] = PostScreen.class;
		stage = new LoadingScreen(stagesCopy);
		stage.addListener(new EventListener() {
			@Override
			public boolean handle(Event event) {
				setNewLevel(false, true);
				return true;
			}
		});
	}

	private void setNewLevel(boolean success) {
		setNewLevel(success, false);
	}

	private void setNewLevel(boolean success, boolean newGame) {
		// Hide ad
		playServices.hideBannerAd();
		// Kill old stage
		if (stage != null)
			stage.dispose();
		// First stage
		if (newGame)
			stage = new StartScreen(playServices);
		// Score stage
		else if (!success)
			stage = new PostScreen(playServices, score);
		// Random stage
		else {
			int r = random;
			while (random == r && stages.length > 1)
				random = (int) Math.floor(Math.random() * stages.length);
			Class clazz = stages[random];
			try {
				stage = (Stage) clazz.newInstance();
			} catch (Exception e) {
				e.printStackTrace();
				stage = new PostScreen(playServices, score);
			}
		}
		// Input
		Gdx.input.setInputProcessor(stage);
		// Stage complete event
		stage.addListener(new EventListener() {
			@Override
			public boolean handle(Event event) {
				if (!(event instanceof LevelEvent))
					return false;
				LevelEvent e = (LevelEvent) event;
				if (e.getScore() == Integer.MIN_VALUE)
					score = 0;
				else
					score += e.getScore();
				setNewLevel(e.getSuccess());
				return true;
			}
		});
	}

	@Override
	public void resume() {
		setNewLevel(false, true);
		score = 0;
	}

	@Override
	public void dispose() {
		Assets.instance.dispose();
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.draw();
	}
}
