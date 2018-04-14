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
    protected double x;
    protected double y;
    protected SoundPool player;


    //Setters
    public void setX(double x){this.x = x;}
    public void setY(double y){this.y = y;}
    public void setcurHealth(int curHealth){this.curHealth = curHealth;}
    public void setmaxHealth(int maxHealth){this.maxHealth = maxHealth;}
    public void setMovement(double movement){this.movement = movement;}


    //Getters
    public double getX(){return x;}
    public double getY(){return y;}
    public double getcurHealth(){return curHealth;}
    public double getMovement(){return movement;}


    //Actions enemy/player can perform
    /**Method to fire projectiles and move down**/
    public void action(){}
    /**Method to draw out the enemy/player**/
    public boolean draw(){return true;}
    /**Method for enemy passing away*/
    public void onDeath(){}

}