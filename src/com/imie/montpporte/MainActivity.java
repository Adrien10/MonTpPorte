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
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		MonTpPorteSQLiteOpenHelper helper = new MonTpPorteSQLiteOpenHelper(
				this, "dbPorte", null, R.string.app_version);
		SQLiteDatabase db = helper.getWritableDatabase() ;
		
		UserSQLiteAdapter usersqladapter = new UserSQLiteAdapter(db);
		User u = new User("Adrien","pswd");
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
		
		Button btnConnexion = (Button) this.findViewById(R.id.btnConnexion);        
        btnConnexion.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		//return true;
		return super.onCreateOptionsMenu(menu);
	}
	
	 @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        // Take appropriate action for each action item click
	        switch (item.getItemId()) {
	        case R.id.action_gestiondonnees:
	        	GestionDonnees();
	            return true;
	        case R.id.action_settings:
	            // check for updates action
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	        }
	    }
	 
	    /**
	     * Launching new activity
	     * */
	    private void GestionDonnees() {
	        Intent i = new Intent(MainActivity.this, GestionDonneesActivity.class);
	        startActivity(i);
	    }
	    
	    public void onClick(View v) {
			
			Button btn = (Button) v;
			
			btn.getId();
			
			Button button = (Button) this.findViewById(R.id.btnConnexion);
	        
			if (v == button) {
				Toast tt = Toast.makeText(this, "Hello", Toast.LENGTH_LONG);
				tt.show();
				
				User user = new User();
				user.setLogin("Adrien");
				user.setPassword("a");
				
				Intent intent = new Intent(this, StationActivity.class);
				this.startActivity(intent);
				//this.startActivityForResult(intent, 1);
				//this.finish();
			}
			
		}
}
