package com.example.mange.space_shooter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Loser_Screen extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loser);
        Intent i = getIntent();
        int score = i.getIntExtra("Score",0);

        final TextView textViewToChange = (TextView) findViewById(R.id.maxscore);
        textViewToChange.setText("Final SCORE:" + Integer.toString(score));

    }

    public void try_again(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
