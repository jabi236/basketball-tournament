package com.example;

public class Main {
    public static void main(String[] args) {
        Player p = new Player();
        p.setId(1);
        p.setName("Reed Sheppard");
        p.setPPG(12.5);
        System.out.println("Season Record for " + p.getName());
        for(int i = 0; i < 10; i++){
            System.out.println("Game " + (i+1) + " Points: " + p.getPoints());
        }
        System.out.println("PPG (Points Per Game) over " + p.getNumGames() + " games: " + p.getPPG());
        
    }
}