package nanterre.paris10.miage.kami_game.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.io.IOException;

import nanterre.paris10.miage.kami_game.R;
import nanterre.paris10.miage.kami_game.adapter.LevelAdapter;
import nanterre.paris10.miage.kami_game.data.Player;
import nanterre.paris10.miage.kami_game.data.PlayerDAO;

public class LevelActivity extends AppCompatActivity {

    private long playerID;
    private PlayerDAO dao;
    private Player player;
    private String[] levels = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        setTitle("Levels");
        GridView gridView = findViewById(R.id.GridView);

        playerID = getIntent().getLongExtra("playerID", 404);
        // On vérifie si on est pas en mode Aventure où un joueur ne peut choisir un niveau que si il a déjà réussi le niveau précédent
        // Sinon si on est en mode exploration, l'utilisateur pourra choisir tous les niveaux disponible
        if(playerID == 404){
            try {
                levels = getAssets().list("StageA");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            // On récupère les niveaux auquels il peut jouer dans la BD via une asyncTask
            TaskRecupUser taskRecupUser =new TaskRecupUser();
            taskRecupUser.doInBackground(null);
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
                intent.putExtra("playerID", playerID);
                startActivity(intent);
            }
        });
    }

    private class TaskRecupUser extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {
            dao = new PlayerDAO(getApplicationContext());
            player = dao.getPlayer(playerID);
            levels = new String[player.getNiveau()];

            return null;
        }

    }
}
