package controller;

import java.util.EventObject;

public class InputEvent {

    public static final int MOUSE_PRESSED = 0;
    public static final int MOUSE_MOVED = 1;
    public static final int KEY_PRESSED = 2;
    public static final int Meteor_CREATE = 3;
    public static final int SHOOTER_CREATE = 4;


    public EventObject event;
    public int type;
}
