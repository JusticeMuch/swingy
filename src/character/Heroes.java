package src.character;

import src.character.*;

public class Heroes{
    
    private String name;
    private String heroClass;
    private int level;
    private int exp;
    private int attack;
    private int defense;
    private int hitPoints;
    private Helm helm;
    private Armor armor;
    private Weapon weapon;

    Heroes(String name, String heroClass, int attack, int defense, int hitPoints,
                                        Armor armor, Helm helm, Weapon weapon){
        this.name = name;
        this.heroClass = heroClass;
        this.level = 0;
        this.exp = 0;
        this.attack = attack;
        this.defense = defense;
        this.hitPoints = hitPoints;
        this.helm = helm;
        this.armor = armor;
        this.weapon = weapon;
    }
}