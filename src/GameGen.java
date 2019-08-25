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

    public void printGrid(){
        System.out.println();
        for (int i = 0; i < this.getGrid().length; i++){
            for (int j = 0; j < this.getGrid().length; j++){
                System.out.print(this.getGrid()[j][i] + " ");
            }
            System.out.println();
        }
    }

    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }

    public void sleep(int ms){
        try
        {
            Thread.sleep(ms);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
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

    public void updateExp(){
        this.currentHero.setLevel(this.level);
        this.currentHero.setExp(this.currentHero.getExp() + this.level * 1000);
        this.currentHero.setAttack(this.level * 30);
        this.currentHero.setDefense(this.level * 30);
        this.currentHero.setHitPoints(this.level * 75);
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


    public void move(){   // work on function
        System.out.println("You are currently at position at " + (this.getCurrentPosition()[0] + 1) + "," + (this.getCurrentPosition()[1] + 1) + " on a board that is " 
                     +this.getGrid().length + " x " + this.getGrid().length);
        System.out.println();
        System.out.println("Please enter a direction or the first letter of that direction that you would like to go");
        System.out.println("(s) South, (e) East, (n) North, (W) West");
        String move = ReadWrite.inp.nextLine();
       move = move.toLowerCase().trim();
       if (move.length() < 1){
           clearScreen();
           this.move();
           return ;
       }
       System.out.println(move);
       //System.out.println(move.toCharArray().length);
       char c = move.toCharArray()[0];
       switch (c){
           case 'n':
                if (this.curentPosition[1] + 1 > this.grid.length){
                    System.out.println("Sorry you can't move any further North, Jon  Snow!!");
                }else{
                    this.setGridBlock(this.curentPosition[0], this.curentPosition[1], 4);
                    this.setCurrentPosition(this.curentPosition[0], this.curentPosition[1] + 1);
                    System.out.println("Your current coordinates are "+ (this.getCurrentPosition()[0] + 1) + "," + (this.getCurrentPosition()[1] + 1));
                }
                break;

           case 'e':
                if (this.curentPosition[0] + 1 > this.grid.length){
                    System.out.println("Sorry you can't move any further East, Alladin!!");
                }else{
                    this.setGridBlock(this.curentPosition[0], this.curentPosition[1], 4);
                    this.setCurrentPosition(this.curentPosition[0] + 1, this.curentPosition[1]);
                    System.out.println("Your current coordinates are "+ (this.getCurrentPosition()[0] + 1) + "," + (this.getCurrentPosition()[1] + 1));
                }
                break;

           case 's':
                if (this.curentPosition[1] - 1 < 0){
                    System.out.println("Sorry you can't move any further South, Sansa!!");
                }else{
                    this.setGridBlock(this.curentPosition[0], this.curentPosition[1], 4);
                    this.setCurrentPosition(this.curentPosition[0], this.curentPosition[1] - 1);
                    System.out.println("Your current coordinates are "+ (this.getCurrentPosition()[0] + 1) + "," + (this.getCurrentPosition()[1] + 1));
                }
                break;

           case 'w':
                if (this.curentPosition[0] - 1 < 0){
                    System.out.println("Sorry you can't move any further West, Lannister!!");
                }else{
                    this.setGridBlock(this.curentPosition[0], this.curentPosition[1], 4);
                    this.setCurrentPosition(this.curentPosition[0] - 1, this.curentPosition[1]);
                    System.out.println("Your current coordinates are "+ (this.getCurrentPosition()[0] + 1) + "," + (this.getCurrentPosition()[1] + 1));
                }
                break;

           default:
                System.out.println("Please input a direction or the letter beggining");
       }

    }
    public void generateArtefact(int level){

        Random rand =  new Random();
        int choice = rand.nextInt(3);

        switch (choice) {
            case 0:
                this.currentHero.setWeapon(new Weapon("Level_" + level + "_" + this.currentHero.getHeroClass() + "_weapon", 20 * level));
                System.out.println(this.getCurrentHero().getWeapon().getWeaponName());
                break;

            case 1:
                this.currentHero.setHelm(new Helm("Level_" + level + "_" + this.currentHero.getHeroClass() + "_helm", 20 * level));
                System.out.println(this.getCurrentHero().getHelm().getHelmName());
                break;

            case 2:
                this.currentHero.setArmor(new Armor("Level_" + level + "_" + this.currentHero.getHeroClass() + "_armor", 20 * level));
                System.out.println(this.getCurrentHero().getArmor().getArmorName());
                break;

            default:
                System.out.println("Error");
        }
    }

    public int runOrFight(Enemy enemy){

        Random rand = new Random();
        int number = 0;
        int attack = this.currentHero.getAttack() + this.currentHero.getWeapon().getAddedAttack();
        int enemyAttack = this.currentHero.getDefense() + this.getCurrentHero().getArmor().getAddedDefense() - enemy.getEnemyAttack();

        System.out.println("Your enemy's name is " + enemy.getEnemyName() + " and he has "+ enemy.getEnemyHealth() +"hp and " + enemy.getEnemyAttack() + " attack points");
        System.out.println("Your currently have "+ this.currentHero.getHitPoints() +"hp and your attack is worth is " + attack);
        do {
            System.out.println("You have to now choose to fight(1) or run(2) !!");
            while (!ReadWrite.inp.hasNextInt()) {
                System.out.println("That's not a 1 or 2!");
                ReadWrite.inp.next();
            }
            number = ReadWrite.inp.nextInt();
        } while (number != 1 && number != 2);
        if (number == 2){
            if (rand.nextInt(2) == 0){
                System.out.println("You made it out alive, you're a good runner !!!");
                this.sleep(3000);
                return (1);
            }else{
                System.out.println("Get ready for a fight, the enemy catches up to you, slowpoke !!!!");
                System.out.println("Your enemy's name is " + enemy.getEnemyName() + " and he has "+ enemy.getEnemyHealth() +"hp and " + enemy.getEnemyAttack() + " attack points");
                System.out.println("Your currently have "+ this.currentHero.getHitPoints() +"hp and your attack is worth is " + attack);
            }
        }
        enemy.setEnemyHealth(enemy.getEnemyHealth() - attack);
        if (enemy.getEnemyHealth() > 0 && enemyAttack < 0){
            this.currentHero.setHitPoints(this.currentHero.getHitPoints() - enemyAttack);
        }

        if (this.currentHero.getHitPoints() > 0 && enemy.getEnemyHealth() <= 0){
            System.out.println("You survived , well done");
            this.sleep(3000);
            return (1);
        }else if (this.currentHero.getHitPoints() > 0 && enemy.getEnemyHealth() > 0){
            System.out.println("Your enemy survives, survive, I dare you !!!!");
            System.out.println();
            this.sleep(3000);
            return this.runOrFight(enemy);
        }else{
            System.out.println("You died");
            this.sleep(3000);
            return (2);
        }
    }

    public Enemy generateEnemy(int level){
        Random rand = new Random();
        String [] choices = {"Ork", "Wizard", "Elf", "Mud Monster", "Itachi", "Jiraiya", "Marshall D. Teach"};
        return new Enemy(choices[rand.nextInt(7)], level * 50 , level * 25);
    }

    public Heroes getCurrentHero(){
        return this.currentHero;
    }

    public void setCurrentHero(Heroes current){
        this.currentHero = current;
    }

    public boolean getGameStatus(){
        for (int i = 0; i < this.getGrid().length; i++){
            if (this.getGrid()[i][this.getGrid().length - 1] == 5)
                return true;
            else if (this.getGrid()[i][0] == 5)
                return true;
        }
        return false;
    }

    public void exitGame(){
        if (this.level != 8){
            System.out.println("Sorry for your loss");
        }else{
            System.out.println("Well done on completing the game");
        }

        if (this.currentHero != null  && !(GameGen.heroList.contains(this.currentHero)))
            GameGen.heroList.add(this.currentHero);
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
        System.exit(0);
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
                clearScreen();
                System.out.println("You will now be starting level " + this.getLevel() + " Good luck , may the odds be ever in your favor!!");
                this.setLevelGrid(this.getLevel());
                this.setEnemiesArtefacts(this.getLevel());
                this.setCurrentPosition(this.getGrid().length/2 - 1, this.getGrid().length/2 - 1);
                while (this.getGameStatus() == false){
                    this.move();
                    System.out.println();
                    switch(this.getGrid()[this.getCurrentPosition()[0]][this.getCurrentPosition()[1]]){
                        case 1:
                            this.setGridBlock(this.getCurrentPosition()[0], this.getCurrentPosition()[1], 5);
                            System.out.println("Nothing happening here !!");
                            break;

                        case 2:
                            this.setGridBlock(this.getCurrentPosition()[0], this.getCurrentPosition()[1], 5);
                            clearScreen();
                            if (this.runOrFight(this.generateEnemy(this.getLevel())) == 2){
                                this.exitGame();
                            }
                            break;

                        case 3:
                            this.setGridBlock(this.getCurrentPosition()[0], this.getCurrentPosition()[1], 5);
                            System.out.println("New Artefacts !!");
                            this.generateArtefact(this.getLevel());
                            break;

                        case 4:
                            this.setGridBlock(this.getCurrentPosition()[0], this.getCurrentPosition()[1], 5);
                            System.out.println("You were here previously , but ok !!!");
                            break;

                        case 5:
                            this.setGridBlock(this.getCurrentPosition()[0], this.getCurrentPosition()[1], 5);
                            break;

                        default:
                            System.out.println("There is a error in the grid");
                    }
                    this.setGridBlock(this.curentPosition[0], this.curentPosition[1], 5);
                }
                (this.level)++;
                this.updateExp();
            }
            if (this.level == 8){
                this.exitGame();
            }
        }
        
    }
}

// 1 is just normal blocks
// 2 is enemies
// 3 is artefacts
// 4 is where the hero was 
// 5 is where he is currently 