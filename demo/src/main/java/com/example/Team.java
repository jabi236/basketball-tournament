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
    private int rank_in_tourn;
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    // Function: constructor
    // Date: 7/2/24
    // Description: initializes the objects (data members) used by the class
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public Team(){
        name = "";
        id = 0;
        numPlayers = 0;
        wins = 0;
        losses = 0;
        tpower = 0;
        rank_in_conf = 0;
        rank_in_ncaa = 0;
        rank_in_tourn = 0;
        players = new Player[MAX_PLAYERS];
    }
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    // Functions: sets
    // Date: 7/2/24
    // Description: set attributes with new values
    //-------------------------------------------------------------------------------------------------------------------------------------------------
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
    public void setRank_in_conf(int newRank){
        rank_in_conf = newRank;
    }
    public void setRank_in_ncaa(int newRank){
        rank_in_ncaa = newRank;
    }
    public void setRank_in_tourn(int newRank){
        rank_in_tourn = newRank;
    }
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    // Functions: addWins and addLosses
    // Date: 7/2/24
    // Description: increment win attributte or losses attribute respectively
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public void addWins(){
        wins = wins + 1;
    }
    public void addLosses(){
        losses = losses +1;
    }
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    // Functions: gets
    // Date: 7/2/24
    // Description: return attributes value
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public String getName(){return name;}
    public int getId(){return id;}
    public int getWins(){return wins;}
    public int getLosses(){return losses;}
    public int getNumPlayers(){return numPlayers;}
    public double getTPower(){return tpower;}
    public int getRank_in_conf(){return rank_in_conf;}
    public int getRank_in_ncaa(){return rank_in_ncaa;}
    public int getRank_in_tourn(){return rank_in_tourn;}
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    // Functions: getsTotPPG
    // Date: 7/2/24
    // Description: add up all ppg's of individual player objects
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public double getTotPPG(){
        double totPPG = 0;
        for(int i = 0; i < numPlayers; i++){
            totPPG = totPPG + players[i].getPPG();
        }
        return totPPG;
    }
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    // Function: searchPlayer
    // Date: 7/2/24
    // Description: searches for player by index of partial array of player objects, if index is out of range it returns an empty player
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public Player searchPlayer_byIndex(int idx){
        if(idx < 0 || idx > MAX_PLAYERS){
            Player emptyPlayer = new Player();
            return emptyPlayer;
        }
        return players[idx];
    }
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    // Function: getRecord
    // Date: 7/2/24
    // Description: return season record by subtracting losses from wins
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public int getRecord(){
        return wins - losses;
    }
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    // Functions: getsTotPPG
    // Date: 7/2/24
    // Description: invokes get points for each player getting a new value of points for a given game. It then ads those points together to get the 
    // score for a given game. This is different from getppg, as this generates points and adds them together for a given game and updates ppg, 
    // while getPPG only adds the already existing ppgs
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public int getScore(){
        int totScore = 0;
        for(int i = 0; i < numPlayers; i++){
            totScore = totScore + players[i].getPoints();
        }
        return totScore;
    }
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    // Function: print roster
    // Date: 7/2/24
    // Description: print team name, then print players in team by evoking player's object print method
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public void printRoster(){
        System.out.println(name.toUpperCase() + " LINEUP:");
        for(int i = 0; i < numPlayers; i++){
            players[i].print();
        }
    }
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    // Function: print
    // Date: 7/2/24
    // Description: print team rank(if applicable), team name in uppercase, and wins and losses
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public void print(){
        String rank = "";
        if(rank_in_ncaa != 0 && rank_in_ncaa <= 25){
            rank = "(" + rank_in_ncaa + ") ";
        }
        System.out.println(rank + name.toUpperCase() + " " + wins + "-" + losses);
    }
}
