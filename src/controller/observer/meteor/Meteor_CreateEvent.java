package controller.observer.meteor;

import java.util.EventObject;

public class Meteor_CreateEvent extends EventObject {

    private int x;
    private int y;


    public Meteor_CreateEvent(Object source,int x,int y) {
        super(source);
        this.x = x;
        this.y = y;

    }

    public int getX(){return x;}
    public int getY(){return y;}


}
