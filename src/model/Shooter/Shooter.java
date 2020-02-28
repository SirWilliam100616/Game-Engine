package model.Shooter;

import controller.main;
import controller.observer.Observer;
import controller.observer.Subject;
import model.GameFigure;
import model.MousePointer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Shooter extends GameFigure implements Subject {

    public BufferedImage Shooter;
    public static int BASE_SIZE = 20;
    public static final int UNIT_MOVE = 20; //10 pixels by 4 arrow keys
    public static final int STATE_INIT = 0;
    public static final int STATE_1 = 1;
    public static final int STATE_2 = 2;
    public static final int STATE_3 = 3;
    public static final int STATE_DONE = 4;
    public int state;
    ShooterAnimStrategy shooterAnimStrategy;
    ArrayList<Observer>listeners = new ArrayList<>();


public Shooter(int x, int y){
    super(x,y);
    state = STATE_INIT;
    shooterAnimStrategy = new Shooter_INIT(this);
    try {
        Shooter = ImageIO.read(new File("src/Images/Shooter.png"));

    }catch (IOException e){e.printStackTrace();}
}



    @Override
    public void render(Graphics2D g2) {
        g2.drawImage(Shooter,(int)location.x - 30, (int)location.y - 130,null);

    }

    @Override
    public void update() {
    UpdateState();
    shooterAnimStrategy.animate();

    }



    private void UpdateState(){
    if(state == STATE_INIT){
        if (hitCount > 0){
            state = STATE_1;
            shooterAnimStrategy = new ShooterEX1(this);

        }
    }else if (state == STATE_1){
        state = STATE_2;
        shooterAnimStrategy = new ShooterEX2(this);
    }else if (state == STATE_2){
        state = STATE_3;
        shooterAnimStrategy = new ShooterEX3(this);
    }else if (state == STATE_3){
        state = STATE_DONE;

    }else if (state == STATE_DONE){
        super.done = true;
        notifyEvent();
        main.running = false;
        main.defeat = true;
        main.gameOver();

    }


    }

    @Override
    public int getCollisionRadius() {
        return BASE_SIZE;
    }

    @Override
    public void attachListener(Observer o) {
    listeners.add(o);

    }

    @Override
    public void detachListener(Observer o) {
    listeners.remove(o);

    }

    @Override
    public void notifyEvent() {
    for (var o: listeners){
        o.eventReceived();
    }

    }
}
