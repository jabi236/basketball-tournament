package com.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Conference conf = new Conference();

        conf.read();
        conf.print();
        conf.printRoster();
        
    }
}