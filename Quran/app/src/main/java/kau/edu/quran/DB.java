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

        sqLiteDatabase.execSQL("create table sourahInfo (sourahName varchar(10) PRIMARY KEY, reasonD varchar2(1000), " +
                "reasonN varhcar2(1000), place varchar(10), noV integer, noL integer)");
        sqLiteDatabase.execSQL("create table settings(id integer PRIMARY KEY)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("drop table if exists sourahInfo");
        onCreate(sqLiteDatabase);
    }

    public boolean insertInto1(String name, String reasonD, String reasonN, String place, int noV, int noL){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues data = new ContentValues();

        data.put("sourahName", name);
        data.put("reasonD", reasonD);
        data.put("reasonN", reasonN);
        data.put("place", place);
        data.put("noV", noV);
        data.put("noL", noL);

        long r = db.insert("sourahInfo", null, data);

        if(r==-1)
            return false;
        else
            return true;
    }

    public String getAttr1(String name, String attr){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cur = db.rawQuery("select * from sourahInfo", null);
        cur.moveToFirst();

        while (cur.isAfterLast() == false){
            if(cur.getString(0).equals(name)){
                return cur.getString(cur.getColumnIndex(name)) ;
            }
            cur.moveToNext();
        }
        return "";
    }

    public boolean update (String id, String deadline, String currentSourah, int currentVerse){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues data = new ContentValues();

        if(!(deadline.equals("")))
            data.put("deadline", deadline);
            data.put("sourah", currentSourah);
            data.put("verse", currentVerse);

            db.update("khatmah", data, "id=1", new String[] {id});
            return true;

    }

    public int delete(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("khatmah", "id=1", new String[] {id});
    }
}
