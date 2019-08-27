package model;

public class Weapon{
    private String weaponName;
    private int addedAttack;

    public Weapon(){
        this.weaponName = "default";
        this.addedAttack = 10;
    }

    public Weapon(String weaponName, int addedAttack){
        this.weaponName = weaponName;
       this.addedAttack = addedAttack;
    }

    public String getWeaponName(){
        return this.weaponName;
    }

    public int getAddedAttack(){
        return this.addedAttack;
    }
}