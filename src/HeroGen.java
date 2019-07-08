package src;

import src.character.*;
import java.util.HashMap;

public class HeroGen{
    
    HeroGen(String heroType, String heroName){
        HashMap<String, String, String , String > heroClass = new HashMap<String, String, String, String>();
        heroClass.put("wizard", "10", "5", "5");
        heroClass.put("shieldMaiden", "5", "10", "5");
        heroClass.put("swordsman", "5", "5", "10");
        heroClass.put("adventurer", "7", "6", "7");

        Helm helm = new Helm("default " + heroType + " helm",Integer.parseInt(heroClass.get(heroType)[0]));
        Armor armor = new Armor("default " + heroType + " armor", Integer.parseInt(heroClass.get(heroType)[1]));
        Weapon weapon = new Weapon("default " + heroType + " weapon", Integer.parseInt(heroClass.get(heroType)[2]));
        Heroes newHero = new Heroes(heroName, heroType, 10, 10, 10, armor, helm, weapon, 0, 0);
    }

    HeroGen(String [] heroes){
        
    }
}