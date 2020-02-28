package model;

import controller.main;
import model.Meteor.Meteor;
import model.Shooter.Shooter;

public class Cheat {
    public boolean W = false;
    public boolean I = false;
    public boolean L = false;
    public boolean A = false;
    public boolean M = false;
    public boolean N = false;
    public boolean S = false;
    public boolean H = false;
    public boolean O = false;
    public boolean T = false;
    public boolean E = false;
    public boolean R = false;
    public boolean flag;
    public int count;
    public Meteor meteor;





    public Cheat(){

        this.flag = false;
        this.count = 0;


    }

    public void updateCheat(String x){
        switch (x){
            case "W": W = true;
            break;
            case "I": I = true;
            break;
            case "L": L = true;
            break;
            case "A": A = true;
            break;
            case "M": M = true;
            break;
            case "N": N = true;
            break;
            case "S": S = true;
            break;
            case "H": H = true;
            break;
            case "O": O = true;
            break;
            case "T": T = true;
            break;
            case "E": E = true;
            break;
            case "R": R = true;
            break;
        }

            if(W && I && L && A && M) {
                flag = true;
                meteor.UNIT_FALLING = 0;
                W = I = L = A = M = false;


            }else if (W && I && N){
                W = I = N = false;
                main.gameData.points += 50000000;
                main.victory();

            }else if(S && H && O && T && E && R){
                main.Music();
                S = H = O = T = E = R = false;
            }





    }














}
