package nanterre.paris10.miage.kami_game.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import nanterre.paris10.miage.kami_game.R;

public class MainActivity extends AppCompatActivity {

    private ImageButton img_btn ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Puzzle Game");
        img_btn = findViewById(R.id.imageButton);
        // Redirection vers l'activité de choix du niveau après avoir cliquer sur l'image Button
        img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LevelActivity.class);
                startActivity(intent);
            }
        });
    }
}
