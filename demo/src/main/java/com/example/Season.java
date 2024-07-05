package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Season extends Conference {
    static final int MAX_CONFERENES = 10;
    static final int MAX_TEAMS_MATCH = 2;
    static final int MAX_GAMES = 16;
    static final int TOP_25 = 25;
    private int numConfs;
    private int numGames;
    private Conference[] confs;
    protected Team[] ranked;
    private int[][] schedule;
    public Season(){
        confs = new Conference[MAX_CONFERENES];
        ranked = new Team[TOP_25];
        schedule = new int[MAX_GAMES*MAX_CONFERENES*MAX_TEAMS][MAX_TEAMS_MATCH];
        numConfs = 0;
        numGames = 0;
    }
    public void setNumConfs(int newNum){
        numConfs = newNum;
    }
    public void setNumGames(int newNum){
        numGames = newNum;
    }
    public int getNumConfs(){return numConfs;}
    public int getNumGames(){return numGames;}
    public Conference getConference(int idx){
        if(idx < 0 || idx > MAX_CONFERENES){
            Conference emptyConference = new Conference();
            return emptyConference;
        }
        return confs[idx];
    }
    public Conference searchConference(int searchId){
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
    public void generateScedule(){
        for(int i=0; i < numConfs; i++){
            for(int j=0; j < confs[i].getNumTeams(); j++){
                for(int k=j+1; k < confs[i].getNumTeams(); k++){
                    // set 2d array of two team ids. Home, away
                    schedule[numGames][0] = confs[i].teams[j].getId();
                    schedule[numGames][1] = confs[i].teams[k].getId();
                    numGames++;
                }  
            }
            // play teams again, but swithc home and away
            for(int j=0; j < confs[i].getNumTeams(); j++){
                for(int k=j+1; k < confs[i].getNumTeams(); k++){
                    // set 2d array of two team ids. Home, away
                    schedule[numGames][0] = confs[i].teams[k].getId();
                    schedule[numGames][1] = confs[i].teams[j].getId();
                    numGames++;
                }  
            }
        }
    }
    public Team game(Team t1, Team t2){
        int score1 = t1.getScore();
        int score2 = t2.getScore();
        String ot = "";
        Team winner = new Team();

        while(score1 == score2){
            score1 = score1 + (t1.getScore()/4);
            score2 = score2 + (t2.getScore()/4);
            ot = "/OT";
        }
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
        System.out.println("FINAL" + ot + ":");
        System.out.println(t1.getName() + " " + score1 + " | " + t2.getName() + " " + score2);
        System.out.println(winner.getName() + " Wins!");
        return winner;
    }
    public void setRanks(){
        int totTeams = 0;
        //get teams
        for(int i = 0; i < numConfs; i++){
            for(int j = 0; j < confs[i].getNumTeams(); j++){
                ranked[totTeams] = confs[i].teams[j];
                totTeams++;
            }
        }
        //sort
        for(int i = 0; i < totTeams; i++){
            for(int j = 1; j < totTeams - i; j++){
                if(ranked[j-1].getTPower() < ranked[j].getTPower()){
                    Team temp = ranked[j-1];
                    ranked[j-1] = ranked[j];
                    ranked[j] = temp;
                }
            }
        }
        // set ncaa ranks
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
    public void run() throws IOException{
        for(int i = 0; i < numConfs; i++){
            read();
            generateScedule();
            //printSchedule();
            for(int j = 0; j < numConfs; j++){
                confs[i].print();
                //confs[i].printRoster();
            }
            for(int j = 0; j < numGames; j++){
                game(confs[i].searchTeam(schedule[j][0]), confs[i].searchTeam(schedule[j][1]));
            }
            confs[i].rankedSort();
        }
        setRanks();
        print();
        printTop25();
    }
    public void read() throws NumberFormatException, IOException{
        String[] conferences = {"sec.txt"};
        String filename;
        for(int i = 0; i < conferences.length; i++){
            filename = conferences[i];
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream is = classloader.getResourceAsStream(filename);
            try{
                BufferedReader myReader = new BufferedReader(new InputStreamReader(is));
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
                    teamName = myReader.readLine();
                    teamId = Integer.parseInt(myReader.readLine());
                    Team t = new Team();
                    t.setName(teamName);
                    t.setId(teamId);
                    confs[i].teams[j] = t;
                    for(int k = 0; k < 10; k++){
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
    public void print(){
        System.out.println("==================== SEASON ====================");
        for(int i = 0; i < numConfs; i++){
            confs[i].print();
        }
    }
    public void printRoster(){
        System.out.println("==================== SEASON ====================");
        for(int i = 0; i < numConfs; i++){
            confs[i].printRoster();
        }
    }
    public void printSchedule(){
        System.out.println("=================== SCHEDULE ===================");
        for(int i = 0; i < numConfs; i++){
            System.out.println("========== " + confs[i].getName() + " ==========");
            for(int j = 0; j < numGames; j++){
                Team t1 = confs[i].searchTeam(schedule[j][0]);
                Team t2 = confs[i].searchTeam(schedule[j][1]);
                System.out.println(t1.getName() + " | " + t2.getName());
            }
        }
    }
    public void printTop25(){
        //TODO: change 8 to TOP_25 when enough teams
        System.out.println("==================== TOP 25 ===================");
        for(int i = 0; i < 8; i++){
            ranked[i].print();
        }
    }
}
