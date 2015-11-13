package com.rougeprogrammers.shootthecat.stages;

import static com.rougeprogrammers.shootthecat.utils.Constants.BOX_TO_WORLD;
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
import com.rougeprogrammers.shootthecat.objects.models.ObjectType.Type;
import com.rougeprogrammers.shootthecat.objects.models.Obstacle;
import com.rougeprogrammers.shootthecat.objects.obstacles.RunPower;
import com.rougeprogrammers.shootthecat.objects.obstacles.Spring;
import com.rougeprogrammers.shootthecat.objects.obstacles.TNT;
import com.rougeprogrammers.shootthecat.objects.obstacles.Thorn;
import com.rougeprogrammers.shootthecat.screens.ScreenModel;
import com.rougeprogrammers.shootthecat.utils.Constants;

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
	private Obstacle[] obstacles = new Obstacle[3];

	private int obstacleIndex;

	// private Obstacle obstacle2;

	/** The contacted. */
	private boolean contacted = false;

	/** The game started. */
	public boolean gameStarted;

	/** The screen. */
	@SuppressWarnings("unused")
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
		firstGround = new Ground(Ground.X, this);
		firstGround.shifted = true;
		secondGround = new Ground(Ground.X + Ground.WIDTH, this);
		cannon = new Cannon();
		for (int i = 0; i < obstacles.length; i++) {
			createNewObstacle(1.5f * WIDTH + WIDTH * i, i);
		}
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
		if (cat == null) {
			cat = new Cat(this);
			cannon.explode(cat);
			startGame();
		} else {
			cat.shoot(new Vector2(0.5f, 1), cat.getPosition());
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
			gameStarted = false;
			addActor(new Dialogs(cat == null ? WIDTH / 2 : cat.getX(), "", "Pause"));
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
		gameStarted = false;
		// screen.fadeIn();
		Dialogs d = new Dialogs(cat.getX(), MathUtils.round(cat.getX() * Constants.WORLD_TO_BOX) + " M",
				"Pause");
		d.setZIndex(getActors().size);
		addActor(d);
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
			updateObstacles();
			updateWorld(delta);
			if (cat.getX() > WIDTH / 2) {
				updateCamera();
			}
			if (cat.getVelocityX() == 0) {
				pause();
			}
		}
		super.act(delta);
	}

	private void updateObstacles() {
		for (int i = 0; i < obstacles.length; i++) {
			if (cat.getX() - obstacles[i].getX() > WIDTH) {
				createNewObstacle(cat.getX() + 2 * WIDTH, i);
			}
		}
	}

	/**
	 * Handle contacted.
	 */
	private void handleContact() {
		if (contacted) {
			obstacles[obstacleIndex].action(cat, contactPoints);
			contacted = false;
		}
	}

	/**
	 * Update ground.
	 */
	private void updateGround() {
		if (!firstGround.shifted && cat.getX() >= secondGround.getX()) {
			removeCannon();
			firstGround.setX(secondGround.getX() + Ground.WIDTH);
			firstGround.shifted = true;
			secondGround.shifted = false;
			Gdx.app.log(TAG, "first ground shifted");
		}
		if (!secondGround.shifted && cat.getX() >= firstGround.getX()) {
			secondGround.setX(firstGround.getX() + Ground.WIDTH);
			secondGround.shifted = true;
			firstGround.shifted = false;
			Gdx.app.log(TAG, "second ground shifted");
		}
	}

	/**
	 * Creates the new obstacle.
	 *
	 * @param x
	 *            the x
	 */
	private void createNewObstacle(float x, int index) {
		if (obstacles[index] != null) {
			obstacles[index].dispose();
		}
		if (MathUtils.randomBoolean(0.1f)) {
			obstacles[index] = new RunPower(x, this, index);
			return;
		}
		switch (MathUtils.random(2)) {
		case 0:
			obstacles[index] = new Spring(x, this, index);
			break;
		case 1:
			obstacles[index] = new Thorn(x, this, index);
			break;
		case 2:
			obstacles[index] = new TNT(x, this, index);
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
		contactPoints = contact.getWorldManifold().getPoints();
		if (a.type != Type.GROUND && b.type != Type.GROUND) {
			obstacleIndex = a.type == Type.CAT ? b.index : a.index;
			contacted = true;
			Gdx.app.log(TAG, "cat contacted with obstacle");
		} else if (cat.getVelocityY() <= Cat.OUCH_SPEED) {
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