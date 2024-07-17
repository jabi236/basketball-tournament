package com.example;

import java.util.Scanner;

public class Menu {
    private final int MAX_OPTIONS = 10;
    private char[] validOptions;
    private String[] optDesc;
    private String errorMsg;
    private String title;
    private int numOpts;
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    // Function: constructor
    // Date: 7/5/24
    // Description: initializes the objects (data members) used by the class
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public Menu(){
        errorMsg = "";
        title = "";
        numOpts = 0;
        validOptions = new char[MAX_OPTIONS];
        optDesc = new String[MAX_OPTIONS];
    }
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    // Functions: sets
    // Date: 7/5/24
    // Description: set attributes with new values
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public void setTitle(String newTitle){
        title = newTitle;
    }
    public void setErrorMsg(String newMsg){
        errorMsg = newMsg;
    }
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    // Functions: addOption
    // Date: 7/5/24
    // Description: adds option to list of valid options
    //-------------------------------------------------------------------------------------------------------------------------------------------------
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
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    // Functions: doMenu
    // Date: 7/5/24
    // Description: displays menu for simulation
    //-------------------------------------------------------------------------------------------------------------------------------------------------
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
        String optionUpper = optionInput.toUpperCase();
        option = (optionUpper.charAt(0));
        while(!validOption(option)){
            System.out.println(errorMsg);
            option = doMenu();
        }
        return option;
    }
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    // Functions: validOption
    // Date: 7/5/24
    // Description: checks if user input is in valid option array
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public Boolean validOption(char opt){
        for(int i = 0; i < numOpts; i++){
            if(validOptions[i] == opt){
                return true;
            }
        }
        return false;
    }
}
