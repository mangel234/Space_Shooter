package com.example.mange.space_shooter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.mange.space_shooter.Characters.Blue;
import com.example.mange.space_shooter.Characters.Character;
import com.example.mange.space_shooter.Characters.Green;
import com.example.mange.space_shooter.Characters.Pink;

public class Invaders {
    // The player ship will be represented by a Bitmap
    private Bitmap bitmap;
    // How long and high our paddle will be
    private float length;
    private float height;

    // X is the far left of the rectangle which forms our paddle
    private float x;

    // Y is the top coordinate
    private float y;

    public Invaders(Context context, int screenX, int screenY, String type) {
        length = screenX/10;
        height = screenY/10;

        // Start ship in roughly the screen centre
        x = screenX / 2;//being overwritten somewhere
        y = screenY/2;

        if(type.equals("blue")){
            // Initialize the bitmap
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.blue);
        }
        if(type.equals("green")){
            // Initialize the bitmap
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.green);
        }
        if(type.equals("pink")){
            // Initialize the bitmap
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.pink);
        }


        // stretch the bitmap to a size appropriate for the screen resolution
        bitmap = Bitmap.createScaledBitmap(bitmap,
                (int) (length),
                (int) (height),
                false);
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void update(){
        SpaceshipView.canvas.drawBitmap(getBitmap(),MainActivity.xPos+100, MainActivity.yPos - 60, SpaceshipView.paint);
        // SpaceInvadersView.canvas.drawBitmap(getBitmap(), getX(), SpaceInvadersView.screenY - 50, SpaceInvadersView.paint);
    }


}
