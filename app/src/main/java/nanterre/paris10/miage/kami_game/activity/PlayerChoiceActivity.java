package nanterre.paris10.miage.kami_game.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
    private List<Player> players;
    private TaskGestionPlayer gestionPlayer;
    private PlayerAdapter adapter;
    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_choice);
        setTitle("Players");
        btn_newPlayer = findViewById(R.id.btn_newPlayer);
        listView = findViewById(R.id.player_listView);
        dao = new PlayerDAO(this);
       // Appel à l'asyncTask pour le chargement des joueurs
        gestionPlayer = new TaskGestionPlayer();
        gestionPlayer.onPreExecute();
        // Init adapter pour l'affichage
        adapter = new PlayerAdapter(this, players);
        listView.setAdapter(adapter);
        // Supprimer un joueur après un Long click
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                AlertDialog builder = new AlertDialog.Builder(PlayerChoiceActivity.this).create();
                builder.setTitle("Delete player");
                builder.setMessage("Are you sure you want to delete this player ?");
                builder.setButton(AlertDialog.BUTTON_NEGATIVE, "YES", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                player = (Player) adapter.getItem(i);
                                // Suppression du joueur dans la base de données dans un autre Thread
                                gestionPlayer.doInBackground(new Object[]{player});
                                adapter.notifyDataSetChanged();
                            }
                        });
                builder.setButton(AlertDialog.BUTTON_NEUTRAL, "CANCEL", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // On ne fait rien si l'utilisateur ne continue pas l'action
                            }
                        });
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.show();

                return false;
            }
        });
        // On dirige l'utilisateur vers la page de saisie des informations du nouveau joueur si il clique sur le New Player
        btn_newPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlayerChoiceActivity.this, NewPlayerActivity.class);
                startActivity(intent);
            }
        });
        // On dirige l'utilisateur vers le choix des niveaux pour commencer les parties
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                player = (Player) adapter.getItem(i);
                Intent intent = new Intent(PlayerChoiceActivity.this, LevelActivity.class);
                intent.putExtra("playerID", player.get_id());
                startActivity(intent);
            }
        });
    }

    private class TaskGestionPlayer extends AsyncTask {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // connexion à la base de données
            dao.openCnx();
            // Récupérations des joueurs dans la base de données
            players = dao.getAllPlayers();
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            // Suppression du joueur
            dao.deletePlayer((Player) objects[0]);
            players.remove((Player) objects[0]);

            return null;
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            // Fermeture de la connexion à la base de données
            dao.closeCnx();
        }
    }
}
