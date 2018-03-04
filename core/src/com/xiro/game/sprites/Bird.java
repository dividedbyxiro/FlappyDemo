package com.xiro.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Bird
{

	private static final int GRAVITY = -15;
	private static final int MOVEMENT = 100;
	private Vector3 position;
	private Vector3 velocity;
	private Texture texture;
	private Rectangle playerBounds;
	private Animation birdAnimation;

	public Bird(int x, int y)
	{
		position = new Vector3(x, y, 0);
		velocity = new Vector3(0, 0, 0);
		
		texture = new Texture("birdAnimation.png");
		birdAnimation = new Animation(texture, 3, 0.5f);
		playerBounds = new Rectangle(x, y, texture.getWidth() / 3, texture.getHeight());
		// TODO Auto-generated constructor stub
	}

	public void update(float dt)
	{
		velocity.add(0, GRAVITY, 0);
		velocity.scl(dt);
		position.add(MOVEMENT * dt, velocity.y, 0);
		velocity.scl(1 / dt);

		if (position.y < 0)
		{
			position.y = 0;
		}

		playerBounds.setPosition(position.x, position.y);
		birdAnimation.update(dt);
	}

	public Vector3 getPosition()
	{
		return position;
	}

	public TextureRegion getTexture()
	{
		return birdAnimation.getFrame();
	}

	public void jump()
	{
		velocity.y = 250;
	}

	public Rectangle getBounds()
	{
		return playerBounds;
	}

	public void dispose()
	{
		texture.dispose();
	}
}
