package com.example;

import java.util.Scanner;

public class Menu {
    private final int MAX_OPTIONS = 10;
    private char[] validOptions;
    private String[] optDesc;
    private String errorMsg;
    private String title;
    private int numOpts;
    public Menu(){
        errorMsg = "";
        title = "";
        numOpts = 0;
        validOptions = new char[MAX_OPTIONS];
        optDesc = new String[MAX_OPTIONS];
    }
    public void setTitle(String newTitle){
        title = newTitle;
    }

    public void setErrorMsg(String newMsg){
        errorMsg = newMsg;
    }
    public void addOption(char opt, String desc){
        if(numOpts < MAX_OPTIONS){
            validOptions[numOpts] = opt;
            optDesc[numOpts] = desc;
            numOpts++;
        }
        else{
            System.out.println("No room for more options");
            return;
        }
    }
    public char doMenu(){
        String optionInput;
        char option;
        System.out.println(title);
        for(int i = 0; i < numOpts; i++){
            System.out.println(validOptions[i] + " - " + optDesc[i]);
        }
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter an option: ");
        optionInput = myObj.nextLine();
        optionInput.toUpperCase();
        option = (optionInput.charAt(0));
        while(!validOption(option)){
            System.out.println(errorMsg);
            option = doMenu();
        }
        return option;
    }
    public Boolean validOption(char opt){
        for(int i = 0; i < numOpts; i++){
            if(validOptions[i] == opt){
                return true;
            }
        }
        return false;
    }
}
