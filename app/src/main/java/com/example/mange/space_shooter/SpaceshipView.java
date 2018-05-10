package com.example.mange.space_shooter;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;
import java.util.Random;
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
    private int tokyo = -1;
    private boolean soundPlay = false;


    // The players ship
    private SpaceShip playerShip;

    // The score
    private int score = 0;

    // Lives
    private int lives = 3;
    //Grab the invaders
    Invaders[] board = new Invaders[24];
    // This is used to help calculate the fps
    private long frameTime;
    private SoundPool soundPool;
    //Grab the players bullets
    Ammo ammo;
    private int shooting_Sound = -1;
    Ammo enemyAmmo[] = new Ammo[3];
    Random rand = new Random();
    String wave = "1";
    // This variable tracks the game frame rate
    private long fps;

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
        // This SoundPool is deprecated but don't worry
        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC,0);

        try{
            // Create objects of the 2 required classes
            AssetManager assetManager = context.getAssets();
            AssetFileDescriptor descriptor;

            // Load our fx in memory ready for use
            descriptor = assetManager.openFd("shoot.ogg");
           shooting_Sound = soundPool.load(descriptor, 0);

            // Load our fx in memory ready for use
            descriptor = assetManager.openFd("tokyo.ogg");
            tokyo = soundPool.load(descriptor, 0);



        }catch(IOException e){
            // Print an error message to the console
            Log.e("error", "failed to load sound files");
        }
        startLevel();


    }

    private void startLevel() {


        // Make a new player space ship
        playerShip = new SpaceShip(context, screenX, screenY);

        //Call the invader class to draw invaders
        //Initialize the board of invaders
        String type;
        for (int row = 0; row < board.length; row++) {
            if (row / 8 == 0) {
                type = "green";
                board[row] = new Invaders(context, screenX, screenY, type);
            } else if (row / 8 == 1) {
                type = "blue";
                board[row] = new Invaders(context, screenX, screenY, type);
            } else if (row / 8 == 2) {
                type = "pink";
                board[row] = new Invaders(context, screenX, screenY, type);
            }
            // invader_board = new Invaders(context,screenX,screenY);

            //Get the bullets ready
            ammo = new Ammo(screenY);
            for(int i = 0; i < 3; i++){
                enemyAmmo[i] = new Ammo(screenY);
            }
        }
    }

    public void checkCollision(){
        float xaddition = 0; //spacing between invaders
        float yaddition = 00; //spacing between rows
        int i = (int)((ammo.getRect().left - screenX/10)/100);
        if(i < 8)
            for (; i < board.length; i+=8) {
                yaddition = i / 8 * 100;
                if((board[i] != null && board[i].getY() + yaddition <= ammo.getRect().top && board[i].getY() + yaddition + 100 >= ammo.getRect().top )
                        || (board[i] != null && board[i].getY() + yaddition <= ammo.getRect().bottom && board[i].getY() + yaddition + 100 >= ammo.getRect().bottom) ){
                    board[i].health -=1;
                    if(board[i] .health== 0) {
                        scoring((int)yaddition);
                        board[i] = null;
                    }
                    ammo.bullet_Not_On_Screen();
                    return;
                }
            }
    }
    public void playerCollision(){
        for(int i = 0; i < 3; i++){
            if(enemyAmmo[i].checkStatus() && (enemyAmmo[i].getRect().top <= MainActivity.yMax && enemyAmmo[i].getRect().top >= MainActivity.yMax -100) &&
                    ( enemyAmmo[i] .getRect().left >= MainActivity.xPos && enemyAmmo[i].getRect().left <= MainActivity.xPos + 100)) {
                enemyAmmo[i].bullet_Not_On_Screen();
                lives -= 1;
            }
        }
    }
    public void scoring(int location){
        if(location== 0)
            score += 500;
        else if(location== 100)
            score +=250;
        else
            score+=100;
    }
    public void draw() {

        if (ourHolder.getSurface().isValid()) {
            // Lock the canvas ready to draw
            canvas = ourHolder.lockCanvas();

            // Draw the background color
            canvas.drawColor(Color.argb(255, 47, 59, 92));

            // Choose the brush color for drawing
            paint.setColor(Color.argb(255, 255, 255, 255));

            // Now draw the player spaceship
            //canvas.drawBitmap(playerShip.getBitmap(), playerShip.getX(), screenY - 50, paint);
            canvas.drawBitmap(playerShip.getBitmap(), MainActivity.xPos, MainActivity.yMax - 100, SpaceshipView.paint);

            float xaddition = 0; //spacing between invaders
            float yaddition = 00; //spacing between rows
            // ------------------------------- Draw the invaders and location------------------------------------------------------
            for (int i = 0; i < board.length; i++) {
                if (i % 8 == 0)
                    xaddition = 0;
                yaddition = i / 8 * 100;
                if(board[i] != null)
                    canvas.drawBitmap(board[i].getBitmap(), board[i].getX() + xaddition, board[i].getY() + yaddition, paint);
                xaddition += 100;
            }

            // Draw the players bullet
            if (ammo.checkStatus()) {
                canvas.drawRect(ammo.getRect(), paint);
            }
            for(int i = 0; i < enemyAmmo.length; i++)
                if (enemyAmmo[i].checkStatus()) {
                    canvas.drawRect(enemyAmmo[i].getRect(), paint);
                  }

//-------------------Draw the text and Color -------------------------------------------------------
            // Draw the score and remaining lives
            // Change the brush color
            paint.setColor(Color.argb(255, 249, 129, 155));
            paint.setTextSize(40);
            canvas.drawText("Score: " + score + "   Lives: " + lives + " Wave: " + wave, 10, 50, paint);

            // Draw everything to the screen
            ourHolder.unlockCanvasAndPost(canvas);
        }
    }
    public void win(){
        for(int i = 0; i < board.length; i++){
            if(board [i]!= null)
                return;
        }
        startLevel();
        wave = Integer.toString(Integer.parseInt(wave)+1);
    }
    @Override
    public void run() {
        while (playing) {
            // Capture the current time in milliseconds in startFrameTime
            if(!soundPlay){
                soundPool.play(tokyo, 1, 1, 0, -1, 1);
                soundPlay =true;
            }
            // Capture the current time in milliseconds in startFrameTime
            long startFrameTime = System.currentTimeMillis();

            if (!paused) {
                update();
            }
            // Draw the frame
            draw();

            // Calculate the fps this frame
            // We can then use the result to
            // time animations and more.
            frameTime = System.currentTimeMillis() - startFrameTime;
            if (frameTime >= 1) {
                fps = 1000 / frameTime;
            }
            win();
        }
    }

    private void update() {

        // Update the players bullet
        if (ammo.checkStatus()) {
            ammo.update(fps);
            checkCollision();
        }
        for (int i = 0; i < enemyAmmo.length; i++) {
            if (enemyAmmo[i].checkStatus()) {
                enemyAmmo[i].update(fps);
            }
            if (enemyAmmo[i].collision() > screenY) {
                enemyAmmo[i].bullet_Not_On_Screen();
            }
        }
        for(int i = 0; i < enemyAmmo.length; i++)
            if(!enemyAmmo[i].checkStatus()){
            enemyAmmo[i].shoot(screenX/10 + (rand.nextInt(8) * 100)+50, (screenY/10) + (i * 100), enemyAmmo[i].downward);
             }
        playerCollision();
        // Has the player's bullet hit the top of the screen
        if(ammo.collision() < 0){
            ammo.bullet_Not_On_Screen();
        }

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
    public void pauseMusic(){
        soundPool.autoPause();
    }
    // If SpaceInvadersActivity is started then
    // start our thread.
    public void resume() {
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }
    public void continueMusic(){
        soundPool.autoResume();
    }



    // The SurfaceView class implements onTouchListener
    // So we can override this method and detect screen touches.
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {

        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {

            // Player has touched the screen
            case MotionEvent.ACTION_DOWN:

                paused = false;

                if(motionEvent.getY() < screenY - screenY / 8) {
                    // Shots fired
                    if (ammo.shoot(MainActivity.xPos+50, screenY, ammo.upward)) {
                        soundPool.pause(tokyo);
                        soundPool.play(shooting_Sound, 1, 1, 0, 0, 1);
                        soundPool.resume(tokyo);
                    }
                }

                break;

        }
        return true;
    }
}