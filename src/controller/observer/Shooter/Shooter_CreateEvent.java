package controller.observer.Shooter;

import java.util.EventObject;

public class Shooter_CreateEvent extends EventObject {

    private int x;
    private int y;





    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public Shooter_CreateEvent(Object source, int x,int y) {
        super(source);
        this.x = x;
        this.y = y;

    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }




}
