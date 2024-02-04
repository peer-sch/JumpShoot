package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);

        Button buttonLogin = findViewById(R.id.buttonLogin);
        Button buttonRegister = findViewById(R.id.buttonRegister);

        DB = new DBHelper(this);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Implementiere die Login-Logik hier
                String user = editTextUsername.getText().toString();
                String pass = editTextPassword.getText().toString();

                if (user.equals(" ") || pass.equals(" ")) {
                    Toast.makeText(LoginActivity.this, "Bitte alle Felder Ausfüllen", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkuserpass = DB.checkusernamePassword(user, pass);
                    if (checkuserpass) {
                        // Benutzer erfolgreich eingeloggt
                        Toast.makeText(LoginActivity.this, "Login Erfolgreich", Toast.LENGTH_SHORT).show();

                        // Benutzernamen in den SharedPreferences speichern
                        SharedPreferences preferences = getSharedPreferences("USER_PREFERENCES", MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("username", user);
                        editor.apply();

                    } else {
                        Toast.makeText(LoginActivity.this, "Falsche Einlogg Daten", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Implementiere die Registrierungs-Logik hier
                String user = editTextUsername.getText().toString();
                String pass = editTextPassword.getText().toString();

                if (user.equals(" ") || pass.equals(" ")) {
                    Toast.makeText(LoginActivity.this, "Bitte alle Felder Ausfüllen", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkuser = DB.checkusername(user);
                    if (!checkuser) {
                        Boolean insert = DB.insertData(user, pass);
                        if (insert) {
                            Toast.makeText(LoginActivity.this, "Erfolgreich ein Account erstellt", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, "Fehler beim Login", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "Nutzer schon im System, bitte anderen Namen eingeben", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void zurueckButtonClicked5(View view) {
        // Erstelle einen Intent, um zur MainActivity zurückzukehren
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish(); // Beende die aktuelle Activity (Einstellung), wenn du nicht möchtest, dass sie im Stapel bleibt.
    }
}
