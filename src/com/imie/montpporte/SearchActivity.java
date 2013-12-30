package com.imie.montpporte;

import java.util.ArrayList;

import com.imie.montpporte.bdd.MonTpPorteSQLiteOpenHelper;
import com.imie.montpporte.data.CommandeSQLiteAdapter;
import com.imie.montpporte.data.LogProdSQLiteAdapter;
import com.imie.montpporte.model.Commande;
import com.imie.montpporte.model.LogProd;
import com.imie.montpporte.model.User;

import android.app.ActionBar;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class SearchActivity extends Activity  {
	User user;
	SQLiteDatabase db;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_search);
        
        user = (User) this.getIntent()
        		.getSerializableExtra("user");
        
     // Back to main page
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        
        final MonTpPorteSQLiteOpenHelper helper = new MonTpPorteSQLiteOpenHelper
        		(this, "dbPorte", null, R.string.app_version);
		
		db = helper.getDb();
		CommandeSQLiteAdapter cdessqladapter = new CommandeSQLiteAdapter(db);
		ArrayList<Commande>  cdes = cdessqladapter.getAll();
		
	 // Get back all command in a spinner
		final Spinner spinnerCdes = (Spinner) this.findViewById(
				R.id.spinnerCdeSearch);
		ArrayAdapter<Commande> spinnerArrayAdapter = new ArrayAdapter<Commande>
		(this, android.R.layout.simple_spinner_dropdown_item,cdes);
		spinnerCdes.setAdapter(spinnerArrayAdapter);
		
		final EditText resume = (EditText) this.findViewById(R.id.editTextResume);
	 // Action Resume
		Button btnSearch = (Button) this.findViewById(R.id.btnSearch);        
		btnSearch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String text = new String();
				// Recupération des valeurs
				LogProdSQLiteAdapter logprodsqladapter = new 
						LogProdSQLiteAdapter(db);
				ArrayList<LogProd>  logprods = logprodsqladapter.getAll(
						(Commande) spinnerCdes.getSelectedItem());
				
				if(!logprods.isEmpty())
				{
				   int len = logprods.size();
				   for(int i=0; i<len; i++)
				   {
					   text += logprods.get(i).toString() + "\n";
				   }
				}
				resume.setText(text);
				
			}
		});
		
//		db.close();
	}
}
