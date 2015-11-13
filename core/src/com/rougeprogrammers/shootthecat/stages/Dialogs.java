package com.rougeprogrammers.shootthecat.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.rougeprogrammers.shootthecat.Main;
import com.rougeprogrammers.shootthecat.utils.Constants;

public class Dialogs extends Dialog {

	public Dialogs(float x, String title, String Type) {
		super(title, getPauseSkin());
		switch (Type) {
		case "Exit":
			text("Really want to exit?");
			button("yes", "yes").getButtonTable().row();
			button("No", "no");
			break;
		case "Pause":
			text("Countinue?");
			button("Resume", "res").getButtonTable().row();
			button("Exit", "ext");
			break;
		default:
			System.err.println("WRONG TYPE ENTERED IN DIOLOG CLASS");
			Gdx.app.exit();
			break;
		}
		pack();
//		setPosition(Constants.WIDTH / 2 - (getWidth() / 2), Constants.HEIGHT / 2 - (getHeight() / 2));
		setPosition(x - getWidth() / 2, 2*Constants.HEIGHT / 3 - (getHeight() / 2));
	}
	
	private static Skin getPauseSkin() {
		BitmapFont font = Main.assets.getGameBitmapFont();
		Skin skin = new Skin();
		skin.addRegions(Main.assets.getWindowTexturAtlas());
		LabelStyle lb = new LabelStyle(font, Color.WHITE);
		TextButtonStyle ts = new TextButtonStyle(null, null, null, font);
		skin.add("default", new WindowStyle(font, Color.WHITE, skin.getDrawable("windowSkin")));
		skin.add("default", lb);
		skin.add("default", ts);
		return skin;
	}

	// {
	// text("Really want to exit?");
	// button("yes", true).getButtonTable().row();
	// button("No", false);
	// }

	@Override
	protected void result(Object object) {
		// TODO Auto-generated method stub
		switch (object.toString()) {
		case "yes":
			Gdx.app.exit();
			break;
		case "no":
		case "res":
			setVisible(false);
			break;
		case "ext":
			setVisible(false);
			break;
		default:
			break;
		}

		// if ((Boolean) object) {
		// Gdx.app.exit();
		// } else {
		// setVisible(false);
		// }
	}

}
