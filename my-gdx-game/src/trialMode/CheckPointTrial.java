package trialMode;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class CheckPointTrial {
	private boolean isClosed = false;
	
	public  CheckPointTrial(TampinhaTrialWorld world,float x, float y, float angle,float width,String id) {
		BodyDef groundBodyDef =new BodyDef();  
		groundBodyDef.position.set(new Vector2(x, y));  
		groundBodyDef.angle =angle;
		
		Body groundBody = world.createBody(groundBodyDef); 
		groundBody.setUserData(id);
		PolygonShape groundBox = new PolygonShape(); 
		groundBox.setAsBox(width, 0f);
		FixtureDef fd = new FixtureDef();
		fd.shape = groundBox;
		fd.density = 1;
		fd.friction = 0.5f;
		fd.restitution = 0.3f;
		fd.isSensor = true;
		groundBody.createFixture(fd);

		groundBox.dispose();
	}
	
	public boolean isClosed() {
		return isClosed;
	}
	public void setClosed(boolean isClosed) {
		this.isClosed = isClosed;
	}
	
}

