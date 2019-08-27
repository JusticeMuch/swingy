package src;

import model.*;
import view.*;
import controller.*;

import java.io.*;
import java.util.*;

public class Console {

    public static GameGen Console;

    public static void main(String[] args) throws IOException {

        Console = new GameGen();
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
        Heroes.HeroesGen(temp0);
        Console.startGame();
        Console.game();
    }
}