package com.example;

public class Team extends Player{
    private static final int MAX_PLAYERS = 10;
    private String name;
    private int id;
    private int numPlayers;
    private Player[] players = new Player[MAX_PLAYERS];
    private int wins;
    private int losses;

    public Team(){
        name = "";
        id = 0;
        numPlayers = 0;
        wins = 0;
        losses = 0;
    }
    public void setName(String newName){
        name = newName;
    }
    public void setId(int newId){
        id = newId;
    }
    public void setWins(int newWins){
        wins = newWins;
    }
    public void setLosses(int newLosses){
        losses = newLosses;
    }

    public String getName(){return name;}
    public int getId(){return id;}
    public int getWins(){return wins;}
    public int getLosses(){return losses;}
    public int getNumPlayers(){return numPlayers;}
    public Player getPlayer(int idx){
        if(idx < 0 || idx > MAX_PLAYERS){
            Player emptyPlayer = new Player();
            return emptyPlayer;
        }
        return players[idx];
    }
    public int getRecord(){
        return wins - losses;
    }
    public void print(){
        System.out.println("========== " + name.toUpperCase() + " LINEUP ==========");
        for(int i = 0; i < numPlayers; i++){
            players[i].print();
        }
    }


}
