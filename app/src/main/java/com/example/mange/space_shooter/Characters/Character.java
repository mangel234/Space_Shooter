package com.example.mange.space_shooter.Characters;

import android.media.SoundPool;

/**
 * Created by alpha on 4/14/18.
 */

public abstract class Character {
    //Attributes for enemy/player movement,health,and coordinates
    protected int curHealth;
    protected int maxHealth;
    protected double movement;
    protected float posX;
    protected float posY;
    protected SoundPool player;

    public Character(){
        curHealth = 1;
        maxHealth=1;
        movement = 1;
    }
    public  Character(int level){
        curHealth = level + 1;
        maxHealth = level + 1;
        movement = 1;
    }
    public  Character(float x, float y, int level){
        curHealth = level + 1;
        maxHealth = level + 1;
        movement = 1;
        posX = x;
        posY = y;
    }
    //Setters
    public void setX(float x){posX = x;}
    public void setY(float y){posY = y;}
    public void setcurHealth(int curHealth){this.curHealth = curHealth;}
    public void setmaxHealth(int maxHealth){this.maxHealth = maxHealth;}
    public void setMovement(double movement){this.movement = movement;}


    //Getters
    public float getX(){return posX;}
    public float getY(){return posY;}
    public int getcurHealth(){return curHealth;}


    //Actions enemy/player can perform
    /**Method to fire projectiles and move down**/
    public void action(){}
    /**Method to draw out the enemy/player**/
    public boolean draw(){return true;}
    /**Method for enemy passing away*/
    public void onDeath(){}

}