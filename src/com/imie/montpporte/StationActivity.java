package com.imie.montpporte;

import com.imie.montpporte.model.Production;


import android.app.ActionBar;
import android.app.Activity;
import android.app.LauncherActivity.ListItem;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class StationActivity extends Activity  {
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_station);
        //this.setResult(RESULT_CANCELED); 
        
        // r�cup�re action bar  
        ActionBar actionBar = getActionBar();
        // Enabling Up / Back navigation
        actionBar.setDisplayHomeAsUpEnabled(true);
        
    	Spinner spinner = (Spinner) findViewById(R.id.spinnerListeProduction);
	}
}
