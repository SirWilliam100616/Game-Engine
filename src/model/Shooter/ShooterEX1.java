package model.Shooter;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class ShooterEX1 implements ShooterAnimStrategy {

    Shooter context;
    public ShooterEX1(Shooter context){
        this.context = context;
    }




    @Override
    public void animate() {
        try {
            context.Shooter = ImageIO.read(new File("src/Images/1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
