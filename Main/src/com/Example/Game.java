package com.Example;


import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Game implements ApplicationListener {

	Screen _currentScreen;
	
	
	OrthographicCamera _camera;
	
	TouchHandler _touchHandler;
	
	boolean _isInitialized=false;
	
	float CAMWIDTH;
	float CAMHEIGHT;
	float CAMSTARTX;
	float CAMSTARTY;
	
	static final int EXIT=0;
	static final int GAMELOOP=1;
	
	@Override
	public void create() {	
		// TODO Auto-generated method stub
		if(!_isInitialized){
			_camera=CameraHelper.GetCamera(GlobalSettings.VIRTUAL_WIDTH,GlobalSettings.VIRTUAL_HEIGHT);
			SetupZoom();
			_camera.update();
			
			_currentScreen=new GameLoop(GAMELOOP,_camera);
		}
	}
	

	void SetupZoom()
	{
		int virtualAsp=GlobalSettings.VIRTUAL_WIDTH/GlobalSettings.VIRTUAL_HEIGHT;
		int realAsp=Gdx.graphics.getWidth()/Gdx.graphics.getHeight();
		if(realAsp==virtualAsp)
		{
			CAMWIDTH=Gdx.graphics.getWidth();
			CAMHEIGHT=Gdx.graphics.getHeight();
			CAMSTARTX=0;
			CAMSTARTY=0;
		}else if(realAsp<virtualAsp)
		{
			CAMWIDTH=Gdx.graphics.getWidth();
			CAMHEIGHT=GlobalSettings.VIRTUAL_HEIGHT*(Gdx.graphics.getWidth()/GlobalSettings.VIRTUAL_WIDTH);
			CAMSTARTX=0;
			CAMSTARTY=(Gdx.graphics.getHeight()-CAMHEIGHT)/2f;
			
		}else if(realAsp>virtualAsp){
			CAMWIDTH=GlobalSettings.VIRTUAL_WIDTH*(Gdx.graphics.getHeight()/GlobalSettings.VIRTUAL_HEIGHT);
			CAMHEIGHT=Gdx.graphics.getHeight();
			CAMSTARTY=0;
			CAMSTARTX=(Gdx.graphics.getWidth()-CAMWIDTH)/2f;
		}
	}


	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		float dt=Gdx.graphics.getDeltaTime();
		Update(dt);
	}
	

	public void Update(float dt)
	{
		UpdateTouch();
		UpdateScreen(dt);
	}
	
	public void UpdateTouch()
	{
		int x=Gdx.input.getX();
		int y=Gdx.input.getY();
		if(_isInitialized && !_currentScreen.IsDone)
		{
			_currentScreen.UnProjectCamera(x, y, _camera,CAMSTARTX,CAMSTARTY,CAMWIDTH,CAMHEIGHT);
			_currentScreen.TouchHandler.UpdateTouch((int)_currentScreen.TouchPoint.x,
					(int)_currentScreen.TouchPoint.y,Gdx.input.isTouched());
		}
		
	}
	
	public void UpdateScreen(float dt)
	{
		_currentScreen.update(dt);
		
		_currentScreen.render();
		
		if(_currentScreen.isDone())
		{
			int previousScreenID=_currentScreen.GetScreenID();
			
			_currentScreen.dispose();
			
			if(previousScreenID==EXIT)
			{
				ExitApplication();
				return;
			}
			//HANDLE OTHER SCREENS
		}
		
	}
	
	public void ExitApplication()
	{
		dispose();
		Gdx.app.exit();
	}
	
	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

}
