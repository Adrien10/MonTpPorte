package com.imie.montpporte;

import com.imie.montpporte.model.User;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;



public class SearchActivity extends Activity  {
	User user;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_search);
        
        user = (User) this.getIntent()
        		.getSerializableExtra("user");
        
     // Activation du retour par l'action bar
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
	}
}
