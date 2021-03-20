package com.example.flashcardappactual;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    FlashcardDatabase flashcardDatabase;
    List<Flashcard> allFlashcards;
    int currentCardIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flashcardDatabase =  new FlashcardDatabase(getApplicationContext());
        allFlashcards = flashcardDatabase.getAllCards();

        if(allFlashcards != null && allFlashcards.size() > 0) {
            TextView question = (TextView) findViewById(R.id.flashcardQuestion);
            question.setText(allFlashcards.get(0).getQuestion());

            TextView answer = (TextView) findViewById(R.id.flashcardAnswer);
            answer.setText(allFlashcards.get(0).getAnswer());
        }

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
        findViewById(R.id.nextButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (allFlashcards.size() > currentCardIndex) {
                    if (currentCardIndex == allFlashcards.size() - 1) {
                        currentCardIndex = -1;
                    }
                    currentCardIndex++;
                    ((TextView) findViewById(R.id.flashcardQuestion)).setText(allFlashcards.get(currentCardIndex).getQuestion());
                    ((TextView) findViewById(R.id.flashcardAnswer)).setText(allFlashcards.get(currentCardIndex).getAnswer());

                }
            }
        });
        findViewById(R.id.trashButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            flashcardDatabase.deleteCard(((TextView) findViewById(R.id.flashcardQuestion)).getText().toString());
            allFlashcards = flashcardDatabase.getAllCards();
            if (currentCardIndex > allFlashcards.size() -1) {
                currentCardIndex = 0;
            }
            if (allFlashcards.size() == 0) {
                ((TextView) findViewById(R.id.flashcardQuestion)).setText("Question goes here!");
                ((TextView) findViewById(R.id.flashcardAnswer)).setText("Answer goes here!");
            }
            else {
                ((TextView) findViewById(R.id.flashcardQuestion)).setText(allFlashcards.get(currentCardIndex).getQuestion());
                ((TextView) findViewById(R.id.flashcardAnswer)).setText(allFlashcards.get(currentCardIndex).getAnswer());


            }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && data != null) {
            String question = data.getExtras().getString("Question");
            String answer = data.getExtras().getString("Answer");

            flashcardDatabase.insertCard(new Flashcard(question, answer));
            allFlashcards = flashcardDatabase.getAllCards();

            TextView questionView = (TextView) findViewById(R.id.flashcardQuestion);
            questionView.setText(question);
            TextView answerView = (TextView) findViewById(R.id.flashcardAnswer);
            answerView.setText(answer);

        }
    }

}
