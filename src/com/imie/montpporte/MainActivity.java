package com.imie.montpporte;


import java.util.ArrayList;

import com.imie.montpporte.bdd.MonTpPorteSQLiteOpenHelper;
import com.imie.montpporte.data.UserSQLiteAdapter;
import com.imie.montpporte.data.ZoneSQLiteAdapter;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final MonTpPorteSQLiteOpenHelper helper = new 
				MonTpPorteSQLiteOpenHelper(this, "dbPorte",
						null, R.string.app_version);
		
		SQLiteDatabase db = helper.getDb();
		ZoneSQLiteAdapter zonesqladapter = new ZoneSQLiteAdapter(db);
		ArrayList<Zone>  zones = zonesqladapter.getAll();
		
		final Spinner s = (Spinner) this.findViewById(R.id.spinnerStations);
		ArrayAdapter<Zone> spinnerArrayAdapter = new ArrayAdapter<Zone>(this,
			        android.R.layout.simple_spinner_dropdown_item,zones);
			    s.setAdapter(spinnerArrayAdapter);
		
		Button btnConnexion = (Button) this.findViewById(R.id.btnConnexion);        
        btnConnexion.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SQLiteDatabase db = helper.getDb();
				UserSQLiteAdapter useradapter = new UserSQLiteAdapter(db);
				EditText login = (EditText) MainActivity.this.findViewById(
						R.id.editTextLogin);
				EditText pwd = (EditText) MainActivity.this.findViewById(
								R.id.editTextPswd);
				
				String nameValue = login.getText().toString();
				String pwdValue = pwd.getText().toString();
				User user = useradapter.getByLogin(nameValue);
				
				if( nameValue != null && pwdValue != null && user != null )
				{
					if(user.getPassword().toString().equals(pwdValue)){
						//Display Welcome Toast
						Toast toast = Toast.makeText(MainActivity.this, 
								"Bienvenue "+user.getLogin().toString(),  
								Toast.LENGTH_LONG);
						toast.show();
						Intent i = new Intent(MainActivity.this,
								StationActivity.class);
						i.putExtra("user", user);
						i.putExtra("station", s.getSelectedItem().toString());
						MainActivity.this.startActivity(i);
					}else{
						Toast toast = Toast.makeText(MainActivity.this, 
								"Mauvais mot de passe",  Toast.LENGTH_LONG);
						toast.show();
					}
				}else{
					Toast toast = Toast.makeText(MainActivity.this, 
							"Données non valides",  Toast.LENGTH_LONG);
					toast.show();
				}
				db.close();
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
	        Intent i = new Intent(MainActivity.this,
	        		GestionDonneesActivity.class);
	        startActivity(i);
	    }
	    
	    
}
