package model.Meteor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MeteorEX2 implements Meteor_AnimStrategy{

    Meteor context;
    public MeteorEX2(Meteor context){this.context = context;}




    @Override
    public void animate() {
        context.color = Color.RED;
        try {
            context.bufferedImage = ImageIO.read(new File("src/Images/2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
