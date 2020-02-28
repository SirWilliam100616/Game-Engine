package controller;

import controller.observer.Shooter.Shooter_ObserverAddNew;
import controller.observer.meteor.Meteor_Observer_Add_New;
import model.*;
import model.Image;
import model.Meteor.Meteor;
import model.Shooter.Shooter;
//import model.ufo.UFO;
import view.Mywindow;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.spi.AudioFileReader;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Random;

public class main {
    public static Mywindow win;
    public static GameData gameData;
    public static PlayersInputEventQueue playersInputEventQueue;
    public static boolean flag = false;
    public static boolean running = false;
    public static boolean restart = false;
    public static boolean victory = false;
    public static boolean defeat = false;
    public static int FPS = 40;
    public static int INDEX_SHOOTER = 1;
    public static boolean clearedRound = false;
    public static int speed = 0;


    public static void main(String[] arg){
        win = new Mywindow();
        gameData = new GameData();
        playersInputEventQueue = new PlayersInputEventQueue();
        win.init();
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setVisible(true);
        startScreen();
        PlayerMenu();
        initGame();
        gameLoop();
        gameOver();






    }

    static void startScreen(){
        Font font = new Font("Courier New",Font.BOLD,50);
        gameData.fixedObject.add(new Text("PRESS THE START BUTTON",250,
                350,Color.CYAN,font));
        while (!flag){
            main.win.canvas.render();
            try {
                Thread.sleep(500);}
             catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }


    public static void victory(){
        gameData.clear();
        victory = true;
        Font font = new Font("Courier New",Font.BOLD,50);
        Font score = new Font("Courier New",Font.BOLD,35);
        Font round = new Font("Courier New",Font.BOLD,35);
        gameData.fixedObject.add(new Text("VICTORY IS YOURS ",250,350,Color.ORANGE,font));
        gameData.fixedObject.add(new Text("SCORE" + "  " + gameData.points,350,400,Color.ORANGE,score));
        gameData.fixedObject.add(new Text("ROUNDS" + " " + gameData.roundCount,350,450,Color.ORANGE,round));
        main.win.Restart.setVisible(true);


        while (victory){
            main.win.canvas.render();

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            win.Restart.setFocusable(false);
            win.Restart.setVisible(false);
            restart = true;
            initGame();
            speed = 0;
            gameLoop();
        }


        public static void gameOver(){


            gameData.clear();
            Font font = new Font("Courier New",Font.BOLD,50);
            Font score = new Font("Courier New",Font.BOLD,35);
            Font round = new Font("Courier New",Font.BOLD,35);
            gameData.fixedObject.add(new Text("DEATH HAS COME FOR YOU",300,300,Color.ORANGE,font));
            gameData.fixedObject.add(new Text("Score "+gameData.points,500,350,Color.ORANGE,score));
            gameData.fixedObject.add(new Text("Round "+gameData.roundCount,500,400,Color.ORANGE,score));
            main.win.Restart.setVisible(true);
            while (defeat){
                main.win.canvas.render();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            win.Restart.setVisible(false);
            win.Restart.setFocusable(false);
            restart = true;
            initGame();
            speed = 0;
            gameLoop();




        }












    static void PlayerMenu(){
        gameData.clear();




        Font font = new Font("Courier New",Font.BOLD,50);
        Font howToPlay = new Font("Courier New",Font.BOLD,22);
        gameData.fixedObject.add(new Text("WELCOME",350,300,Color.red,font));
        gameData.fixedObject.add(new Text("1) MOVE. USE THE ARROW KEYS TO MOVE AROUND THE SCREEN",200,350,Color.red,howToPlay));
        gameData.fixedObject.add(new Text("2) SHOOT. USE THE SPACE-BAR TO SHOOT",200,400,Color.red,howToPlay));

        while (!running){
            main.win.canvas.render();
            try {
                Thread.sleep(500);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

    }







    static void initGame(){
        gameData.clear();
        Meteor.UNIT_FALLING = 1;

        gameData.fixedObject.add(new MousePointer(0,0));
        int x = main.win.getWidth()/2;
        int y = main.win.getHeight()-100;
        addShooterwithListener(x,y);
    }

    public static void addMeteorwithListener(int x, int y,int faster){
        var meteor = new Meteor(x,y,faster);


        meteor.attachListener(new Meteor_Observer_Add_New());
        gameData.enemyObject.add(meteor);
    }
    public static void addShooterwithListener(int x, int y){
        var shooter = new Shooter(x,y);
        shooter.attachListener(new Shooter_ObserverAddNew());
        gameData.fixedObject.add(shooter);

    }








    static void gameLoop(){

        Font font = new Font("Courier New", Font.BOLD, 20);
        running = true;
        while (running){


            long startTime = System.currentTimeMillis();
            playersInputEventQueue.processPlayerEvents();
            processCollisions();
            gameData.textObject.add(new Text("Points "+ gameData.points,25,25,Color.CYAN,font));
            gameData.textObject.add(new Text("Round" + "  " + gameData.roundCount,1000,25,Color.CYAN,font));
            gameData.update();
            win.canvas.render();
            if(!clearedRound) {
                rounds(gameData.roundCount,speed);

                ++gameData.roundCount;
                gameData.textObject.clear();
                ++speed;
            }else if (gameData.enemyObject.isEmpty()){
                clearedRound = false;

            }
            if (gameData.fixedObject.isEmpty()){
                running = false;
                defeat = true;
            }
            long endTime = System.currentTimeMillis();

            long timeSpent = endTime - startTime;
            long sleepTime = (long) (1000.0 / FPS  - timeSpent);

            try {
               if(sleepTime > 0) {Thread.sleep(sleepTime);}
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }






    }

    static void rounds(int x,int speed){
        if (x == 0){

            addMeteorwithListener(getSpawnX(),getSpawnY(),speed);
            addMeteorwithListener(getSpawnX(),getSpawnY(),speed);
            addMeteorwithListener(getSpawnX(),getSpawnY(),speed);
            addMeteorwithListener(getSpawnX(),getSpawnY(),speed);
            addMeteorwithListener(getSpawnX(),getSpawnY(),speed);
            addMeteorwithListener(getSpawnX(),getSpawnY(),speed);
            addMeteorwithListener(getSpawnX(),getSpawnY(),speed);
            Meteor.once = true;
            clearedRound = true;





        }else if (x == 1){

            addMeteorwithListener(getSpawnX(),getSpawnY(),speed);
            addMeteorwithListener(getSpawnX(),getSpawnY(),speed);
            addMeteorwithListener(getSpawnX(),getSpawnY(),speed);
            addMeteorwithListener(getSpawnX(),getSpawnY(),speed);
            addMeteorwithListener(getSpawnX(),getSpawnY(),speed);
            addMeteorwithListener(getSpawnX(),getSpawnY(),speed);
            addMeteorwithListener(getSpawnX(),getSpawnY(),speed);
            addMeteorwithListener(getSpawnX(),getSpawnY(),speed);
            addMeteorwithListener(getSpawnX(),getSpawnY(),speed);
            addMeteorwithListener(getSpawnX(),getSpawnY(),speed);
            addMeteorwithListener(getSpawnX(),getSpawnY(),speed);
            addMeteorwithListener(getSpawnX(),getSpawnY(),speed);
            addMeteorwithListener(getSpawnX(),getSpawnY(),speed);
            addMeteorwithListener(getSpawnX(),getSpawnY(),speed);
            Meteor.once = true;
            clearedRound = true;
        }else if(x ==2){

            addMeteorwithListener(getSpawnX(),getSpawnY(),speed);
            addMeteorwithListener(getSpawnX(),getSpawnY(),speed);
            addMeteorwithListener(getSpawnX(),getSpawnY(),speed);
            addMeteorwithListener(getSpawnX(),getSpawnY(),speed);
            addMeteorwithListener(getSpawnX(),getSpawnY(),speed);
            addMeteorwithListener(getSpawnX(),getSpawnY(),speed);
            addMeteorwithListener(getSpawnX(),getSpawnY(),speed);
            addMeteorwithListener(getSpawnX(),getSpawnY(),speed);
            addMeteorwithListener(getSpawnX(),getSpawnY(),speed);
            addMeteorwithListener(getSpawnX(),getSpawnY(),speed);
            addMeteorwithListener(getSpawnX(),getSpawnY(),speed);
            addMeteorwithListener(getSpawnX(),getSpawnY(),speed);
            addMeteorwithListener(getSpawnX(),getSpawnY(),speed);
            addMeteorwithListener(getSpawnX(),getSpawnY(),speed);
            Meteor.once = true;
            clearedRound = true;

        }else if(x == 3){

            addMeteorwithListener(getSpawnX(),getSpawnY(),speed);
            addMeteorwithListener(getSpawnX(),getSpawnY(),speed);
            addMeteorwithListener(getSpawnX(),getSpawnY(),speed);
            addMeteorwithListener(getSpawnX(),getSpawnY(),speed);
            addMeteorwithListener(getSpawnX(),getSpawnY(),speed);
            addMeteorwithListener(getSpawnX(),getSpawnY(),speed);
            addMeteorwithListener(getSpawnX(),getSpawnY(),speed);
            addMeteorwithListener(getSpawnX(),getSpawnY(),speed);
            addMeteorwithListener(getSpawnX(),getSpawnY(),speed);
            addMeteorwithListener(getSpawnX(),getSpawnY(),speed);
            addMeteorwithListener(getSpawnX(),getSpawnY(),speed);
            addMeteorwithListener(getSpawnX(),getSpawnY(),speed);
            addMeteorwithListener(getSpawnX(),getSpawnY(),speed);
            addMeteorwithListener(getSpawnX(),getSpawnY(),speed);
            Meteor.once = true;
            clearedRound = true;

        }else if (x == 4){

            addMeteorwithListener(getSpawnX(),getSpawnY(),speed);
            addMeteorwithListener(getSpawnX(),getSpawnY(),speed);
            addMeteorwithListener(getSpawnX(),getSpawnY(),speed);
            addMeteorwithListener(getSpawnX(),getSpawnY(),speed);
            addMeteorwithListener(getSpawnX(),getSpawnY(),speed);
            addMeteorwithListener(getSpawnX(),getSpawnY(),speed);
            addMeteorwithListener(getSpawnX(),getSpawnY(),speed);
            addMeteorwithListener(getSpawnX(),getSpawnY(),speed);
            addMeteorwithListener(getSpawnX(),getSpawnY(),speed);
            addMeteorwithListener(getSpawnX(),getSpawnY(),speed);
            addMeteorwithListener(getSpawnX(),getSpawnY(),speed);
            addMeteorwithListener(getSpawnX(),getSpawnY(),speed);
            addMeteorwithListener(getSpawnX(),getSpawnY(),speed);
            addMeteorwithListener(getSpawnX(),getSpawnY(),speed);
            Meteor.once = true;
            clearedRound = true;
        }



        else {
            victory();
        }
    }



    static void processCollisions() {

            var shooter = (Shooter) main.gameData.fixedObject.get(main.INDEX_SHOOTER);
            for (var enemy : main.gameData.enemyObject) {
                if (shooter.collideWith(enemy)) {
                    ++shooter.hitCount;
                    ++enemy.hitCount;
                }
            }
            for (var friend : main.gameData.friendObject) {
                for (var enemy : main.gameData.enemyObject) {
                    if (friend.collideWith(enemy)) {
                        ++friend.hitCount;
                        ++enemy.hitCount;
                    }
                }
            }

        }


        static int getSpawnX(){
        int x =  1+ (int) (Math.random() * 1000);

        return x; }

        static int getSpawnY(){
        int y = 1 + (int) (Math.random() * 400);
        return y;

        }

        public static void Music(){

            InputStream music;
            try {

                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/model/Music/audio.wav"));
                BufferedInputStream bufferedInputStream = new BufferedInputStream(audioInputStream);
                audioInputStream = new AudioInputStream(bufferedInputStream,audioInputStream.getFormat(),audioInputStream.getFrameLength());
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.loop(Clip.LOOP_CONTINUOUSLY);


            } catch (Exception e) {
                e.printStackTrace();
            }


        }














}
