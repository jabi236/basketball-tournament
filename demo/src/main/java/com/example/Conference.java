package com.example;

public class Conference extends Team{
    protected static final int MAX_TEAMS = 8;
    private String name;
    private int numTeams;
    private int numGames;
    protected Team[] teams;
    private int confRank;
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    // Function: constructor
    // Date: 7/3/24
    // Description: initializes the objects (data members) used by the class
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public Conference(){
        numTeams = 0;
        confRank = 0;
        numGames = 0;
        name = "";
        teams = new Team[MAX_TEAMS];
    }
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    // Functions: sets
    // Date: 7/3/24
    // Description: set attributes with new values
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public void setName(String newName){
        name = newName;
    }
    public void setConfRank(int newRank){
        confRank = newRank;
    }
    public void setNumTeams(int newNum){
        numTeams = newNum;
    }
    public void setNumGames(int newNum){
        numGames = newNum;
    }
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    // Functions: gets
    // Date: 7/3/24
    // Description: return attributes value
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public String getName(){return name;}
    public int getNumTeams(){return numTeams;}
    public int getNumGames(){return numGames;}
    public int getConfRank(){return confRank;}
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    // Function: searchTeam by index
    // Date: 7/3/24
    // Description: searches for team by index of partial array of team objects, if index is out of range it returns an empty team
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public Team searchTeam_byIndex(int idx){
        if(idx < 0 || idx > MAX_TEAMS){
            Team emptyTeam = new Team();
            return emptyTeam;
        }
        return teams[idx];
    }
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    // Function: searchTeam by id
    // Date: 7/3/24
    // Description: searches for team by id of team objects, if it is not found or is not in range, return an empty team
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public Team searchTeam_byId(int searchId){
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
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    // Function: sortTeams
    // Date: 7/3/24
    // Description: use bubble sort to sort team objects from highest record to lowest record
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public void sortTeams(){
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
            // generate power number for each team based on the rank of their conference, their season record, their rank within the conference, and the team's total PPG
            teams[i].setTPower((1 + (0.05 * (10 - confRank)) * ((teams[i].getRecord() * 2) + (numTeams - teams[i].getRank_in_conf()) + teams[i].getTotPPG())));
        }
    }
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    // Function: print roster
    // Date: 7/3/24
    // Description: print conference name, team name, then print players in team by evoking player's object print method
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public void printRoster(){
        System.out.println("============= " + name.toUpperCase() + " CONFERENCE =============");
        for(int i = 0; i < numTeams; i++){
            teams[i].printRoster();
        }
    }
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    // Function: print
    // Date: 7/3/24
    // Description: print conference name, then print teams by invoking team objects print method
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public void print(){
        System.out.println("============= " + name.toUpperCase() + " CONFERENCE =============");
        for(int i = 0; i < numTeams; i++){
            teams[i].print();
        }
    }
}
