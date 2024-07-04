package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Conference extends Team{
    private static final int MAX_TEAMS = 16;
    private String name;
    private int numTeams;
    private Team[] teams;
    private Team[] ranks;

    public Conference(){
        numTeams = 0;
        name = "";
        teams = new Team[MAX_TEAMS];
    }
    public void setName(String newName){
        name = newName;
    }
    public String getName(){return name;}
    public int getNumTeams(){return numTeams;}
    public Team getTeam(int idx){
        if(idx < 0 || idx > MAX_TEAMS){
            Team emptyTeam = new Team();
            return emptyTeam;
        }
        return teams[idx];
    }
    public Team searchTeam(int searchId){
        if(searchId < 0){
            Team emptyTeam = new Team();
            return emptyTeam;
        }
        for(int i = 0; i < numTeams; i++){
            if(teams[i].getId() == searchId){
                return teams[i];
            }
        }
        Team emptyTeam = new Team();
        return emptyTeam;
    }

    public void read()throws IOException {
        String[] conferences = {"sec.txt"};
        String filename;
        for(int i = 0; i < conferences.length; i++){
            filename = conferences[i];
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream is = classloader.getResourceAsStream(filename);
            try{
                BufferedReader myReader = new BufferedReader(new InputStreamReader(is));
                name = myReader.readLine();
                numTeams = Integer.parseInt(myReader.readLine());
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
                    teams[j] = t;
                    for(int k = 0; k < 10; k++){
                        playerId = teamId + k + 1;
                        playerName = myReader.readLine();
                        playerPPG = Double.parseDouble(myReader.readLine());
                        Player p = new Player();
                        p.setId(playerId);
                        p.setName(playerName);
                        p.setPPG(playerPPG);
                        teams[j].players[k] = p;
                        teams[j].setNumPlayers(k+1);
                    }
                }
            } finally {
                try { is.close(); } catch (Throwable ignore) {}
            }
        }
    }
    public Team match(Team t1, Team t2){
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
    public void rankedSort(){
        for(int i = 0; i < numTeams; i++){
            for(int j = 1; j < numTeams - i; j++){
                if(teams[j-1].getRecord() < teams[j].getRecord()){
                    Team temp = teams[j-1];
                    teams[j-1] = teams[j];
                    teams[j] = temp; 
                }
            }
        }
    }

    public void printRoster(){
        System.out.println("============= " + name.toUpperCase() + " CONFERENCE =============");
        for(int i = 0; i < numTeams; i++){
            teams[i].printRoster();
        }
    }
    public void print(){
        System.out.println("============= " + name.toUpperCase() + " CONFERENCE =============");
        for(int i = 0; i < numTeams; i++){
            teams[i].print();
        }
    }


}
