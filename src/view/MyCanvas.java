package view;

import controller.main;

import javax.swing.*;
import java.awt.*;

public class MyCanvas extends JPanel {
    public int height;
    public int width;



    public void render(){
        height = getSize().height;
        width = getSize().width;




        //off-screen double buffer image
        Image doubleBufferImage = createImage(width,height);
        if (doubleBufferImage == null) {
            System.out.println("CRITICAL ERROR: DOUBLEBUFFERIMAGE NULL");
            System.exit(1);
        }
        //off-screen rendering
        Graphics2D g2offScreen = (Graphics2D) doubleBufferImage.getGraphics();
        if (g2offScreen == null){
            System.out.println("CRITICAL ERROR: G2OFFSCREEN IS NULL");
            System.exit(1);
        }
        //initialize the image buffer
        g2offScreen.setBackground(Color.BLACK);
        g2offScreen.clearRect(0,0,width,height);




        //render all game data
       for (var fig: main.gameData.fixedObject){
           fig.render(g2offScreen);
       }
       for(var fig: main.gameData.friendObject){
           fig.render(g2offScreen);
       }
       for (var fig: main.gameData.enemyObject){
           fig.render(g2offScreen);
       }
        for (var fig: main.gameData.textObject){
            fig.render(g2offScreen);
        }

        // use rendering  to put buffer image on screen
        Graphics gOnScreen;
        gOnScreen = this.getGraphics();
        if (gOnScreen != null){
            //copy off screen to on screen
            gOnScreen.drawImage(doubleBufferImage,0,0,null);
        }
            Toolkit.getDefaultToolkit().sync();// sync the display on some systems
            if (gOnScreen != null){
                gOnScreen.dispose();
            }


    }


}
