package Game.Entities.Statics;

import java.awt.Graphics;
import java.awt.Rectangle;

import Game.Entities.Dynamics.BigChungus;
import Main.Handler;
import Resources.Images;

public class Boat extends BaseStaticEntity {
	
	Rectangle collision;
	int width, height;
	
	public Boat(Handler handler, int xPosition, int yPosition) {
		super(handler, xPosition, yPosition);
		width = 200;
		height = 250;
		
		this.setXOffset(xPosition);
		this.setYOffset(yPosition);

		
		collision = new Rectangle();
	}
	
	
	@Override
	public void render(Graphics g) {
		if(BigChungus.recievedSkill == false) {   //while the quest is not completed it will draw the image
		g.drawImage(Images.boat, (int)(handler.getXDisplacement() + xPosition),(int)( handler.getYDisplacement() + yPosition), width, height, null);
		collision = new Rectangle((int)(handler.getXDisplacement() + xPosition + 35), (int)(handler.getYDisplacement() + yPosition + 50), width/4, height/2);
		}
	}
	
	@Override
	public Rectangle getCollision() {
		return collision;
	}
	
	@Override
	public double getXOffset() {
		return xPosition;
	}
	
	
}
