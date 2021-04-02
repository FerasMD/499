package kau.edu.quran;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DB extends SQLiteOpenHelper {

    public static final String DBName = "DB.db";


    public DB(@Nullable Context context) {
        super(context, DBName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table khatm (id integer PRIMARY KEY, dailyPages integer, startDate varchar(15)" +
                ", endDate varchar(15), currentSourah varchar(10), currentVerse integer, currentPage integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("drop table if exists sourahInfo");
        onCreate(sqLiteDatabase);
    }

    public boolean insertInto1(int id, int dailyPages, String startDate, String endDate, String currentSourah,
                               int currentVerse, int currentPage){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues data = new ContentValues();

        data.put("id", id);
        data.put("dailyPages", dailyPages);
        data.put("startDate", String.valueOf(startDate));
        data.put("endDate", String.valueOf(endDate));
        data.put("currentSourah", currentSourah);
        data.put("currentVerse", currentVerse);
        data.put("currentPage", currentPage);

        long r = db.insert("khatm", null, data);

        if(r==-1)
            return false;
        else
            return true;
    }

    public ArrayList getAllAttr(){
        ArrayList attrs = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cur = db.rawQuery("select * from khatm", null);
        cur.moveToFirst();

        while (cur.isAfterLast() == false){
            int id = cur.getInt(0);
            int dailyPages = cur.getInt(1);
            String startDate = cur.getString(2);
            String endDate = cur.getString(3);
            String currentSourah = cur.getString(4);
            int currentVerse = cur.getInt(5);
            int currentPage = cur.getInt(6);
            attrs.add(id);
            attrs.add(dailyPages);
            attrs.add(startDate);
            attrs.add(endDate);
            attrs.add(currentSourah);
            attrs.add(currentVerse);
            attrs.add(currentPage);
            cur.moveToNext();
        }
        return attrs;
    }

    public boolean update (String id, int dailyPages,String endDate , String currentSourah, int currentVerse,
                           int currentPage){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues data = new ContentValues();

            data.put("dailyPages", dailyPages);
            data.put("currentSourah", currentSourah);
            data.put("currentVerse", currentVerse);
            data.put("endDate", endDate);
            data.put("currentPage", currentPage);
            db.update("khatm", data, "id=?", new String[] {id});
            return true;

    }

    public int delete(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("khatm", "id=?", new String[] {id});
    }

    public boolean isEmpty(String n){
        SQLiteDatabase db = this.getWritableDatabase();
        boolean empty = true;
        Cursor cur = db.rawQuery("SELECT COUNT(*) FROM khatm", null);
        if (cur != null && cur.moveToFirst())
            empty = (cur.getInt (0) == 0);
        cur.close();
        return empty;
    }
}
