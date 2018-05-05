package com.example.mange.space_shooter.Characters;

public class Green extends Enemy {
    public Green(){
        super();
        maxHealth += 2;
        curHealth = maxHealth;
    }
    public Green(int level){
        super(level);
        maxHealth += 2;
        curHealth = maxHealth;
    }
}
