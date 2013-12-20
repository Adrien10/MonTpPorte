package com.imie.montpporte.bdd;

import com.imie.montpporte.data.CommandeSQLiteAdapter;
import com.imie.montpporte.data.LogProdSQLiteAdapter;
import com.imie.montpporte.data.ProductionSQLiteAdapter;
import com.imie.montpporte.data.UserSQLiteAdapter;
import com.imie.montpporte.data.ZoneSQLiteAdapter;
import com.imie.montpporte.model.Commande;
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
		db.execSQL( LogProdSQLiteAdapter.getSchema() );
		
		UserSQLiteAdapter usersqladapter = new UserSQLiteAdapter(db);
		User u = new User("a","a");
		User u2 = new User("admin","admin");
		User u3 = new User("Adrien","");
		usersqladapter.insert(u);
		usersqladapter.insert(u2);
		usersqladapter.insert(u3);
		
		ZoneSQLiteAdapter zonesqladapter = new ZoneSQLiteAdapter(db);
		Zone zoneMagasin = new Zone("Magasin",10,null);
		Zone zone4 = new Zone("Assemblage",10,zoneMagasin);
		Zone zone3 = new Zone("Peinture",10,zone4);
		Zone zone2 = new Zone("Façonnage",10,zone3);
		Zone zone = new Zone("Découpage",10,zone2);
		
		
		
		zonesqladapter.insert(zone);
		zonesqladapter.insert(zone2);
		zonesqladapter.insert(zone3);
		zonesqladapter.insert(zone4);
		zonesqladapter.insert(zoneMagasin);
	
		
		CommandeSQLiteAdapter cdesqladapter = new CommandeSQLiteAdapter(db);
		Commande cde = new Commande(2,"Porte","Acier",1);
		Commande cde2 = new Commande(2,"Porte","Bois",2);
		Commande cde3 = new Commande(2,"Porte","Fer",3);
		Commande cde4 = new Commande(2,"Porte","Acier",1);
		Commande cde5 = new Commande(2,"Porte","Bois",5);
		Commande cde6 = new Commande(2,"Porte","Fer",69);
		cdesqladapter.insert(cde);
		cdesqladapter.insert(cde2);
		cdesqladapter.insert(cde3);
		cdesqladapter.insert(cde4);
		cdesqladapter.insert(cde5);
		cdesqladapter.insert(cde6);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	public SQLiteDatabase getDb() {	
		return this.getWritableDatabase() ;
	}
	

}
