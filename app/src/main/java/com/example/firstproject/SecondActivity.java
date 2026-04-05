package com.example.firstproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);

        Button shareSubwaySurf = findViewById(R.id.btnSubwaySurf);
        shareSubwaySurf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                composeEmail("Subway Surfers", "https://play.google.com/store/apps/details?id=com.kiloo.subwaysurf&hl=pt_BR&pli=1");
            }
        });

        Button shareTft = findViewById(R.id.btnTft);
        shareTft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                composeEmail("TFT", "http://play.google.com/store/apps/details?id=com.riotgames.league.teamfighttactics&hl=pt_BR&pli=1");
            }
        });

        Button sharePou = findViewById(R.id.btnPou);
        sharePou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                composeEmail("POU", "https://play.google.com/store/apps/details?id=me.pou.app&hl=pt_BR");
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void composeEmail(String nomeJogo, String link) {
        String[] destinatarios = {};
        String assunto = "Jogue "  + nomeJogo;
        String txt = "Jogue esse jogo " + nomeJogo + ", segue o link: " + link;
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // Only email apps handle this.

        intent.putExtra(Intent.EXTRA_EMAIL, destinatarios);
        intent.putExtra(Intent.EXTRA_SUBJECT, assunto);
        intent.putExtra(Intent.EXTRA_TEXT, txt);
        try {
            startActivity(intent);
        } catch (android.content.ActivityNotFoundException e) {
            View view = findViewById(R.id.main);
            Snackbar.make(view, "Nenhum aplicativo de e-mail encontrado.", Snackbar.LENGTH_SHORT).show();
        }
    }
}