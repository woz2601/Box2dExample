

package com.Example;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;



public abstract class Screen {

	protected int BackScreenID;
	
	public boolean IsDone;
	
	public Vector3 TouchPoint;
	public TouchHandler TouchHandler;
	
	protected SpriteBatch spritebatch;
	
	public abstract void update (float dt);

	public abstract void render ();

	public abstract boolean isDone ();

	public abstract void dispose ();
	
	public abstract void OnPause();
	
	public abstract void OnResume();
	
	public void UnProjectCamera(int x,int y,OrthographicCamera cam,float startX,float startY,float width,float height)
	{
		TouchPoint.set(x,y,0);
		cam.unproject(TouchPoint,startX,startY,width,height);
	}
	
	public abstract void OnKeyUp(int keyCode);
	
	public abstract void OnKeyDown(int keyCode);
	
	public int GetScreenID()
	{
		return BackScreenID;
	}
	


}
