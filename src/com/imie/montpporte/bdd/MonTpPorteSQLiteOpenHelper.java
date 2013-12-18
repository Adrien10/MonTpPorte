package com.imie.montpporte.bdd;

import com.imie.montpporte.data.CommandeSQLiteAdapter;
import com.imie.montpporte.data.ProductionSQLiteAdapter;
import com.imie.montpporte.data.UserSQLiteAdapter;
import com.imie.montpporte.data.ZoneSQLiteAdapter;
import com.imie.montpporte.model.Commande;
import com.imie.montpporte.model.Production;
import com.imie.montpporte.model.User;
import com.imie.montpporte.model.Zone;

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
		
		UserSQLiteAdapter usersqladapter = new UserSQLiteAdapter(db);
		User u = new User("a","a");
		usersqladapter.insert(u);
		
		ZoneSQLiteAdapter zonesqladapter = new ZoneSQLiteAdapter(db);
		Zone zone = new Zone("Découpage",10);
		Zone zone2 = new Zone("Façonnage",10);
		Zone zone3 = new Zone("Peinture",10);
		Zone zone4 = new Zone("Assemblage",10);
		zonesqladapter.insert(zone);
		zonesqladapter.insert(zone2);
		zonesqladapter.insert(zone3);
		zonesqladapter.insert(zone4);
	
		
		CommandeSQLiteAdapter cdesqladapter = new CommandeSQLiteAdapter(db);
		Commande cde = new Commande(2,"Porte","Acier",1);
		cdesqladapter.insert(cde);
		
		ProductionSQLiteAdapter productionesqladapter =
				new ProductionSQLiteAdapter(db);
		int i = 1;
		while ( i <= cde.getQuantite()){
		Production production = new Production(cde, i);
		productionesqladapter.insert(production);
		i++;
		}
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	public SQLiteDatabase getDb() {	
		return this.getWritableDatabase() ;
	}
	

}
