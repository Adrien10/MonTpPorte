package com.imie.montpporte.data;

import java.util.ArrayList;

import com.imie.montpporte.bdd.SQLiteAdapterBase;
import com.imie.montpporte.model.Zone;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Class ZoneSQLiteAdapter implements SQLiteAdapterBase <br/>
 * Permet la liaison base de données avec l'objet Zone
 * @author Adrien C.
 */
public class ZoneSQLiteAdapter implements SQLiteAdapterBase<Zone> {
/** Global Fields */
	public SQLiteDatabase db;
	private static final String TAG = "ZoneDBAdapter";
	public static final String TABLE_NAME = "Zone";
	
	// Columns constants fields mapping
	public static final String COL_ID = "id";
	public static final String COL_NOM = "nom";
	public static final String COL_QUANTITE_TAMPON = "quantite_tampon";
	public static final String COL_ETAT = "etat";
	public static final String COL_STATOIN_DEST = "station_destination";
	
	public static final String[] COLS = new String[] {
		COL_ID,
		COL_NOM,
		COL_QUANTITE_TAMPON,
		COL_ETAT,
		COL_STATOIN_DEST
	};
	
	/** Constructors */
	public ZoneSQLiteAdapter(SQLiteDatabase db) {
		 this.db = db;
	}

	/** Getters/Setters */
	
	/**
	 * Return table name
	 * @return table name
	 */
	public String getTableName(){
		return TABLE_NAME;
	}
	/**
	 * Return columns 
	 * @return string array of table
	 */
	public String[] getCols(){
		return COLS;
	}
	/**
	 * Return table schema
	 * @return string schema
	 */
	public static final String getSchema() {
		return "CREATE TABLE "
		+ TABLE_NAME	+ " ("
		+ COL_ID	+ " integer PRIMARY KEY AUTOINCREMENT,"
		+ COL_NOM	+ " string ,"
		+ COL_QUANTITE_TAMPON	+ " integer ,"
		+ COL_ETAT	+ " integer ,"
		+ COL_STATOIN_DEST + " integer "
		+ ");";
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
		result.put(	COL_ETAT,	
				String.valueOf(zone.getEtat()) 
				);
		if(zone.getStation_destination() == null)
		{
		result.put(	COL_STATOIN_DEST,	
				String.valueOf(0) 
				);
		}
		else
		{
			result.put(	COL_STATOIN_DEST,	
					String.valueOf(zone.getStation_destination().getId()) 
					);
		}

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
			result.setQuantite_tampon(
					c.getInt(c.getColumnIndexOrThrow(COL_ETAT) ));
			result.setStation_destination(getByID(
					c.getInt(c.getColumnIndexOrThrow(COL_STATOIN_DEST) )));
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
		{
			c.moveToFirst();
		Zone result = this.cursorToItem(c);
		c.close();
		return result;
		}
		else
			return null;
		
	}

	public long insert(Zone item) {
		Log.d(TAG, "Insert DB(" + TABLE_NAME + ")");
		
		ContentValues values = ZoneSQLiteAdapter.zoneToContentValues(item);
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
		
		ContentValues values = ZoneSQLiteAdapter.zoneToContentValues(item);	
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
		ArrayList<Zone> zones = new ArrayList<Zone>();
		
    	Cursor cursor = db.query(TABLE_NAME, 
        		 COLS, null,
                null, null, null, null, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	Zone zone = new Zone();
            	zone.setId(Integer.parseInt(cursor.getString(0)));
            	zone.setNom(cursor.getString(1));
            	zone.setQuantite_tampon(Integer.parseInt(cursor.getString(2)));
            	zone.setEtat(Integer.parseInt(cursor.getString(3)));
            	if(getByID(Integer.parseInt(cursor.getString(4))) == null)
        		{
            		zone.setStation_destination(getByID(0));
        		}
        		else
        		{
        			zone.setStation_destination(getByID(Integer.parseInt(cursor.
                			getString(4))));
        		}
                // Adding zone to list
            	zones.add(zone);
            } while (cursor.moveToNext());
        }
 
        // return zones list
        return zones;
	}

	/*@Override
	public ArrayList<Zone> cursorToItems(Cursor c) {
		// TODO Auto-generated method stub
		return null;
	}
*/
}
