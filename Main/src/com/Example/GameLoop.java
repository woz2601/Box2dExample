package com.Example;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class GameLoop extends Screen {
	
	
	TextureWrapper backTexture;
	BoxObjectManager boxManager;
	
	BoxRectObject ground;
	BoxCircleObject ball;
	Box2DDebugRenderer debugRenderer;  
	
	Matrix4 debugMatrix;
	
	public GameLoop(int screenId,OrthographicCamera cam){
		this.BackScreenID=screenId;
		spritebatch=new SpriteBatch();
		spritebatch.setProjectionMatrix(cam.combined);
		debugMatrix=new Matrix4(cam.combined);
		debugMatrix.scale(BoxObjectManager.BOX_TO_WORLD, BoxObjectManager.BOX_TO_WORLD, 1f);
		IsDone=false;
		TouchPoint=new Vector3(0,0,0);
		TouchHandler=new TouchHandler();
		debugRenderer=new Box2DDebugRenderer();
		Init();
	}
	
	private void Init() {
		// TODO Auto-generated method stub
		Assets.Load();
		
		backTexture=new TextureWrapper(Assets.backRegion,new Vector2(GlobalSettings.VIRTUAL_WIDTH/2,
				GlobalSettings.VIRTUAL_HEIGHT/2));
		backTexture.SetDimension(TextureDimensions.BACK_WIDTH, TextureDimensions.BACK_HEIGHT);
		
		boxManager=new BoxObjectManager();
		InitGround();
		InitBall();
	}
	
	

	private void InitBall() {
		// TODO Auto-generated method stub
		int gRadius=TextureDimensions.BALL_WIDTH/2;
		int gPositionX=160;
		int gPositionY=280;
		ball=new BoxCircleObject(boxManager.GetNewObjectIndex(), 1,
				gRadius, BodyType.DynamicBody,
				1, 1, gPositionX, gPositionY, 0, Assets.ballRegion);
		ball.SetTextureDimension(TextureDimensions.BALL_WIDTH, TextureDimensions.BALL_HEIGHT);
		boxManager.AddObject(ball);
	}

	private void InitGround() {
		// TODO Auto-generated method stub
		int gWidth=TextureDimensions.GROUND_WIDTH;
		int gHeight=TextureDimensions.GROUND_HEIGHT;
		int gPositionX=160;
		int gPositionY=40;
		ground=new BoxRectObject(boxManager.GetNewObjectIndex(), 1,
				gWidth, gHeight, BodyType.StaticBody,
				1, 1, gPositionX, gPositionY, 0, Assets.groundRegion);
		ground.SetTextureDimension(gWidth, gHeight);
		boxManager.AddObject(ground);
		
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		boxManager.Update(dt);
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		Gdx.graphics.getGL10().glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		spritebatch.begin();
		
		spritebatch.disableBlending();
		backTexture.Draw(spritebatch);
		
		spritebatch.enableBlending();
		boxManager.Draw(spritebatch);
		
		
		spritebatch.end();
		
		spritebatch.begin();
		debugRenderer.render(BoxObjectManager.GetWorld(), debugMatrix);
		spritebatch.end();
	}

	@Override
	public boolean isDone() {
		// TODO Auto-generated method stub
		return IsDone;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		Assets.Dispose();
		debugRenderer.dispose();
		boxManager.Dispose();
	}

	@Override
	public void OnPause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void OnResume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void OnKeyUp(int keyCode) {
		// TODO Auto-generated method stub

	}

	@Override
	public void OnKeyDown(int keyCode) {
		// TODO Auto-generated method stub

	}

}
