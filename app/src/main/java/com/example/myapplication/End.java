package com.example.myapplication;

import static androidx.core.content.ContextCompat.startActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class End extends Activity {
//End Screen wenn das spiel vorbei ist (Geht noch nicht)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
    }

    public void zurueckButtonClicked3(View view) {
        // Erstelle einen Intent, um zur MainActivity zurückzukehren
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish(); // Beende die aktuelle Activity (Einstellung), wenn du nicht möchtest, dass sie im Stapel bleibt.
    }
}
