package com.imie.montpporte.data;

import java.util.ArrayList;

import com.imie.montpporte.bdd.SQLiteAdapterBase;
import com.imie.montpporte.model.Production;
import com.imie.montpporte.model.Zone;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ProductionSQLiteAdapter implements SQLiteAdapterBase<Production> {

private static final String TAG = "ProductionDBAdapter";

	public SQLiteDatabase db;
	public static final String TABLE_NAME = "Production";
	
	// Columns constants fields mapping
	public static final String COL_ID = "id";
	public static final String COL_ID_COMMANDE = "commande";
	public static final String COL_NORDRE = "nOrdre";
	public static final String COL_STATION_COURANTE = "station_courante";
	
	/** Global Fields */
	public static final String[] COLS = new String[] {
		COL_ID,
		COL_ID_COMMANDE,
		COL_NORDRE,
		COL_STATION_COURANTE
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
		+ COL_ID_COMMANDE	+ " string ,"
		+ COL_NORDRE	+ " integer ,"
		+ COL_STATION_COURANTE	+ " integer "
		+ ");";
	}
	
	public ProductionSQLiteAdapter(SQLiteDatabase db) {
		 this.db = db;
		 
		 
	}

	public static ContentValues productionToContentValues(Production production) {		
		ContentValues result = new ContentValues();		
		result.put(	COL_ID, String.valueOf(production.getId()) 
					);				
		result.put(	COL_ID_COMMANDE,
					String.valueOf(production.getCommande() )
					);				
		result.put(	COL_NORDRE,	
					String.valueOf(production.getnOrdre()) 
					);
		result.put(	COL_STATION_COURANTE,	
				String.valueOf(production.getStationCourante()) 
				);		

		return result;
	}
	
	public Production cursorToItem(Cursor c) {
		Production result = null;

		if (c.getCount() != 0) {
			result = new Production();			

			result.setId(c.getInt( c.getColumnIndexOrThrow(COL_ID) ));
			result.setCommande( c.getInt(
					c.getColumnIndexOrThrow(COL_ID_COMMANDE))); 
			result.setnOrdre(
					c.getInt(c.getColumnIndexOrThrow(COL_NORDRE)));
			result.setStationCourante(
					c.getInt(c.getColumnIndexOrThrow(COL_STATION_COURANTE)));
		}
		
		return result;
	}
	
	//***** CRUD *****//
	/**
	 * Find entity from database.
	 * @param id id of entity
	 * @return instance of user
	 */
	public Production getByID(int id) {
		Cursor c = this.getSingleCursor(id);
		if(c.getCount()!=0)
			c.moveToFirst();
		Production result = this.cursorToItem(c);
		c.close();
		
		return result;
	}

	public long insert(Production item) {
		Log.d(TAG, "Insert DB(" + TABLE_NAME + ")");
		
		ContentValues values = ProductionSQLiteAdapter.productionToContentValues(item);
		values.remove(COL_ID);
	
		if(values.size()!=0){
			int newid = (int)this.db.insert(TABLE_NAME,null,values);
			
			return newid;
		}else{
			return -1;
		}
	}
	@Override
	public int update(Production item) {
		Log.d(TAG, "Update DB(" + TABLE_NAME + ")");
		
		ContentValues values = ProductionSQLiteAdapter.productionToContentValues(item);	
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
	public int delete(Production item) {
		return this.db.delete(
				TABLE_NAME,
				COL_ID+" = ?",
				new String[]{String.valueOf(item.getId())});
	}

	@Override
	public ArrayList<Production> getAll() {

		ArrayList<Production> productions = new ArrayList<Production>();
		
    	Cursor cursor = db.query(TABLE_NAME, 
        		COLS, null,
                null, null, null, null, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	Production production = new Production();
            	production.setId(Integer.parseInt(cursor.getString(0)));
            	production.setCommande(Integer.parseInt(cursor.getString(1)));
            	production.setnOrdre(Integer.parseInt(cursor.getString(2)));
            	production.setStationCourante(Integer.parseInt(cursor.getString(3)));
                // Adding user to list
            	productions.add(production);
            } while (cursor.moveToNext());
        }
 
        // return production list
        return productions;

	}
	
	public ArrayList<Production> getAllFromZone(Zone zone) {

		ArrayList<Production> productions = new ArrayList<Production>();
		String whereClause =  COL_STATION_COURANTE + "=? ";
		String[] whereArgs = new String[] {String.valueOf(zone.getId()) };
		
		Cursor cursor = db.query(TABLE_NAME, 
        		COLS, whereClause,
        		whereArgs, null, null, null, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	Production production = new Production();
            	production.setId(Integer.parseInt(cursor.getString(0)));
            	production.setCommande(Integer.parseInt(cursor.getString(1)));
            	production.setnOrdre(Integer.parseInt(cursor.getString(2)));
            	production.setStationCourante(Integer.parseInt(cursor.getString(3)));
                // Adding user to list
            	productions.add(production);
            } while (cursor.moveToNext());
        }
 
        // return production list
        return productions;

	}

	/*@Override
	public ArrayList<Production> cursorToItems(Cursor c) {
		// TODO Auto-generated method stub
		return null;
	}*/

}
