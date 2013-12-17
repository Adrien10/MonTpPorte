package com.imie.montpporte.data;

import java.util.ArrayList;

import com.imie.montpporte.bdd.SQLiteAdapterBase;
import com.imie.montpporte.model.Zone;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class CommandeSQLiteAdapter implements SQLiteAdapterBase<Zone> {

private static final String TAG = "ZoneDBAdapter";

	public SQLiteDatabase db;
	public static final String TABLE_NAME = "Zone";
	
	// Columns constants fields mapping
	public static final String COL_ID = "id";
	public static final String COL_NOM = "nom";
	public static final String COL_QUANTITE_TAMPON = "quantite_tampon";
	
	/** Global Fields */
	public static final String[] COLS = new String[] {
		COL_ID,
		COL_NOM,
		COL_QUANTITE_TAMPON
	};
	
	public String getTableName(){
		return TABLE_NAME;
	}
	
	public String[] getCols(){
		return COLS;
	}

	public static final String getSchema() {
		return "CREATE TABLE "
		+ TABLE_NAME	+ " ("
		+ COL_ID	+ " integer PRIMARY KEY AUTOINCREMENT,"
		+ COL_NOM	+ " string ,"
		+ COL_QUANTITE_TAMPON	+ " integer "
		+ ");";
	}
	
	public CommandeSQLiteAdapter(SQLiteDatabase db) {
		 this.db = db;
	}

	public static ContentValues zoneToContentValues(Zone zone) {		
		ContentValues result = new ContentValues();		
		result.put(	COL_ID, String.valueOf(zone.getId()) 
					);				
		result.put(	COL_NOM, String.valueOf(zone.getNom()) 
					);				
		result.put(	COL_QUANTITE_TAMPON,	
					String.valueOf(zone.getQuantite_tampon()) 
					);			

		return result;
	}
	
	public Zone cursorToItem(Cursor c) {
		Zone result = null;

		if (c.getCount() != 0) {
			result = new Zone();			

			result.setId(c.getInt( c.getColumnIndexOrThrow(COL_ID) ));
			result.setNom(c.getString( c.getColumnIndexOrThrow(COL_NOM) )); 
			result.setQuantite_tampon(
					c.getInt(c.getColumnIndexOrThrow(COL_QUANTITE_TAMPON) ));
		}
		
		return result;
	}
	
	//***** CRUD *****//
	/**
	 * Find entity from database.
	 * @param id id of entity
	 * @return instance of user
	 */
	public Zone getByID(int id) {
		Cursor c = this.getSingleCursor(id);
		if(c.getCount()!=0)
			c.moveToFirst();
		Zone result = this.cursorToItem(c);
		c.close();
		
		return result;
	}

	public long insert(Zone item) {
		Log.d(TAG, "Insert DB(" + TABLE_NAME + ")");
		
		ContentValues values = CommandeSQLiteAdapter.zoneToContentValues(item);
		values.remove(COL_ID);
	
		if(values.size()!=0){
			int newid = (int)this.db.insert(TABLE_NAME,null,values);
			
			return newid;
		}else{
			return -1;
		}
	}
	@Override
	public int update(Zone item) {
		Log.d(TAG, "Update DB(" + TABLE_NAME + ")");
		
		ContentValues values = CommandeSQLiteAdapter.zoneToContentValues(item);	
		String whereClause =  COL_ID + "=? ";
		String[] whereArgs = new String[] {String.valueOf(item.getId()) };
		
		return this.db.update(
				TABLE_NAME,
				values, 
				whereClause, 
				whereArgs);
	}

	public int remove(int id) {
		Log.d(TAG, "Delete DB(" + TABLE_NAME + ") id : "+ id);
		
		String whereClause =  COL_ID + "=? ";
		String[] whereArgs = new String[] {String.valueOf(id) };
		
		return this.db.delete(
				TABLE_NAME,
				whereClause, 
				whereArgs);
	}
	
	// Internal Cursor
	protected Cursor getSingleCursor(int id) {
		Log.d(TAG, "Get entities id : " + id);
		
		String whereClause =  COL_ID + "=? ";
		String[] whereArgs = new String[] {String.valueOf(id) };
		
		return this.db.query(
				TABLE_NAME, COLS, whereClause, whereArgs, null, null, null);
	}
	
	
	public Cursor query(int id){
		return this.db.query(
				TABLE_NAME,
				COLS,
				COL_ID+" = ?",
				new String[]{String.valueOf(id)},
				null,
				null,
				null);
	}
	
	
	public int delete(int id){
		return this.db.delete(
				TABLE_NAME,
				COL_ID+" = ?",
				new String[]{String.valueOf(id)});
	}

	@Override
	public int delete(Zone item) {
		return this.db.delete(
				TABLE_NAME,
				COL_ID+" = ?",
				new String[]{String.valueOf(item.getId())});
	}

	@Override
	public ArrayList<Zone> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Zone> cursorToItems(Cursor c) {
		// TODO Auto-generated method stub
		return null;
	}

}
