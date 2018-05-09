package com.example.mange.space_shooter;

import android.graphics.RectF;

public class Ammo {

    private int width = 1;
    private int height;
    private RectF rect;
    private boolean onScreen;
    private  float x;
    private float y;
    // where we shooting
    public int upward = 0;
    public int downward = 1;
    // Going nowhere
    int going = -1;
    float speed =  350;

    public Ammo(int screenY){
        rect = new RectF();
        //track bullet off screen
        height = screenY / 20;
        //Is bullet in the screen?
        onScreen = false;

    }

    public RectF getRect(){
        return  rect;
    }

    public boolean checkStatus(){
        return onScreen;
    }

    public void bullet_Not_On_Screen(){
        onScreen = false;
    }

    public float collision(){
      //Check colissions
        if (going == downward){
            return y + height;
        }else{
            return  y;
        }

    }

    public boolean shoot(float startX, float startY, int direction) {
       //Bullet hasnt been shot
        if (!onScreen) {
            x =  startX;
            y =  startY;
            going = direction;
            onScreen = true;
            return true;
        }

        // Bullet already active
        return false;
    }

    public void update(long fps){

        // Just move up or down
        if(going == upward){
            y =  (y - speed*4 / fps);
        }else{
            y =  (y + speed*4 / fps);
        }

        // Update rect
        rect.left = x;
        rect.right = x + width;
        rect.top = y;
        rect.bottom = y + height;

    }

}
