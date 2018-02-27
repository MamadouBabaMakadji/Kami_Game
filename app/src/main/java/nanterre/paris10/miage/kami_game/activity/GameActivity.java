package nanterre.paris10.miage.kami_game.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashSet;

import nanterre.paris10.miage.kami_game.R;
import nanterre.paris10.miage.kami_game.views.ButtonView;
import nanterre.paris10.miage.kami_game.views.PuzzleView;

public class GameActivity extends AppCompatActivity {
    private PuzzleView puzzleView;
    private ImageButton img_btn_refresh;
    private ImageButton img_btn_info;
    private TextView nb_tentatives;
    private int[] puzzle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setTitle("Level 1");
        img_btn_info = findViewById(R.id.btn_info);
        img_btn_refresh = findViewById(R.id.btn_refresh);
        nb_tentatives = findViewById(R.id.textView_nb_tentative);
        nb_tentatives.setText("0/1");
        nb_tentatives.setTextSize(35);
        nb_tentatives.setTextColor(Color.BLACK);
        puzzle = getPuzzle();
        puzzleView = new PuzzleView(this, puzzle);
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
        afficherPuzzle();
        afficherButtons();
    }

    private void restartGame() {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    private int[] getPuzzle() {
        int[] grid = {1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,1,1,1,1,1,1,1,1,
                2,2,2,2,2,2,2,2,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,1,
                1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,1,1,1,1,1,1,1,1,2,
                2,2,2,2,2,2,2,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2};

        return grid;
    }

    // Retourne la liste des différentes couleurs
    private HashSet<Integer> getDifferentColors(){
        HashSet<Integer> result = new HashSet<>();
        puzzle = getPuzzle();
        for(int i = 0; i < puzzle.length; i++){
            result.add(puzzle[i]);
        }
        return result;
    }

    // Cette fonction permet d'inialiaser le layout en mettant les différents buttons correspondant au puzzle
    private void afficherButtons(){
        LinearLayout btn_layout = findViewById(R.id.buttonLayout);
        btn_layout.removeAllViews();
        //for (int color : getDifferentColors()){
            ButtonView buttonView = new ButtonView(this);
            buttonView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: Changer la couleur du puzzle avec le button selectionné
                }
            });
            buttonView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.MATCH_PARENT, 1.0f));
            buttonView.setPadding(1, 1, 1, 1);

                btn_layout.addView(buttonView);
        //}
    }

    private void afficherPuzzle() {
        final LinearLayout gridPuzzle = findViewById(R.id.puzzleGridView);
        gridPuzzle.removeAllViews();
        GridView gridView = gridPuzzle.findViewById(R.id.GridView);
        gridPuzzle.addView(puzzleView);
    }

}
