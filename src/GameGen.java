package src;

import src.character.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import src.*;

public class GameGen{
    
    static public ArrayList <Heroes> heroList =  new ArrayList <Heroes>();
    public static int level;
    private Heroes currentHero;
    private int[][] grid;
    String temp;

    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }

    public int[][] getGrid(){
        return this.grid;
    }

    public void setLevelGrid(int level){
        if (level >= 1  && level <= 7){
            level = (level - 1) * 5 + 10 - (level % 2);
            this.grid = new int [level][level];
            for (int i = 0; i < level ; i++){
                for (int j = 0; j < level ;  j++){
                    this.grid[i][j] = 1;
                }
            }
        }
    }

    public void setEnemiesArtefacts(int level){
        Random rand = new Random();
        level = (level - 1) * 5 + 10 - (level % 2);
        int enemies = (level * level) / 10;
        int j = rand.nextInt(level);
        int k = rand.nextInt(level);
        for (int i = 0; i < enemies; i++){
            while (this.grid[j][k] != 1){
                j = rand.nextInt(level);
                k = rand.nextInt(level);
            }
            this.grid[j][k] = 2;
            if (i % 10 == 0){
                while (this.grid[j][k] != 1){
                    j = rand.nextInt(level);
                    k = rand.nextInt(level);
                }
                this.grid[j][k] = 3;
            }
        }
    }

    public void generateArtefact(int level){

        Random rand =  new Random();
        int choice = rand.nextInt(3);

        switch (choice) {
            case 0:
                currentHero.setWeapon(new Weapon("Level " + level + " " + currentHero.getHeroClass() + " weapon", 20 * level));
            case 1:
                currentHero.setHelm(new Helm("Level " + level + " " + currentHero.getHeroClass() + " helm", 20 * level));
            case 2:
                currentHero.setArmor(new Armor("Level " + level + " " + currentHero.getHeroClass() + " armor", 20 * level));
            default:
                System.out.println("Error");
        }
    }

    public Enemy generateEnemy(int level){
        Random rand = new Random();
        String [] choices = {"Ork", "Wizard", "Elf", "Mud Monster", "Itachi", "Jiraiya", "Marshall D. Teach"};
        return new Enemy(choices[rand.nextInt(7)], level * 20 , level * 10);
    }

    public Heroes getCurrentHero(){
        return this.currentHero;
    }

    public void setCurrentHero(Heroes current){
        this.currentHero = current;
    }


    public void exitGame(){
        
        try {
            ReadWrite.reader.close();
        } catch (IOException e) {
			e.printStackTrace();
        }
        
        try {
            ReadWrite.writer = new PrintWriter(ReadWrite.saveFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        for (Heroes j : GameGen.heroList){
            for (String k : j.toArray()){
                ReadWrite.writer.print(k+" ");
            }
            ReadWrite.writer.println();
        }
        ReadWrite.inp.close();
        ReadWrite.writer.close();
    }

    public void selectCharacter(String input){
        if (!(temp == "1")){
            System.out.println("Here are your options : ");
            int i = -1;
            for (Heroes j : GameGen.heroList){
                System.out.print("Index : " + ++i + " Hero Name : " + j.getName() + " Hero Class: "+ j.getHeroClass() + " Level: " + j.getLevel());
            }
            System.out.println("Please select one of the heroes by inputting the number of the corresponding index");
            i = ReadWrite.inp.nextInt();
            if (i >= 0 && i >= GameGen.heroList.size())
        }else if ((!(temp == "yes") || !(temp == "y"))){
            clearScreen();
            System.out.println("Then lets begin the game !!");
        }else{
            System.out.println("Input invalid , closing game");
            this.exitGame();
            return;
        }
    }

    public void startGame(){
        System.out.println("Would you like to start a game (Y/N) ?");
        temp = ReadWrite.inp.nextLine().trim().toLowerCase();
        if (!(temp == "no") || !(temp == "n")){
            this.exitGame();
            return ;
        }else if ((!(temp == "yes") || !(temp == "y"))){
            clearScreen();
            System.out.println("Then lets begin the game !!");
        }else{
            System.out.println("Input invalid , closing game");
            this.exitGame();
            return;
        }
        System.out.println("Would you like to load or create a character (1 to load / 2 to save) ?");
        this.selectCharacter(ReadWrite.inp.nextLine().trim().toLowerCase());
    }
}

// 1 is just normal blocks
// 2 is enemies
// 3 is artefacts
// 4 is where the hero was 
// 5 is where he is currently 