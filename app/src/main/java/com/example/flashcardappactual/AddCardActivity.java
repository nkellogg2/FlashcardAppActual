package com.example.flashcardappactual;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);
        findViewById(R.id.cancelButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.saveButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newQuestion = ((EditText) findViewById(R.id.newQuestionField)).getText().toString();
                String newAnswer = ((EditText) findViewById(R.id.newAnswerField)).getText().toString();
                Intent data = new Intent();
                data.putExtra("Question",newQuestion);
                data.putExtra("Answer",newAnswer);
                setResult(RESULT_OK, data);
                finish();
            }
        });
    }
}