package com.example;

import java.io.IOException;

public class UserInt extends Menu{
    private Menu mainMenu;

    public UserInt() throws IOException{
        mainMenu = new Menu();
        // set main menu
        mainMenu.setTitle("NCAA BASKETBALL SIMULATOR");
        mainMenu.setErrorMsg("Invalid option! Try again!");
        mainMenu.addOption('S', "Simulate Regular Season");
        mainMenu.addOption('T', "Simulate Tournament");
        mainMenu.addOption('X', "Exit");
    }
    
    public void go() throws IOException, InterruptedException{
        char opt = ' ';
        while(opt != 'X'){
            opt = mainMenu.doMenu();
            switch(opt){
                case 'S': Season s = new Season(); s.playSeason(); break;
                case 'T': Season s2 = new Season(); s2.playSeason(); 
                          Tournament t = new Tournament(); t.playTournamnet(8, s2); break;
                case 'X': System.out.println("ENDING SIMULATION");
            }
        }
    }
}
