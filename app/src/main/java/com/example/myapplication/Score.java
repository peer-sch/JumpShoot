package com.example.myapplication;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Score extends Activity {

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        dbHelper = new DBHelper(this);

        // Fülle die Liste mit Daten aus der Datenbank
        List<String> scoreList = getScoreData();

        // Verknüpfe die ListView mit der Datenliste
        ListView scoreListView = findViewById(R.id.scoreListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, scoreList);
        scoreListView.setAdapter(adapter);
    }

    private List<String> getScoreData() {
        List<String> scoreList = new ArrayList<>();

        // Rufe die Daten aus der Datenbank ab
        SQLiteDatabase MyDB = dbHelper.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM scores ORDER BY score DESC LIMIT 10", null);

        if (cursor.moveToFirst()) {
            do {
                String username = cursor.getString(cursor.getColumnIndex("username"));
                int score = cursor.getInt(cursor.getColumnIndex("score"));
                scoreList.add(username + ": " + score);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return scoreList;
    }

    public void zurueckButtonClicked3(View view) {
        // Erstelle einen Intent, um zur MainActivity zurückzukehren
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish(); // Beende die aktuelle Activity (Einstellung), wenn du nicht möchtest, dass sie im Stapel bleibt.
    }
}
