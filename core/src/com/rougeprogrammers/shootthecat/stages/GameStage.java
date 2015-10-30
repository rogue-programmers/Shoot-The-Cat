package com.rougeprogrammers.shootthecat.stages;

import static com.rougeprogrammers.shootthecat.utils.Constants.FPS;
import static com.rougeprogrammers.shootthecat.utils.Constants.GRAVITY;
import static com.rougeprogrammers.shootthecat.utils.Constants.HEIGHT;
import static com.rougeprogrammers.shootthecat.utils.Constants.WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.rougeprogrammers.shootthecat.objects.Cannon;
import com.rougeprogrammers.shootthecat.objects.Cat;
import com.rougeprogrammers.shootthecat.objects.Ground;
import com.rougeprogrammers.shootthecat.objects.models.ObjectType;
import com.rougeprogrammers.shootthecat.objects.models.Obstacle;
import com.rougeprogrammers.shootthecat.objects.obstacles.RunPower;
import com.rougeprogrammers.shootthecat.objects.obstacles.Spring;
import com.rougeprogrammers.shootthecat.objects.obstacles.TNT;
import com.rougeprogrammers.shootthecat.objects.obstacles.Thorn;
import com.rougeprogrammers.shootthecat.screens.ScreenModel;

// TODO: Auto-generated Javadoc
/**
 * The Class GameStage.
 */
public class GameStage extends Stage implements ContactListener {

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

	/** The first ground. */
	private Ground firstGround;

	/** The second ground. */
	private Ground secondGround;

	/** The cannon. */
	private Cannon cannon;

	/** The cat. */
	private Cat cat;

	/** The contact points. */
	public Vector2[] contactPoints;

	/** The obstacle. */
	private Obstacle obstacle;

	/** The contacted. */
	private boolean contacted = false;

	/** The game started. */
	public boolean gameStarted;

	/** The screen. */
	private ScreenModel screen;

	/**
	 * Instantiates a new game stage.
	 *
	 * @param screen
	 *            the game screen
	 * @param camera
	 *            the camera
	 */
	public GameStage(ScreenModel screen, OrthographicCamera camera) {
		super(new ScalingViewport(Scaling.stretch, WIDTH, HEIGHT, camera));
		this.screen = screen;
		this.camera = camera;
		world = new World(GRAVITY, true);
		world.setContactListener(this);
		debugRenderer = new Box2DDebugRenderer();
		createObjects();
		Gdx.input.setInputProcessor(this);
		Gdx.app.log(TAG, "created");
	}

	/**
	 * Create objects.
	 */
	private void createObjects() {
		firstGround = new Ground(this);
		firstGround.reseted = true;
		secondGround = new Ground(this);
		cannon = new Cannon();
	}

	/**
	 * Start game.
	 */
	private void startGame() {
		gameStarted = true;
		Gdx.app.log(TAG, "game started");
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
			cat = new Cat(this);
			cannon.explode(cat);
			startGame();
		} else {
			// if (cat.getRestitution() == 0) {
			// cat.setRestitution(Cat.RESTITUTION);
			// }
			cat.shoot(new Vector2(20, 40), cat.getPosition());
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
			break;
		case Keys.LEFT:
			break;
		default:
			break;
		}
		return true;
	}

	/**
	 * Pause.
	 */
	private void pause() {
		screen.fadeIn();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.scenes.scene2d.Stage#act(float)
	 */
	@Override
	public void act(float delta) {
		if (gameStarted) {
			cat.act(delta);
			handleContact();
			updateGround();
			updateWorld(delta);
			if (cat.getX() > WIDTH / 2) {
				updateCamera();
			}
		}
		super.act(delta);
	}

	/**
	 * Handle contacted.
	 */
	private void handleContact() {
		if (contacted) {
			obstacle.action(cat, contactPoints);
			contacted = false;
		}
	}

	/**
	 * Update ground.
	 */
	private void updateGround() {
		if (!firstGround.reseted && cat.getX() >= secondGround.getX()) {
			removeCannon();
			firstGround.setX(secondGround.getX() + Ground.WIDTH);
			firstGround.reseted = true;
			secondGround.reseted = false;
			Gdx.app.log(TAG, "first ground shifted");
			createNewObstacle(firstGround.getX() - Ground.WIDTH / 2);
		}
		if (!secondGround.reseted && cat.getX() >= firstGround.getX()) {
			secondGround.setX(firstGround.getX() + Ground.WIDTH);
			secondGround.reseted = true;
			firstGround.reseted = false;
			Gdx.app.log(TAG, "second ground shifted");
			createNewObstacle(secondGround.getX() - Ground.WIDTH / 2);
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
		switch (MathUtils.random(3)) {
		case 0:
			obstacle = new TNT(x, this);
			break;
		case 1:
			obstacle = new Thorn(x, this);
			break;
		case 2:
			obstacle = new Spring(x, this);
			break;
		case 3:
			obstacle = new RunPower(x, this);
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
		super.draw();
		if (cannon != null) {
			getBatch().begin();
			cannon.draw(getBatch());
			getBatch().end();
		}
		// debugRenderer.render(world, camera.combined.cpy().scl(BOX_TO_WORLD));
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
			contactPoints = contact.getWorldManifold().getPoints();
			Gdx.app.log(TAG, "cat contacted with obstacle");
		} else if (cat.getVelocityY() <= Cat.OUCH_SPEED) {
			contactPoints = contact.getWorldManifold().getPoints();
			cat.ouch(contactPoints);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.scenes.scene2d.Stage#dispose()
	 */
	@Override
	public void dispose() {
		world.dispose();
		super.dispose();
	}

}