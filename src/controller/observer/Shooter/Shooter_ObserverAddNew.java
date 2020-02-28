package controller.observer.Shooter;

import controller.InputEvent;
import controller.main;
import controller.observer.Observer;

public class Shooter_ObserverAddNew implements Observer {



    @Override
    public void eventReceived() {
        InputEvent inputEvent = new InputEvent();
        inputEvent.event = new Shooter_CreateEvent("Shooter",0,0);
        inputEvent.type = InputEvent.SHOOTER_CREATE;
        main.playersInputEventQueue.queue.add(inputEvent);

    }
}
