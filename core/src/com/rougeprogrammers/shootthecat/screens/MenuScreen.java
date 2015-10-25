package com.rougeprogrammers.shootthecat.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.rougeprogrammers.shootthecat.stages.MenuStage;

public class MenuScreen implements Screen {

	private MenuStage stage;

	public MenuScreen(Game game) {
		stage = new MenuStage(game);
	}

	@Override
	public void show() {
		stage.startMusic();
		stage.fadeOut();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.draw();
		stage.act();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {

	}

}
