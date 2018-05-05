package com.example.mange.space_shooter.Characters;

public class Pink extends Enemy {
    public Pink(){
        super();
        maxHealth += 1;
        curHealth = maxHealth;
    }
    public Pink(int level){
        super(level);
        maxHealth += 1;
        curHealth = maxHealth;
    }
}
