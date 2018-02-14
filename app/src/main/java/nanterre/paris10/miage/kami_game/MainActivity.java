package nanterre.paris10.miage.kami_game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import nanterre.paris10.miage.kami_game.stageA.SAL1Activity;

public class MainActivity extends AppCompatActivity {

    private Button btn_niveau1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Niveaux");
        btn_niveau1 = findViewById(R.id.btn_niveau1);
        btn_niveau1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SAL1Activity.class);
                startActivity(intent);
            }
        });
    }
}
