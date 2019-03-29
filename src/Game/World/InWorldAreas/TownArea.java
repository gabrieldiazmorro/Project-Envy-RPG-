package Game.World.InWorldAreas;

import Main.GameSetUp;
import Main.Handler;
import Resources.Images;
import java.awt.*;
import java.util.ArrayList;

import Game.Entities.EntityManager;
import Game.Entities.Dynamics.BigChungus;
import Game.World.Walls;

public class TownArea extends BaseArea {

	Rectangle exit;
	Rectangle playerRect;
	public static boolean isInTown = false;
	private BigChungus bigChungus;

	private int imageWidth = 5850, imageHeight = 4270;
	public final static int playerXSpawn = -1380, playerYSpawn = -3180;

	private Rectangle background = new Rectangle(3000, 3000);

	public static ArrayList<InWorldWalls> townWalls;

	public TownArea(Handler handler, EntityManager entityManager) {
		super(handler, entityManager);
		name="Town";
		handler.setXInWorldDisplacement(playerXSpawn);
		handler.setYInWorldDisplacement(playerYSpawn);

		playerRect = new Rectangle((int) handler.getWidth() / 2 - 5, (int) (handler.getHeight() / 2) + 300, 70, 70);

		this.entityManager = entityManager;

		this.entityManager.AddEntity(new BigChungus(handler, 1000,1000, Images.bigChungus));
		townWalls = new ArrayList<>();
		AddWalls();
	}

	public void tick() {
		super.tick();

		for (Walls w : townWalls) {
			w.tick();
		}
		if(!GameSetUp.LOADING) {
			entityManager.tick();
		}

	}

	@Override
	public void render(Graphics g) {
		super.render(g);


		Graphics2D g2 = (Graphics2D) g;

		g2.setColor(Color.black);
		g2.fill(background);

		g.drawImage(Images.ScaledTown, handler.getXInWorldDisplacement(), handler.getYInWorldDisplacement(), null);

		if (GameSetUp.DEBUGMODE) {
			for (Walls w : townWalls) {

				if (w.getType().equals("Wall"))
					g2.setColor(Color.black);
				else
					g2.setColor(Color.magenta);

				w.render(g2);
			}
		}


		entityManager.render(g);

	}

	private void AddWalls() {



		townWalls.add(new InWorldWalls(handler, 595, 625, 10, 3400, "Wall"));									// Left border of town
		townWalls.add(new InWorldWalls(handler, 595, 4000, 5000, 10, "Wall"));			 						// south side of town

		townWalls.add(new InWorldWalls(handler, 5325, 335, 10, 3650, "Wall"));									// Right border of town

		townWalls.add(new InWorldWalls(handler, 1519, 420, 3800, 20, "Wall"));									// north side border part 1
		townWalls.add(new InWorldWalls(handler, 1501, 400, 20, 350, "Wall"));									//north side border part 2
		townWalls.add(new InWorldWalls(handler, 595, 725, 907, 20, "Wall"));								//north side border part 3

		townWalls.add(new InWorldWalls(handler, 2157, 1578, 675, 735, "Wall"));		// house							
		townWalls.add(new InWorldWalls(handler, 3415, 1010, 870, 850, "Wall"));				//town hall					
		townWalls.add(new InWorldWalls(handler, 2800, 3076, 820, 700, "Wall"));			//Health Center				
		townWalls.add(new InWorldWalls(handler, 4218, 3076, 675, 735, "Wall"));				//house low right				

		townWalls.add(new InWorldWalls(handler, 4245, 2150, 615, 500, "Wall"));		//pokemart							
		//        townWalls.add(new InWorldWalls(handler, 2950, 340, 320, 100, "End Exit"));							// Exit at Start
		townWalls.add(new InWorldWalls(handler, 1230+1050, 3900, 280, 100, "Start Exit"));							// Exit at End



	}

	@Override
	public ArrayList<InWorldWalls> getWalls() {
		return townWalls;
	}
}




