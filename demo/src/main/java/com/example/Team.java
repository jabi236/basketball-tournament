package com.example;

public class Team extends Player{
    private static final int MAX_PLAYERS = 10;
    private String name;
    private int id;
    private int numPlayers;
    protected Player[] players;
    private int wins;
    private int losses;
    private double tpower; //team power
    private int rank_in_conf;
    private int rank_in_ncaa;
    public Team(){
        name = "";
        id = 0;
        numPlayers = 0;
        wins = 0;
        losses = 0;
        tpower = 0;
        rank_in_conf = 0;
        rank_in_ncaa = 0;
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
    public void setTPower(double newPower){
        tpower = newPower;
    }
    public void addWins(){
        wins = wins + 1;
    }
    public void addLosses(){
        losses = losses +1;
    }
    public void setRank_in_conf(int newRank){
        rank_in_conf = newRank;
    }
    public void setRank_in_ncaa(int newRank){
        rank_in_ncaa = newRank;
    }
    public String getName(){return name;}
    public int getId(){return id;}
    public int getWins(){return wins;}
    public int getLosses(){return losses;}
    public int getNumPlayers(){return numPlayers;}
    public double getTPower(){return tpower;}
    public int getRank_in_conf(){return rank_in_conf;}
    public int getRank_in_ncaa(){return rank_in_ncaa;}
    public double getTotPPG(){
        double totPPG = 0;
        for(int i = 0; i < numPlayers; i++){
            totPPG = totPPG + players[i].getPPG();
        }
        return totPPG;
    }
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
        String rank = "";
        if(rank_in_ncaa != 0 && rank_in_ncaa <= 25){
            rank = "(" + rank_in_ncaa + ") ";
        }
        System.out.println(rank + name.toUpperCase() + " " + wins + "-" + losses);
    }
}
