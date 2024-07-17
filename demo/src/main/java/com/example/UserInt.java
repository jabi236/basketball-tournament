package com.example;

import java.io.IOException;

public class UserInt extends Menu{
    private Menu mainMenu;
    private Menu tournyMenu;
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    // Function: constructor
    // Date: 7/5/24
    // Description: initializes the objects (data members) used by the class
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public UserInt() throws IOException{
        mainMenu = new Menu();
        // set main menu
        mainMenu.setTitle("NCAA BASKETBALL SIMULATOR");
        mainMenu.setErrorMsg("Invalid option! Try again!");
        mainMenu.addOption('S', "Simulate Regular Season");
        mainMenu.addOption('T', "Simulate Tournament");
        mainMenu.addOption('X', "Exit");

        tournyMenu = new Menu();
        tournyMenu.setTitle("SIMULATE TOURNAMENT");
        tournyMenu.setErrorMsg("Invalid option! Try again!");
        tournyMenu.addOption('Y', "Print infomration about season and each game");
        tournyMenu.addOption('N', "Don't do that");
    }
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    // Functions: go
    // Date: 7/5/24
    // Description: This is the main function of the simulation
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public void go() throws IOException, InterruptedException{
        char opt = ' ';
        while(opt != 'X'){
            opt = mainMenu.doMenu();
            switch(opt){
                case 'S': Season s = new Season(); s.playSeason(); break;
                case 'T': doTournament(); break;
                case 'X': System.out.println("ENDING SIMULATION");
            }
        }
    }

    public void doTournament() throws IOException, InterruptedException{
        Season s = new Season(); 
        s.playSeason(); 
        Tournament t = new Tournament(); 
        t.playTournamnet(s);
    }
}