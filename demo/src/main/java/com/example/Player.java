package com.example;

import java.util.Random;

public class Player {
    private int id;
    private String name;
    private double ppg;
    private int numGames;
    private int totPts;
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    // Function: constructor
    // Date: 7/1/24
    // Description: initializes the objects (data members) used by the class
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public Player(){
        id = 0;
        ppg = 0;
        numGames = 0;
        totPts = 0;
        name = "";
    }
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    // Functions: Sets
    // Date: 7/1/24
    // Description: set attributes with new values
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public void setId(int newId){
        id = newId;
    }
    public void setPPG(double newPGG){
        ppg = newPGG;
    }
    public void setName(String newName){
        name = newName;
    }
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    // Functions: Gets
    // Date: 7/1/24
    // Description: return attributes value
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public int getId(){return id;}
    public double getPPG(){return ppg;}
    public int getTotPts(){return totPts;}
    public String getName(){return name;}
    public int getNumGames(){return numGames;}
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    // Function: getPoints
    // Date: 7/1/24
    // Description: generate number of points player got in given game based on ppg and random devation
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public int getPoints(){
        numGames++;
        int pts = 0;
        // get random number between min and max to be added as standard devation of player's ppg
        int min = -3;
        int max = 3;
        Random r = new Random();
        double std = r.nextDouble(max -min) + min;
        pts = (int)(ppg + std);
        // update total points to update ppg
        totPts = totPts + pts;
        // a player can't score negative points
        if(pts < 0){
            pts = 0;
        }
        // update ppg acording to generated points
        if(numGames == 1){
            ppg = pts;
        }
        else{
            ppg = (double)(totPts / numGames);
        }
        return pts;
    }
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    // Function: print
    // Date: 7/1/24
    // Description: print player object's id and name
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public void print(){
        System.out.println(id + ". " + name);
    }
}
