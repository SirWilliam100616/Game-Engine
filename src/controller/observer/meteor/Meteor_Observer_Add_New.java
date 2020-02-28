package controller.observer.meteor;

import controller.InputEvent;
import controller.main;
import controller.observer.Observer;

public class Meteor_Observer_Add_New implements Observer {
    @Override
    public void eventReceived() {
        InputEvent event = new InputEvent();
        event.event = new Meteor_CreateEvent("Meteor",100,100);
        event.type = InputEvent.Meteor_CREATE;

        main.playersInputEventQueue.queue.add(event);

    }
}
