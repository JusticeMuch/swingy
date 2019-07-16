package src;

import src.character.*;
import java.util.ArrayList;
import src.*;

public class GameGen{
    static public ArrayList <Heroes> heroList =  new ArrayList <Heroes>();
    public static int level;
    private Heroes current;

    public void generateArtefact(int level){
        Random rand =  new Random();
        int choice = rand.nextInt(3);
        switch (choice) {
            case 0:
                current.setWeapon(new Weapon("Level " + level + " " + current.getHeroClass() + " weapon", 20 * level));
            case 1:
                current.setHelm(new Helm("Level " + level + " " + current.getHeroClass() + " helm", 20 * level));
            case 2:
                current.setArmor(new Armor("Level " + level + " " + current.getHeroClass() + " armor", 20 * level));
            default:
                System.out.println("Error");
        }
    }

    public Enemy generateEnemy(int level){
        Random rand = new Random();
        String [] choices = {"Ork", "Wizard", "Elf", "Mud Monster", "Itachi", "Jiraiya", "Marshall D. Teach"};
        return new Enemy(choices[rand.nextInt(7)], level * 20 , level * 10);
    }

    public void getCurrentHero(){
        return this.current;
    }
}