package com.imie.montpporte;


import com.imie.montpporte.bdd.MonTpPorteSQLiteOpenHelper;
import com.imie.montpporte.data.CommandeSQLiteAdapter;
import com.imie.montpporte.data.ProductionSQLiteAdapter;
import com.imie.montpporte.data.UserSQLiteAdapter;
import com.imie.montpporte.data.ZoneSQLiteAdapter;
import com.imie.montpporte.model.Commande;
import com.imie.montpporte.model.Production;
import com.imie.montpporte.model.User;
import com.imie.montpporte.model.Zone;

import android.os.Bundle;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		MonTpPorteSQLiteOpenHelper helper = new MonTpPorteSQLiteOpenHelper(
				this, "dbPorte", null, R.string.app_version);
		SQLiteDatabase db = helper.getWritableDatabase() ;
		
		UserSQLiteAdapter usersqladapter = new UserSQLiteAdapter(db);
		User u = new User("Adrien","password");
		usersqladapter.insert(u);
		
		ZoneSQLiteAdapter zonesqladapter = new ZoneSQLiteAdapter(db);
		Zone zone = new Zone("Découpage",10);
		zonesqladapter.insert(zone);
		
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
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
