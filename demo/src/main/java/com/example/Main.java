package com.example;

public class Main {
    public static void main(String[] args) {
        Conference conf = new Conference();

        conf.read();
        conf.print();
        conf.printRoster();
        
    }
}