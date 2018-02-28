package nanterre.paris10.miage.kami_game.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MAKADJI Mamadou Baba on 28/02/2018.
 */
// Player Data Access Object
public class PlayerDAO {
    private SQLiteDatabase sqLiteDatabase;
    private MydataBase mydataBase;
    private String[] colums = {MydataBase.COlUMN_ID, MydataBase.COlUMN_NAME, MydataBase.COlUMN_IMAGE, MydataBase.COlUMN_LEVEL, MydataBase.COLUMN_SCORE};
    private byte[] bytes;

    public PlayerDAO(Context context){
        mydataBase = new MydataBase(context);
    }

    public void openCnx(){
        sqLiteDatabase = mydataBase.getWritableDatabase();
    }

    public void closeCnx() {
        mydataBase.close();
    }

    public long addPlayer(Player player, byte[] img){
        ContentValues contentValues = new ContentValues();
        bytes = img;
        //contentValues.put(MydataBase.COlUMN_IMAGE, img);
        contentValues.put(MydataBase.COlUMN_NAME, player.getName());
        contentValues.put(MydataBase.COlUMN_LEVEL, player.getNiveau());
        contentValues.put(MydataBase.COLUMN_SCORE, player.getScore());

        long playerID = sqLiteDatabase.insert(MydataBase.TABLE_NAME, null, contentValues);

        return playerID;
    }


    public void deletePlayer(Player player) {
        sqLiteDatabase.delete(MydataBase.TABLE_NAME, MydataBase.COlUMN_ID + " = " + player.get_id(), null);
    }

    public List<Player> getAllPlayers(){
        List<Player> players = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query(MydataBase.TABLE_NAME, colums, null, null, null, null, null);
        cursor.moveToFirst();
        while (! cursor.isAfterLast()){
            Player player = new Player();
            player.set_id(cursor.getLong(cursor.getColumnIndex(MydataBase.COlUMN_ID)));
            player.setName(cursor.getString(cursor.getColumnIndex(MydataBase.COlUMN_NAME)));
            player.setImage(bytes);
            player.setNiveau(cursor.getInt(cursor.getColumnIndex(MydataBase.COlUMN_LEVEL)));
            player.setScore(cursor.getInt(cursor.getColumnIndex(MydataBase.COLUMN_SCORE)));
            // Ajout du joueur
            players.add(player);
            // On passe au joueur suivant
            cursor.moveToNext();
        }
        // Fermeture du curseur
        cursor.close();

        return players;
    }
}
