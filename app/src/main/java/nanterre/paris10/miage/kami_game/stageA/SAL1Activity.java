package nanterre.paris10.miage.kami_game.stageA;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;

import nanterre.paris10.miage.kami_game.R;
import nanterre.paris10.miage.kami_game.views.ButtonView;
import nanterre.paris10.miage.kami_game.views.PuzzleView;

public class SAL1Activity extends AppCompatActivity {
    private PuzzleView puzzleView;
    private ImageButton img_btn_refresh;
    private ImageButton img_btn_info;
    private TextView nb_tentatives;
    private int[] puzzle;
    private PuzzleView[] puzzle_rect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sal1);
        setTitle("Level 1");
        img_btn_info = findViewById(R.id.btn_info);
        img_btn_refresh = findViewById(R.id.btn_refresh);
        nb_tentatives = findViewById(R.id.textView_nb_tentative);
        nb_tentatives.setText("0/1");
        nb_tentatives.setTextSize(35);
        nb_tentatives.setTextColor(Color.BLACK);
        puzzle = getPuzzle();
        this.puzzleView = new PuzzleView(this, puzzle);
        afficherPuzzle();
        afficherButtons();
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
        for (int color : getDifferentColors()){
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
        }
    }

    private void afficherPuzzle() {
        LinearLayout gridPuzzle = findViewById(R.id.puzzleGridView);
        gridPuzzle.removeAllViews();
        gridPuzzle.addView(puzzleView);
    }

}
