package com.example.wordlist;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.nfc.Tag;
import android.util.Log;

public class MySQLiteHandler {
	
	MySQLiteOpenHelper helper;
	SQLiteDatabase db;
	
	public MySQLiteHandler(Context ctx){
		
		helper = new MySQLiteOpenHelper(ctx, "word.sqlite", null, 1);
	}
	//
	public static MySQLiteHandler open(Context ctx){
		return new MySQLiteHandler(ctx);
	}
	//
	public  void close(){
		helper.close();
	}

	// insert
	public void insert( String Word, String Meaning, String Tag){
		
		db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("Word", Word);
		values.put("Meaning", Meaning);
		values.put("Tag", Tag);
		db.insert("word", null, values);
	}

	// update
	public void update(String id, String Word, String Meaning, String Tag ){
		
		db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("Word", Word);
		values.put("Meaning", Meaning);
		values.put("Tag", Tag);
		db.update("word", values, "_id = ?", new String[]{ id });
	}
	// delete
	public void delete(String id){
		db = helper.getWritableDatabase();
		db.delete("word", " _id = ? ",  new String[]{ id } );
	}
	
	// select
	public Cursor selectAll(){
		
		db = helper.getReadableDatabase();
		Cursor c = db.query("word", null, null, null, null, null, null);
	    return c;
	}

	public Cursor  selectById(String _id){

		db = helper.getReadableDatabase();
		Cursor c = db.query("word", null, "_id = ?", new String[]{ _id }, null, null, null);
		return c;
	}

	public Cursor selectBySearch(String Tag){

		db = helper.getReadableDatabase();
		Cursor c = db.query("word", null, "Tag = ?", new String[]{ Tag },null, null,null,null);
		return c;
	}
	
}






