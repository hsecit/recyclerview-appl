package org.devmobile.myapplicationrecyclerview;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
class AdherentDataSource {
    private SQLiteDatabase db;
    private MySqLiteHelper dbHelper;
    private String[] lesColumns = {MySqLiteHelper.COLUMN_ID,
            MySqLiteHelper.COLUMN_NOM, MySqLiteHelper.COLUMN_TEL};
    public AdherentDataSource(Context context) {
        dbHelper = new MySqLiteHelper(context);
    }
    public  void open() throws SQLException {
        db = dbHelper.getWritableDatabase();
    }
    public  void close() {
        dbHelper.close();
    }
    public Adherent creerAdherent(String nom, String tel) {
        ContentValues values = new ContentValues();
        values.put(MySqLiteHelper.COLUMN_NOM, nom);
        values.put(MySqLiteHelper.COLUMN_TEL, tel);

        long idAdherantInsert = db.insert(MySqLiteHelper.TABLE_ADHERENT, null, values);
        Cursor cursor = db.query(MySqLiteHelper.TABLE_ADHERENT, lesColumns,
                MySqLiteHelper.COLUMN_ID + " = " +
                idAdherantInsert, null, null, null, null);
        cursor.moveToFirst();
        Adherent adherent = cursorToAdherent(cursor);
        cursor.close();
        return adherent;
    }

    public void updateAdherent(String nomAdherent,String telAdherent,String nomUpdate,String telUpdate){
        ContentValues values = new ContentValues();
        values.put(MySqLiteHelper.COLUMN_NOM, nomUpdate);
        values.put(MySqLiteHelper.COLUMN_TEL, telUpdate);
        db.update(MySqLiteHelper.TABLE_ADHERENT, values,
                MySqLiteHelper.COLUMN_NOM+"=?" + " AND "
                        + MySqLiteHelper.COLUMN_TEL +"=?",new String[]{nomAdherent,telAdherent}
        );
    }
    public void deleteAdherent(String nom, String tel) {
        db.delete(MySqLiteHelper.TABLE_ADHERENT,
                MySqLiteHelper.COLUMN_NOM+"=?" + " AND " + MySqLiteHelper.COLUMN_TEL +"=?",new String[]{nom,tel}
        );

    }
    public List<Adherent> getAllAdherents() {
        List<Adherent> adherents = new ArrayList<>();

        Cursor myCursor = db.query(MySqLiteHelper.TABLE_ADHERENT, lesColumns,
                null, null, null, null, null);
        myCursor.moveToFirst();
        while (!myCursor.isAfterLast()) {
            Adherent adherent = cursorToAdherent(myCursor);
            adherents.add(adherent);
            myCursor.moveToNext();
        }
        myCursor.close();
        return adherents;
    }
    private Adherent cursorToAdherent(Cursor cursor) {
        Adherent adherent = new Adherent();
        adherent.setIdAdherent(cursor.getString(0));
        adherent.setNom(cursor.getString(1));
        adherent.setTel(cursor.getString(2));
        return adherent;
    }
}
