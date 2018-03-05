package nanterre.paris10.miage.kami_game.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.io.IOException;

import nanterre.paris10.miage.kami_game.R;
import nanterre.paris10.miage.kami_game.adapter.LevelAdapter;

public class LevelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        setTitle("Levels");
        GridView gridView = findViewById(R.id.GridView);
        String[] levels = null;
        try {
            levels = getAssets().list("StageA");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Instanciation de LevelAdapter pour l'affichage customiser des différents niveaux
        LevelAdapter adapter = new LevelAdapter(this, levels);
        gridView.setAdapter(adapter);
        // Permet de rediriger vers le puzzle correspondant au niveau choisis
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(LevelActivity.this, GameActivity.class);
                intent.putExtra("Level", i + 1);
                startActivity(intent);
            }
        });
    }
}
