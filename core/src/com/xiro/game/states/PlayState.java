package com.xiro.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.xiro.game.FlappyDemo;
import com.xiro.game.sprites.Bird;
import com.xiro.game.sprites.Tube;

public class PlayState extends State
{

    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;

    private Bird bird;
    private Texture bg;

    private Array<Tube> tubes;

    

    protected PlayState(GameStateManager gsm)
    {
	super(gsm);
	bird = new Bird(50, 300);
	cam.setToOrtho(false, FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2);
	bg = new Texture("bg.png");
	

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
	bird.update(dt);

	cam.position.x = bird.getPosition().x + 80;
	cam.update();

	for (Tube tube : tubes)
	{
	    if (cam.position.x - cam.viewportWidth / 2 > tube.getBottomPos().x + tube.getTopTube().getWidth())
	    {
		tube.rePosition(tube.getBottomPos().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
		
	    }
	    
	    if(tube.collides(bird.getBounds()))
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
	sb.end();
	// TODO Auto-generated method stub

    }

    @Override
    public void dispose()
    {
	bg.dispose();
	bird.dispose();
	for(Tube tube : tubes)
	{
	    tube.dispose();
	}
	System.out.println("playstate disposed");
	// TODO Auto-generated method stub

    }

}
