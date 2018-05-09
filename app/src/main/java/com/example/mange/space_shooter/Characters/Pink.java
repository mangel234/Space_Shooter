package com.example.mange.space_shooter.Characters;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.example.mange.space_shooter.R;

public class Pink extends Enemy {
    public Pink(){
        super();
        maxHealth += 1;
        curHealth = maxHealth;
    }
    public Pink(Context context, int level){
        super(level);
        maxHealth += 1;
        curHealth = maxHealth;
        //specify bitmap
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.playership);
    }
}
