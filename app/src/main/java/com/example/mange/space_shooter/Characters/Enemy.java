package com.example.mange.space_shooter.Characters;

import com.example.mange.space_shooter.Characters.Character;

/**
 * Created by mangel234 on 4/14/18.
 */

public class Enemy extends Character {

    //Actions enemy/player can perform
    /**Method to fire projectiles and move down**/
    @Override
    public void action(){}

    /**Method to draw out the enemy/player**/
    @Override
    public boolean draw(){return true;}

    /**Method for enemy passing away*/
    @Override
    public void onDeath(){}
}