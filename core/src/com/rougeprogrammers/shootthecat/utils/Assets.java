package com.rougeprogrammers.shootthecat.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

// TODO: Auto-generated Javadoc
/**
 * The Class Assets.
 */
public class Assets {

	/** The tag. */
	protected final String TAG = this.getClass().getSimpleName();

	/** The logo texture region. */
	private TextureRegion logoTextureRegion;

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

	/** The menu texture region. */
	private TextureRegion menuTextureRegion;

	/** The blood texture region. */
	private TextureRegion bloodTextureRegion;

	/** The energy texture region. */
	private TextureRegion energyTextureRegion;

	/** The buttons texture atlas. */
	private TextureAtlas buttonsTextureAtlas;

	/** The window textur atlas. */
	private TextureAtlas windowTexturAtlas;

	/** The cat texture atlas. */
	private TextureAtlas catTextureAtlas;

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

	/** The click sound. */
	private Sound clickSound;

	/** The menu music. */
	private Music menuMusic;

	/** The game bitmap font. */
	private BitmapFont gameBitmapFont;

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
		loadMusics();
		loadFont();
		Gdx.app.log(TAG, "loaded");
	}

	/**
	 * Load textures.
	 */
	private void loadTextures() {
		logoTextureRegion = new TextureRegion(new Texture(Gdx.files.internal("logo.png")));
		tntTextureRegion = new TextureRegion(new Texture(Gdx.files.internal("textures/tnt.png")));
		explosionTextureRegion = new TextureRegion(new Texture(Gdx.files.internal("textures/exploded_tnt.png")));
		backgroundTextureRegion = new TextureRegion(new Texture(Gdx.files.internal("textures/background.jpg")));
		cannonTextureRegion = new TextureRegion(new Texture(Gdx.files.internal("textures/cannon.gif")));
		thornTextureRegion = new TextureRegion(new Texture(Gdx.files.internal("textures/thorn.png")));
		springTextureRegion = new TextureRegion(new Texture(Gdx.files.internal("textures/spring.png")));
		menuTextureRegion = new TextureRegion(new Texture(Gdx.files.internal("textures/menu_background.jpg")));
		bloodTextureRegion = new TextureRegion(new Texture(Gdx.files.internal("textures/blood.png")));
		energyTextureRegion = new TextureRegion(new Texture(Gdx.files.internal("textures/energy.png")));
		buttonsTextureAtlas = new TextureAtlas(Gdx.files.internal("atlases/buttons.pack"));
		windowTexturAtlas = new TextureAtlas(Gdx.files.internal("atlases/window_atlas.pack"));
		catTextureAtlas = new TextureAtlas(Gdx.files.internal("atlases/cat.pack"));
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
		clickSound = Gdx.audio.newSound(Gdx.files.internal("sounds/click.wav"));
	}

	/**
	 * Load musics.
	 */
	private void loadMusics() {
		menuMusic = Gdx.audio.newMusic(Gdx.files.internal("musics/menu_music.wav"));
	}

	/**
	 * Load font.
	 */
	private void loadFont() {
		gameBitmapFont = new BitmapFont(Gdx.files.internal("font/text.fnt"), false);
	}

	/**
	 * Gets the logo texture region.
	 *
	 * @return the logo texture region
	 */
	public TextureRegion getLogoTextureRegion() {
		return logoTextureRegion;
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
	 * Gets the menu texture region.
	 *
	 * @return the menu texture region
	 */
	public TextureRegion getMenuTextureRegion() {
		return menuTextureRegion;
	}

	/**
	 * Gets the blood texture region.
	 *
	 * @return the blood texture region
	 */
	public TextureRegion getBloodTextureRegion() {
		return bloodTextureRegion;
	}

	/**
	 * Gets the energy texture region.
	 *
	 * @return the energy texture region
	 */
	public TextureRegion getEnergyTextureRegion() {
		return energyTextureRegion;
	}

	/**
	 * Gets the buttons texture atlas.
	 *
	 * @return the buttons texture atlas
	 */
	public TextureAtlas getButtonsTextureAtlas() {
		return buttonsTextureAtlas;
	}

	/**
	 * Gets the window textur atlas.
	 *
	 * @return the window textur atlas
	 */
	public TextureAtlas getWindowTexturAtlas() {
		return windowTexturAtlas;
	}

	/**
	 * Gets the cat texture atlas.
	 *
	 * @return the cat texture atlas
	 */
	public TextureAtlas getCatTextureAtlas() {
		return catTextureAtlas;
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

	/**
	 * Gets the click sound.
	 *
	 * @return the click sound
	 */
	public Sound getClickSound() {
		return clickSound;
	}

	/**
	 * Gets the menu music.
	 *
	 * @return the menu music
	 */
	public Music getMenuMusic() {
		return menuMusic;
	}

	/**
	 * Gets the game bitmap font.
	 *
	 * @return the game bitmap font
	 */
	public BitmapFont getGameBitmapFont() {
		return gameBitmapFont;
	}

}