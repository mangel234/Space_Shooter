package com.example.mange.space_shooter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Loser_Screen extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loser);
        Intent i = getIntent();
        //String score = i.getIntExtra("Score",0);
    }

    public void try_again(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
