package com.example.mange.space_shooter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class SpaceShip {

    // The player ship will be represented by a Bitmap
    private Bitmap bitmap;
    // How long and high our paddle will be
    private float length;
    private float height;

    // X is the far left of the rectangle which forms our paddle
    public float x;

    // Y is the top coordinate
    public float y;

    public SpaceShip(Context context, int screenX, int screenY) {
        length = screenX/10;
        height = screenY/10;

        // Start ship in roughly the screen centre
        x = screenX / 2;//being overwritten somewhere
        y = screenY/2;

        // Initialize the bitmap
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.playership);

        // stretch the bitmap to a size appropriate for the screen resolution
        bitmap = Bitmap.createScaledBitmap(bitmap,
                (int) (length),
                (int) (height),
                false);
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void update(){
        SpaceshipView.canvas.drawBitmap(getBitmap(),MainActivity.xPos, MainActivity.yPos - 60, SpaceshipView.paint);
        // SpaceInvadersView.canvas.drawBitmap(getBitmap(), getX(), SpaceInvadersView.screenY - 50, SpaceInvadersView.paint);
    }
}
