package com.rougeprogrammers.shootthecat.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.rougeprogrammers.shootthecat.Main;
import com.rougeprogrammers.shootthecat.screens.ScreenModel;
import com.rougeprogrammers.shootthecat.utils.Constants;

// TODO: Auto-generated Javadoc
/**
 * The Class MenuStage.
 */
public class MenuStage extends Stage {

	/** The tag. */
	protected final String TAG = this.getClass().getSimpleName();

	/** The game. */
	private ScreenModel screen;

	/** The backgraound. */
	private TextureRegion backgraound;

	/** The music. */
	private Music music;

	/** The click sound. */
	private Sound clickSound;

	/** The font. */
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
	 * @param screen
	 *            the screen
	 * @param camera
	 *            the camera
	 */
	public MenuStage(ScreenModel screen, OrthographicCamera camera) {
		super(new ScalingViewport(Scaling.stretch, Constants.WIDTH, Constants.HEIGHT, camera));
		this.screen = screen;
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
			screen.fadeIn();
			break;
		case SETTING:

			break;
		case EXIT:
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
	 * Sets the music volume.
	 *
	 * @param volume
	 *            the new music volume
	 */
	public void setMusicVolume(float volume) {
		music.setVolume(volume);
	}

	/**
	 * Gets the button table x.
	 *
	 * @return the button table x
	 */
	public float getButtonTableX() {
		return buttonTable.getX();
	}

	/**
	 * Sets the button table x.
	 *
	 * @param x
	 *            the new button table x
	 */
	public void setButtonTableX(float x) {
		buttonTable.setX(x);
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
		getBatch().end();
		super.draw();
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
		super.dispose();
		Gdx.app.log(TAG, "destroyed");
	}

}
