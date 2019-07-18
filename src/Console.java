package src;

import src.character.*;

import java.io.*;
import java.util.*;

import src.*;

public class Console {

    public static GameGen Console;
    public static HeroGen hgen;

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
        String temp [][] = new String [100][13];
        for (int i = 0; i < fileContents.length && fileContents[i] != null; i++){
            temp[i] = fileContents[i].split(" ");
        }
       hgen = new HeroGen(temp);
       System.out.println("Would you like to play a game (y/n), any answer besides no is yes , right?");
       tempAnswer = ReadWrite.inp.nextLine().toLowerCase().trim();
       System.out.println(tempAnswer);
       if (tempAnswer == "no" || tempAnswer == "n"){
            return ;
       }
        System.out.println("Then let us begin");

    }

}