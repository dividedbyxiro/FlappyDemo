package com.xiro.game.sprites;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.math.Vector2;

public class Tube
{
	private Texture topTube;
	private Texture bottomTube;
	private Vector2 topPos, bottomPos;
	private Random random;

	private static final int FLUCTUATION = 130;
	private static final int TUBE_GAP = 100;
	private static final int LOWEST_OPENING = 120;
	public static final int TUBE_WIDTH = 52;

	public Tube(float x)
	{
		topTube = new Texture("toptube.png");
		bottomTube = new Texture("bottomtube.png");
		random = new Random();

		topPos = new Vector2(x, random.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
		bottomPos = new Vector2(x, topPos.y - TUBE_GAP - bottomTube.getHeight());
	}

	public Vector2 getBottomPos()
	{
		return bottomPos;
	}

	public Texture getBottomTube()
	{
		return bottomTube;
	}

	public Vector2 getTopPos()
	{
		return topPos;
	}

	public Texture getTopTube()
	{
		return topTube;
	}
	
	public void rePosition(float x)
	{
		topPos.set(x, random.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
		bottomPos.set(x, topPos.y - TUBE_GAP - bottomTube.getHeight());
	}
}
