package model.missile;

import controller.main;
import model.GameFigure;
import model.Image;
import model.Shooter.Shooter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Missile extends GameFigure {

    public static final int UNIT_MOVED = 5;
    public static final int INIT_MISSILE_SIZE = 20;
    public static final int STATE_SHOOTING = 0;
    public static final int STATE_DONE = 2;


    int size = INIT_MISSILE_SIZE;
    Point2D.Float target;// where mouse was pressed
    Color color;
    int state;
    public BufferedImage bufferedImage;





    public Missile(int tx, int ty){
        Shooter shooter = (Shooter) main.gameData.fixedObject.get(main.INDEX_SHOOTER);
        super.location.x = shooter.location.x + 32;
        super.location.y = shooter.location.y - 100;
        try {
            bufferedImage = ImageIO.read(new File("src/Images/Missile.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        target = new Point2D.Float(tx,ty);
        state = STATE_SHOOTING;

    }





    @Override
    public void render(Graphics2D g2) {
        g2.drawImage(bufferedImage,(int)location.x-2,(int)location.y-50,null);
    }

    @Override
    public void update() {
        updateState();
        if (state == STATE_SHOOTING){
            location.y-=UNIT_MOVED;
        }

    }
    private void updateState(){
        if (state == STATE_SHOOTING){
            if (hitCount > 0 || target.distance(location) <= 53.0){
                state = STATE_DONE;

            }
        } else if (state == STATE_DONE){
            super.done = true;
        }
    }

    @Override
    public int getCollisionRadius() {
        return (size/2);
    }
}
