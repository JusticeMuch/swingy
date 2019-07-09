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
        String[] res = {this.getName(), this.getHeroClass(), Integer.toString(};
        //must still finish
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

    
}