package com.example;

public class Team extends Player{
    private static final int MAX_PLAYERS = 10;
    private String name;
    private int id;
    private int numPlayers;
    private Player[] players = new Player[MAX_PLAYERS];

    public Team(){
        name = "";
        id = 0;
        numPlayers = 0;
    }
    
    public void setName(String newName){
        name = newName;
    }
    public void setId(int newId){
        id = newId;
    }

    public String getName(){return name;}
    public int getId(){return id;}
}
