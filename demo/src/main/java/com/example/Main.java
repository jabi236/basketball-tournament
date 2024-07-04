package com.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Conference conf = new Conference();
        conf.read();
        conf.print();
        conf.printRoster();
        for(int i = 0; i < conf.getNumTeams(); i++){
            for(int j = i + 1; j < conf.getNumTeams(); j++){
                // get team by teamid. (0 + 1) * 100 
                conf.match(conf.searchTeam((i + 1) * 100),conf.searchTeam((j + 1) * 100));
                conf.match(conf.searchTeam((j + 1) * 100),conf.searchTeam((i + 1) * 100));
            }
        }
        conf.rankedSort();
        conf.print();
    }
}