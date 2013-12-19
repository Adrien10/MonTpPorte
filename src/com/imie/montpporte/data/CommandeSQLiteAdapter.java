package com.imie.montpporte.data;

import java.util.ArrayList;

import com.imie.montpporte.bdd.SQLiteAdapterBase;
import com.imie.montpporte.model.Commande;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class CommandeSQLiteAdapter implements SQLiteAdapterBase<Commande> {

private static final String TAG = "CommandeDBAdapter";

	public SQLiteDatabase db;
	public static final String TABLE_NAME = "Commande";
	
	// Columns constants fields mapping
	public static final String COL_ID = "id";
	public static final String COL_QUANTITE = "quantite";
	public static final String COL_TYPE_ITEM = "typeItem";
	public static final String COL_MATERIAUX = "materiaux";
	public static final String COL_ID_CLIENT = "idClient";
	
	/** Global Fields */
	public static final String[] COLS = new String[] {
		COL_ID,
		COL_QUANTITE,
		COL_TYPE_ITEM,
		COL_MATERIAUX,
		COL_ID_CLIENT
	};
	
	/**
	 * get the name of table sql
	 * @return string
	 */
	public String getTableName(){
		return TABLE_NAME;
	}
	
	/**
	 * get all column of the table sql
	 * @return String array
	 */
	public String[] getCols(){
		return COLS;
	}
	
	/**
	 * Get base schema 
	 * @return string
	 */
	public static final String getSchema() {
		return "CREATE TABLE "
		+ TABLE_NAME	+ " ("
		+ COL_ID	+ " integer PRIMARY KEY AUTOINCREMENT,"
		+ COL_QUANTITE	+ " string ,"
		+ COL_TYPE_ITEM	+ " integer ,"
		+ COL_MATERIAUX	+ " string ,"
		+ COL_ID_CLIENT	+ " integer "
		+ ");";
	}
	
	/**
	 * get db instance
	 * @param db
	 */
	public CommandeSQLiteAdapter(SQLiteDatabase db) {
		 this.db = db;
	}

	public static ContentValues commandeToContentValues(Commande commande) {		
		ContentValues result = new ContentValues();		
		result.put(	COL_ID, String.valueOf(commande.getId()) 
					);				
		result.put(	COL_QUANTITE, String.valueOf(commande.getQuantite()) 
					);				
		result.put(	COL_TYPE_ITEM,	
					String.valueOf(commande.getTypeItem()) 
					);
		result.put(	COL_MATERIAUX, String.valueOf(commande.getMateriaux()) 
				);				
		result.put(	COL_ID_CLIENT,	
				String.valueOf(commande.getIdClient()) 
				);

		return result;
	}
	
	public Commande cursorToItem(Cursor c) {
		Commande result = null;

		if (c.getCount() != 0) {
			result = new Commande();			

			result.setId(c.getInt(
					c.getColumnIndexOrThrow(COL_ID) ));
			result.setQuantite(
					c.getInt(c.getColumnIndexOrThrow(COL_QUANTITE) )); 
			result.setTypeItem(
					c.getString(c.getColumnIndexOrThrow(COL_TYPE_ITEM) ));
			result.setMateriaux(
					c.getString(c.getColumnIndexOrThrow(COL_MATERIAUX) ));
			result.setIdClient(
					c.getInt(c.getColumnIndexOrThrow(COL_ID_CLIENT) ));
		}
		
		return result;
	}
	
	//***** CRUD *****//
	/**
	 * Find entity from database.
	 * @param id id of entity
	 * @return instance of user
	 */
	public Commande getByID(int id) {
		Cursor c = this.getSingleCursor(id);
		if(c.getCount()!=0)
			c.moveToFirst();
		Commande result = this.cursorToItem(c);
		c.close();
		
		return result;
	}

	/**
	 * Insert an order
	 */
	public long insert(Commande item) {
		Log.d(TAG, "Insert DB(" + TABLE_NAME + ")");
		
		ContentValues values = CommandeSQLiteAdapter.commandeToContentValues(item);
		values.remove(COL_ID);
	
		if(values.size()!=0){
			int newid = (int)this.db.insert(TABLE_NAME,null,values);
			
			return newid;
		}else{
			return -1;
		}
	}
	@Override
	public int update(Commande item) {
		Log.d(TAG, "Update DB(" + TABLE_NAME + ")");
		
		ContentValues values = CommandeSQLiteAdapter.commandeToContentValues(item);	
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
	
	/**
	 * Delete order which id = id parameter
	 * @param id
	 * @return 
	 */
	public int delete(int id){
		return this.db.delete(
				TABLE_NAME,
				COL_ID+" = ?",
				new String[]{String.valueOf(id)});
	}

	@Override
	public int delete(Commande item) {
		return this.db.delete(
				TABLE_NAME,
				COL_ID+" = ?",
				new String[]{String.valueOf(item.getId())});
	}
	
	/**
	 * get all order in base sql
	 */
	@Override
	public ArrayList<Commande> getAll() {
		ArrayList<Commande> commandes = new ArrayList<Commande>();
		
    	Cursor cursor = db.query(TABLE_NAME, 
        		 COLS, null,
                null, null, null, null, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	Commande commande = new Commande();
            	commande.setId(Integer.parseInt(cursor.getString(0)));
            	commande.setQuantite(Integer.parseInt(cursor.getString(1)));
            	commande.setTypeItem(cursor.getString(2));
            	commande.setMateriaux(cursor.getString(2));
            	commande.setIdClient(Integer.parseInt(cursor.getString(2)));
                // Adding user to list
                commandes.add(commande);
            } while (cursor.moveToNext());
        }
 
        // return commande list
        return commandes;
	}

	/*@Override
	public Cursor cursorToItems(Cursor c) {
		
		Cursor cursor = db.query(TABLE_NAME, 
				new String[] { COL_ID, COL_QUANTITE, COL_TYPE_ITEM,
    			COL_MATERIAUX,COL_ID_CLIENT }, null,
                null, null, null, null, null);

        return cursor;
	}*/

}
