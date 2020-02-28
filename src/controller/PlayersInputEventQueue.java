package controller;

import model.Cheat;
import model.Meteor.Meteor;
import model.missile.Missile;
//import model.MousePointer;
import model.Shooter.Shooter;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

public class PlayersInputEventQueue {
    public LinkedList<InputEvent> queue = new LinkedList<>();
    public static Cheat cheat = new Cheat();

    public void processPlayerEvents() {

        while (!queue.isEmpty()) {

            InputEvent inputEvent = queue.removeFirst();


            switch (inputEvent.type) {
                case InputEvent.KEY_PRESSED:
                    var shooter = main.gameData.fixedObject.get(main.INDEX_SHOOTER);
                    KeyEvent keyEvent = (KeyEvent) inputEvent.event;
                    switch (keyEvent.getKeyCode()) {
                        case KeyEvent.VK_LEFT:
                            shooter.location.x -= Shooter.UNIT_MOVE;
                            break;
                        case KeyEvent.VK_RIGHT:
                            shooter.location.x += Shooter.UNIT_MOVE;
                            break;
                        case KeyEvent.VK_SPACE:
                            Missile missile1 = new Missile((int)shooter.location.x,(int)shooter.location.y);
                            main.gameData.friendObject.add(missile1);
                            break;
                        case KeyEvent.VK_W:
                            cheat.updateCheat("W");
                            System.out.println("W");
                            break;

                        case KeyEvent.VK_I:
                            cheat.updateCheat("I");
                            break;
                        case KeyEvent.VK_L:
                            cheat.updateCheat("L");
                            break;
                        case KeyEvent.VK_A:
                            cheat.updateCheat("A");
                            break;
                        case KeyEvent.VK_M:
                            cheat.updateCheat("M");
                            break;
                        case KeyEvent.VK_N:
                            cheat.updateCheat("N");
                            break;
                        case KeyEvent.VK_S:
                            cheat.updateCheat("S");
                            break;
                        case KeyEvent.VK_H:
                            cheat.updateCheat("H");
                            break;
                        case KeyEvent.VK_O:
                            cheat.updateCheat("O");
                            break;
                        case KeyEvent.VK_T:
                            cheat.updateCheat("T");
                            break;
                        case KeyEvent.VK_E:
                            cheat.updateCheat("E");
                            break;
                        case KeyEvent.VK_R:
                            cheat.updateCheat("R");
                            break;
                    }
                    break;




            }


        }


    }


}
