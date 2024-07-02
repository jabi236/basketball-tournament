package com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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

    public void read(){

        String[] conferences = {"sec.txt"};
        String filepath;
        for(int i = 0; i < conferences.length; i++){
            //TODO: Change this to actually be able to read
            filepath = conferences[i];
            try{
                File myObj = new File(filepath);
                Scanner myReader = new Scanner(myObj);
                name = myReader.nextLine();
                numTeams = myReader.nextInt();
                String teamName;
                int teamId;
                int playerId;
                String playerName;
                int playerPPG;
                
                for(int j = 0; j < numTeams; j++){
                    teamName = myReader.nextLine();
                    teamId = myReader.nextInt();
                    teams[j].setName(teamName);
                    teams[j].setId(teamId);
                    for(int k = 0; k < 10; k++){
                        playerId = teamId + k + 1;
                        playerName = myReader.nextLine();
                        playerPPG = myReader.nextInt();
                        teams[j].players[k].setId(playerId);
                        teams[j].players[k].setName(playerName);
                        teams[j].players[k].setPPG(playerPPG);
                    }
                }
                myReader.close();
            } catch(FileNotFoundException e){
                System.out.println("Unable to open " + filepath);
                e.printStackTrace();
            }
        }
    }
    public void printRoster(){
        System.out.println("========== " + name.toUpperCase() + " CONFERENCE ==========");
        for(int i = 0; i < numTeams; i++){
            teams[i].printRoster();
        }
    }
    public void print(){
        System.out.println("========== " + name.toUpperCase() + " CONFERENCE ==========");
        for(int i = 0; i < numTeams; i++){
            teams[i].print();
        }
    }


}
