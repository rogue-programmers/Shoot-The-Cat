package com.rougeprogrammers.shootthecat.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

// TODO: Auto-generated Javadoc
/**
 * The Class Assets.
 */
public class Assets {

	/** The tag. */
	protected final String TAG = this.getClass().getSimpleName();

	/** The cat texture region. */
	private TextureRegion catTextureRegion;

	/** The grass texture region. */
	private TextureRegion grassTextureRegion;

	/** The tnt texture region. */
	private TextureRegion tntTextureRegion;

	/** The exploded tnt texture region. */
	private TextureRegion explosionTextureRegion;

	/** The background texture region. */
	private TextureRegion backgroundTextureRegion;

	/** The cannon texture region. */
	private TextureRegion cannonTextureRegion;

	/** The thorn texture region. */
	private TextureRegion thornTextureRegion;

	/** The spring texture region. */
	private TextureRegion springTextureRegion;

	/** The cannon fire sound. */
	private Sound cannonFireSound;

	/** The cat shoot sound. */
	private Sound catShootSound;

	/** The cat ouch sound. */
	private Sound catOuchSound;

	/** The tnt explosion sound. */
	private Sound tntExplosionSound;

	/** The spring sound. */
	private Sound springSound;

	/**
	 * Instantiates a new assets.
	 */
	public Assets() {

	}

	/**
	 * Load.
	 */
	public void load() {
		loadTextures();
		loadSounds();
		Gdx.app.log(TAG, "loaded");
	}

	/**
	 * Load textures.
	 */
	private void loadTextures() {
		catTextureRegion = new TextureRegion(new Texture(Gdx.files.internal("textures/cat.png")));
		grassTextureRegion = new TextureRegion(new Texture(Gdx.files.internal("textures/grass.png")));
		tntTextureRegion = new TextureRegion(new Texture(Gdx.files.internal("textures/tnt.png")));
		explosionTextureRegion = new TextureRegion(new Texture(Gdx.files.internal("textures/exploded_tnt.png")));
		backgroundTextureRegion = new TextureRegion(new Texture(Gdx.files.internal("textures/background.jpg")));
		cannonTextureRegion = new TextureRegion(new Texture(Gdx.files.internal("textures/cannon.gif")));
		thornTextureRegion = new TextureRegion(new Texture(Gdx.files.internal("textures/thorn.png")));
		springTextureRegion = new TextureRegion(new Texture(Gdx.files.internal("textures/spring.png")));
	}

	/**
	 * Load sounds.
	 */
	private void loadSounds() {
		cannonFireSound = Gdx.audio.newSound(Gdx.files.internal("sounds/cannon_fire.ogg"));
		catShootSound = Gdx.audio.newSound(Gdx.files.internal("sounds/cat_shoot.wav"));
		catOuchSound = Gdx.audio.newSound(Gdx.files.internal("sounds/cat_ouch.wav"));
		tntExplosionSound = Gdx.audio.newSound(Gdx.files.internal("sounds/tnt_explosion.wav"));
		springSound = Gdx.audio.newSound(Gdx.files.internal("sounds/spring.wav"));
	}

	/**
	 * Gets the exploded tnt texture region.
	 *
	 * @return the exploded tnt texture region
	 */
	public TextureRegion getExplosionTextureRegion() {
		return explosionTextureRegion;
	}

	/**
	 * Gets the cat texture region.
	 *
	 * @return the cat texture region
	 */
	public TextureRegion getCatTextureRegion() {
		return catTextureRegion;
	}

	/**
	 * Gets the grass texture region.
	 *
	 * @return the grass texture region
	 */
	public TextureRegion getGrassTextureRegion() {
		return grassTextureRegion;
	}

	/**
	 * Gets the tnt texture region.
	 *
	 * @return the tnt texture region
	 */
	public TextureRegion getTntTextureRegion() {
		return tntTextureRegion;
	}

	/**
	 * Gets the background texture region.
	 *
	 * @return the background texture region
	 */
	public TextureRegion getBackgroundTextureRegion() {
		return backgroundTextureRegion;
	}

	/**
	 * Gets the cannon texture region.
	 *
	 * @return the cannon texture region
	 */
	public TextureRegion getCannonTextureRegion() {
		return cannonTextureRegion;
	}

	/**
	 * Gets the thorn texture region.
	 *
	 * @return the thorn texture region
	 */
	public TextureRegion getThornTextureRegion() {
		return thornTextureRegion;
	}

	/**
	 * Gets the spring texture region.
	 *
	 * @return the spring texture region
	 */
	public TextureRegion getSpringTextureRegion() {
		return springTextureRegion;
	}

	/**
	 * Gets the cannon fire sound.
	 *
	 * @return the cannon fire sound
	 */
	public Sound getCannonFireSound() {
		return cannonFireSound;
	}

	/**
	 * Gets the cat shoot sound.
	 *
	 * @return the cat shoot sound
	 */
	public Sound getCatShootSound() {
		return catShootSound;
	}

	/**
	 * Gets the cat ouch sound.
	 *
	 * @return the cat ouch sound
	 */
	public Sound getCatOuchSound() {
		return catOuchSound;
	}

	/**
	 * Gets the tnt explosion sound.
	 *
	 * @return the tnt explosion sound
	 */
	public Sound getTntExplosionSound() {
		return tntExplosionSound;
	}

	/**
	 * Gets the spring sound.
	 *
	 * @return the spring sound
	 */
	public Sound getSpringSound() {
		return springSound;
	}

}