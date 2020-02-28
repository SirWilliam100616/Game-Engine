package model.Shooter;

import controller.main;

public class Shooter_INIT implements ShooterAnimStrategy {

    Shooter context;
    public Shooter_INIT(Shooter context){
        this.context = context;
    }




    @Override
    public void animate() {


        if (context.location.x >= main.win.canvas.width - context.UNIT_MOVE){
            context.location.x -= context.UNIT_MOVE;

        }else if (context.location.x <= context.UNIT_MOVE){
            context.location.x += context.UNIT_MOVE;
        }


    }
}
