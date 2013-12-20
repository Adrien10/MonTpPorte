package com.imie.montpporte.fragment;

import java.util.ArrayList;

import com.imie.montpporte.R;
import com.imie.montpporte.bdd.MonTpPorteSQLiteOpenHelper;
import com.imie.montpporte.data.UserSQLiteAdapter;
import com.imie.montpporte.data.ZoneSQLiteAdapter;
import com.imie.montpporte.model.User;
import com.imie.montpporte.model.Zone;

import android.app.Fragment;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class StationGestionFragment extends Fragment {
	
	public StationGestionFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        final View rootView = inflater.inflate(R.layout.fragment_gestion_station
        		, container, false);
        //instantiate helper
        MonTpPorteSQLiteOpenHelper helper = new 
				MonTpPorteSQLiteOpenHelper(rootView.getContext(), "dbPorte",
						null, R.string.app_version);
        final SQLiteDatabase db = helper.getDb(); 
        Button btnAdd= (Button) rootView.findViewById(R.id.buttonAddStation); 
        final ZoneSQLiteAdapter stationAdapter = new ZoneSQLiteAdapter(db);
        final ArrayList<Zone>  zones = stationAdapter.getAll();
        final Spinner s = (Spinner) rootView.findViewById(R.id.
        		spinnerStationDestination);
		final ArrayAdapter<Zone> spinnerArrayAdapter = new ArrayAdapter<Zone>(
				rootView.getContext(),android.R.layout.
				simple_spinner_dropdown_item,zones);
			    s.setAdapter(spinnerArrayAdapter);
	         
	    btnAdd.setOnClickListener(new OnClickListener() {
	    	@Override
			public void onClick(View v) {
	    		 //Get back text in name station  edit text
		        EditText name = (EditText) rootView.findViewById(
						R.id.editTextNameStation);
		        EditText tampon = (EditText) rootView.findViewById(
						R.id.editTextTampon);
		       
				if(!tampon.getText().toString().isEmpty())
				{				 
					int tamponValue = Integer.parseInt(tampon.getText()
							.toString());
					String nameValue = name.getText().toString();
					if( nameValue != null)
					{
							Zone zonespinner = (Zone) s.getSelectedItem();
							//insert a user
							Zone zone = new Zone(nameValue,tamponValue,
									zonespinner);
							stationAdapter.insert(zone);
							//Display a toast 
							Toast toast = Toast.makeText(rootView.getContext(), 
									"Zone enregistré",  Toast.LENGTH_LONG);
							toast.show();
					 }else{
						 Toast toast = Toast.makeText(rootView.getContext(), 
									"Donnée invalide",  Toast.LENGTH_LONG);
						 toast.show();
					 }
				}else{
					Toast toast = Toast.makeText(rootView.getContext(), 
							"Donnée invalide",  Toast.LENGTH_LONG);
					toast.show();
				}
	    	}
	    });
        
         
        return rootView;
    }
}
