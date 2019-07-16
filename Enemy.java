

public class Enemy{
    
    private String name;
    private int health;
    private int attack;

    Enemy(String name, int health, int attack){
        this.name = name;
        this.health = health;
        this.attack = attack;
    }

    public String getEnemyName(){
        return this.name;
    }

    public int getEnemyHealth(){
        return this.health;
    }

    public int getEnemyAttack(){
        return this.attack;
    }

    public void setEnemyName(String name){
        this.name = name;
    }

    public void setEnemyHealth(int health){
        this.health = health;
    }

    public void setEnemyAttack(){
        this.attack = attack;
    }
}