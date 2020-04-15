package org.devmobile.myapplicationrecyclerview;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class MySqLiteHelper extends SQLiteOpenHelper {
    public static  final String TABLE_ADHERENT = "adherent";
    public static  final String COLUMN_ID = "_id";
    public static  final String COLUMN_NOM = "nom";
    public static  final String COLUMN_TEL = "tel";

    public static  final String NOM_BdD = "adherent.db";
    public static  final int VERSION_BdD = 1;
    private static final String REQ_CREATION_BdD = "create table "
            + TABLE_ADHERENT + "(" +
            COLUMN_ID + " integer primary key autoincrement, "+
            COLUMN_NOM + " text not null, "+
            COLUMN_TEL + " text not null) ";

    public MySqLiteHelper(Context context) {
        super(context, NOM_BdD, null, VERSION_BdD);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(REQ_CREATION_BdD);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADHERENT);
        onCreate(db);
    }
}


