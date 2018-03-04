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
import android.widget.TextView;
import android.widget.Toast;

import nanterre.paris10.miage.kami_game.R;
import nanterre.paris10.miage.kami_game.adapter.ChoixCouleurAdapter;
import nanterre.paris10.miage.kami_game.adapter.RectAdapter;
import nanterre.paris10.miage.kami_game.util.Game;

public class GameActivity extends AppCompatActivity {
    private ImageButton img_btn_refresh;
    private ImageButton img_btn_info;
    private TextView nb_tentatives;
    private Game game;
    private int nb_coup;
    private int[] puzzle;
    private GridView gridView;
    private GridView gridButtonColor;
    private int[] diffColor = new int[]{1,2};
    private int selectColor = 0;
    private RectAdapter adapter;
    private int nb_coup_max = 1;
    private int couleurCliquer;
    private boolean winOrLose = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setTitle("Level 1");
        nb_coup = 0;
        puzzle = getPuzzle();
        game = new Game();
        game.setListColors(puzzle);
        game.setNb_coup_max(nb_coup_max);
        game.setNb_coup(0);
        img_btn_info = findViewById(R.id.btn_info);
        img_btn_refresh = findViewById(R.id.btn_refresh);
        nb_tentatives = findViewById(R.id.textView_nb_tentative);
        gridView = findViewById(R.id.GridView);
        gridButtonColor = findViewById(R.id.GridChoixCouleur);
        nb_tentatives.setText(game.getNb_coup() + "/" + game.getNb_coup_max());
        nb_tentatives.setTextSize(35);
        nb_tentatives.setTextColor(Color.BLACK);
         adapter = new RectAdapter(getApplicationContext(), puzzle);
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
                if(selectColor == game.getListColors()[i] || winOrLose){
                    // Si la couleur sélectionnée est égale à la couleur de la case à remplir ou que le joueur a déjà gagné ou perdu => on ne fait rien
                } else {
                    couleurCliquer = i;
                    GameTask task = new GameTask();
                    //task.execute();
                    boolean[] resultGame = (boolean[]) task.doInBackground(null);
                    game.setNb_coup(game.getNb_coup() + 1);
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
                selectColor = diffColor[i];
            }
        });

    }


    private void restartGame() {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    private int[] getPuzzle() {
        int[] grid = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
                1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,
                2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,
                2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2};

        return grid;
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

            return result;
        }
    }

}
