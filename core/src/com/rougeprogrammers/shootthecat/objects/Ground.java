package com.rougeprogrammers.shootthecat.objects;

import com.badlogic.gdx.Gdx;
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
import com.rougeprogrammers.shootthecat.stages.GameStage;
import com.rougeprogrammers.shootthecat.utils.Constants;

// TODO: Auto-generated Javadoc
/**
 * The Class Ground.
 */
public class Ground extends Model {

	/** The rect. */
	private Rectangle rect;

	/** The texture region. */
	private TextureRegion textureRegion;

	/** The reseted. */
	public boolean reseted;

	/**
	 * Instantiates a new ground.
	 *
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param width
	 *            the width
	 * @param height
	 *            the height
	 * @param gameStage
	 *            the game stage
	 */
	public Ground(float x, float y, float width, float height, GameStage gameStage) {
		super(x, y, width, height, gameStage);
		rect = new Rectangle(x, y + height * (Constants.GROUND_BODY_TEXTURE_SCALER - 1) / 2, width,
				height * Constants.GROUND_BODY_TEXTURE_SCALER);
		textureRegion = Main.assets.getGrassTextureRegion();
		Gdx.app.log(TAG, "created");
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
		body.createFixture(shape, Constants.GROUND_DENSITY);
		shape.dispose();
		body.setUserData(ObjectType.GROUND);
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
