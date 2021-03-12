package com.example.flashcardappactual;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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
        findViewById(R.id.flashcardAnswer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.flashcardAnswer).setVisibility(View.INVISIBLE);
                findViewById(R.id.flashcardQuestion).setVisibility(View.VISIBLE);
            }
        });
        findViewById(R.id.addButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                MainActivity.this.startActivityForResult(intent,100);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100 && data != null) {
            String string1 = data.getExtras().getString("Question");
            String string2 = data.getExtras().getString("Answer");
            TextView questionView = (TextView) findViewById(R.id.flashcardQuestion);
            questionView.setText(string1);
            TextView answerView = (TextView) findViewById(R.id.flashcardAnswer);
            answerView.setText(string2);

        }
    }

}
