package com.example;

import java.util.Random;

public class Player {
    private static int id;
    private static String first;
    private static String last;
    private static double ppg;
    private static int numGames;

    public Player(){
        id = 0;
        ppg = 0;
        numGames = 0;
        first = "";
        last = "";
    }
    public static void setId(int newId){
        id = newId;
    }
    public static void setPPG(float newPGG){
        ppg = newPGG;
    }
    public static void setFirst(String fname){
        first = fname;
    }
    public static void setLast(String lname){
        last = lname;
    }
    public static int getId(){return id;}
    public static double getPPG(){return ppg;}
    public static String setFirst(){return first;}
    public static String getlast(){return last;}
    // generate number of points player got in given game based on ppg and random devation
    public static int getPoints(){
        numGames++;
        int pts = 0;

        // get random number between min and max to be added as standard devation of ppg
        int min = -5;
        int max = 5;
        Random r = new Random();
        double std = r.nextDouble(max -min) + min;

        pts = (int)(ppg + std);

        if(pts < 0){
            pts = 0;
        }
        if(numGames == 1){
            ppg = pts;
        }
        else{
            ppg = (ppg + pts / numGames);
        }
        return pts;
    }

}
