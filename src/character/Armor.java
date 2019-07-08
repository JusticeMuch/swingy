package src.character;

public class Armor{
    private String armorName;
    private int addedDefense;

    public Armor(){
        this.armorName = "default";
        this.addedDefense = 10;
    }

    public Armor(String armorName, int addedDefense){
        this.armorName = armorName;
       this.addedDefense = addedDefense;
    }

    public String getArmorName(){
        return this.armorName;
    }

    public int getAddedDefense(){
        return this.addedDefense;
    }
}