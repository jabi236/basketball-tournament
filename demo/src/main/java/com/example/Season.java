package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
//import java.util.concurrent.TimeUnit;

public class Season extends Conference {
    static final int MAX_CONFERENES = 12;
    static final int MAX_TEAMS_MATCH = 2;
    static final int MAX_GAMES = 16;
    static final int TOP_25 = 25;
    static final int MAX_RANKED_TEAMS = 128;
    private int numConfs;
    private int totGames;
    private int totTeams;
    private Conference[] confs;
    protected Team[] ranked;
    private Team[][] schedule;
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    // Function: constructor
    // Date: 7/3/24
    // Description: initializes the objects (data members) used by the class
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public Season(){
        confs = new Conference[MAX_CONFERENES];
        ranked = new Team[MAX_RANKED_TEAMS];
        schedule = new Team[MAX_GAMES*MAX_CONFERENES*MAX_TEAMS][MAX_TEAMS_MATCH];
        numConfs = 0;
        totGames = 0;
        totTeams = 0;
    }
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    // Functions: sets
    // Date: 7/3/24
    // Description: set attributes with new values
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public void setNumConfs(int newNum){
        numConfs = newNum;
    }
    public void setTotGames(int newNum){
        totGames = newNum;
    }
    public void setTotTeams(int newNum){
        totTeams = newNum;
    }
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    // Functions: gets
    // Date: 7/3/24
    // Description: return attributes value
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public int getNumConfs(){return numConfs;}
    public int getTotGames(){return totGames;}
    public int getTotTeams(){return totTeams;}
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    // Function: searchConference by index
    // Date: 7/3/24
    // Description: searches for conference by index of partial array of conference objects, if index is out of range it returns an empty conference
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public Conference searchConference_byIndex(int idx){
        if(idx < 0 || idx > MAX_CONFERENES){
            Conference emptyConference = new Conference();
            return emptyConference;
        }
        return confs[idx];
    }
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    // Function: searchConference by id
    // Date: 7/3/24
    // Description: searches for conference by id of confeence objects, if it is not found or is not in range, return an empty conference
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public Conference searchConference_byId(int searchId){
        if(searchId < 0){
            Conference emptyConference = new Conference();
            return emptyConference;
        }
        for(int i = 0; i < numConfs; i++){
            if(confs[i].getId() == searchId){
                return confs[i];
            }
        }
        Conference emptyConference = new Conference();
        return emptyConference;
    }
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    // Function: sort Schedule
    // Date: 7/4/24
    // Description: sort schedule so no team plays twice in the same week.
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    // TODO: sort schedule
    public void sortSchedule(){
        for(int i = 0; i < numConfs; i++){
            for(int j = 0; j< confs[i].getNumGames(); j++){
                for(int k = 0; k < confs[i].getNumTeams(); k++){

                }
            }
        }
    }
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    // Function: create Schedule
    // Date: 7/4/24
    // Description: generate schedule for conference by having each team play all the other teams in the conference twice
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    // TODO: Change to make menu sorted by week. No same team twice in the same week
    public void createSchedule(){
        for(int i=0; i < numConfs; i++){
            int confGames = 0;
            for(int j=0; j < confs[i].getNumTeams(); j++){
                for(int k=j+1; k < confs[i].getNumTeams(); k++){
                    // set 2d array of two team ids. Home, away
                    schedule[totGames][0] = confs[i].teams[j];
                    schedule[totGames][1] = confs[i].teams[k];
                    totGames++;
                    confGames++;
                }  
            }
            // play teams again, but swithc home and away
            for(int j=0; j < confs[i].getNumTeams(); j++){
                for(int k=j+1; k < confs[i].getNumTeams(); k++){
                    // set 2d array of two team ids. Home, away
                    schedule[totGames][0] = confs[i].teams[k];
                    schedule[totGames][1] = confs[i].teams[j];
                    totGames++;
                    confGames++;
                }  
            }
            confs[i].setNumGames(confGames);
        }
    }
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    // Function: match
    // Date: 7/4/24
    // Description: simulate game played between two teams by generating a score and returning the team with the higher score
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public Team match(Team t1, Team t2) throws InterruptedException{
        // generate scores for teams
        int score1 = t1.getScore();
        int score2 = t2.getScore();
        // for printing ot if game goes into overtime
        String ot = "";
        Team winner = new Team();
        // if teams tied, generate another quarter(game score divided by 4)
        while(score1 == score2){
            score1 = score1 + (t1.getScore()/4);
            score2 = score2 + (t2.getScore()/4);
            ot = "/OT";
        }
        // increment wins and losses of each team based on outcome of the game
        if(score1 > score2){
            winner = t1;
            t1.addWins();
            t2.addLosses();
        }
        else{
            winner = t2;
            t2.addWins();
            t1.addLosses();
        }
        // print game result
        System.out.println("FINAL" + ot + ":");
        System.out.println(t1.getName() + " " + score1 + " | " + t2.getName() + " " + score2);
        System.out.println(winner.getName() + " Wins!");
        return winner;
    }
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    // Function: Season Standing
    // Date: 7/4/24
    // Description: sorts trams in list by their performance in the season/power attribute formula(tpower)
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public void seasonStanding(){
        // set total teams in season
        for(int i = 0; i < numConfs; i++){
            for(int j = 0; j < confs[i].getNumTeams(); j++){
                ranked[totTeams] = confs[i].teams[j];
                totTeams++;
            }
        }
        // sort teams based on tpower(formula to determine strength of team) from highest to lowest using bubble sort
        for(int i = 0; i < totTeams; i++){
            for(int j = 1; j < totTeams - i; j++){
                if(ranked[j-1].getTPower() < ranked[j].getTPower()){
                    Team temp = ranked[j-1];
                    ranked[j-1] = ranked[j];
                    ranked[j] = temp;
                }
            }
        }
        // set ncaa/season ranks
        for(int i = 0; i < totTeams; i++){
            ranked[i].setRank_in_ncaa(i+1);
            for(int j = 0; j < numConfs; j++){
                for(int k = 0; k < confs[j].getNumTeams(); k++){
                    if(confs[j].teams[k] == ranked[i]){
                        //copy ranks from ranked lists to conference list
                        confs[j].teams[k].setRank_in_ncaa(ranked[i].getRank_in_ncaa());
                    }
                }
            }
        }
    }
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    // Function: play Season
    // Date: 7/4/24
    // Description: main function of season class
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public void playSeason() throws IOException, InterruptedException{
        read();
        createSchedule();
        //printSchedule();
        for(int i = 0; i < numConfs; i++){
            // print conferences in season
            for(int j = 0; j < numConfs; j++){
                confs[j].print();
                //confs[i].printRoster();
            }
            for(int j = 0; j < confs[i].getNumGames(); j++){
                // generate points for two teams in a game based on the schedule
                match(schedule[j+(i*confs[i].getNumGames())][0], schedule[j+(i*confs[i].getNumGames())][1]);
            }
            confs[i].sortTeams();
        }
        // sort teams by tpower for ranking in season
        seasonStanding();
        print();
        printTop25();
    }
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    // Function: read
    // Date: 7/4/24
    // Description: read in data for teams/players by conference
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public void read() throws NumberFormatException, IOException{
        // create array of file names of conference, team, and player data
        // data is dived into conferences
        String[] conferences = {"sec.txt","big12.txt","big10.txt","acc.txt","bigeast.txt","pac12.txt","mountainwest.txt","atlantic10.txt","americanathletic.txt","missourivalley.txt", "westcoast.txt", "southern.txt"};
        // set number of conferences you wish to test, may move later
        //setNumConfs(MAX_CONFERENES);
        setNumConfs(8); // change for how many teams you want to test
        String filename;
        // loop through number of conference files to be read, setting attributes accordingly
        for(int i = 0; i < numConfs; i++){
            filename = conferences[i];
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream is = classloader.getResourceAsStream(filename);
            try{
                BufferedReader myReader = new BufferedReader(new InputStreamReader(is));
                // read in and set conference data
                String name;
                int confRank;
                int numTeams;
                name = myReader.readLine();
                confRank = Integer.parseInt(myReader.readLine());
                numTeams = Integer.parseInt(myReader.readLine());
                Conference c = new Conference();
                c.setName(name);
                c.setConfRank(confRank);
                c.setNumTeams(numTeams);
                confs[i] = c;
                String teamName;
                int teamId;
                int playerId;
                String playerName;
                double playerPPG;
                for(int j = 0; j < numTeams; j++){
                    //read in and set team data
                    teamName = myReader.readLine();
                    teamId = Integer.parseInt(myReader.readLine());
                    Team t = new Team();
                    t.setName(teamName);
                    t.setId(teamId);
                    confs[i].teams[j] = t;
                    for(int k = 0; k < 10; k++){
                        // read in and set player data
                        playerId = teamId + k + 1;
                        playerName = myReader.readLine();
                        playerPPG = Double.parseDouble(myReader.readLine());
                        Player p = new Player();
                        p.setId(playerId);
                        p.setName(playerName);
                        p.setPPG(playerPPG);
                        confs[i].teams[j].players[k] = p;
                        confs[i].teams[j].setNumPlayers(k+1);
                    }
                }
            } finally {
                try { is.close(); } catch (Throwable ignore) {}
            }
        }
    }
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    // Function: print
    // Date: 7/4/24
    // Description: invoke conference object print function for each conference in partial array
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public void print(){
        System.out.println("==================== SEASON ====================");
        for(int i = 0; i < numConfs; i++){
            confs[i].print();
        }
    }
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    // Function: print
    // Date: 7/4/24
    // Description: invoke conference object print roster function for each conference in partial array to also print all players
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public void printRoster(){
        System.out.println("==================== SEASON ====================");
        for(int i = 0; i < numConfs; i++){
            confs[i].printRoster();
        }
    }
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    // Function: print
    // Date: 7/4/24
    // Description: print team names in schedule array
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public void printSchedule(){
        System.out.println("=================== SCHEDULE ===================");
        for(int i = 0; i < numConfs; i++){
            System.out.println("========== " + confs[i].getName() + " ==========");
            for(int j = 0; j < totGames; j++){
                System.out.println(schedule[j][0].getName() + " | " + schedule[j][1].getName());
            }
        }
    }
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    // Function: print
    // Date: 7/4/24
    // Description: print team in top 25 of ncaa ranks
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public void printTop25(){
        System.out.println("==================== TOP 25 ===================");
        int nTeams = 0;
        for(int i = 0; i < numConfs; i++){
            nTeams = nTeams + confs[i].getNumTeams();
        }
        if(nTeams > 25){
            nTeams = 25;
        }
        for(int i = 0; i < nTeams; i++){
            ranked[i].print();
        }
    }
}
