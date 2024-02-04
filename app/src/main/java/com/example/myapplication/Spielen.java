package com.example.myapplication;

import static com.example.myapplication.GameEngine.gameView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

public class Spielen extends Activity {

    private TextView scoreTextView;
    private GameEngine gameEngine;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Finde den Start-Button
        Button startButton = findViewById(R.id.startButton);

        gameEngine = new GameEngine(gameView);
        gameEngine.setSpielenActivity(this);

        // Setze den OnClickListener für den Start-Button
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mache den Start-Button unsichtbar
                startButton.setVisibility(View.INVISIBLE);

                // Lade die neue XML-Layout-Datei für die GameView
                setContentView(R.layout.activity_ingame);

                // Initialisiere die Score-Anzeige
                scoreTextView = findViewById(R.id.scoreTextView);
                GameEngine.updateScore(1);
                updateScoreDisplay();

                // Initialisiere die GameView für die neue Layout-Datei
                ConstraintLayout mainLayout = findViewById(R.id.activity_ingame);
                ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(
                        ConstraintLayout.LayoutParams.MATCH_PARENT,
                        ConstraintLayout.LayoutParams.MATCH_PARENT
                );
                mainLayout.addView(gameView, 0, params);
            }
        });
    }

    // Methode zur Aktualisierung der Score-Anzeige
    public void updateScoreDisplay() {

        TextView scoreTextView = findViewById(R.id.scoreTextView);
        scoreTextView.setText("Score: " + gameEngine.getScore());

    }

    public void spielen(View view) {
        // Entferne die finish-Methode hier
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
        finish();
    }

    public void zurueckButtonClicked2(View view) {
        // Erstelle einen Intent, um zur MainActivity zurückzukehren
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish(); // Beende die aktuelle Activity
    }
}
