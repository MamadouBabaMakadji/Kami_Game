package nanterre.paris10.miage.kami_game.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import nanterre.paris10.miage.kami_game.R;
import nanterre.paris10.miage.kami_game.adapter.ChoixCouleurAdapter;
import nanterre.paris10.miage.kami_game.adapter.RectAdapter;
import nanterre.paris10.miage.kami_game.data.Player;
import nanterre.paris10.miage.kami_game.data.PlayerDAO;
import nanterre.paris10.miage.kami_game.util.Game;
import nanterre.paris10.miage.kami_game.util.XmlReader;

public class GameActivity extends AppCompatActivity {
    private ImageButton img_btn_refresh;
    private ImageButton img_btn_info;
    private TextView nb_tentatives;
    private Game game;
    private GridView gridView;
    private GridView gridButtonColor;
    private ArrayList<Integer> diffColor;
    private int selectColor = 0;
    private RectAdapter adapter;
    private int couleurCliquer;
    private boolean winOrLose = false;
    private long playerID;
    private PlayerDAO dao;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        playerID = getIntent().getLongExtra("playerID", 404);
        game = new Game();
        game.setNiveau(getIntent().getIntExtra("Level", 404));
        setTitle("Level " + game.getNiveau());
        // Exécution de l'asyntask pour le remplissage des données dépuis le fichier XML
        LoadDataTask loadDataTask = new LoadDataTask();
        loadDataTask.doInBackground(null);
        // On initialise le nombre de coups à 0
        game.setNb_coup(0);
        game.setListColors(game.getListColors());
        game.setNb_coup(0);
        img_btn_info = findViewById(R.id.btn_info);
        ImageButton btn_home = findViewById(R.id.home);
        ImageButton btn_users = findViewById(R.id.user);
        img_btn_refresh = findViewById(R.id.btn_refresh);
        nb_tentatives = findViewById(R.id.textView_nb_tentative);
        gridView = findViewById(R.id.GridView);
        gridButtonColor = findViewById(R.id.GridChoixCouleur);
        nb_tentatives.setText(game.getNb_coup() + "/" + game.getNb_coup_max());
        nb_tentatives.setTextSize(35);
        nb_tentatives.setTextColor(Color.BLACK);
        adapter = new RectAdapter(getApplicationContext(), game.getListColors());
        ChoixCouleurAdapter adapter2 = new ChoixCouleurAdapter(getApplicationContext(), diffColor);
        gridView.setAdapter(adapter);
        gridButtonColor.setAdapter(adapter2);
        img_btn_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restartGame();
            }
        });
        img_btn_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog info = new AlertDialog.Builder(GameActivity.this).create();
                info.setTitle("About Game");
                info.setMessage("");
                info.setButton(AlertDialog.BUTTON_POSITIVE, "The aim of this game is to have the same color in all surface", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                info.show();
            }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(selectColor == 0) {
                    Toast.makeText(getApplicationContext(), "Veuillez choisir la couleur de remplissage en premier", Toast.LENGTH_LONG).show();
                }
                if(selectColor == 0 || selectColor == game.getListColors()[i] || winOrLose){
                    // Si la couleur sélectionnée est égale à la couleur de la case à remplir ou que le joueur a déjà gagné ou perdu => on ne fait rien
                } else {
                    couleurCliquer = i;
                    game.setNb_coup(game.getNb_coup() + 1);
                    GameTask task = new GameTask();
                    //task.execute();
                    boolean[] resultGame = (boolean[]) task.doInBackground(null);
                    nb_tentatives.setText(game.getNb_coup() + "/" + game.getNb_coup_max());
                    adapter.notifyDataSetChanged();
                    if(resultGame[0]){
                        Toast.makeText(getApplicationContext(), "You Win !", Toast.LENGTH_LONG).show();
                        winOrLose = true;
                    }
                    if(resultGame[1]){
                        Toast.makeText(getApplicationContext(), "You Lose !", Toast.LENGTH_LONG).show();
                        winOrLose = true;
                    }
                }
            }
        });

        gridButtonColor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectColor = diffColor.get(i);
            }
        });
        // Retour à la page d'accueil en cliquant sur le bouton Home
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(GameActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        // Affichage des players au clic sur le bouton users
        btn_users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 intent = new Intent(GameActivity.this, PlayerChoiceActivity.class);
                 startActivity(intent);
            }
        });

    }

    // Recommencer la partie
    private void restartGame() {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("Level", game.getNiveau());
        intent.putExtra("playerID", playerID);
        startActivity(intent);
    }


    // Definition d'une asyncTask pour effectuer les opérations consommant énormémant de ressources dans un nouveau Thread
    private class GameTask extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {
            // Mettre à jour le puzzle
            game.changeColor(couleurCliquer, selectColor);
            boolean[] result = new boolean[2];
            // premiere case du tableau permet de savoir si le joueur a gagné ou pas
            // deuxième case du tableau permet de savoir si le joueur a perdu ou pas
            result[0] = game.isWin();
            result[1] = game.isLose(result[0]);
            // Si le joueur gagne on augmente le niveau et le score
            if (playerID != 404 && result[0]){
                dao = new PlayerDAO(getApplicationContext());
                Player player = dao.getPlayer(playerID);
                // On ne modifie le score que si seulement si le niveau actuel du joueur est <= au niveau
                // ceci afin d'éviter d'augmenter le score à chaque fois que le joueur joue et gagne le même niveau
                if(player.getNiveau() <= game.getNiveau())
                    dao.updateScore_Level(playerID);
            }

            return result;
        }
    }

    // Le but de ce asyncTask est de charger les contenus du puzzle (Coulours, numColors ...) dépuis les fichiers XML
    private class LoadDataTask extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                InputStream inputStream = getAssets().open("StageA/SAL" + game.getNiveau() +".xml");
                HashMap<String, String> contains = XmlReader.getXmlContains(inputStream);
                // Initialisation du jeu avec le contenu du fichier XML
                game.setListColors(convertString(contains.get("colours")));
                game.setNb_coup_max(Integer.valueOf(contains.get("NombreTentatives")));
                game.setDiffCouleur(Integer.valueOf(contains.get("numColours")));
                diffColor = game.diffColorValue();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        // Transforme la chaine de couleurs en tableau de int
        public int[] convertString(String colors){
            int[] res = new int[colors.length()];
            for (int i = 0; i < res.length; i++){
                res[i] = Integer.parseInt(String.valueOf(Character.getNumericValue(colors.charAt(i))));
            }

            return res;
        }
    }

}
