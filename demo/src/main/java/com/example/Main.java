package com.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Season s = new Season();
        int numConfs = 1;
        s.setNumConfs(numConfs);
        s.run();
        Tournament t = new Tournament();
        t.getEntries(s);
        t.printEntries();
    }
}