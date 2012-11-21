package com.Example;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
	static TextureAtlas atlas;
	
	public static TextureRegion ballRegion;
	public static TextureRegion groundRegion;
	public static TextureRegion backRegion;
	
	public static void Load()
	{
		String textureDir = "Images/";
		String textureFile = textureDir+"/examplepack" ;
		atlas = new TextureAtlas(Gdx.files.internal(textureFile), Gdx.files.internal(textureDir));
		
//		LoadFont();
		LoadTextures();
		
	}

	private static void LoadTextures() {
		// TODO Auto-generated method stub
		backRegion=atlas.findRegion("back1");
		groundRegion=atlas.findRegion("ground1");
		ballRegion=atlas.findRegion("ball1");
	}
	

	public static void Dispose()
	{
		atlas.dispose();
		
	}
}
