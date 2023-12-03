package com.example.myapplication;
import static com.example.myapplication.GameEngine.gameView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.AppConstants;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppConstants.initialization(this.getApplicationContext(), gameView);
    }

    public void startGame(View view){
        Intent intent = new Intent(this, Spielen.class);
        startActivity(intent);

        // Speichere in den SharedPreferences, dass das Spiel gestartet wurde
        SharedPreferences preferences = getSharedPreferences("GAME_PREFERENCES", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isGameStarted", true);
        editor.apply();
    }

    public void scoreV(View view){
        Intent intent = new Intent(this, Score.class);
        startActivity(intent);
    }
    public void startEinstellung(View view){
        Intent intent = new Intent(this, Einstellung.class);
        startActivity(intent);
    }
}
