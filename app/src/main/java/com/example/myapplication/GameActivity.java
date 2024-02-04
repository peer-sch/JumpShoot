package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.constraintlayout.widget.ConstraintLayout;

public class GameActivity extends Activity {
//InGame View um einen Zurückbutton zuhaben um das Spiel zu beenden
    private GameView gameView;

    private Button zuruckButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Initialisiere die GameView
        gameView = new GameView(this);

        // Finde das übergeordnete ConstraintLayout
        ConstraintLayout mainLayout = findViewById(R.id.activity_game);

        // Setze die Layout-Parameter für die GameView
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.MATCH_PARENT
        );

        // Füge die GameView zum ConstraintLayout hinzu
        mainLayout.addView(gameView, 0, params);

        // Setze den OnClickListener für den Zurück-Button
        zuruckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Starte die MainActivity und beende die GameActivity
                Intent intent = new Intent(GameActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Füge den Zurück-Button zum ConstraintLayout hinzu
        mainLayout.addView(zuruckButton);
    }

    public void zurueckButtonClicked2(View view) {
        // Erstelle einen Intent, um zur MainActivity zurückzukehren
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish(); // Beende die aktuelle Activity
    }

    private void startGame() {

    }
}

