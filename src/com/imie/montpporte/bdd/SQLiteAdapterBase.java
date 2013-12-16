package com.imie.montpporte.bdd;

import java.util.ArrayList;

import android.database.Cursor;

public interface SQLiteAdapterBase<T>{

	/** Table name of SQLite database */
	public static String TAG = "TpPorteSQLiteAdapterBase";
	
	long insert(T item);
	
	int delete(T item);
	
	int update(T item);
	
	public ArrayList<T> getAll();
	
	public ArrayList<T> cursorToItems(Cursor c);
}