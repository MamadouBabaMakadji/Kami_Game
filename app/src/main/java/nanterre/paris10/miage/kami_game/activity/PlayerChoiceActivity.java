package nanterre.paris10.miage.kami_game.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import nanterre.paris10.miage.kami_game.R;
import nanterre.paris10.miage.kami_game.adapter.PlayerAdapter;
import nanterre.paris10.miage.kami_game.data.Player;
import nanterre.paris10.miage.kami_game.data.PlayerDAO;

public class PlayerChoiceActivity extends AppCompatActivity {

    private Button btn_newPlayer;
    private ListView listView;
    private PlayerDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_choice);
        setTitle("Players");
        btn_newPlayer = findViewById(R.id.btn_newPlayer);
        listView = findViewById(R.id.player_listView);
        dao = new PlayerDAO(this);
        // connexion à la base de données
        dao.openCnx();
        // Récupérations des joueurs dans la base de données
        List<Player> players = dao.getAllPlayers();
        // Init adapter pour l'affichage
        PlayerAdapter adapter = new PlayerAdapter(this, players);
        listView.setAdapter(adapter);
        dao.closeCnx();
        // On dirige l'utilisateur vers la page de saisie des informations du nouveau joueur si il clique sur le New Player
        btn_newPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlayerChoiceActivity.this, NewPlayerActivity.class);
                startActivity(intent);
            }
        });
    }
}
