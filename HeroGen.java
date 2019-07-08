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

        Heroes newHero = new Heroes(heroName, heroType, 10, 10, 10, 
            new Armor("Low "+ heroType + " armor", ))
    }

}