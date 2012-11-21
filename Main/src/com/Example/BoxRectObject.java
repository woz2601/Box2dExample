package com.Example;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class BoxRectObject extends BaseBoxObject{
	TextureWrapper texture;
	
	public BoxRectObject(int bodyIndex, int collisionGroup,float width,float height,
			BodyType bodyType,float density,float restitution,float px,float py,
			float angle,TextureRegion texRegion) {
		super(bodyIndex, collisionGroup);
		// TODO Auto-generated constructor stub
		Vector2 pos=new Vector2(px,py);
		MakeBody(width, height, 0, bodyType, density, restitution, pos, angle);
		texture=new TextureWrapper(texRegion, pos);
	}

	
	public void SetTextureDimension(int width,int height){
		texture.SetDimension(width, height);
	}
	@Override
	public void Draw(SpriteBatch sp) {
		// TODO Auto-generated method stub
		if(IsAlive){
			texture.Draw(sp);
		}
	}
	
	public void Update(float dt){
		if(IsAlive){
			super.Update(dt);
			texture.Position.set(bodyWorldPosition);
			texture.rotation=GetBodyRotationInDegrees();
		}
		
	}
	
	public float GetBodyRotationInDegrees(){
		return body.getAngle()*MathUtils.radiansToDegrees;
	}
}
