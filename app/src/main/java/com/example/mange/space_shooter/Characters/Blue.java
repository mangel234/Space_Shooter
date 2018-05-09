package com.example.mange.space_shooter.Characters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.mange.space_shooter.R;

public class Blue extends Enemy {
    public Blue(){
        super();;
        //specify bitmap
    }
    public Blue(Context context, int level){
        super(level);
        //specify bitmap
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.playership);
    }

}
