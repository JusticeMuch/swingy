package src.character;

import java.util.HashMap;

import src.*;

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

    public Heroes(String name, String heroClass, int attack, int defense, int hitPoints, Armor armor, Helm helm,
            Weapon weapon, int level, int exp) {
        this.setName(name);
        this.setHeroClass(heroClass);
        this.setLevel(level);
        this.setExp(exp);
        this.setAttack(attack);
        this.setDefense(defense);
        this.setHitPoints(hitPoints);
        this.setHelm(helm);
        this.setArmor(armor);
        this.setWeapon(weapon);
    }

    public String[] toArray(){
        String[] res = {this.getName(), this.getHeroClass(), Integer.toString(this.getAttack()) , Integer.toString(this.getDefense()),
                        Integer.toString(this.getHitPoints()) , this.getArmor().getArmorName() , Integer.toString(this.getArmor().getAddedDefense()) ,
                        this.getHelm().getHelmName(), Integer.toString(this.getHelm().getAddedHitPoints()), this.getWeapon().getWeaponName(),
                        Integer.toString(this.getWeapon().getAddedAttack()), Integer.toString(this.getLevel()), Integer.toString(this.getExp())};
        return res;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public Helm getHelm() {
        return helm;
    }

    public void setHelm(Helm helm) {
        this.helm = helm;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getHeroClass() {
        return heroClass;
    }

    public void setHeroClass(String heroClass) {
        this.heroClass = heroClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Heroes(String heroType, String heroName){
        HashMap<String, String[]> heroClass = new HashMap<String, String[]>();
        String [][] details = {{"10", "5", "50"},{"5", "10", "75"},{"5", "5", "50"},{"7", "6", "60"}};
        heroClass.put("wizard", details[0]);
        heroClass.put("shieldMaiden", details[1]);
        heroClass.put("swordsman", details[2]);
        heroClass.put("adventurer", details[3]);

        Helm helm = new Helm("default " + heroType + " helm",Integer.parseInt(heroClass.get(heroType)[0]));
        Armor armor = new Armor("default " + heroType + " armor", Integer.parseInt(heroClass.get(heroType)[1]));
        Weapon weapon = new Weapon("default " + heroType + " weapon", Integer.parseInt(heroClass.get(heroType)[2]));
        this.setName(heroName);
        this.setHeroClass(heroType);
        this.setLevel(0);
        this.setExp(0);
        this.setAttack(10);
        this.setDefense(10);
        this.setHitPoints(50);
        this.setHelm(helm);
        this.setArmor(armor);
        this.setWeapon(weapon);
    }

    public static void HeroesGen(String [][] heroes){
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