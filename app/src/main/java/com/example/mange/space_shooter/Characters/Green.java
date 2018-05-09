package com.example.mange.space_shooter.Characters;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.example.mange.space_shooter.R;

public class Green extends Enemy {
    public Green(){
        super();
        maxHealth += 2;
        curHealth = maxHealth;
    }
    public Green(Context context, int level){
        super(level);
        maxHealth += 2;
        curHealth = maxHealth;
        //specify bitmap
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.playership);
    }
}
