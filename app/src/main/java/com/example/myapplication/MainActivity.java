package com.example.myapplication;

import static com.example.myapplication.GameEngine.gameView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity {

    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Erstelle eine Instanz von GameView und setze sie als gameView in GameEngine
        gameView = new GameView(this);
        GameEngine.gameView = gameView;

        // Führe die Initialisierung durch
        AppConstants.initialization(this.getApplicationContext(), gameView);

        // Benutzernamen aus den SharedPreferences abrufen und in das TextView einfügen
        SharedPreferences userPreferences = getSharedPreferences("USER_PREFERENCES", MODE_PRIVATE);
        String username = userPreferences.getString("username", "");
        TextView loginNameTextView = findViewById(R.id.loginName);
        loginNameTextView.setText(username);

    }

    public void startGame(View view) {
        Intent intent = new Intent(this, Spielen.class);
        startActivity(intent);

        // Speichere in den SharedPreferences, dass das Spiel gestartet wurde
        SharedPreferences preferences = getSharedPreferences("GAME_PREFERENCES", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isGameStarted", true);
        editor.apply();
    }

    public void scoreV(View view) {
        Intent intent = new Intent(this, Score.class);
        startActivity(intent);
    }

    public void startEinstellung(View view) {
        Intent intent = new Intent(this, Einstellung.class);
        startActivity(intent);
    }

    public void startLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
