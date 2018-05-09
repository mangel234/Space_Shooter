package com.example.mange.space_shooter;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.widget.Toast;

public class MainActivity extends Activity implements SensorEventListener{
    private SensorManager senseManagement; //= (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    private Sensor sense;// = senseManagement.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    private int playerState;
    private SpaceshipView customView;
    private BoardConfiguration board;
    SpaceshipView spaceshipView;
    private float gravity;
    private float linear_acceleration;
    private float currentstate;


    public static float xPos, xAccel, xVel = 0.0f;
    public static float yPos;
    public static  float xMax, yMax;
    private SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        xMax = (float) size.x - 100;
        yMax = (float) size.y - 100;

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //Initialize gameView
        spaceshipView = new SpaceshipView(this, size.x,size.y);
        setContentView(spaceshipView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onStop() {
        sensorManager.unregisterListener(this);
        super.onStop();
    }


    @Override
    protected void onResume() {
        super.onResume();

        // Tell the gameView resume method to execute
        spaceshipView.resume();
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME);

    }

    // This method executes when the player quits the game
    @Override
    protected void onPause() {
        super.onPause();

        // Tell the gameView pause method to execute
       spaceshipView.pause();
        sensorManager.unregisterListener(this);
    }
    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
        //No calibration Needed
    }
    float prev;
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            xAccel = sensorEvent.values[0];//Retrieves tilt of the phone
            updateShip();
        }
    }

    private void updateShip() {
        float frameTime = 0.666f;
        if((xAccel > 0.1 && prev < -0.1) || (xAccel < -0.1 && prev > 0.1))//Resets velocity when tilt is changed
            xVel = 0;
        xVel += (xAccel * frameTime);//increases speed when tilted in same direction
        float xS = (xVel / 2) * frameTime;

        xPos -= xS;

        if (xPos > xMax) {//should catch when we go off the side of the screen
            xPos = xMax;
        } else if (xPos < 0) {
            xPos = 0;
        }
        prev = xAccel;
    }
    private void toast(String msg) {
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 0, 170);
        toast.show();
    }
}
