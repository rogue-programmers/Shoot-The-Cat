package com.rougeprogrammers.shootthecat.stages;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.rougeprogrammers.shootthecat.Main;
import com.rougeprogrammers.shootthecat.screens.GameScreen;
import com.rougeprogrammers.shootthecat.utils.Constants;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenAccessor;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;
import aurelienribon.tweenengine.equations.Back;

// TODO: Auto-generated Javadoc
/**
 * The Class MenuStage.
 */
public class MenuStage extends Stage implements TweenAccessor<MenuStage>, TweenCallback {

	/** The tag. */
	protected final String TAG = this.getClass().getSimpleName();

	/** The Constant FADE_TYPE. */
	private static final int FADE_TYPE = 0;

	/** The Constant TABLE_X_TYPE. */
	private static final int TABLE_X_TYPE = 1;

	/** The game. */
	private Game game;

	/** The renderer. */
	private ShapeRenderer renderer;

	/** The backgraound. */
	private TextureRegion backgraound;

	/** The music. */
	private Music music;

	private Sound clickSound;

	private BitmapFont font;

	/** The button table. */
	private Table buttonTable;

	/** The button skin. */
	private Skin buttonSkin;

	/** The play button. */
	private TextButton playButton;

	/** The setting button. */
	private TextButton settingButton;

	/** The exit button. */
	private TextButton exitButton;

	/** The input listener. */
	private InputListener inputListener;

	/** The tween manager. */
	private TweenManager tweenManager;

	/**
	 * The Enum ButtonType.
	 */
	private enum ButtonType {

		/** The play. */
		PLAY,
		/** The setting. */
		SETTING,
		/** The exit. */
		EXIT

	}

	/**
	 * Instantiates a new menu stage.
	 *
	 * @param game
	 *            the game
	 */
	public MenuStage(Game game) {
		super(new StretchViewport(Constants.WIDTH, Constants.HEIGHT), new SpriteBatch());
		this.game = game;
		renderer = new ShapeRenderer();
		renderer.setColor(Color.BLACK);
		renderer.setProjectionMatrix(getViewport().getCamera().combined);
		backgraound = Main.assets.getMenuTextureRegion();
		music = Main.assets.getMenuMusic();
		music.setLooping(true);
		music.setVolume(0);
		clickSound = Main.assets.getClickSound();
		font = Main.assets.getGameBitmapFont();
		buttonTable = new Table();
		buttonTable.setFillParent(true);
		buttonTable.setPosition(0, 0, 0);
		buttonTable.setX(300);
		addActor(buttonTable);
		buttonSkin = new Skin(Main.assets.getButtonsTextureAtlas());
		inputListener = new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				handleButtonDown((ButtonType) event.getListenerActor().getUserObject());
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				handleButtonUp((ButtonType) event.getListenerActor().getUserObject());
			}

		};
		setButtons();
		tweenManager = new TweenManager();
		Tween.registerAccessor(this.getClass(), this);
		Gdx.input.setInputProcessor(this);
		Gdx.app.log(TAG, "created");
	}

	/**
	 * Sets the buttons.
	 */
	private void setButtons() {
		playButton = newButton(ButtonType.PLAY, "playButton");
		settingButton = newButton(ButtonType.SETTING, "setting");
		exitButton = newButton(ButtonType.EXIT, "exit");

		buttonTable.right();
		buttonTable.add(settingButton).padRight(50).padBottom(50).align(Align.topRight);
		buttonTable.row();
		buttonTable.add(playButton).padRight(150).align(Align.right);
		buttonTable.row();
		buttonTable.add(exitButton).padRight(50).padTop(50).align(Align.bottomRight);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.scenes.scene2d.Stage#keyDown(int)
	 */
	@Override
	public boolean keyDown(int keyCode) {
		switch (keyCode) {
		case Keys.UP:

			break;
		case Keys.DOWN:

			break;
		case Keys.RIGHT:

			break;
		case Keys.LEFT:

			break;
		default:
			break;
		}
		return super.keyDown(keyCode);
	}

	/**
	 * New button.
	 *
	 * @param type
	 *            the type
	 * @param drawableName
	 *            the drawable name
	 * @return the text button
	 */
	private TextButton newButton(ButtonType type, String drawableName) {
		TextButtonStyle style = new TextButtonStyle();
		style.up = buttonSkin.getDrawable(drawableName);
		style.font = font;
		TextButton button = new TextButton("", style);
		button.setUserObject(type);
		button.addListener(inputListener);
		return button;
	}

	/**
	 * Handle button down.
	 *
	 * @param type
	 *            the type
	 */
	private void handleButtonDown(ButtonType type) {
		clickSound.play();
		Gdx.app.log(TAG, type + " button down");
	}

	/**
	 * Handle button up.
	 *
	 * @param type
	 *            the type
	 */
	private void handleButtonUp(ButtonType type) {
		Gdx.app.log(TAG, type + " button up");
		switch (type) {
		case PLAY:
			fadeIn();
			break;
		case SETTING:

			break;
		case EXIT:
			dispose();
			Gdx.app.exit();
			break;
		default:
			break;
		}
	}

	/**
	 * Start music.
	 */
	public void startMusic() {
		music.play();
	}

	/**
	 * Fade in.
	 */
	public void fadeIn() {
		Tween.to(this, FADE_TYPE, 1f).target(1, 0).setCallback(this).setUserData("fade_in").start(tweenManager);
	}

	/**
	 * Fade out.
	 */
	public void fadeOut() {
		Tween.to(this, FADE_TYPE, 1f).target(0, 1).setCallback(this).setUserData("fade_out").start(tweenManager);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.scenes.scene2d.Stage#draw()
	 */
	@Override
	public void draw() {
		getBatch().begin();
		getBatch().draw(backgraound, 0, 0, getWidth(), getHeight());
		font.draw(getBatch(), "Shoot The Cat", 100, 80);
		getBatch().end();
		super.draw();

		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		renderer.begin(ShapeType.Filled);
		renderer.rect(0, 0, getWidth(), getHeight());
		renderer.end();
		Gdx.gl.glDisable(GL20.GL_BLEND);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.scenes.scene2d.Stage#act(float)
	 */
	@Override
	public void act(float delta) {
		tweenManager.update(delta);
		super.act(delta);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see aurelienribon.tweenengine.TweenAccessor#getValues(java.lang.Object,
	 * int, float[])
	 */
	@Override
	public int getValues(MenuStage target, int tweenType, float[] returnValues) {
		switch (tweenType) {
		case FADE_TYPE:
			returnValues[0] = target.renderer.getColor().a;
			returnValues[1] = target.music.getVolume();
			break;
		case TABLE_X_TYPE:
			returnValues[0] = target.buttonTable.getX();
			break;
		default:
			break;
		}
		return returnValues.length;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see aurelienribon.tweenengine.TweenAccessor#setValues(java.lang.Object,
	 * int, float[])
	 */
	@Override
	public void setValues(MenuStage target, int tweenType, float[] newValues) {
		switch (tweenType) {
		case FADE_TYPE:
			target.renderer.setColor(0, 0, 0, newValues[0]);
			target.music.setVolume(newValues[1]);
			break;
		case TABLE_X_TYPE:
			target.buttonTable.setX(newValues[0]);
			break;
		default:
			break;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see aurelienribon.tweenengine.TweenCallback#onEvent(int,
	 * aurelienribon.tweenengine.BaseTween)
	 */
	@Override
	public void onEvent(int type, BaseTween<?> source) {
		if (type == TweenCallback.COMPLETE) {
			if (source.getUserData().equals("fade_out")) {
				Tween.to(this, TABLE_X_TYPE, 1.5f).target(0).ease(Back.OUT).start(tweenManager);
			} else if (source.getUserData().equals("fade_in")) {
				dispose();
				game.setScreen(new GameScreen());
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.scenes.scene2d.Stage#dispose()
	 */
	@Override
	public void dispose() {
		playButton.clear();
		settingButton.clear();
		exitButton.clear();
		buttonSkin.dispose();
		buttonTable.clear();
		music.dispose();
		tweenManager.killAll();
		super.dispose();
		game.dispose();
		Gdx.app.log(TAG, "destroyed");
	}

}
