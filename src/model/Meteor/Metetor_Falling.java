package model.Meteor;

public class Metetor_Falling implements Meteor_AnimStrategy{
    Meteor context;

    public Metetor_Falling(Meteor context){
        this.context = context;
    }




    @Override
    public void animate() {

        context.location.y += context.UNIT_FALLING;



    }
}
