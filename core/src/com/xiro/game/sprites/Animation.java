/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xiro.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 *
 * @author Bradley
 */
public class Animation
{

	private Array<TextureRegion> frames;
	private float maxFrameTime;
	private float currentFrameTime;
	private int frameCount;
	private int currentFrame;

	public Animation(Texture frames, int frameCount, float cycleTime)
	{
		this.frames = new Array<TextureRegion>();
		int frameWidth = frames.getWidth() / frameCount;
		for(int i = 0; i < frameCount; i++)
		{
			this.frames.add(new TextureRegion(frames, i * frameWidth, 0, frameWidth, frames.getHeight()));
		}
		this.frameCount = frameCount;
		maxFrameTime = cycleTime / frameCount;
		currentFrame = 0;
	}
	
	public void update(float dt)
	{
		currentFrameTime += dt;
		if(currentFrameTime > maxFrameTime)
		{
			currentFrame++;
			currentFrameTime = 0;
		}
		currentFrame %= frameCount;
		
	}

	public TextureRegion getFrame()
	{
		return frames.get(currentFrame);
	}
}
