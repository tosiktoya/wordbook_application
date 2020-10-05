package com.example.wordlist;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

	public MySQLiteOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		/// 테이블생성
		String sql = "create table word ( _id integer primary key autoincrement ," +
				" Word text , Meaning text, Tag text )";
        db.execSQL(sql);
	}


	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		String sql = "drop table if exists word";
		db.execSQL(sql);
		
		onCreate(db);
	}

}
