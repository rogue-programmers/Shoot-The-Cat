package com.rougeprogrammers.shootthecat.stages;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Align;
import com.rougeprogrammers.shootthecat.Main;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.TweenAccessor;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

public class PauseMenu extends Window implements TweenAccessor<PauseMenu>, TweenCallback {

	protected final String TAG = this.getClass().getSimpleName();

	private static final int MENU_SCALE_TYPE = 0;

	private Skin skin;

	private TextButton resumeButton;

	private TextButton exitButton;

	private BitmapFont font;

	private TweenManager tweenManager;

	private enum ButtonType {
		RESUME, EXIT
	}

	public PauseMenu(GameStage stage, float x, float y) {
		super("", newStyle());
		font = Main.assets.getGameBitmapFont();
		skin = new Skin(Main.assets.getWindowTexturAtlas());
		resumeButton = newButton(ButtonType.RESUME, "Resume", "pauseButtons");
		exitButton = newButton(ButtonType.EXIT, "Exit", "pauseButtons");
		WindowStyle windowStyle = new WindowStyle(font, Color.BLACK, skin.getDrawable("windowSkin"));
		windowStyle.titleFont = font;
		setDebug(true);
		setBounds(640 - 250, 480 - 150, 500, 300);
		setOrigin(Align.center);
		add(resumeButton).row();
		add(exitButton);
		setVisible(false);
		stage.addActor(this);
	}

	private static WindowStyle newStyle() {
		WindowStyle style = new WindowStyle();
		return style;
	}

	private TextButton newButton(ButtonType buttonType, String buttonName, String drawableName) {
		TextButtonStyle style = new TextButtonStyle();
		style.up = skin.getDrawable(drawableName);
		style.font = font;
		TextButton button = new TextButton(buttonName, style);
		button.setUserObject(buttonType);
		return button;
	}

	@Override
	public void onEvent(int type, BaseTween<?> sourc) {

	}

	@Override
	public int getValues(PauseMenu target, int tweenType, float[] returnValues) {
		switch (tweenType) {
		case MENU_SCALE_TYPE:
			returnValues[0] = target.getScaleX();
			break;
		default:
			break;
		}
		return returnValues.length;
	}

	@Override
	public void setValues(PauseMenu target, int tweenType, float[] newValues) {
		switch (tweenType) {
		case MENU_SCALE_TYPE:
			target.setScale(newValues[0]);
			break;
		default:
			break;
		}
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		tweenManager.update(delta);
	}

}
