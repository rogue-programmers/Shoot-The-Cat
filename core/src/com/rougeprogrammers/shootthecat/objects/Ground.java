package com.rougeprogrammers.shootthecat.objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.rougeprogrammers.shootthecat.Main;
import com.rougeprogrammers.shootthecat.objects.models.Model;
import com.rougeprogrammers.shootthecat.objects.models.ObjectType;
import com.rougeprogrammers.shootthecat.objects.models.ObjectType.Type;
import com.rougeprogrammers.shootthecat.stages.GameStage;
import com.rougeprogrammers.shootthecat.utils.Constants;

// TODO: Auto-generated Javadoc
/**
 * The Class Ground.
 */
public class Ground extends Model {

	/** The Constant GROUND_WIDTH. */
	public static final float WIDTH = Constants.WIDTH * 2f;

	/** The Constant GROUND_HEIGHT. */
	public static final float HEIGHT = 38;

	/** The Constant GROUND_X. */
	public static final float X = Constants.WIDTH / 2;

	/** The Constant GROUND_Y. */
	public static final float Y = HEIGHT / 2;

	/** The Constant GROUND_DENSITY. */
	public static final float DENSITY = 1f;

	/** The Constant GROUND_BODY_TEXTURE_SCALER. */
	public static final float BODY_TEXTURE_SCALER = 12;

	/** The rect. */
	private Rectangle rect;

	/** The texture region. */
	private TextureRegion textureRegion;

	/** The reseted. */
	public boolean shifted;

	/**
	 * Instantiates a new ground.
	 *
	 * @param gameStage
	 *            the game stage
	 */
	public Ground(float x, GameStage gameStage) {
		super(x, Y, WIDTH, HEIGHT, gameStage, 0);
		rect = new Rectangle(x, Constants.HEIGHT / 2, WIDTH, Constants.HEIGHT);
		textureRegion = Main.assets.getBackgroundTextureRegion();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.rougeprogrammers.shootthecat.objects.models.Model#createBody(float,
	 * float)
	 */
	@Override
	protected Body createBody(float x, float y) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.StaticBody;
		bodyDef.position.set(x * Constants.WORLD_TO_BOX, y * Constants.WORLD_TO_BOX);
		Body body = gameStage.getWorld().createBody(bodyDef);
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(getWidth() / 2 * Constants.WORLD_TO_BOX, getHeight() / 2 * Constants.WORLD_TO_BOX);
		body.createFixture(shape, DENSITY);
		shape.dispose();
		body.setUserData(new ObjectType(Type.GROUND));
		return body;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.badlogic.gdx.scenes.scene2d.Actor#draw(com.badlogic.gdx.graphics.g2d.
	 * Batch, float)
	 */
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.draw(textureRegion, rect.x - rect.width / 2, rect.y - rect.height / 2, rect.width, rect.height);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.scenes.scene2d.Actor#setX(float)
	 */
	@Override
	public void setX(float x) {
		body.setTransform(x * Constants.WORLD_TO_BOX, body.getPosition().y, body.getAngle());
		rect.x = x;
	}

}
