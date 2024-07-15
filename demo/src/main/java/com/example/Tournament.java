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
    public Boolean isPowerOf2(int x){ return (x != 0)&&((x & (x-1))==0);}
    public void getEntries(Season s){
        // total entries is 64, unless their are less teams entering the tournament
        int totEntries = MAX_RANKED_TEAMS;
        if(s.getTotTeams() < totEntries){
            totEntries = s.getTotTeams();
        }
        for(int i = 0; i < totEntries; i++){
            entries[i] = s.ranked[i];
            entries[i].setRank_in_tourn(i+1);
            numEntries++;
        }
    }
    public void createBracket(int numGames){
        int currGame = 0;
        // add entries to bracket, paired up according to remaining highest seed and lowest seed (increment over start of list and backwards at the end of list)
        for(int j = 0; j < numEntries; j++){
            bracket[currGame][0] = entries[j];
            bracket[currGame][1] = entries[numEntries - j -1];
            currGame++;
            if(currGame == numGames){
                break;
            }
        }
        // sort games to be in format similar to official ncca bracket. Highest and lowest seed differences play first, 
        // highest seed should be as far away from each other as possible
        sortBracket(numGames);
        
    }
    public int sortRoundOne(Team[][] tempBracket, int idx){
        int diff = getSeedDiff(tempBracket[idx][0], tempBracket[idx][1]);
        return diff;
    }
    public int sortRoundTwo(Team[][] tempBracket, int idx){
        int diff = getSeedDiff(tempBracket[idx][0], tempBracket[idx][1]) - getSeedDiff(tempBracket[idx+1][0], tempBracket[idx+1][1]);
        return diff;
    }
    public int sortRoundThree(Team[][] tempBracket, int idx){
        int diff = (getSeedDiff(tempBracket[idx][0], tempBracket[idx][1]) - getSeedDiff(tempBracket[idx+1][0], tempBracket[idx+1][1])) 
                    - (getSeedDiff(tempBracket[idx+2][0], tempBracket[idx+2][1]) - getSeedDiff(tempBracket[idx+3][0], tempBracket[idx+3][1]));
        return diff;
    }
    public int sortRoundFour(Team[][] tempBracket, int idx){
        int diff = ((getSeedDiff(tempBracket[idx][0], tempBracket[idx][1]) - getSeedDiff(tempBracket[idx+1][0], tempBracket[idx+1][1])) - (getSeedDiff(tempBracket[idx+2][0], tempBracket[idx+2][1]) - getSeedDiff(tempBracket[idx+3][0], tempBracket[idx+3][1])))
                    - ((getSeedDiff(tempBracket[idx+4][0], tempBracket[idx+4][1]) - getSeedDiff(tempBracket[idx+5][0], tempBracket[idx+5][1])) - (getSeedDiff(tempBracket[idx+6][0], tempBracket[idx+6][1]) - getSeedDiff(tempBracket[idx+7][0], tempBracket[idx+7][1])));
        return diff;
    }

    // TODO: Update to work on more than 8 teams: 16,32,64
    // Make it so it sorts for x times baed on number of teams, doubling the number of teams/games being used to get the seed diff each time
    // loop 1; seed diff game 1(2 teams) vs game 2(2 teams); loop 2 seed diff (game 1 - game 2)(4 teams) vs (game 3 - game 4)(4 teams);
    // 8 teams = 1 time; 16,2; 32,3; 64,4;
    public void sortBracket(int numGames){
        // set temp bracket to be copy of bracket
        Team[][] tempBracket = bracket;
        // set new bracket to be empty with size of origianl bracket
        Team[][] newBracket = new Team[MAX_ROUND_ONE_GAMES][2];;
        double sortRounds = ((Math.log(numEntries)/Math.log(2))-2);
        for(int i=0; i<sortRounds; i++){
            // set new bracket to be empty with size of origianl bracket
            newBracket = new Team[MAX_ROUND_ONE_GAMES][2];
            // loop over remaing teams in temp bracket and match teams/game with hishest seed difference to be followed by teams/game with lowest seed difference
            for(int j=0; j < numGames; j++){
                //set intial variables for min, min index. max, and max index 
                int max = -1000000;
                int maxidx = 0;
                int min = 1000000;
                int minidx = 0;
                for(int k = 0; k < numGames-j; k++){
                    // get difference in seed numbers. high seed team - low seed team = diff
                    int diff = 0;
                    switch(i){
                        case 0:
                            diff = getSeedDiff(tempBracket[k][0], tempBracket[k][1]);
                            break;
                        case 1:
                            diff = getSeedDiff(tempBracket[k][0], tempBracket[k][1]) - getSeedDiff(tempBracket[k+1][0], tempBracket[k+1][1]);
                            break;
                        case 2:
                            diff = (getSeedDiff(tempBracket[k][0], tempBracket[k][1]) - getSeedDiff(tempBracket[k+1][0], tempBracket[k+1][1])) 
                                    - (getSeedDiff(tempBracket[k+2][0], tempBracket[k+2][1]) - getSeedDiff(tempBracket[k+3][0], tempBracket[k+3][1]));
                            break;
                        case 3:
                            diff = ((getSeedDiff(tempBracket[k][0], tempBracket[k][1]) - getSeedDiff(tempBracket[k+1][0], tempBracket[k+1][1])) - (getSeedDiff(tempBracket[k+2][0], tempBracket[k+2][1]) - getSeedDiff(tempBracket[k+3][0], tempBracket[k+3][1])))
                                    - ((getSeedDiff(tempBracket[k+4][0], tempBracket[k+4][1]) - getSeedDiff(tempBracket[k+5][0], tempBracket[k+5][1])) - (getSeedDiff(tempBracket[k+6][0], tempBracket[k+6][1]) - getSeedDiff(tempBracket[k+7][0], tempBracket[k+7][1])));
                            break;
                    }
                    if(diff > max){
                        max = diff;
                        maxidx = k;
                    }
                    // update min seed difference and corresponding index
                    if(diff < min){
                        min = diff;
                        minidx = k;
                    }
                    if(i > 0){
                        k = k + ((int)Math.pow(2, i)-1);
                    }
                }
                Team[][] remainingBracket = new Team[MAX_ROUND_ONE_GAMES][2];
                switch(i){
                    case 0:
                        // add biggest seed difference to be followed by smallest seed difference in new bracket
                        newBracket[j][0] = tempBracket[maxidx][0];
                        newBracket[j][1] = tempBracket[maxidx][1];
                        newBracket[j+1][0] = tempBracket[minidx][0];
                        newBracket[j+1][1] = tempBracket[minidx][1];
                        // increment i to account for setting 2 indexes
                        j++;
                        // copy all teams in teams/games in temp bracket except those just added to the new bracket
                        for(int k=0, m=0;k<numGames;k++){
                            // skip teams added to new bracket
                            if(k!=maxidx && k != minidx){
                                remainingBracket[m][0]=tempBracket[k][0];
                                remainingBracket[m][1]=tempBracket[k][1];
                                m++;
                            }
                        }
                        break;
                    case 1:
                        // add biggest seed difference to be followed by smallest seed difference in new bracket
                        newBracket[j][0] = tempBracket[maxidx][0];
                        newBracket[j][1] = tempBracket[maxidx][1];
                        newBracket[j+1][0] = tempBracket[maxidx+1][0];
                        newBracket[j+1][1] = tempBracket[maxidx+1][1];
                        newBracket[j+2][0] = tempBracket[minidx][0];
                        newBracket[j+2][1] = tempBracket[minidx][1];
                        newBracket[j+3][0] = tempBracket[minidx+1][0];
                        newBracket[j+3][1] = tempBracket[minidx+1][1];
                        j = j+3;
                        // copy all teams in teams/games in temp bracket except those just added to the new bracket
                        for(int k=0, m=0;k<numGames;k++){
                            // skip teams added to new bracket
                            if(k!=maxidx && k != minidx && k!=maxidx+1 && k != minidx+1){
                                remainingBracket[m][0]=tempBracket[k][0];
                                remainingBracket[m][1]=tempBracket[k][1];
                                m++;
                            }
                        }
                        break;
                    case 2:
                        // add biggest seed difference to be followed by smallest seed difference in new bracket
                        newBracket[j][0] = tempBracket[maxidx][0];
                        newBracket[j][1] = tempBracket[maxidx][1];
                        newBracket[j+1][0] = tempBracket[maxidx+1][0];
                        newBracket[j+1][1] = tempBracket[maxidx+1][1];
                        newBracket[j+2][0] = tempBracket[maxidx+2][0];
                        newBracket[j+2][1] = tempBracket[maxidx+2][1];
                        newBracket[j+3][0] = tempBracket[maxidx+3][0];
                        newBracket[j+3][1] = tempBracket[maxidx+3][1];
                        newBracket[j+4][0] = tempBracket[minidx][0];
                        newBracket[j+4][1] = tempBracket[minidx][1];
                        newBracket[j+5][0] = tempBracket[minidx+1][0];
                        newBracket[j+5][1] = tempBracket[minidx+1][1];
                        newBracket[j+6][0] = tempBracket[minidx+2][0];
                        newBracket[j+6][1] = tempBracket[minidx+2][1];
                        newBracket[j+7][0] = tempBracket[minidx+3][0];
                        newBracket[j+7][1] = tempBracket[minidx+3][1];
                        j = j+7;
                        // copy all teams in teams/games in temp bracket except those just added to the new bracket
                        for(int k=0, m=0;k<numGames;k++){
                            // skip teams added to new bracket
                            if(k!=maxidx && k != minidx && k!=maxidx+1 && k != minidx+1&& k!=maxidx+2 && k != minidx+2 && k!=maxidx+3 && k != minidx+3){
                                remainingBracket[m][0]=tempBracket[k][0];
                                remainingBracket[m][1]=tempBracket[k][1];
                                m++;
                            }
                        }
                        break;
                    case 3:
                        // add biggest seed difference to be followed by smallest seed difference in new bracket
                        newBracket[j][0] = tempBracket[maxidx][0];
                        newBracket[j][1] = tempBracket[maxidx][1];
                        newBracket[j+1][0] = tempBracket[maxidx+1][0];
                        newBracket[j+1][1] = tempBracket[maxidx+1][1];
                        newBracket[j+2][0] = tempBracket[maxidx+2][0];
                        newBracket[j+2][1] = tempBracket[maxidx+2][1];
                        newBracket[j+3][0] = tempBracket[maxidx+3][0];
                        newBracket[j+3][1] = tempBracket[maxidx+3][1];
                        newBracket[j+4][0] = tempBracket[maxidx+4][0];
                        newBracket[j+4][1] = tempBracket[maxidx+4][1];
                        newBracket[j+5][0] = tempBracket[maxidx+5][0];
                        newBracket[j+5][1] = tempBracket[maxidx+5][1];
                        newBracket[j+6][0] = tempBracket[maxidx+6][0];
                        newBracket[j+6][1] = tempBracket[maxidx+6][1];
                        newBracket[j+7][0] = tempBracket[maxidx+7][0];
                        newBracket[j+7][1] = tempBracket[maxidx+7][1];
                        newBracket[j+8][0] = tempBracket[minidx][0];
                        newBracket[j+8][1] = tempBracket[minidx][1];
                        newBracket[j+9][0] = tempBracket[minidx+1][0];
                        newBracket[j+9][1] = tempBracket[minidx+1][1];
                        newBracket[j+10][0] = tempBracket[minidx+2][0];
                        newBracket[j+10][1] = tempBracket[minidx+2][1];
                        newBracket[j+11][0] = tempBracket[minidx+3][0];
                        newBracket[j+11][1] = tempBracket[minidx+3][1];
                        newBracket[j+12][0] = tempBracket[minidx+4][0];
                        newBracket[j+12][1] = tempBracket[minidx+4][1];
                        newBracket[j+13][0] = tempBracket[minidx+5][0];
                        newBracket[j+13][1] = tempBracket[minidx+5][1];
                        newBracket[j+14][0] = tempBracket[minidx+6][0];
                        newBracket[j+14][1] = tempBracket[minidx+6][1];
                        newBracket[j+15][0] = tempBracket[minidx+7][0];
                        newBracket[j+15][1] = tempBracket[minidx+7][1];
                        j = j+15;
                        // copy all teams in teams/games in temp bracket except those just added to the new bracket
                        for(int k=0, m=0;k<numGames;k++){
                            // skip teams added to new bracket
                            if(k!=maxidx && k != minidx && k!=maxidx+1 && k != minidx+1&& k!=maxidx+2 && k != minidx+2 && k!=maxidx+3 && k != minidx+3
                               && k!=maxidx+4 && k != minidx+4 && k!=maxidx+5 && k != minidx+5&& k!=maxidx+6 && k != minidx+6 && k!=maxidx+7 && k != minidx+7){
                                remainingBracket[m][0]=tempBracket[k][0];
                                remainingBracket[m][1]=tempBracket[k][1];
                                m++;
                            }
                        }
                        break;
                }
                // copy reamaing bracket to temp bracket
                tempBracket = remainingBracket;
            }
            tempBracket = newBracket;
        }
        bracket = newBracket;
    }

    public int getSeedDiff(Team t1, Team t2){
        int diff = 0;
        diff = t2.getRank_in_ncaa() - t1.getRank_in_ncaa();
        return diff;
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
    
    public void printBracket(int numGames, Season s)throws InterruptedException{
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
            int rankT1 = bracket[i][0].getRank_in_tourn();
            
            if(s.getTotTeams() > 16 && rankT1 % 2 ==0){
                rankT1 = rankT1 / (s.getTotTeams()/16);
            }
            else if(s.getTotTeams() > 16 && rankT1 % 2 !=0){
                rankT1 = (rankT1 / (s.getTotTeams()/16)) + 1;
            }
            
            System.out.println(rankT1 + " " + bracket[i][0].getName());

            System.out.println("-------------------------------------");
            int rankT2 = bracket[i][1].getRank_in_tourn();
            
            if(s.getTotTeams() > 16 && rankT2 % 2 ==0){
                rankT2 = rankT2 / (s.getTotTeams()/16);
            }
            else if(s.getTotTeams() > 16 && rankT2 % 2 !=0){
                rankT2 = (rankT2 / (s.getTotTeams()/16)) + 1;
            }
            
            System.out.println(rankT2 + " " + bracket[i][1].getName());
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
            printBracket(numGames, s);
            round(numGames, s);
        }
        else{
            System.out.println(winners[0].getName() + " WIN THE NATIONAL CHAMPIONSHIP!");
            System.out.println();
        }
    }
    
    public void playTournamnet(Season s) throws InterruptedException{
        if((!isPowerOf2(s.getTotTeams())) && (s.getTotTeams() < 64)){
            System.out.println();
            System.out.println("Error: Invalid number of teams. Valid numbers are 8, 16, 32, or >=64");
            System.out.println();
            return;
        }
        getEntries(s);
        printEntries();
        int numGames = 0;
        if(s.getTotTeams() % 2 == 0){
            numGames = s.getTotTeams()/2;
        }
        else{
            numGames = s.getTotTeams()/2 + 1;
        }
        createBracket(numGames);
        printBracket(numGames, s);
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
