package src;

import src.character.*;
import java.util.HashMap;

public class HeroGen{
    
    HeroGen(String heroType, String heroName){
        HashMap<String, String[]> heroClass = new HashMap<String, String[]>();
        String [][] details = {{"10", "5", "50"},{"5", "10", "75"},{"5", "5", "50"},{"7", "6", "60"}};
        heroClass.put("wizard", details[0]);
        heroClass.put("shieldMaiden", details[1]);
        heroClass.put("swordsman", details[2]);
        heroClass.put("adventurer", details[3]);

        Helm helm = new Helm("default " + heroType + " helm",Integer.parseInt(heroClass.get(heroType)[0]));
        Armor armor = new Armor("default " + heroType + " armor", Integer.parseInt(heroClass.get(heroType)[1]));
        Weapon weapon = new Weapon("default " + heroType + " weapon", Integer.parseInt(heroClass.get(heroType)[2]));
        Heroes newHero = new Heroes(heroName, heroType, 10, 10, 50, armor, helm, weapon, 0, 0);
        GameGen.current = newHero;
    }

    HeroGen(String [][] heroes){
        for (String[] line : heroes){
            if (line[0] == null){
                break;
            }
            Heroes temp = new Heroes(line[0], line[1], Integer.parseInt(line[2]), Integer.parseInt(line[3]), 
                Integer.parseInt(line[4]), new Armor(line[5], Integer.parseInt(line[6])), 
                new Helm(line[7], Integer.parseInt(line[8])), new Weapon(line[9], Integer.parseInt(line[10])),
                Integer.parseInt(line[11]), Integer.parseInt(line[12]));
            GameGen.heroList.add(temp);
        }
    }
}