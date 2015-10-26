package com.rougeprogrammers.shootthecat.objects.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Disposable;

// TODO: Auto-generated Javadoc
/**
 * The Class SimpleModel.
 */
public class SimpleModel extends Rectangle implements Disposable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5948828413768988253L;

	/** The tag. */
	protected final String TAG = this.getClass().getSimpleName();

	/** The texture region. */
	protected TextureRegion textureRegion;

	/**
	 * Instantiates a new simple model.
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
	public SimpleModel(float x, float y, float width, float height) {
		super(x, y, width, height);
	}

	/**
	 * Update.
	 *
	 * @param delta
	 *            the delta
	 */
	public void update(float delta) {
		
	}

	/**
	 * Draw.
	 *
	 * @param batch
	 *            the batch
	 */
	public void draw(Batch batch) {
		batch.draw(textureRegion, x - width / 2, y - height / 2, width, height);
	}

	/**
	 * Gets the texture region.
	 *
	 * @return the texture region
	 */
	public TextureRegion getTextureRegion() {
		return textureRegion;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.utils.Disposable#dispose()
	 */
	@Override
	public void dispose() {
		Gdx.app.log(TAG, "destroyed");
	}

}
