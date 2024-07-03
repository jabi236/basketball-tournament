package com.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Conference conf = new Conference();

        conf.read();
        conf.print();
        conf.printRoster();
        for(int i = 0; i < 8; i++){
            for(int j = i + 1; j < 8; j++){
                conf.match(conf.getTeam(i),conf.getTeam(j));
                conf.match(conf.getTeam(j),conf.getTeam(i));
            }
        }
        conf.print();
    }
}