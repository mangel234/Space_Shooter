package com.example.mange.space_shooter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.mange.space_shooter.Characters.Blue;

/**
 * Created by alpha on 4/18/18.
 * Modified by mangel234 4/19/2018
 */

public class mainMenu extends AppCompatActivity {



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

    }

    /**
     *  Modified this so we are not creating a button everytime on create
     *
     *  When the New Game Button is clicked it will start a new Intent
     */

    public void beginNew(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
