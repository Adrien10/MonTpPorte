package com.imie.montpporte;

import com.imie.montpporte.model.LogProd;
import com.imie.montpporte.model.User;
import com.imie.montpporte.model.Zone;

import java.util.ArrayList;

import com.imie.montpporte.bdd.MonTpPorteSQLiteOpenHelper;
import com.imie.montpporte.data.LogProdSQLiteAdapter;
import com.imie.montpporte.data.ProductionSQLiteAdapter;
import com.imie.montpporte.model.Production;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.Spinner;

public class StationActivity extends Activity  {

	User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_station);
        
        user = (User) this.getIntent()
        		.getSerializableExtra("user");
        
     // Activation du retour par l'action bar
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    	
     // Mise � jour du titre de l'activity
    	final Zone zone = (Zone) this.getIntent().getSerializableExtra("station");
    	this.setTitle("Station : "+ zone.toString());
     
    	// Connexion � la bdd
    	MonTpPorteSQLiteOpenHelper helper = new 
				MonTpPorteSQLiteOpenHelper(this, "dbPorte",
						null, R.string.app_version);
        SQLiteDatabase db = helper.getDb();
        
     // Initialisation du spinner des lignes de production
        final ProductionSQLiteAdapter productionesqladapter =
				new ProductionSQLiteAdapter(db);
        
        final LogProdSQLiteAdapter logprodsqladapter =
				new LogProdSQLiteAdapter(db);
        
        final ArrayList<Production> productions = 
        		productionesqladapter.getAllFromZone(zone);
    	final Spinner spinnerProduction = (Spinner) this.findViewById(
    			R.id.spinnerListeProduction);
		final ArrayAdapter<Production> spinnerArrayAdapter = 
				new ArrayAdapter<Production>(this,
						android.R.layout.simple_spinner_dropdown_item,
						productions);
		spinnerProduction.setAdapter(spinnerArrayAdapter);
		
	  // Recup�ration des objects
	    final Button btnStart = (Button) this.findViewById(R.id.btnStart); 
	    final Button btnStop = (Button) this.findViewById(R.id.btnStop);
	    
	  // Ev�nements 
	    btnStart.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				btnStart.setEnabled(false);
				btnStop.setEnabled(true);
				spinnerProduction.setEnabled(false);
				// Log de d�but de production
				Production ligneproduction = (Production) 
			    		spinnerProduction.getSelectedItem();
				LogProd log = new LogProd("Start",ligneproduction,
						user,zone);
				logprodsqladapter.insert(log);
			}
		});
	    btnStop.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final Production ligneproduction = (Production) 
			    		spinnerProduction.getSelectedItem();
				
				btnStart.setEnabled(true);
				btnStop.setEnabled(false);
				spinnerProduction.setEnabled(true);
				
				// Log de fin de production
				productions.remove(ligneproduction);
				spinnerArrayAdapter.remove(ligneproduction);
				
				ligneproduction.setStationCourante(
						zone.getStation_destination());
				productionesqladapter.update(ligneproduction);
				
				spinnerArrayAdapter.notifyDataSetChanged();
				// Log de fin de production
				LogProd log = new LogProd("Stop",ligneproduction,
						user,zone);
				logprodsqladapter.insert(log);
			}
		});
	}
    
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_station, menu);
		
		return super.onCreateOptionsMenu(menu);
	}
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
        case R.id.action_info:
        	// Appele la dialog qui d�tail l'utilisateur connect�
        	displayDialog();
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
    
    public void displayDialog(){
    	AlertDialog alertDialog = new AlertDialog.Builder(this).create();
    	alertDialog.setTitle("Utilisateur connect�");
    	alertDialog.setMessage(user.getLogin());
    	alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
    	public void onClick(DialogInterface dialog, int which) {
    		// here you can add functions
    	}
    	});
    	alertDialog.setIcon(R.drawable.ic_action_about);
    	alertDialog.show();
    }
}
