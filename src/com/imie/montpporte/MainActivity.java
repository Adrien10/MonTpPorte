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
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final MonTpPorteSQLiteOpenHelper helper = new MonTpPorteSQLiteOpenHelper(
				this, "dbPorte", null, R.string.app_version);
		SQLiteDatabase db = helper.getWritableDatabase() ;
		
		UserSQLiteAdapter usersqladapter = new UserSQLiteAdapter(db);
		User u = new User("a","a");
		usersqladapter.insert(u);
		
		ZoneSQLiteAdapter zonesqladapter = new ZoneSQLiteAdapter(db);
		Zone zone = new Zone("D�coupage",10);
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
				
				UserSQLiteAdapter adapter = new UserSQLiteAdapter(
						helper.getWritableDatabase());
				
				EditText login = (EditText) MainActivity.this.findViewById(
						R.id.editTextLogin);
				EditText pwd = (EditText) MainActivity.this.findViewById(
								R.id.editTextPswd);
				
				String nameValue = login.getText().toString();
				String pwdValue = pwd.getText().toString();
				User user = adapter.getByLogin(nameValue);
				
				if( nameValue != null && pwdValue != null && user!= null )
				{
					if(user.getPassword().toString().equals(pwdValue)){
						//Display Welcome Toast
						Toast toast = Toast.makeText(MainActivity.this, 
								"Bienvenue "+user.getLogin().toString(),  
								Toast.LENGTH_LONG);
						toast.show();
						Intent i = new Intent(MainActivity.this, StationActivity.class);
				        startActivity(i);
						
					}
				}
				else{
					Toast toast = Toast.makeText(MainActivity.this, 
							"Mauvais mot de passe",  Toast.LENGTH_LONG);
					toast.show();
				}
				
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
	    
	    
}
