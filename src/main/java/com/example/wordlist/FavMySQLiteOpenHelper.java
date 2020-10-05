package com.example.wordlist;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class FavMySQLiteOpenHelper extends SQLiteOpenHelper {

    public FavMySQLiteOpenHelper(Context context, String name,
                              CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        /// 테이블생성
        String sql = "create table FavoriteWord ( Favorite_id integer primary key autoincrement ," +
                " FavoriteWord text , FavoriteMeaning text, FavoriteTag text )";
        db.execSQL(sql);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql = "drop table if exists word";
        db.execSQL(sql);

        onCreate(db);
    }

}
