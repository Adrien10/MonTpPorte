package com.imie.montpporte.fragment;

import com.imie.montpporte.R;
import com.imie.montpporte.bdd.MonTpPorteSQLiteOpenHelper;
import com.imie.montpporte.data.CommandeSQLiteAdapter;
import com.imie.montpporte.data.ZoneSQLiteAdapter;
import com.imie.montpporte.model.Commande;
import com.imie.montpporte.model.User;

import android.app.Fragment;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class CommandGestionFragment extends Fragment {
	
	public CommandGestionFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		final View rootView = inflater.inflate(R.layout.
				fragment_gestion_commande, 
				container, false);
		//instantiate helper
        MonTpPorteSQLiteOpenHelper helper = new 
				MonTpPorteSQLiteOpenHelper(rootView.getContext(), "dbPorte",
						null, R.string.app_version);
        final SQLiteDatabase db = helper.getDb();
        final CommandeSQLiteAdapter cmdsqladapter = 
        		new CommandeSQLiteAdapter(db);
        Button btnAddCmd= (Button) rootView.findViewById(R.id.buttonAddCommand);
        btnAddCmd.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				 RadioGroup product = (RadioGroup) rootView.findViewById
						 (R.id.radioprod);
				 RadioGroup material = (RadioGroup) rootView.findViewById
						 (R.id.radiomat);
				 EditText nbProduct = (EditText) rootView.findViewById
						 (R.id.editTextNbProduct); 
				 EditText numClt = (EditText) rootView.findViewById
						 (R.id.editTextNumClt); 
				//Returns integer represents the selected radio button's ID
				 int selectedMat = material.getCheckedRadioButtonId();
				 int selectedProd = product.getCheckedRadioButtonId();
				 int numberValue = Integer.parseInt(nbProduct.getText()
						 .toString());
				 int numberClt = Integer.parseInt(nbProduct.getText()
						 .toString());
				 // Gets a reference to our "selected" radio button
				 RadioButton mat = (RadioButton) rootView.findViewById
						 (selectedMat);
				 RadioButton prod = (RadioButton) rootView.findViewById
						 (selectedProd); 
				 //get the text  from the "selected" radio button
				 
				 String productValue = (String) prod.getText();
				 String materialValue = (String) mat.getText();

				 
				 if( productValue != null && materialValue != null)
				 {
					 	//insert a commande
						Commande cmd = new Commande(numberValue,productValue,
								materialValue,numberClt);
						cmdsqladapter.insert(cmd);
						//Display a toast 
						Toast toast = Toast.makeText(rootView.getContext(), 
								"Commande enregistrée",  Toast.LENGTH_LONG);
						toast.show();
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
