package com.rougeprogrammers.shootthecat.objects;

import com.rougeprogrammers.shootthecat.Main;
import com.rougeprogrammers.shootthecat.objects.models.SimpleModel;

// TODO: Auto-generated Javadoc
/**
 * The Class Blood.
 */
public class Blood extends SimpleModel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4980552166636235102L;

	/** The Constant WIDTH. */
	public static final float WIDTH = 20f;

	/** The Constant HEIGHT. */
	public static final float HEIGHT = 20f;

	/**
	 * Instantiates a new blood.
	 *
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 */
	public Blood(float x, float y) {
		super(x, y, WIDTH, HEIGHT);
		textureRegion = Main.assets.getBloodTextureRegion();
	}

}
