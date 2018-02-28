package nanterre.paris10.miage.kami_game.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import nanterre.paris10.miage.kami_game.R;
import nanterre.paris10.miage.kami_game.data.Player;

public class MainActivity extends AppCompatActivity {

    private ImageButton img_btn ;
    private Button btn_exploration;
    private Button btn_aventure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Puzzle Game");
        img_btn = findViewById(R.id.imageButton);
        btn_exploration = findViewById(R.id.btn_exploration);
        btn_aventure = findViewById(R.id.btn_aventure);
        // Redirection vers l'activité de choix du niveau après avoir cliquer sur l'image Button
        img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LevelActivity.class);
                startActivity(intent);
            }
        });
        // Le mode exploration permet de jouer en mode libre c'est à dire tester tous les niveaux
        btn_exploration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LevelActivity.class);
                startActivity(intent);
            }
        });
        // Le mode aventure nécessite de créer un nouveau joueur ou de continuer la partie d'un joueur
        btn_aventure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PlayerChoiceActivity.class);
                startActivity(intent);
            }
        });
    }
}
