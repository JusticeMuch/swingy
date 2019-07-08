package src.character;

public class Helm{
    private String helmName;
    private int addedHitPoints;

    public Helm(){
        this.helmName = "default";
        this.addedHitPoints = 10;
    }

    public Helm(String helmName, int addedHitPoints){
        this.helmName = helmName;
       this.addedHitPoints = addedHitPoints;
    }

    public String getHelmName(){
        return this.helmName;
    }

    public int getAddedHitPoints(){
        return this.addedHitPoints;
    }
}