package com.example.wordlist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WordBook extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        Button startBtn = (Button)findViewById(R.id.startBtn);
        Button quizBtn = (Button)findViewById(R.id.quizBtn);
        Button favoriteBtn = (Button)findViewById(R.id.favoriteBtn);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WordListtest.class);
                startActivity(intent);
            }
        });

        quizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        favoriteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Favorite.class);
                startActivity(intent);
            }
        });
    }
}
