package view;

import controller.KeyEventListener;
import controller.MouseEventListener;
import controller.main;
import model.GameData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Mywindow extends JFrame {
    public MyCanvas canvas;
    public JButton startButton;
    public JButton Play;
    public JButton Restart;


    public void init(){
    setSize(1150,750);
    setLocation(300,50);
    setTitle("Game Engine");

    canvas = new MyCanvas();
    MouseEventListener mouseEventListener = new MouseEventListener();
    canvas.addMouseListener(mouseEventListener);
    canvas.addMouseMotionListener(mouseEventListener);

    KeyEventListener keyEventListener = new KeyEventListener();

    canvas.addKeyListener(keyEventListener);
    canvas.setFocusable(true);

    var cp = getContentPane();
    cp.add(BorderLayout.CENTER,canvas);
    startButton = new JButton("Start");
    startButton.setFocusable(false);
    Play = new JButton("Quit");
    Play.setFocusable(false);
    Restart = new JButton("Restart");
    Restart.setFocusable(false);
    JPanel buttonPanel = new JPanel();
    buttonPanel.add(startButton);
    buttonPanel.add(Play);
    buttonPanel.add(Restart);
    cp.add(BorderLayout.SOUTH,buttonPanel);
    Restart.setVisible(false);




    startButton.addActionListener(e -> {
        if (!main.flag) {
            startButton.setText("Next");
            main.flag = true;
        }else if (!main.running){
            main.running = true;
            buttonPanel.remove(startButton);
            buttonPanel.revalidate();
            buttonPanel.repaint();
        }






    });
    Restart.addActionListener(e ->{
        main.gameData.roundCount = 0;
        main.gameData.points = 0;
        main.defeat = false;
        main.victory = false;
    });



            Play.addActionListener(e -> {
                System.exit(0);
            });










    }
}
