package com.imie.montpporte.data;

import java.util.ArrayList;

import com.imie.montpporte.bdd.SQLiteAdapterBase;
import com.imie.montpporte.model.User;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Class UserSQLiteAdapter implements SQLiteAdapterBase <br/>
 * Permet la liaison base de données avec l'objet User
 * @author Adrien C.
 */
public class TamponSQLiteAdapter implements SQLiteAdapterBase<User> {
	
	public SQLiteDatabase db;
	/** Global Fields */
	// Columns constants fields mapping
	private static final String TAG = "UserDBAdapter";
	public static final String TABLE_NAME = "User";
	public static final String COL_ID = "id";
	public static final String COL_LOGIN = "login";
	public static final String COL_PASSWORD = "password";
	public static final String[] COLS = new String[] {
		COL_ID,
		COL_LOGIN,
		COL_PASSWORD
	};
	
	/** Getters/Setters */
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
		+ COL_LOGIN	+ " string NOT NULL,"
		+ COL_PASSWORD	+ " string NOT NULL"
		+ ");";
	}
	
	public TamponSQLiteAdapter(SQLiteDatabase db) {
		 this.db = db;
	}

	public static ContentValues userToContentValues(User user) {		
		ContentValues result = new ContentValues();		
		result.put(COL_ID, 			String.valueOf(user.getId()) );				
		result.put(COL_LOGIN, 		String.valueOf(user.getLogin()) );				
		result.put(COL_PASSWORD, 	String.valueOf(user.getPassword()) );			

		return result;
	}
	
	public User cursorToItem(Cursor c) {
		User result = null;

		if (c.getCount() != 0) {
			result = new User();			

			result.setId(c.getInt( c.getColumnIndexOrThrow(COL_ID) ));
			result.setLogin(c.getString( c.getColumnIndexOrThrow(COL_LOGIN) )); 
			result.setPassword(
					c.getString(c.getColumnIndexOrThrow(COL_PASSWORD) ));
		}
		
		return result;
	}
	
	//***** CRUD *****//
	/**
	 * Find entity from database.
	 * @param id id of entity
	 * @return instance of user
	 */
	public User getByID(int id) {
		Cursor c = this.getSingleCursor(id);
		if(c.getCount()!=0)
			c.moveToFirst();
		User result = this.cursorToItem(c);
		c.close();
		
		return result;
	}
	
	/**
	 * Insert entity in database
	 * @param item item is instance of User
 	*/
	public long insert(User item) {
		Log.d(TAG, "Insert DB(" + TABLE_NAME + ")");
		
		ContentValues values = TamponSQLiteAdapter.userToContentValues(item);
		values.remove(COL_ID);
	
		if(values.size()!=0){
			int newid = (int)this.db.insert(TABLE_NAME,null,values);
			
			return newid;
		}else{
			return -1;
		}
	}
	
	/**
	 * Update entity on database
	 * @param item item is instance of User
 	*/
	public int update(User item) {
		Log.d(TAG, "Update DB(" + TABLE_NAME + ")");
		
		ContentValues values = TamponSQLiteAdapter.userToContentValues(item);	
		String whereClause =  COL_ID + "=? ";
		String[] whereArgs = new String[] {String.valueOf(item.getId()) };
		
		return this.db.update(
				TABLE_NAME,
				values, 
				whereClause, 
				whereArgs);
	}
	
	/**
	 * Remove entity on database
	 * @param item item is instance of User
 	*/
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
	 * Delete entity from database
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
	public int delete(User item) {
		return this.db.delete(
				TABLE_NAME,
				COL_ID+" = ?",
				new String[]{String.valueOf(item.getId())});
	}

	@Override
	public ArrayList<User> cursorToItems(Cursor c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
