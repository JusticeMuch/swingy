package src;

import src.character.*;

import java.io.*;

import src.*;

public class Console {

    public static GameGen Console;

    public static void main(String[] args) throws IOException {

        Console = new GameGen();
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
            
    }

}