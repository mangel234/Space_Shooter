package com.example.mange.space_shooter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Miguel on 5/6/18.
 */

public class SpaceshipView extends SurfaceView implements Runnable {

    private Context context;
    // The size of the screen in pixels
    public static int screenX;
    public static int screenY;
    // Our SurfaceHolder to lock the surface before we draw our graphics
    public SurfaceHolder ourHolder;
    // A Canvas and a Paint object
    public static Canvas canvas;
    public static Paint paint;
    // Game is paused at the start
    private boolean paused = true;
    // A boolean which we will set and unset
    // when the game is running- or not.
    private volatile boolean playing;
    // This is our thread
    private Thread gameThread = null;

    // The players ship
    private SpaceShip playerShip;

    // The score
    private int score = 0;

    // Lives
    private int lives = 3;

    public SpaceshipView(Context context, int x, int y) {
        super(context);

       // Bitmap ballSrc = BitmapFactory.decodeResource(getResources(), R.drawable.playership);
        //Global context to be made
        this.context = context;


        // Initialize ourHolder and paint objects
        ourHolder = getHolder();
        paint = new Paint();

        screenX = x;
        screenY = y;
        //Start Game
        startLevel();



    }

    private void startLevel() {
        // Make a new player space ship
        playerShip = new SpaceShip(context, screenX, screenY);


    }

    public void draw() {

        if (ourHolder.getSurface().isValid()) {
            // Lock the canvas ready to draw
            canvas = ourHolder.lockCanvas();

            // Draw the background color
            canvas.drawColor(Color.argb(255, 47, 59, 92));

            // Choose the brush color for drawing
            paint.setColor(Color.argb(255,  255, 255, 255));

            // Now draw the player spaceship
            //canvas.drawBitmap(playerShip.getBitmap(), playerShip.getX(), screenY - 50, paint);
            canvas.drawBitmap(playerShip.getBitmap(),MainActivity.xPos, MainActivity.yMax-100, SpaceshipView.paint);


            // Draw the score and remaining lives
            // Change the brush color
            paint.setColor(Color.argb(255,  249, 129, 155));
            paint.setTextSize(40);
            canvas.drawText("Score: " + score + "   Lives: " + lives, 10,50, paint);

            // Draw everything to the screen
            ourHolder.unlockCanvasAndPost(canvas);
        }
    }

        @Override
        public void run(){
            while (playing) {
                // Capture the current time in milliseconds in startFrameTime

                if(!paused){
                    update();
                }
                // Draw the frame
                draw();

            }
        }

        private void update(){
            playerShip.update();
        }
// If SpaceInvadersActivity is paused/stopped
    // shutdown our thread.
    public void pause() {
        playing = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            Log.e("Error:", "joining thread");
        }

    }

    // If SpaceInvadersActivity is started then
    // start our thread.
    public void resume() {
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    }