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
    private int level;
    private Heroes currentHero;
    private int[][] grid;
    String temp;
    private int[] curentPosition;


    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }

    public int[][] getGrid(){
        return this.grid;
    }

    public void setGridBlock(int x, int y, int fill){
        this.grid[x][y] = fill;
    }

    public int getLevel(){
        return this.level;
    }

    public void setLevel(int level){
        this.level = level;
    }

    public int[] getCurrentPosition(){
        return this.curentPosition;
    }

    public void setCurrentPosition(int x, int y){
        this.curentPosition = new int [2];
        this.curentPosition[0] = x;
        this.curentPosition[1] = y;
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
        int enemies = (level * level) / 3;
        int artefacts = (level * level) / 10;
        int j = rand.nextInt(level);
        int k = rand.nextInt(level);
        for (int i = 0; i < artefacts; i++){
            while (this.grid[j][k] != 1){
                j = rand.nextInt(level);
                k = rand.nextInt(level);
            }
            this.grid[j][k] = 2;
        }
        for (int i = 0; i < enemies; i++){
            while (this.grid[j][k] != 1){
                j = rand.nextInt(level);
                k = rand.nextInt(level);
            }
            this.grid[j][k] = 3;
        }
    }


    public boolean move(String move){
        if (move.isBlank()){
            System.out.println("Please dont shoot blanks");
            return false;
        }
       move = move.toLowerCase().trim();
       char c = move.toCharArray()[0];
       switch (c){
           case 'n':
                if (this.curentPosition[1] + 1 > this.grid.length){
                    System.out.println("Sorry you can't move any further North, Jon  Snow!!");
                    return false;
                }else{
                    this.setGridBlock(this.curentPosition[0], this.curentPosition[1], 4);
                    this.setCurrentPosition(this.curentPosition[0], this.curentPosition[1] + 1);
                    System.out.println("Your current coordinates are "+ this.curentPosition[0] + ", "+ this.curentPosition[1]);
                    return true;
                }
           case 'e':
                if (this.curentPosition[0] + 1 > this.grid.length){
                    System.out.println("Sorry you can't move any further East, Alladin!!");
                    return false;
                }else{
                    this.setGridBlock(this.curentPosition[0], this.curentPosition[1], 4);
                    this.setCurrentPosition(this.curentPosition[0] + 1, this.curentPosition[1]);
                    System.out.println("Your current coordinates are "+ this.curentPosition[0] + ", "+ this.curentPosition[1]);
                    return true;
                }
           case 's':
                if (this.curentPosition[1] - 1 < 0){
                    System.out.println("Sorry you can't move any further South, Sansa!!");
                    return false;
                }else{
                    this.setGridBlock(this.curentPosition[0], this.curentPosition[1], 4);
                    this.setCurrentPosition(this.curentPosition[0], this.curentPosition[1] - 1);
                    System.out.println("Your current coordinates are "+ this.curentPosition[0] + ", "+ this.curentPosition[1]);
                    return true;
                }
           case 'w':
                if (this.curentPosition[0] - 1 < 0){
                    System.out.println("Sorry you can't move any further West, Lannister!!");
                    return false;
                }else{
                    this.setGridBlock(this.curentPosition[0], this.curentPosition[1], 4);
                    this.setCurrentPosition(this.curentPosition[0] - 1, this.curentPosition[1]);
                    System.out.println("Your current coordinates are "+ this.curentPosition[0] + ", "+ this.curentPosition[1]);
                    return true;
                }
           default:
                System.out.println("Please input a direction or the letter beggining");
                return false;
       }

    }
    public void generateArtefact(int level){

        Random rand =  new Random();
        int choice = rand.nextInt(3);

        switch (choice) {
            case 0:
                this.currentHero.setWeapon(new Weapon("Level " + level + " " + this.currentHero.getHeroClass() + " weapon", 20 * level));
                System.out.println(this.getCurrentHero().getWeapon());
            case 1:
                this.currentHero.setHelm(new Helm("Level " + level + " " + this.currentHero.getHeroClass() + " helm", 20 * level));
                System.out.println(this.getCurrentHero().getHelm());
            case 2:
                this.currentHero.setArmor(new Armor("Level " + level + " " + this.currentHero.getHeroClass() + " armor", 20 * level));
                System.out.println(this.getCurrentHero().getArmor());
            default:
                System.out.println("Error");
        }
    }


    public void EnemyVersus(Enemy enemy){
        System.out.println("Do you wanna fight or run, if you run, there's a 50% chance you'll survive ??");
        System.out.println("Select 1 to fight, 2 to run");
        String input = ReadWrite.inp.nextLine().trim();
        while (this.currentHero.getHitPoints() > 0 && enemy.getEnemyHealth() > 0){
            if (input.matches("1")){
                enemy.setEnemyHealth(enemy.getEnemyHealth() - this.currentHero.getAttack());
                if (enemy.getEnemyHealth() <= 0){
                    System.out.println("The enemy is dead, carry on with your game, your remaining life left is "+ this.currentHero.getHitPoints());
                }else{
                    int enemyAttack = enemy.getEnemyAttack() - this.currentHero.getDefense();
                    enemyAttack = enemyAttack > 0 ? enemyAttack : 0;
                    this.currentHero.setHitPoints(this.currentHero.getHitPoints() - enemyAttack);
                    if (this.currentHero.getHitPoints() <= 0){
                        this.currentHero.setHitPoints(50 * this.currentHero.getLevel());
                        System.out.println("You died in battle, Your name will shouted at the dinner halls in Valhalla !!!!");
                        this.exitGame();
                    }
                }
            }
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

    public boolean getGameStatus(){
        for (int i = 0; i < this.getGrid().length; i++){
            if (this.getGrid()[this.getGrid().length - 1][i] == 5)
                return true;
            else if (this.getGrid()[0][i] == 5)
                return true;
        }
        return false;
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
        String [] heroesClass = {"wizard", "shieldMaiden", "swordsman", "adventurer"};
        int i = -1;
        String temp = "default";
        if (input.matches("1")){
            System.out.println("Here are your options : ");
            
            for (Heroes j : GameGen.heroList){
                System.out.println("Index : " + ++i + " Hero Name : " + j.getName() + " Hero Class: "+ j.getHeroClass() + " Level: " + j.getLevel());
            }
            System.out.println("Please select one of the heroes by inputting the number of the corresponding index");
            i = ReadWrite.inp.nextInt();
            if (i >= 0 && i < GameGen.heroList.size()){
                this.currentHero = GameGen.heroList.get(i);
            }else{
                clearScreen();
                System.out.println("Error in selection");
                this.selectCharacter(input);
            }
            System.out.println("Your hero's name is " + this.currentHero.getName() + " and his class is " + this.getCurrentHero().getHeroClass());
        }else if (input.matches("2")){
            System.out.println("Please give your hero a name");
            temp = ReadWrite.inp.nextLine().trim();
            System.out.println();
            System.out.println("Please select one of the classes by selecting the corresponding index");
            System.out.println("1. Wizard");
            System.out.println("2. Shield Maiden");
            System.out.println("3. Swordsman");
            System.out.println("4. Adventurer");
            i = ReadWrite.inp.nextInt();
            if (i < 1 || i > 4 || temp.length() < 1){
                clearScreen();
                System.out.println("Please note that your input is incorrect, so here is another try ");
                this.selectCharacter(input);
            }else{
                this.currentHero = new Heroes(heroesClass[i - 1], temp);
            }
            System.out.println("Your hero's name is " + this.currentHero.getName() + " and his class is " + this.getCurrentHero().getHeroClass());
        }else{
            System.out.println("Input invalid , closing game");
            this.exitGame();
            return;
        }
    }

    public void startGame(){
        System.out.println("Would you like to start a game (Y/N) ?");
        String temp = ReadWrite.inp.nextLine().trim().toLowerCase();
        if (temp.matches("no")|| temp.matches("no")){
            this.exitGame();
            return ;
        }else if (temp.matches("yes")|| temp.matches("y")){
            clearScreen();
            System.out.println("Then lets begin the game !!");
        }else{
            System.out.println("Input invalid , closing game");
            this.exitGame();
            return;
        }
        System.out.println("Would you like to load or create a character (1 to load / 2 to create ) ?");
        this.selectCharacter(ReadWrite.inp.nextLine().trim().toLowerCase());

    }

    public void game(){
        System.out.println("You can only start by level one in this game !!!");
        this.setLevel(1);
        while (true){
            while (this.getLevel() != 8){
                System.out.println("You will now be starting level " + this.getLevel() + " Good luck , may the odds be ever in your favor!!");
                this.setLevelGrid(this.getLevel());
                this.setCurrentPosition(this.getGrid().length/2, this.getGrid().length/2);
                System.out.println("Current position working");
                while (this.getGameStatus() == false){
                    System.out.println("You are currently at position at " + this.getCurrentPosition()[0] + "," + this.getCurrentPosition()[1] + " on a board that is " 
                    +this.getGrid().length + " x " + this.getGrid().length);
                    System.out.println();
                    System.out.println("Please enter a direction or the first letter of that direction that you would like to go");
                    System.out.println("(s) South, (e) East, (n) North, (W) West");
                    while (this.move(ReadWrite.inp.nextLine()) != true){
                        clearScreen();
                    }
                    switch(this.getGrid()[this.getCurrentPosition()[0]][this.getCurrentPosition()[1]]){
                        case 1:
                            this.setGridBlock(this.getCurrentPosition()[0], this.getCurrentPosition()[1], 5);
                            System.out.println("Nothing happening here !!");
                        case 2:
                            this.setGridBlock(this.getCurrentPosition()[0], this.getCurrentPosition()[1], 5);
                            this.EnemyVersus(this.generateEnemy(this.getLevel()));
                        case 3:
                            this.setGridBlock(this.getCurrentPosition()[0], this.getCurrentPosition()[1], 5);
                            System.out.println("New Artefacts !!");
                            this.generateArtefact(this.getLevel());
                        case 4:
                            this.setGridBlock(this.getCurrentPosition()[0], this.getCurrentPosition()[1], 5);
                            System.out.println("You were here previously , but ok !!!");
                        case 5:
                            this.setGridBlock(this.getCurrentPosition()[0], this.getCurrentPosition()[1], 5);
                    }
                     
                }
            }
        }
    }
}

// 1 is just normal blocks
// 2 is enemies
// 3 is artefacts
// 4 is where the hero was 
// 5 is where he is currently 