package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class Einstellung extends Activity {

    private Switch switchDarkMode;
    private Switch switchMusic;
    private Switch switchAds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_einstellung);

        // Initialisiere die UI-Elemente
        switchDarkMode = findViewById(R.id.switchDarkMode);
        switchMusic = findViewById(R.id.switchMusic);
        switchAds = findViewById(R.id.switchAds);

        // Hier kannst du Listener für die Switches hinzufügen und auf Änderungen reagieren
        switchDarkMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Hier kannst du Aktionen ausführen, wenn sich der Dark Mode ändert
            }
        });

        switchMusic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Hier kannst du Aktionen ausführen, wenn sich die Musik-Einstellung ändert
            }
        });

        switchAds.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Hier kannst du Aktionen ausführen, wenn sich die Werbe-Einstellung ändert
            }
        });
    }

    // Methode, die aufgerufen wird, wenn der "Zurück"-Button geklickt wird
    public void zurueckButtonClicked(View view) {
        // Erstelle einen Intent, um zur MainActivity zurückzukehren
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish(); // Beende die aktuelle Activity (Einstellung), wenn du nicht möchtest, dass sie im Stapel bleibt.
    }
}
