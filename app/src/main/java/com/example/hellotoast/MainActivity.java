package com.example.hellotoast;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int compteur = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvCompteur = findViewById(R.id.tvCompteur);
        Button btnToast = findViewById(R.id.btnToast);
        Button btnIncrementer = findViewById(R.id.btnIncrementer);

        btnToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Hi! Toast :)", Toast.LENGTH_SHORT).show();
            }
        });

        btnIncrementer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compteur++;
                tvCompteur.setText("Compteur : " + compteur);
            }
        });
    }
}