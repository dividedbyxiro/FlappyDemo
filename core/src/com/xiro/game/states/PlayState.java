package com.xiro.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.xiro.game.FlappyDemo;
import com.xiro.game.sprites.Bird;
import com.xiro.game.sprites.Tube;

public class PlayState extends State
{

	private static final int TUBE_SPACING = 125;
	private static final int TUBE_COUNT = 4;
	private static final int GROUND_OFFSET = -30;

	private Bird bird;
	private Texture bg;
	private Texture ground;
	private Vector2 groundPos1, groundPos2;

	private Array<Tube> tubes;

	protected PlayState(GameStateManager gsm)
	{
		super(gsm);
		bird = new Bird(50, 300);
		cam.setToOrtho(false, FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2);
		bg = new Texture("bg.png");
		ground = new Texture("ground.png");
		groundPos1 = new Vector2(cam.position.x - cam.viewportWidth / 2, GROUND_OFFSET);
		groundPos2 = new Vector2(cam.position.x - cam.viewportWidth / 2 + ground.getWidth(), GROUND_OFFSET);

		tubes = new Array<Tube>();

		for (int i = 0; i < TUBE_COUNT; i++)
		{
			tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
		}
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void handleInput()
	{
		if (Gdx.input.justTouched() || Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
		{
			bird.jump();
		}
		// TODO Auto-generated method stub

	}

	@Override
	public void update(float dt)
	{
		handleInput();
		updateGround();
		bird.update(dt);

		cam.position.x = bird.getPosition().x + 80;
		cam.update();

		if (bird.getPosition().y < ground.getHeight() + GROUND_OFFSET)
		{
			gsm.set(new PlayState(gsm));

		}

		for (Tube tube : tubes)
		{
			if (cam.position.x - cam.viewportWidth / 2 > tube.getBottomPos().x + tube.getTopTube().getWidth())
			{
				tube.rePosition(tube.getBottomPos().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));

			}

			if (tube.collides(bird.getBounds()))
			{
				gsm.set(new PlayState(gsm));
				break;
			}
		}
		// TODO Auto-generated method stub

	}

	@Override
	public void render(SpriteBatch sb)
	{
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		sb.draw(bg, cam.position.x - cam.viewportWidth / 2, 0);
		sb.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);
		for (Tube tube : tubes)
		{
			sb.draw(tube.getTopTube(), tube.getTopPos().x, tube.getTopPos().y);
			sb.draw(tube.getBottomTube(), tube.getBottomPos().x, tube.getBottomPos().y);
		}
		sb.draw(ground, groundPos1.x, groundPos1.y);
		sb.draw(ground, groundPos2.x, groundPos2.y);
		sb.end();
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose()
	{
		bg.dispose();
		bird.dispose();
		ground.dispose();
		for (Tube tube : tubes)
		{
			tube.dispose();
		}
		System.out.println("playstate disposed");
		// TODO Auto-generated method stub

	}

	private void updateGround()
	{
		if (cam.position.x - cam.viewportWidth / 2 > groundPos1.x + ground.getWidth())
		{
			groundPos1.add(ground.getWidth() * 2, 0);
		}
		if (cam.position.x - cam.viewportWidth / 2 > groundPos2.x + ground.getWidth())
		{
			groundPos2.add(ground.getWidth() * 2, 0);
		}
	}

}
