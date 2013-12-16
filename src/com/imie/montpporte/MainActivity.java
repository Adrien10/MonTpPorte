package com.imie.montpporte;


import com.imie.montpporte.bdd.MonTpPorteSQLiteOpenHelper;
import com.imie.montpporte.data.UserSQLiteAdapter;
import com.imie.montpporte.model.User;

import android.os.Bundle;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		MonTpPorteSQLiteOpenHelper helper = new MonTpPorteSQLiteOpenHelper(
				this, "dbPorte", null, R.string.app_version);
		SQLiteDatabase db = helper.getWritableDatabase() ;
		
		UserSQLiteAdapter usersqladapter = new UserSQLiteAdapter(db);
		User u = new User("Adrien","password");
		usersqladapter.insert(u);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
