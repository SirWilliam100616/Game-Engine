package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseEventListener extends MouseAdapter {

    @Override
    public void mousePressed(MouseEvent e){
        //System.out.println("Moused Pressed at " + e.getX() + "  " + e.getY());
        InputEvent inputEvent = new InputEvent();
        inputEvent.event = e;
        inputEvent.type = InputEvent.MOUSE_PRESSED;
        main.playersInputEventQueue.queue.add(inputEvent);
    }
    @Override
    public void mouseMoved(MouseEvent e){
        //System.out.println("Moused moved at " + e.getX() + "  " + e.getY());
        InputEvent inputEvent = new InputEvent();
        inputEvent.event = e;
        inputEvent.type = InputEvent.MOUSE_MOVED;
        main.playersInputEventQueue.queue.add(inputEvent);
    }



}
