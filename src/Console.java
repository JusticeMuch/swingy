package src;

import src.character.*;

import java.io.*;
import java.util.*;

import src.*;

public class Console {

    public static GameGen Console;
    public static HeroGen hgen;

    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
       }

    public static void main(String[] args) throws IOException {

        Console = new GameGen();
        String tempAnswer;
        ReadWrite.inp = new Scanner(System.in);
        String[] fileContents = new String[100];
        int counter = -1;
        ReadWrite.saveFile = new File("heroes.txt");
        try {
            ReadWrite.saveFile.createNewFile();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try {
            ReadWrite.reader = new BufferedReader(new FileReader(ReadWrite.saveFile));
        } catch (FileNotFoundException e) {
            System.out.println("No savefile and cannot be created");
        }
        while ((fileContents[++counter] = ReadWrite.reader.readLine()) != null);
        String temp0 [][] = new String [100][13];
        for (int i = 0; i < fileContents.length && fileContents[i] != null; i++){
            temp0[i] = fileContents[i].split(" ");
        }
       hgen = new HeroGen(temp0);
       System.out.println("Would you like to play a game (y/n), any answer besides no is yes , right?");
       tempAnswer = ReadWrite.inp.nextLine().toLowerCase().trim();
       if (tempAnswer.matches("no")|| tempAnswer.matches("n"))
            return ;
        System.out.println("Then let us begin");
        while (!tempAnswer.matches("1") && !tempAnswer.matches("2")){
            clearScreen();
            System.out.println("Please select either to create or load a character : 1 is to create , 2 is to load");
            tempAnswer = ReadWrite.inp.nextLine().trim();
        }
        if (tempAnswer.matches("2")){
            System.out.println("Here are your options :");
            int i = 0;
            for (Heroes temp : GameGen.heroList){
                System.out.println("Option " + ++i + "  Name : " + temp.getName() + " class : " + temp.getHeroClass());
            }
        }else{
            
        }
       
    }

}