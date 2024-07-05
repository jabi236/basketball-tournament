package com.example;

public class Team extends Player{
    private static final int MAX_PLAYERS = 10;
    private String name;
    private int id;
    private int numPlayers;
    protected Player[] players;
    private int wins;
    private int losses;
    public Team(){
        name = "";
        id = 0;
        numPlayers = 0;
        wins = 0;
        losses = 0;
        players = new Player[MAX_PLAYERS];
    }
    public void setName(String newName){
        name = newName;
    }
    public void setId(int newId){
        id = newId;
    }
    public void setNumPlayers(int newNum){
        numPlayers = newNum;
    }
    public void addWins(){
        wins = wins + 1;
    }
    public void addLosses(){
        losses = losses +1;
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
    public int getScore(){
        int totScore = 0;
        for(int i = 0; i < numPlayers; i++){
            totScore = totScore + players[i].getPoints();
        }
        return totScore;
    }
    public void printRoster(){
        System.out.println(name.toUpperCase() + " LINEUP:");
        for(int i = 0; i < numPlayers; i++){
            players[i].print();
        }
    }
    public void print(){
        System.out.println(name.toUpperCase() + " " + wins + "-" + losses);
    }
}
