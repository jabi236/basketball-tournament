package com.example;

import java.util.Random;

public class Player {
    private int id;
    private String name;
    private double ppg;
    private int numGames;
    private int totPts;
    public Player(){
        id = 0;
        ppg = 0;
        numGames = 0;
        totPts = 0;
        name = "";
    }
    public void setId(int newId){
        id = newId;
    }
    public void setPPG(double newPGG){
        ppg = newPGG;
    }
    public void setName(String newName){
        name = newName;
    }
    public int getId(){return id;}
    public double getPPG(){return ppg;}
    public int getTotPts(){return totPts;}
    public String getName(){return name;}
    public int getNumGames(){return numGames;}
    // generate number of points player got in given game based on ppg and random devation
    public int getPoints(){
        numGames++;
        int pts = 0;
        // get random number between min and max to be added as standard devation of ppg
        int min = -3;
        int max = 3;
        Random r = new Random();
        double std = r.nextDouble(max -min) + min;

        pts = (int)(ppg + std);
        totPts = totPts + pts;

        if(pts < 0){
            pts = 0;
        }
        if(numGames == 1){
            ppg = pts;
        }
        else{
            ppg = (double)(totPts / numGames);
        }
        return pts;
    }
    public void print(){
        System.out.println(id + ". " + name);
    }

}
