package com.rougeprogrammers.shootthecat.objects;

import com.badlogic.gdx.Gdx;
import com.rougeprogrammers.shootthecat.Main;
import com.rougeprogrammers.shootthecat.objects.models.SimpleModel;

// TODO: Auto-generated Javadoc
/**
 * The Class Background.
 */
public class Background extends SimpleModel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3934673464840910646L;

	/** The tag. */
	protected final String TAG = this.getClass().getSimpleName();

	/** The reseted. */
	public boolean reseted;

	/**
	 * Instantiates a new background.
	 *
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param width
	 *            the width
	 * @param height
	 *            the height
	 */
	public Background(float x, float y, float width, float height) {
		super(x, y, width, height);
		textureRegion = Main.assets.getBackgroundTextureRegion();
		Gdx.app.log(TAG, "created");
	}

}
