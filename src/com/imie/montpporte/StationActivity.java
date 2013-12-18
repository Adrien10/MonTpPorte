package com.imie.montpporte;


import com.imie.montpporte.model.User;
import com.imie.montpporte.model.Zone;

import java.util.ArrayList;

import com.imie.montpporte.bdd.MonTpPorteSQLiteOpenHelper;
import com.imie.montpporte.data.ProductionSQLiteAdapter;
import com.imie.montpporte.model.Production;


import android.app.ActionBar;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class StationActivity extends Activity  {
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_station);
        //this.setResult(RESULT_CANCELED); 
        

        ActionBar actionBar = getActionBar();
        // Enabling Up / Back navigation
        actionBar.setDisplayHomeAsUpEnabled(true);
    	
    	User user = (User) this.getIntent()
        		.getSerializableExtra("user");
    	
    	Zone zone = (Zone) this.getIntent()
        		.getSerializableExtra("station");
    	
    	this.setTitle("Station : "+ zone.toString());
    	
    	TextView Hello = (TextView) StationActivity.this
				.findViewById(R.id.textViewUser);
    	Hello.setText(user.getLogin());

        MonTpPorteSQLiteOpenHelper helper = new 
				MonTpPorteSQLiteOpenHelper(this, "dbPorte",
						null, R.string.app_version);
        
        SQLiteDatabase db = helper.getDb();
        ProductionSQLiteAdapter productionesqladapter =
				new ProductionSQLiteAdapter(db); 

        ArrayList<Production> productions = productionesqladapter.getAll();
    	Spinner s = (Spinner) this.findViewById(R.id.spinnerListeProduction);
		ArrayAdapter<Production> spinnerArrayAdapter = 
				new ArrayAdapter<Production>(this,
						android.R.layout.simple_spinner_dropdown_item,
						productions);
			    s.setAdapter(spinnerArrayAdapter);

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

            return true;
        case R.id.action_settings:
            // check for updates action
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
}
