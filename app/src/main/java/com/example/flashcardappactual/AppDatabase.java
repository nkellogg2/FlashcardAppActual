package com.example.flashcardappactual;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {com.example.flashcardappactual.Flashcard.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {
    public abstract com.example.flashcardappactual.FlashcardDao flashcardDao();
}
