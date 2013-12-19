package com.imie.montpporte.data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.imie.montpporte.bdd.SQLiteAdapterBase;
import com.imie.montpporte.model.LogProd;
import com.imie.montpporte.model.Zone;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.SimpleAdapter;

/**
 * Class LogProdSQLiteAdapter implements SQLiteAdapterBase <br/>
 * Permet la liaison base de donn�es avec l'objet LogProd
 * @author Adrien C.
 */
public class LogProdSQLiteAdapter implements SQLiteAdapterBase<LogProd> {
/** Global Fields */
	public SQLiteDatabase db;
	private static final String TAG = "LogDBAdapter";
	public static final String TABLE_NAME = "Log";
	
	// Columns constants fields mapping
	public static final String COL_ID = "id";
	public static final String COL_MOMENT = "moment";
	public static final String COL_DATE = "date";
	public static final String COL_LGNE_PROD= "ligne_production";
	public static final String COL_USER = "user";
	public static final String COL_STATOIN = "station";
	
	public static final String[] COLS = new String[] {
		COL_ID,
		COL_MOMENT,
		COL_DATE,
		COL_LGNE_PROD,
		COL_USER,
		COL_STATOIN
	};
	
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	/** Constructors */
	public LogProdSQLiteAdapter(SQLiteDatabase db) {
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
		+ COL_MOMENT	+ " string ,"
		+ COL_DATE	+ " datetime ,"
		+ COL_LGNE_PROD	+ " integer ,"
		+ COL_USER + " integer ,"
		+COL_STATOIN + " integer ,"
		+ ");";
	}

	public static ContentValues zoneToContentValues(LogProd log) {		
		ContentValues result = new ContentValues();		
		result.put(	COL_ID, String.valueOf(log.getId()) 
					);				
		result.put(	COL_MOMENT, String.valueOf(log.getMoment()) 
					);				
		result.put(	COL_DATE,	
					String.valueOf(log.getDate()) 
					);
		result.put(	COL_LGNE_PROD,	
				String.valueOf(log.getLigneproduction()) 
				);
		result.put(	COL_USER,	
				String.valueOf(log.getUser()) 
				);	
		result.put(	COL_STATOIN,	
				String.valueOf(log.getZone()) 
				);	
		

		return result;
	}
	
	public LogProd cursorToItem(Cursor c) {
		LogProd result = null;

		if (c.getCount() != 0) {
			result = new LogProd();			

			result.setId(c.getInt( c.getColumnIndexOrThrow(COL_ID) ));
			result.setMoment(c.getString( c.getColumnIndexOrThrow(COL_MOMENT) )); 
			result.setDate(
					c.getString(df.parse(c.getColumnIndexOrThrow(COL_DATE) )));
			result.setZone(
					c.getInt(c.getColumnIndexOrThrow(COL_STATOIN) ));
			result.setUser(
					c.getInt(c.getColumnIndexOrThrow(COL_USER) ));
			result.setLigneproduction(
					c.getInt(c.getColumnIndexOrThrow(COL_LGNE_PROD) ));
		}
		
		return result;
	}
	
	//***** CRUD *****//
	/**
	 * Find entity from database.
	 * @param id id of entity
	 * @return instance of user
	 */
	public LogProd getByID(int id) {
		Cursor c = this.getSingleCursor(id);
		if(c.getCount()!=0)
			c.moveToFirst();
		LogProd result = this.cursorToItem(c);
		c.close();
		
		return result;
	}

	public long insert(LogProd item) {
		Log.d(TAG, "Insert DB(" + TABLE_NAME + ")");
		
		ContentValues values = LogProdSQLiteAdapter.zoneToContentValues(item);
		values.remove(COL_ID);
	
		if(values.size()!=0){
			int newid = (int)this.db.insert(TABLE_NAME,null,values);
			
			return newid;
		}else{
			return -1;
		}
	}
	@Override
	public int update(LogProd item) {
		Log.d(TAG, "Update DB(" + TABLE_NAME + ")");
		
		ContentValues values = LogProdSQLiteAdapter.zoneToContentValues(item);	
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
	public int delete(LogProd item) {
		return this.db.delete(
				TABLE_NAME,
				COL_ID+" = ?",
				new String[]{String.valueOf(item.getId())});
	}

	@Override
	public ArrayList<LogProd> getAll() {
		ArrayList<LogProd> logs = new ArrayList<LogProd>();
		
    	Cursor cursor = db.query(TABLE_NAME, 
        		 COLS, null,
                null, null, null, null, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	LogProd log = new LogProd();
            	log.setId(Integer.parseInt(cursor.getString(0)));
            	log.setMoment(cursor.getString(1));
            	log.setDate((Date)df.parse(cursor.getString(2)));
            	log.setLigneproduction(Integer.parseInt(cursor.getString(3)));
            	log.setUser(Integer.parseInt(cursor.getString(4)));
            	log.setZone(Integer.parseInt(cursor.getString(4)));
                // Adding zone to list
            	logs.add(log);
            } while (cursor.moveToNext());
        }
 
        // return zones list
        return logs;
	}

	/*@Override
	public ArrayList<Zone> cursorToItems(Cursor c) {
		// TODO Auto-generated method stub
		return null;
	}
*/
}
