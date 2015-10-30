package com.rougeprogrammers.shootthecat.objects;

import com.badlogic.gdx.Gdx;
import com.rougeprogrammers.shootthecat.Main;
import com.rougeprogrammers.shootthecat.objects.models.SimpleModel;

public class Blood extends SimpleModel {

	private static final long serialVersionUID = 4980552166636235102L;

	public static final float WIDTH = 20f;

	public static final float HEIGHT = 20f;
	

	public Blood(float x, float y) {
		super(x, y, WIDTH, HEIGHT);
		textureRegion = Main.assets.getBloodTextureRegion();
		Gdx.app.log(TAG, "created");
	}
	
}
