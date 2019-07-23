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
        String tempAnswer2;
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
                if (temp == null)
                    break;
                System.out.println("Option " + ++i + "  Name : " + temp.getName() + " class : " + temp.getHeroClass());
            }
            tempAnswer2 = "56565";
            while (Integer.parseInt(tempAnswer2) - 1 >= GameGen.heroList.size() && Integer.parseInt(tempAnswer2) - 1 <= 0){
                System.out.println("Please select the corresponding number to select a hero");
                tempAnswer2 = ReadWrite.inp.nextLine().trim();
            }
            GameGen.current = GameGen.heroList.get(Integer.parseInt(tempAnswer2) - 1);
            System.out.println(GameGen.current.getName());
        }else if (tempAnswer.matches("1")){
            System.out.println("Please give your hero a name ?");
            tempAnswer = ReadWrite.inp.nextLine().trim();
            System.out.println("Please select a class from 1 of these four by selecting a number :");
            System.out.println("1 . Wizard");
            System.out.println("2 . Shield Maiden");
            System.out.println("3 . Swordsman");
            System.out.println("4 . Adventurer");
            tempAnswer2 = ReadWrite.inp.nextLine().trim();
            if (tempAnswer2.matches("1"))
                tempAnswer2 = "wizard";
            else if (tempAnswer2.matches("2"))
                tempAnswer2 = "shieldMaiden";
            else if (tempAnswer2.matches("3"))
                tempAnswer2 = "swordsman";
            else if (tempAnswer2.matches("4"))
                tempAnswer2 = "adventurer";
            else
                tempAnswer2 = "adventurer";
                    
            System.out.println(tempAnswer2);
            hgen = new HeroGen(tempAnswer2, tempAnswer);
            int i = 0;
            for (Heroes temp : GameGen.heroList){
                System.out.println("Option " + ++i + "  Name : " + temp.getName() + " class : " + temp.getHeroClass());
            }
        }
       
    }

}