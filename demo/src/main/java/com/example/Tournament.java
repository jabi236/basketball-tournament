package com.example;

import java.util.concurrent.TimeUnit;

public class Tournament extends Season {
    private final int MAX_TOURNAMENT_TEAMS = 64;
    private final int MAX_ROUND_ONE_GAMES = 32;
    private Team[] entries;
    private Team[][] bracket;
    private int numEntries;
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    // Function: constructor
    // Date: 7/3/24
    // Description: initializes the objects (data members) used by the class
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public Tournament(){
        entries = new Team[MAX_TOURNAMENT_TEAMS];
        bracket = new Team[MAX_ROUND_ONE_GAMES][2];
        numEntries = 0;
    }
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    // Functions: sets
    // Date: 7/3/24
    // Description: set attributes with new values
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public void setNumEntries(int newNum){
        numEntries = newNum;
    }
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    // Functions: gets
    // Date: 7/3/24
    // Description: return attributes value
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public int getNumEntries(){return numEntries;}
    public void getEntries(Season s){
        //change 8 to MAX_TOURNAMENT_TEAMS
        for(int i = 0; i < 8; i++){
            entries[i] = s.ranked[i];
            entries[i].setRank_in_tourn(i+1);
            numEntries++;
        }
    }
    public void createBracket(int numGames){
        int currGame = 0;
        for(int j = 0; j < numEntries; j++){
            bracket[currGame][0] = entries[j];
            bracket[currGame][1] = entries[numEntries-j -1];
            currGame++;
            if(currGame == numGames){
                break;
            }
        }
    }
    public void updateBracket(int numGames, Team[] winners){
        Team[][] updatedBracket = new Team[numGames][2];
        int j = 0;
        for(int i = 0; i < numGames; i++){
            updatedBracket[i][0] = winners[j];
            updatedBracket[i][1] = winners[j+1];
            j = j + 2;
        }
        bracket = updatedBracket;
    }
    
    public void printBracket(int numGames)throws InterruptedException{
        System.out.println();
        System.out.println("========== OFFICAL BRACKET ==========");
        if(numGames == 1){
            System.out.println("CHAMPIONSHIP GAME");
        }
        else{
            System.out.println("ROUND OF " + (numGames*2));  
        }
        for(int i = 0; i < numGames; i++){
            System.out.println("GAME " + (i+1) +": " );
            System.out.println(bracket[i][0].getRank_in_tourn() + " " + bracket[i][0].getName());
            System.out.println("-------------------------------------");
            System.out.println(bracket[i][1].getRank_in_tourn() + " " + bracket[i][1].getName());
            System.out.println("-------------------------------------");
            System.out.println();
        }
        //TimeUnit.SECONDS.sleep(5);
    }

    public void round(int numGames, Season s) throws InterruptedException{
        Team[] winners = new Team[numGames];

        for(int i = 0; i < numGames; i++){
            winners[i] = s.match(bracket[i][0], bracket[i][1]);
        }

        numGames = numGames/2;
        if(numGames != 0){
            updateBracket(numGames, winners);
            printBracket(numGames);
            round(numGames, s);
        }
        else{
            System.out.println(winners[0].getName() + " WIN THE NATIONAL CHAMPIONSHIP!");
            System.out.println();
        }
    }
    
    public void playTournamnet(int numTeams, Season s) throws InterruptedException{
        getEntries(s);
        printEntries();
        int numGames = 0;
        if(numTeams % 2 == 0){
            numGames = numTeams/2;
        }
        else{
            numGames = numTeams/2 + 1;
        }
        createBracket(numGames);
        printBracket(numGames);
        round(numGames, s);
    }
    // Might delete
    public void printEntries(){
        System.out.println("=============================== QUALIFYING TEAMS ================================");
        for(int i = 0; i < numEntries; i++){
            entries[i].print();
        }
    }
}
