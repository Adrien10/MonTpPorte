package com.imie.montpporte;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Spinner;

public class StationActivity extends Activity  {
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_station);
        //this.setResult(RESULT_CANCELED); 
        
        // récupère action bar  
        ActionBar actionBar = getActionBar();
        // Enabling Up / Back navigation
        actionBar.setDisplayHomeAsUpEnabled(true);
        
    	Spinner spinner = (Spinner) findViewById(R.id.spinnerListeProduction);
    	
    	
	}
}
