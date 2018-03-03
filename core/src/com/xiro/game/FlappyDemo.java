package com.xiro.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.xiro.game.states.GameStateManager;
import com.xiro.game.states.MenuState;

public class FlappyDemo extends ApplicationAdapter
{
	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;
	public static final String TITLE = "Flappy Demo";
	private GameStateManager gsm;
	private SpriteBatch batch;

	@Override
	public void create()
	{
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		gsm.push(new MenuState(gsm));
		Gdx.gl.glClearColor(1, 0, 0, 1);
	}

	@Override
	public void render()
	{
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}

	@Override
	public void dispose()
	{
		batch.dispose();
	}
}
