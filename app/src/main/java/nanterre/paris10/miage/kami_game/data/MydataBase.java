package nanterre.paris10.miage.kami_game.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mamadou BABA on 27/02/2018.
 */

public class MydataBase extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "player";
    private static  final String DB_NAME = "players.db";
    private static  final int version = 1;
    public static  final String COLUMN_SCORE = "score";
    public static  final String COlUMN_NAME = "name";
    public static  final String COlUMN_LEVEL = "niveau";
    public static  final String COlUMN_ID = "_id";
    public static  final String COlUMN_IMAGE = "image";
    private static  final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + COlUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_SCORE + " integer, " + COlUMN_LEVEL + " integer, " + COlUMN_NAME + " text not null, " + COlUMN_IMAGE + " blob);" ;

    public MydataBase(Context context) {
        super(context, DB_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
