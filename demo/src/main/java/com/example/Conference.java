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
