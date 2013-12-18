package com.imie.montpporte;

import java.util.ArrayList;

import com.imie.montpporte.bdd.MonTpPorteSQLiteOpenHelper;
import com.imie.montpporte.data.ProductionSQLiteAdapter;
import com.imie.montpporte.model.Production;

import android.app.ActionBar;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class StationActivity extends Activity  {
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_station);
        //this.setResult(RESULT_CANCELED); 
        
        // récupèrer action bar  
        ActionBar actionBar = getActionBar();
        // Enabling Up / Back navigation
        actionBar.setDisplayHomeAsUpEnabled(true);
        
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
}
