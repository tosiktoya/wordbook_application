package com.example.wordlist;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.nfc.Tag;
import android.util.Log;

public class FavMySQLiteHandler {

    MySQLiteOpenHelper Fav_helper;
    SQLiteDatabase Fav_db;

    public FavMySQLiteHandler(Context ctx){

        Fav_helper = new MySQLiteOpenHelper(ctx, "FavoriteWord.sqlite", null, 1);
    }
    //
    public static MySQLiteHandler open(Context ctx){
        return new MySQLiteHandler(ctx);
    }
    //
    public  void close(){
        Fav_helper.close();
    }

    // insert
    public void insert( String FavoriteWord, String FavoriteMeaning, String FavoriteTag){

        Fav_db = Fav_helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("FavoriteWord", FavoriteWord);
        values.put("FavoriteMeaning", FavoriteMeaning);
        values.put("FavoriteTag", FavoriteTag);
        Fav_db.insert("FavoriteWord", null, values);
    }

    // update
    public void update(String Favorite_id, String FavoriteWord, String FavoriteMeaning, String FavoriteTag){

        Fav_db = Fav_helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("FavoriteWord", FavoriteWord);
        values.put("FavoriteMeaning", FavoriteMeaning);
        values.put("FavoriteTag", FavoriteTag);
        Fav_db.update("FavoriteWord", values, "Favorite_id = ?", new String[]{ Favorite_id });
    }
    // delete
    public void delete(String Favorite_id){
        Fav_db = Fav_helper.getWritableDatabase();
        Fav_db.delete("FavoriteWord", " Favorite_id = ? ",  new String[]{ Favorite_id  } );
    }

    // select
    public Cursor selectAll(){

        Fav_db = Fav_helper.getWritableDatabase();
        Cursor Fav_c = Fav_db.query("FavoriteWord", null, null, null, null, null, null);
        return Fav_c;
    }

    public Cursor  selectById(String Favorite_id){

        Fav_db = Fav_helper.getWritableDatabase();
        Cursor Fav_c = Fav_db.query("FavoriteWord", null, "Favorite_id = ?", new String[]{ Favorite_id }, null, null, null);
        return Fav_c;    }

}




