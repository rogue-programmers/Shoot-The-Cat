package com.rougeprogrammers.shootthecat.stages;

import static com.rougeprogrammers.shootthecat.utils.Constants.BACKGROUND_HEIGHT;
import static com.rougeprogrammers.shootthecat.utils.Constants.BACKGROUND_WIDTH;
import static com.rougeprogrammers.shootthecat.utils.Constants.BACKGROUND_X;
import static com.rougeprogrammers.shootthecat.utils.Constants.BACKGROUND_Y;
import static com.rougeprogrammers.shootthecat.utils.Constants.BOX_TO_WORLD;
import static com.rougeprogrammers.shootthecat.utils.Constants.CANNON_HEIGHT;
import static com.rougeprogrammers.shootthecat.utils.Constants.CANNON_WIDTH;
import static com.rougeprogrammers.shootthecat.utils.Constants.CANNON_X;
import static com.rougeprogrammers.shootthecat.utils.Constants.CANNON_Y;
import static com.rougeprogrammers.shootthecat.utils.Constants.CAT_HEIGHT;
import static com.rougeprogrammers.shootthecat.utils.Constants.CAT_RESTITUTION;
import static com.rougeprogrammers.shootthecat.utils.Constants.CAT_WIDTH;
import static com.rougeprogrammers.shootthecat.utils.Constants.CAT_X;
import static com.rougeprogrammers.shootthecat.utils.Constants.CAT_Y;
import static com.rougeprogrammers.shootthecat.utils.Constants.FPS;
import static com.rougeprogrammers.shootthecat.utils.Constants.GRAVITY;
import static com.rougeprogrammers.shootthecat.utils.Constants.GROUND_HEIGHT;
import static com.rougeprogrammers.shootthecat.utils.Constants.GROUND_WIDTH;
import static com.rougeprogrammers.shootthecat.utils.Constants.GROUND_X;
import static com.rougeprogrammers.shootthecat.utils.Constants.GROUND_Y;
import static com.rougeprogrammers.shootthecat.utils.Constants.HEIGHT;
import static com.rougeprogrammers.shootthecat.utils.Constants.CAT_OUCH_SPEED;
import static com.rougeprogrammers.shootthecat.utils.Constants.SPRING_HEIGHT;
import static com.rougeprogrammers.shootthecat.utils.Constants.SPRING_WIDTH;
import static com.rougeprogrammers.shootthecat.utils.Constants.SPRING_Y;
import static com.rougeprogrammers.shootthecat.utils.Constants.THORN_HEIGHT;
import static com.rougeprogrammers.shootthecat.utils.Constants.THORN_WIDTH;
import static com.rougeprogrammers.shootthecat.utils.Constants.THORN_Y;
import static com.rougeprogrammers.shootthecat.utils.Constants.TNT_HEIGHT;
import static com.rougeprogrammers.shootthecat.utils.Constants.TNT_WIDTH;
import static com.rougeprogrammers.shootthecat.utils.Constants.TNT_Y;
import static com.rougeprogrammers.shootthecat.utils.Constants.WIDTH;

import javax.swing.GroupLayout.Alignment;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.rougeprogrammers.shootthecat.Main;
import com.rougeprogrammers.shootthecat.objects.Background;
import com.rougeprogrammers.shootthecat.objects.Cannon;
import com.rougeprogrammers.shootthecat.objects.Cat;
import com.rougeprogrammers.shootthecat.objects.Ground;
import com.rougeprogrammers.shootthecat.objects.models.ObjectType;
import com.rougeprogrammers.shootthecat.objects.models.Obstacle;
import com.rougeprogrammers.shootthecat.objects.obstacles.Spring;
import com.rougeprogrammers.shootthecat.objects.obstacles.TNT;
import com.rougeprogrammers.shootthecat.objects.obstacles.Thorn;
import com.rougeprogrammers.shootthecat.screens.GameScreen;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenAccessor;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;
import aurelienribon.tweenengine.equations.Back;

// TODO: Auto-generated Javadoc
/**
 * The Class GameStage.
 */
public class GameStage extends Stage implements ContactListener, TweenAccessor<GameStage>, TweenCallback {

	/** The tag. */
	protected final String TAG = this.getClass().getSimpleName();

	/** The world. */
	private World world;

	/** The camera. */
	private OrthographicCamera camera;

	/** The debug renderer. */
	private Box2DDebugRenderer debugRenderer;

	/** The accumulator. */
	private float accumulator = 0f;

	/** The first background. */
	private Background firstBackground;

	/** The second background. */
	private Background secondBackground;

	/** The first ground. */
	private Ground firstGround;

	/** The second ground. */
	private Ground secondGround;

	/** The cannon. */
	private Cannon cannon;

	/** The cat. */
	private Cat cat;

	/** The obstacle. */
	private Obstacle obstacle;

	/** The contacted. */
	private boolean contacted = false;

	/** The game started. */
	public boolean gameStarted;

	private GameScreen gameScreen;

	private Window window;

	private Skin skin;

	private TextButton resumeButton;

	private TextButton exitButton;

	private BitmapFont font;

	private TweenManager tweenManager;

	private enum ButtonType {
		RESUME, EXIT
	}

	/**
	 * Instantiates a new game stage.
	 */
	public GameStage(GameScreen gameScreen) {
		super(new ScalingViewport(Scaling.stretch, WIDTH, HEIGHT, new OrthographicCamera(WIDTH, HEIGHT)));
		this.gameScreen = gameScreen;
		camera = (OrthographicCamera) getCamera();
		world = new World(GRAVITY, true);
		world.setContactListener(this);
		debugRenderer = new Box2DDebugRenderer();
		tweenManager = new TweenManager();
		Tween.registerAccessor(getClass(), this);
		createObjects();
		initWindow();
		Gdx.input.setInputProcessor(this);
		Gdx.app.log(TAG, "created");
	}

	private TextButton newButton(ButtonType buttonType, String buttonName, String drawableName) {
		TextButtonStyle style = new TextButtonStyle();
		style.up = skin.getDrawable(drawableName);
		style.font = font;
		TextButton button = new TextButton(buttonName, style);
		button.setUserObject(buttonType);
		return button;
	}

	private void initWindow() {
		font = Main.assets.getGameBitmapFont();
		skin = new Skin(Main.assets.getWindowTexturAtlas());
		resumeButton = newButton(ButtonType.RESUME, "Resume", "pauseButtons");
		exitButton = newButton(ButtonType.EXIT, "Exit", "pauseButtons");
		WindowStyle windowStyle = new WindowStyle(font, Color.BLACK, skin.getDrawable("windowSkin"));
		windowStyle.titleFont = font;
		window = new Window("Menu", windowStyle);
		window.setDebug(true);
		window.setBounds(WIDTH / 2 - 250, HEIGHT / 2 - 150, 500, 300);
		window.setOrigin(Align.center);
		window.add(resumeButton).row();
		window.add(exitButton);
		window.setVisible(false);
		addActor(window);
	}

	/**
	 * Create objects.
	 */
	private void createObjects() {
		firstBackground = new Background(BACKGROUND_X, BACKGROUND_Y, BACKGROUND_WIDTH, BACKGROUND_HEIGHT);
		firstBackground.reseted = true;
		secondBackground = new Background(BACKGROUND_X + BACKGROUND_WIDTH, BACKGROUND_Y, BACKGROUND_WIDTH,
				BACKGROUND_HEIGHT);
		firstGround = new Ground(GROUND_X, GROUND_Y, GROUND_WIDTH, GROUND_HEIGHT, this);
		firstGround.reseted = true;
		secondGround = new Ground(GROUND_X + GROUND_WIDTH, GROUND_Y, GROUND_WIDTH, GROUND_HEIGHT, this);
		cannon = new Cannon(CANNON_X, CANNON_Y, CANNON_WIDTH, CANNON_HEIGHT);
	}

	/**
	 * Start game.
	 */
	private void startGame() {
		gameStarted = true;
		Gdx.app.log(TAG, "game started");
	}

	@Override
	public void onEvent(int type, BaseTween<?> sourc) {

	}

	@Override
	public int getValues(GameStage target, int tweenType, float[] returnValues) {
		returnValues[0] = target.window.getScaleX();
		return returnValues.length;
	}

	@Override
	public void setValues(GameStage target, int tweenType, float[] newValues) {
		target.window.setScale(newValues[0]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.scenes.scene2d.Stage#touchDown(int, int, int, int)
	 */
	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		x = getScaledX(x);
		y = getScaledY(y);
		Gdx.app.log(TAG, "touched (" + x + ", " + y + ")");
		if (!gameStarted) {
			cat = new Cat(CAT_X, CAT_Y, CAT_WIDTH, CAT_HEIGHT, this);
			cannon.explode(cat);
			startGame();
		} else {
			if (cat.getRestitution() == 0) {
				cat.setRestitution(CAT_RESTITUTION);
			}
			cat.shoot(new Vector2(30, 100));
		}
		return true;
	}

	/**
	 * Gets the scaled x.
	 *
	 * @param screenX
	 *            the screen x
	 * @return the scaled x
	 */
	private int getScaledX(float screenX) {
		return (int) (screenX * WIDTH / Gdx.graphics.getWidth());
	}

	/**
	 * Gets the scaled y.
	 *
	 * @param screenY
	 *            the screen y
	 * @return the scaled y
	 */
	private int getScaledY(int screenY) {
		return (int) (HEIGHT - screenY * HEIGHT / Gdx.graphics.getHeight());
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
		case Keys.VOLUME_UP:
			camera.zoom--;
			break;
		case Keys.DOWN:
		case Keys.VOLUME_DOWN:
			camera.zoom++;
			break;
		case Keys.ESCAPE:
		case Keys.BACK:
			pause();
			break;
		case Keys.RIGHT:
			window.setScale(0.5f);
			break;
		case Keys.LEFT:
			window.setScale(1.5f);
			break;
		default:
			break;
		}
		return true;
	}

	private void pause() {
		window.setScale(0);
		window.setVisible(true);
		Tween.to(this, 0, 0.5f).target(1).ease(Back.OUT).start(tweenManager);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.scenes.scene2d.Stage#act(float)
	 */
	@Override
	public void act(float delta) {
		super.act(delta);
		if (gameStarted) {
			cat.act(delta);
			handleContact();
			updateGround();
			updateWorld(delta);
			if (cat.getX() > WIDTH / 2) {
				updateBackground();
				updateCamera();
			}
		}
		tweenManager.update(delta);
	}

	/**
	 * Handle contacted.
	 */
	private void handleContact() {
		if (contacted) {
			obstacle.action(cat);
			contacted = false;
		}
	}

	/**
	 * Update ground.
	 */
	private void updateGround() {
		if (!firstGround.reseted && cat.getX() >= secondGround.getX()) {
			removeCannon();
			firstGround.setX(secondGround.getX() + GROUND_WIDTH);
			firstGround.reseted = true;
			secondGround.reseted = false;
			Gdx.app.log(TAG, "first ground shifted");
			createNewObstacle(firstGround.getX() - GROUND_WIDTH / 2);
		}
		if (!secondGround.reseted && cat.getX() >= firstGround.getX()) {
			secondGround.setX(firstGround.getX() + GROUND_WIDTH);
			secondGround.reseted = true;
			firstGround.reseted = false;
			Gdx.app.log(TAG, "second ground shifted");
			createNewObstacle(secondGround.getX() - GROUND_WIDTH / 2);
		}
	}

	/**
	 * Creates the new obstacle.
	 *
	 * @param x
	 *            the x
	 */
	private void createNewObstacle(float x) {
		if (obstacle != null) {
			obstacle.dispose();
		}
		switch (MathUtils.random(2)) {
		case 0:
			obstacle = new TNT(x, TNT_Y, TNT_WIDTH, TNT_HEIGHT, this);
			break;
		case 1:
			obstacle = new Thorn(x, THORN_Y, THORN_WIDTH, THORN_HEIGHT, this);
			break;
		case 2:
			obstacle = new Spring(x, SPRING_Y, SPRING_WIDTH, SPRING_HEIGHT, this);
			break;
		case 3:

			break;
		default:
			break;
		}
	}

	/**
	 * Update world.
	 *
	 * @param delta
	 *            the delta
	 */
	private void updateWorld(float delta) {
		accumulator += delta;
		while (accumulator >= delta) {
			world.step(FPS, 6, 2);
			accumulator -= FPS;
		}
	}

	/**
	 * Update background.
	 */
	private void updateBackground() {
		firstBackground.x += cat.getVelocityX() * 1.5;
		secondBackground.x += cat.getVelocityX() * 1.5;
		if (!firstBackground.reseted && cat.getX() >= secondBackground.x) {
			firstBackground.x = secondBackground.x + BACKGROUND_WIDTH;
			firstBackground.reseted = true;
			secondBackground.reseted = false;
			Gdx.app.log(TAG, "first background shifted");
		}
		if (!secondBackground.reseted && cat.getX() >= firstBackground.x) {
			secondBackground.x = firstBackground.x + BACKGROUND_WIDTH;
			secondBackground.reseted = true;
			firstBackground.reseted = false;
			Gdx.app.log(TAG, "second background shifted");
		}
	}

	/**
	 * Removes the cannon.
	 */
	private void removeCannon() {
		if (cannon != null) {
			cannon.dispose();
			cannon = null;
		}
	}

	/**
	 * Update camera.
	 */
	private void updateCamera() {
		camera.position.x = cat.getX();
		camera.update();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.scenes.scene2d.Stage#draw()
	 */
	@Override
	public void draw() {
		float delta = Gdx.graphics.getDeltaTime();
		getBatch().begin();
		firstBackground.draw(getBatch(), delta);
		secondBackground.draw(getBatch(), delta);
		getBatch().end();
		super.draw();
		if (cannon != null) {
			getBatch().begin();
			cannon.draw(getBatch(), delta);
			getBatch().end();
		}
		debugRenderer.render(world, camera.combined.cpy().scl(BOX_TO_WORLD));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.badlogic.gdx.physics.box2d.ContactListener#beginContact(com.badlogic.
	 * gdx.physics.box2d.Contact)
	 */
	@Override
	public void beginContact(Contact contact) {
		ObjectType a = (ObjectType) contact.getFixtureA().getBody().getUserData();
		ObjectType b = (ObjectType) contact.getFixtureB().getBody().getUserData();
		if (a != ObjectType.GROUND && b != ObjectType.GROUND) {
			contacted = true;
			Gdx.app.log(TAG, "cat contacted with obstacle");
		} else if (cat.getVelocityY() <= CAT_OUCH_SPEED) {
			cat.ouch();
			Gdx.app.log(TAG, "cat contacted with ground");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.badlogic.gdx.physics.box2d.ContactListener#endContact(com.badlogic.
	 * gdx.physics.box2d.Contact)
	 */
	@Override
	public void endContact(Contact contact) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.badlogic.gdx.physics.box2d.ContactListener#preSolve(com.badlogic.gdx.
	 * physics.box2d.Contact, com.badlogic.gdx.physics.box2d.Manifold)
	 */
	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		if (contacted) {
			contact.setEnabled(false);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.badlogic.gdx.physics.box2d.ContactListener#postSolve(com.badlogic.gdx
	 * .physics.box2d.Contact, com.badlogic.gdx.physics.box2d.ContactImpulse)
	 */
	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {

	}

	/**
	 * Gets the world.
	 *
	 * @return the world
	 */
	public World getWorld() {
		return world;
	}

	@Override
	public void dispose() {
		super.dispose();
	}

}