package nanterre.paris10.miage.kami_game.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;

import nanterre.paris10.miage.kami_game.R;
import nanterre.paris10.miage.kami_game.data.Player;
import nanterre.paris10.miage.kami_game.data.PlayerDAO;

public class NewPlayerActivity extends AppCompatActivity {

    private Button btn_upload;
    private Button btn_save;
    private EditText champName;
    private Bitmap bitmap;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_player);
        setTitle("Add New Player");
        champName = findViewById(R.id.editText_name);
        btn_upload = findViewById(R.id.btn_upload);
        btn_save = findViewById(R.id.btn_save);

        // demande permission à l'utilisateur afin d'accéder aux images du mobile
        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ActivityCompat.checkSelfPermission(NewPlayerActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    System.out.println("Je suis dans le if de la permission");
                    ActivityCompat.requestPermissions(NewPlayerActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                }else {
                    intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*");
                    System.out.println("Je suis dans le else de la permission");
                    startActivityForResult(intent, 1);
                }
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String playerName = champName.getText().toString();
                if(playerName.length() <= 1 || playerName.equals("Name") || bitmap == null){
                    AlertDialog info = new AlertDialog.Builder(NewPlayerActivity.this).create();
                    info.setTitle("Error");
                    info.setMessage("");
                    info.setButton(AlertDialog.BUTTON_POSITIVE, "Field 'Name' or image is missing", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    info.show();
                }else {
                    PlayerDAO dao = new PlayerDAO(getApplicationContext());
                    Player player = new Player();
                    // On initialise le score à 0 pour les nouveaux joueurs
                    player.setScore(0);
                    player.setNiveau(1);
                    player.setName(playerName);
                    dao.openCnx();
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap .compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] bytes = stream.toByteArray();
                    player.setImage(bytes);
                    // On sauvegarder  les informations du nouveau joueur dans la BD
                    dao.addPlayer(player, bytes);
                    // Fermeture de la connexion
                    dao.closeCnx();
                    Intent i = new Intent(NewPlayerActivity.this, LevelActivity.class);
                    startActivity(i);
                }
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        System.out.println("Je suis dans le onRequestPermissionsResult");
        switch (requestCode) {
            case 1:
                // Si l'utilisateur donne la permission d'accéder aux contenus on continue l'action de selection d'image sinon on affiche un msg
                // pour l'informer que l'appli a besoin d'avoir la permission pour accéder aux images
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    System.out.println("Je suis dans le onRequestPermissionsResult ->>>> IF ");
                    intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*");
                    startActivityForResult(intent, 1);
                } else {
                    AlertDialog info = new AlertDialog.Builder(NewPlayerActivity.this).create();
                    info.setTitle("Permission");
                    info.setMessage("");
                    info.setButton(AlertDialog.BUTTON_POSITIVE, "App need your permission to access gallery", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    info.show();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("Je suis dans le onActivtyResult");
        Uri imageChoisi = data.getData();
        try {
            bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageChoisi);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
