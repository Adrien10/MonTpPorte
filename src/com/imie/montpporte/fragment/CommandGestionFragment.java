package com.imie.montpporte.fragment;

import com.imie.montpporte.R;
import com.imie.montpporte.bdd.MonTpPorteSQLiteOpenHelper;
import com.imie.montpporte.model.User;

import android.app.Fragment;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CommandGestionFragment extends Fragment {
	
	public CommandGestionFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_gestion_commande, 
				container, false);
		//instantiate helper
        MonTpPorteSQLiteOpenHelper helper = new 
				MonTpPorteSQLiteOpenHelper(rootView.getContext(), "dbPorte",
						null, R.string.app_version);
		
        final SQLiteDatabase db = helper.getDb();
        Button btnAddCmd= (Button) rootView.findViewById(R.id.buttonAddCommand);
        btnAddCmd.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
			
			}
	    });
        
         
        return rootView;
    }
}
