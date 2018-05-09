package com.example.mange.space_shooter.Characters;

import android.content.Context;
import android.graphics.Bitmap;
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
    // How long and high our paddle will be
    private float length;
    private float height;

    // X is the far left of the rectangle which forms our paddle
    private float x;

    // Y is the top coordinate
    private float y;
    // The player ship will be represented by a Bitmap
    Bitmap bitmap;

    protected SoundPool player;


    public Character(Context context, int screenX, int screenY) {
        length = screenX/10;
        height = screenY/10;

        // Start ship in roughly the screen centre
        x = screenX / 2;//being overwritten somewhere
        y = screenY/2;

        // Initialize the bitmap


        // stretch the bitmap to a size appropriate for the screen resolution
        bitmap = Bitmap.createScaledBitmap(bitmap,
                (int) (length),
                (int) (height),
                false);
    }

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
    public Bitmap getBitmap(){return bitmap;}

    //Actions enemy/player can perform
    /**Method to fire projectiles and move down**/
    public void action(){}
    /**Method to draw out the enemy/player**/
    public boolean draw(){return true;}
    /**Method for enemy passing away*/
    public void onDeath(){}



}