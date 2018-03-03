package com.xiro.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.xiro.game.FlappyDemo;

public class MenuState extends State
{
	private Texture background;
	private Texture playButton;

	public MenuState(GameStateManager gsm)
	{
		super(gsm);
		background = new Texture("bg.png");
		playButton = new Texture("playbtn.png");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handleInput()
	{
		if(Gdx.input.justTouched())
		{
			gsm.set(new PlayState(gsm));
			dispose();
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(float dt)
	{
		handleInput();
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(SpriteBatch sb)
	{
		sb.begin();
		sb.draw(background, 0, 0, FlappyDemo.WIDTH, FlappyDemo.HEIGHT);
		sb.draw(playButton, FlappyDemo.WIDTH / 2 - playButton.getWidth() / 2, FlappyDemo.HEIGHT / 2);
		sb.end();
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void dispose()
	{
		background.dispose();
		playButton.dispose();
		
	}

}
