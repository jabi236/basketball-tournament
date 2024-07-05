package com.example;

public class Conference extends Team{
    protected static final int MAX_TEAMS = 8;
    private String name;
    private int numTeams;
    protected Team[] teams;
    private int confRank;
    public Conference(){
        numTeams = 0;
        confRank = 0;
        name = "";
        teams = new Team[MAX_TEAMS];
    }
    public void setName(String newName){
        name = newName;
    }
    public void setConfRank(int newRank){
        confRank = newRank;
    }
    public void setNumTeams(int newNum){
        numTeams = newNum;
    }
    public String getName(){return name;}
    public int getNumTeams(){return numTeams;}
    public int getConfRank(){return confRank;}
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
        for(int i = 0; i < numTeams; i++){
            teams[i].setRank_in_conf(i+1);
            teams[i].setTPower((1 + (0.1 * (10 - confRank))*(teams[i].getRecord() + (numTeams - teams[i].getRank_in_conf()) + teams[i].getTotPPG())));
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
