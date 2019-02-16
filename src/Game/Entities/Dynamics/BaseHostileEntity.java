package Game.Entities.Dynamics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;
import Main.GameSetUp;
import Main.Handler;

public class BaseHostileEntity extends BaseDynamicEntity {

    private Random rand;
    private boolean chasingPlayer;
    private Rectangle detector;

    private int count;
    private int directionMov;
    double chaseSpeed = 1.4;
    boolean canMove = true;


    public BaseHostileEntity(Handler handler, int xPosition, int yPosition) {
        super(handler, xPosition, yPosition);

        chasingPlayer = false;
        count = 0;
        directionMov = 4;

        rand = new Random();
        detector = new Rectangle();
    }

    @Override
    public void tick() {
        super.tick();

        count++;
        if (count >= 100 + rand.nextInt(350)) {

            directionMov = rand.nextInt(5); // 0 (idle), 1(up), 2(down), 3(left), 4(right)

            count = 0;
        }

        PlayerDetector();

        if (!chasingPlayer) {
            Move();
        } else {
            Chase();
        }
    }

    private void PlayerDetector() {

        detector = this.getCollision();
        detector.setRect(detector.getX()-135, detector.getY()-140, 300, 300);
        chasingPlayer = handler.getEntityManager().getPlayer().getCollision().intersects(detector);
    }


    @Override
    public void render(Graphics g) {
        if(GameSetUp.DEBUGMODE){
            Graphics2D g2 = (Graphics2D) g;
            g2.draw(detector);
        }

    }



    private void Chase() {
        if (this.handler.getEntityManager().getPlayer().getXOffset() > this.getXOffset() && canMove) {
            facing = "Right";
            this.setXOffset(this.getXOffset() + chaseSpeed);
        }
        if (this.handler.getEntityManager().getPlayer().getXOffset() < this.getXOffset() && canMove) {
            facing = "Left";
            this.setXOffset(this.getXOffset() - chaseSpeed);
        }

        if (this.handler.getEntityManager().getPlayer().getYOffset() < this.getYOffset() && canMove) {
            facing = "Up";
            this.setYOffset(this.getYOffset() - chaseSpeed);
        }

        if (this.handler.getEntityManager().getPlayer().getYOffset() > this.getYOffset() && canMove) {
            facing = "Down";
            this.setYOffset(this.getYOffset() + chaseSpeed);
        }

    }

    private void Move() {


        switch (directionMov) {
            case 0:
                break;
            case 1://down
                facing = "Down";
                this.setYOffset(this.getYOffset() + speed);
                break;

            case 2://up
                facing = "Up";
                this.setYOffset(this.getYOffset() - speed);
                break;

            case 3://left
                facing = "Left";
                this.setXOffset(this.getXOffset() - speed);
                break;

            case 4://right
                facing = "Right";
                this.setXOffset(this.getXOffset() + speed);
                break;
        }
    }


}