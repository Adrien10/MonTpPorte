package com.imie.montpporte.bdd;

import com.imie.montpporte.data.CommandeSQLiteAdapter;
import com.imie.montpporte.data.ProductionSQLiteAdapter;
import com.imie.montpporte.data.UserSQLiteAdapter;
import com.imie.montpporte.data.ZoneSQLiteAdapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class MonTpPorteSQLiteOpenHelper extends SQLiteOpenHelper{

	public MonTpPorteSQLiteOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version); 
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL( UserSQLiteAdapter.getSchema() );
		db.execSQL( ZoneSQLiteAdapter.getSchema() );
		db.execSQL( CommandeSQLiteAdapter.getSchema() );
		db.execSQL( ProductionSQLiteAdapter.getSchema() );
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
