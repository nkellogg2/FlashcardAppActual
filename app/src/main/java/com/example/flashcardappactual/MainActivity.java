package com.example.flashcardappactual;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.flashcardQuestion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.flashcardQuestion).setVisibility(View.INVISIBLE);
                findViewById(R.id.flashcardAnswer).setVisibility(View.VISIBLE);
            }
        });
    }

}
