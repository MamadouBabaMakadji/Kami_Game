package nanterre.paris10.miage.kami_game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import nanterre.paris10.miage.kami_game.stageA.SAL1Activity;
import nanterre.paris10.miage.kami_game.views.LevelAdapter;

public class LevelActivity extends AppCompatActivity {

    private GridView gridView ;
    private int[] niveaux = {1,2,3,4,5,6,7,8,9,10};
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        setTitle("Level");
        gridView = findViewById(R.id.GridView);
        // Instanciation de LevelAdapter pour l'affichage customiser des différents niveaux
        LevelAdapter adapter = new LevelAdapter(this, niveaux);
        gridView.setAdapter(adapter);
        // Permet de rediriger vers le niveau dès que l'on clique sur un item
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(getApplicationContext(), "Item : "+i, Toast.LENGTH_LONG).show();
                switch (i){
                    case 0:
                        intent = new Intent(LevelActivity.this, SAL1Activity.class);
                        intent.putExtra("Level", i);
                        startActivity(intent);
                        break;
                    case 1:
                       /* intent = new Intent(LevelActivity.this, SAL1Activity.class);
                        intent.putExtra("niveau", i+1);
                        startActivity(intent); */
                        break;
                }
            }
        });
    }
}
