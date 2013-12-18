package com.imie.montpporte;

<<<<<<< HEAD
import com.imie.montpporte.model.User;
=======
import java.util.ArrayList;

import com.imie.montpporte.bdd.MonTpPorteSQLiteOpenHelper;
import com.imie.montpporte.data.ProductionSQLiteAdapter;
import com.imie.montpporte.model.Production;
>>>>>>> 0d89d8cae6aa58471381525322033d4aa2e8b353

import android.app.ActionBar;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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
        
<<<<<<< HEAD
       
        // récupère action bar  
=======
        // récupèrer action bar  
>>>>>>> 0d89d8cae6aa58471381525322033d4aa2e8b353
        ActionBar actionBar = getActionBar();
        // Enabling Up / Back navigation
        actionBar.setDisplayHomeAsUpEnabled(true);
        
<<<<<<< HEAD
    	Spinner spinner = (Spinner) findViewById(R.id.spinnerListeProduction);
    	
    	User user = (User) this.getIntent()
        		.getSerializableExtra("user");
    	TextView Hello = (TextView) StationActivity.this
				.findViewById(R.id.textViewUser);
    	Hello.setText(user.getLogin());
=======
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
>>>>>>> 0d89d8cae6aa58471381525322033d4aa2e8b353
    	
	}
}
