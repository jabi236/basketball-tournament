package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Season extends Conference {
    static final int MAX_CONFERENES = 10;
    
    private Conference[] confs;
    private int numConfs;
    public Season(){
        confs = new Conference[MAX_CONFERENES];
        numConfs = 0;
    }
    public void setNumConfs(int newNum){
        numConfs = newNum;
    }
    public int getNumConfs(){return numConfs;}
    public void schedule(){
        for(int i = 0; i < numConfs; i++){

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
    public void run() throws IOException{
        for(int i = 0; i < numConfs; i++){
            read();
            for(int j = 0; j < numConfs; j++){
                confs[i].print();
                confs[i].printRoster();
            }
            int numT = confs[i].getNumTeams();
            for(int j = 0; j < numT; j++){
                for(int k = j + 1; k < numT; k++){
                    // get team by teamid. (0 + 1) * 100 
                    game(confs[i].searchTeam((j + 1) * 100),confs[i].searchTeam((k + 1) * 100));
                }
            }
            confs[i].rankedSort();
            for(int j = 0; j < numT; j++){
                for(int k = j + 1; k < numT; k++){
                    // get team by teamid. (0 + 1) * 100 
                    game(confs[i].searchTeam((k + 1) * 100),confs[i].searchTeam((j + 1) * 100));
                }
            }
            confs[i].rankedSort();
        }
        print();
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
}
