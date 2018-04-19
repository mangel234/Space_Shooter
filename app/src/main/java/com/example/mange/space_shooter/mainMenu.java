package com.example.mange.space_shooter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.mange.space_shooter.Characters.Blue;

/**
 * Created by alpha on 4/18/18.
 */

public class mainMenu extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        Button newGame = (Button)(findViewById(R.id.new_game));
        newGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                beginNew();
            }
        });
    }
    public void beginNew() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
