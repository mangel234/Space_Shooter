package com.example.mange.space_shooter;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    private SensorManager senseManagement; //= (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    private Sensor sense;// = senseManagement.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    private float gravity;
    private float linear_acceleration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activateSensors();
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
        toast("activated");
    }

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do something here if sensor accuracy changes.
    }


    @Override
    public void onSensorChanged(SensorEvent event){
        // In this example, alpha is calculated as t / (t + dT),
        // where t is the low-pass filter's time-constant and
        // dT is the event delivery rate.

        final float alpha = 0.8f;

        // Isolate the force of gravity with the low-pass filter.
        gravity = alpha * gravity + (1 - alpha) * event.values[0];
        // Remove the gravity contribution with the high-pass filter.
        linear_acceleration = event.values[0] - gravity;
        if(linear_acceleration > 0.1)
            toast("device tilted to the left");
        if(linear_acceleration < -0.1)
            toast("device tilted to the right");

    }

    private void toast(String msg) {
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 0, 170);
        toast.show();
    }
}
