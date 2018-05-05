package com.example.mange.space_shooter;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.example.mange.space_shooter.Characters.Pink;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    private SensorManager senseManagement; //= (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    private Sensor sense;// = senseManagement.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    private int playerState;
    private Space customView;
    private BoardConfiguration board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customView = findViewById(R.id.area);
        activateSensors();
       init();
    }
    private void init(){
        board = customView.board;

    }

    @Override
    protected void onResume() {
        super.onResume();
        senseManagement.registerListener(this, sense, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        senseManagement.unregisterListener(this);
    }

    private void activateSensors(){
        senseManagement = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sense = senseManagement.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        playerState = 0;
    }

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
        //No calibration Needed
    }


    @Override
    public void onSensorChanged(SensorEvent event){
        if(event.values[0] > 0.8) playerState = 1;
        else if(event.values[0]  < -0.8 ) playerState = 2;
        else    playerState = 0;
    }

    private void toast(String msg) {
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 0, 170);
        toast.show();
    }

    public class playerThread extends Thread{
        @Override
        public void run() {
            try{sleep(1000);}
            catch(InterruptedException e){toast("Player has become unresponsive");}
            //add method for player to move
        }
    }
}
