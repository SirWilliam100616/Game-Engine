package model.Meteor;

import controller.main;
import controller.observer.Observer;
import controller.observer.Subject;
import model.GameFigure;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Meteor extends GameFigure implements Subject {
   public static int UNIT_FALLING = 1;
   public static final int STATE_FALLING = 0;
    public static final int STATE_1 =1;
    public static final int STATE_2 = 2;
    public static final int STATE_3 = 3;
    public static final int STATE_DONE = 4;
    public int count = 11;
    public static boolean once = true;
    int state;
    Color color;
    Meteor_AnimStrategy meteor_animStrategy;
    ArrayList<Observer> list = new ArrayList<>();
   int size = 30;
   public BufferedImage bufferedImage;


   public Meteor(int x,int y,int z){
       super(x,y);
       if (once) {
           UNIT_FALLING += z;
           System.out.println("Unit fall = "+ UNIT_FALLING);
           once = false;

       }
       state = STATE_FALLING;
       meteor_animStrategy = new Metetor_Falling(this);

           try {
               bufferedImage = ImageIO.read(new File("src/Images/Meteor.png"));
           } catch (IOException e) {
               e.printStackTrace();
           }

       }


















    @Override
    public void render(Graphics2D g2) {
       if (count == 11) {

           g2.drawImage(bufferedImage, (int) location.x - 150, (int) location.y - 150, null);
       }
       else{
           g2.drawImage(bufferedImage, (int) location.x-1 , (int) location.y-1 , null);
       }

    }

    @Override
    public void update() {
       if (location.y > 700){
           state = STATE_DONE;
           super.done = true;


           main.gameData.textObject.clear();
       }else
       UpdateState();
       meteor_animStrategy.animate();

    }
    private void UpdateState(){
        if (state == STATE_FALLING){

            if (hitCount > 0){
                state = STATE_1;
                ++count;
                meteor_animStrategy = new MeteorEX1(this);
            }
        }
        else if (state == STATE_1){
            state = STATE_2;
            meteor_animStrategy = new MeteorEX2(this);
        }else if (state == STATE_2){
            state = STATE_3;
            meteor_animStrategy = new MeteorEX3(this);
        }
        else if (state == STATE_3) {
            state = STATE_DONE;
        }else if (state == STATE_DONE){
            super.done = true;



            notifyEvent();
            main.gameData.points += 100;
            main.gameData.textObject.clear();
        }


    }


    @Override
    public int getCollisionRadius() {
        return (int)(size);
    }

    @Override
    public void attachListener(Observer o) {
       list.add(o);

    }

    @Override
    public void detachListener(Observer o) {
       list.remove(o);

    }

    @Override
    public void notifyEvent() {
       for (var o: list){
           o.eventReceived();
       }

    }






}
