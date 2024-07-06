package com.example;

import java.io.IOException;

public class UserInt extends Menu{
    private Menu mainMenu;

    public UserInt(){

    }
    public void go() throws IOException{
        Season s = new Season();
        int numConfs = 1;
        s.setNumConfs(numConfs);
        s.run();
        Tournament t = new Tournament();
        t.getEntries(s);
        t.printEntries();
    }
}
