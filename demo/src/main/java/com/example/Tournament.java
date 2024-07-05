package com.example;

public class Tournament extends Season {
    private final int MAX_TOURNAMENT_TEAMS = 64;
    private Team[] entries;
    private int numEntries;

    public Tournament(){
        entries = new Team[MAX_TOURNAMENT_TEAMS];
        numEntries = 0;
    }
    public void setNumEntries(int newNum){
        numEntries = newNum;
    }
    public int getNumEntries(){return numEntries;}
    public void getEntries(Season s){
        //change 8 to MAX_TOURNAMENT_TEAMS
        for(int i = 0; i < 8; i++){
            entries[i] = s.ranked[i];
            numEntries++;
        }
    }
    public void start(){

    }
    public void printBracket(){

    }
    // Might delete
    public void printEntries(){
        System.out.println("=============================== QUALIFYING TEAMS ================================");
        for(int i = 0; i < numEntries; i++){
            entries[i].print();
        }
    }
}
