package model.Shooter;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class ShooterEX3 implements ShooterAnimStrategy {
    Shooter context;
    public ShooterEX3(Shooter context){
        this.context = context;
    }







    @Override
    public void animate() {
        try {
            context.Shooter = ImageIO.read(new File("src/Images/3.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
