package Game.Entities.Dynamics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Game.GameStates.FightState;
import Game.GameStates.State;
import Game.World.WorldManager;
import Game.World.InWorldAreas.TownArea;
import Main.Handler;
import Resources.Images;
import Resources.Animation;

public class BigChungus extends BaseDynamicEntity{

    Rectangle enemyOne;
    int width, height;
    private Animation chungus;
    
    
    public static boolean talkedTONPC = false;
    public static boolean recievedSkill = false; //once player kills the enemies and receives skill, this turns true
    

    public BigChungus(Handler handler, int xPosition, int yPosition, BufferedImage[] animFrames) {
        super(handler, yPosition, yPosition,animFrames);
        width = 90;
        height = 120;
       
        this.setXOffset(xPosition);
        this.setYOffset(yPosition);

  
        animFrames = Images.bigChungus;
        enemyOne = new Rectangle();
        chungus = new Animation(200, Images.bigChungus);
    }

    @Override
    public void tick() {

        if(!Player.isinArea)super.tick();

    }

    @Override
    public void render(Graphics g) {
        super.render(g);

        Graphics2D g2 = (Graphics2D) g;

        if (TownArea.isInTown) {
        	enemyOne = new Rectangle((int) (handler.getXInWorldDisplacement() +850),
        			(int) (handler.getYInWorldDisplacement() + 490), 400, 400);
			 g.drawImage(chungus.getCurrentFrame(),(int) (handler.getXInWorldDisplacement() +930),
	        			(int) (handler.getYInWorldDisplacement() + 470), 180, 240, null);
		}

            if (enemyOne.intersects(handler.getEntityManager().getPlayer().getCollision()) && handler.getKeyManager().ekey == false && Player.QuestCompleted == false) {
            	g.setFont(new Font("Times New Roman", Font.BOLD, 30));
            	g.setColor(Color.white);
                g.drawString("Press & hold E to talk.", handler.getXInWorldDisplacement() + 915, handler.getYInWorldDisplacement()+ 460);
            }
            if (enemyOne.intersects(handler.getEntityManager().getPlayer().getCollision()) && handler.getKeyManager().ekey == true && Player.QuestCompleted == false) {
            	g.setFont(new Font("Times New Roman", Font.BOLD, 30));
            	g.setColor(Color.white);
                g.drawString("Kill two enemies to recieve skill.", handler.getXInWorldDisplacement() + 870, handler.getYInWorldDisplacement()+ 460);
                BigChungus.talkedTONPC =true;
            }
            
            
            
            if (enemyOne.intersects(handler.getEntityManager().getPlayer().getCollision()) && handler.getKeyManager().ekey == false && Player.QuestCompleted == true) {
            	g.setFont(new Font("Times New Roman", Font.BOLD, 30));
            	g.setColor(Color.white);
                g.drawString("Press & hold E to recieve your award!", handler.getXInWorldDisplacement() + 915, handler.getYInWorldDisplacement()+ 460);
            }
            if (enemyOne.intersects(handler.getEntityManager().getPlayer().getCollision()) && handler.getKeyManager().ekey == true && Player.QuestCompleted == true) {
            	g.setFont(new Font("Times New Roman", Font.BOLD, 30));
            	g.setColor(Color.white);
                g.drawString("Congratulations you are now ready to enter the cave!", handler.getXInWorldDisplacement() + 870, handler.getYInWorldDisplacement()+ 460);
                handler.getEntityManager().getPlayer().skill = "Ice";     //if player has completed quest and talks to BigChungus the player receives a skill
                BigChungus.recievedSkill =true;     //boolean to confirm that player has completed the quest and talked to chungus
                
            }
            
            if (handler.getGame().DEBUGMODE) {
            	if(TownArea.isInTown) {
            	g.setColor(Color.pink);
            	g.drawRect((int) (handler.getXInWorldDisplacement() +850),
    			(int) (handler.getYInWorldDisplacement() + 490), 400, 400);
            	}
			}
//        }


    }

    @Override
    public Rectangle getCollision() {
        return enemyOne;
    }

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

    //GETTERS AND SETTERS FOR FIGHT STATS

    
    }

