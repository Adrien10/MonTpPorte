package com.imie.montpporte.fragment;

import java.util.ArrayList;

import com.imie.montpporte.R;
import com.imie.montpporte.bdd.MonTpPorteSQLiteOpenHelper;
import com.imie.montpporte.data.UserSQLiteAdapter;
import com.imie.montpporte.model.User;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
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

public class UserGestionFragment extends Fragment {
	
	public UserGestionFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		
		//get context
        final View rootView = inflater.inflate(R.layout.fragment_gestion_user, 
        		container, false);
        //instantiate helper
        MonTpPorteSQLiteOpenHelper helper = new 
				MonTpPorteSQLiteOpenHelper(rootView.getContext(), "dbPorte",
						null, R.string.app_version);
		
        final SQLiteDatabase db = helper.getDb();
        Button btnAdd= (Button) rootView.findViewById(R.id.buttonAddUser); 
        final UserSQLiteAdapter useradapter = new UserSQLiteAdapter(db);
		final ArrayList<User>  users = useradapter.getAll();
		final Spinner s = (Spinner) rootView.findViewById(R.id.spinnerUser);
		final ArrayAdapter<User> spinnerArrayAdapter = new ArrayAdapter<User>(
				rootView.getContext(),android.R.layout.
				simple_spinner_dropdown_item,users);
			    s.setAdapter(spinnerArrayAdapter);
		Button btnDel= (Button) rootView.findViewById(R.id.buttonDel);        
        btnDel.setOnClickListener(new OnClickListener() {
					
				@Override
				public void onClick(View v) {
				//Create a dialog alert to accept deleting user
				AlertDialog.Builder alert = new AlertDialog.Builder(rootView.
						getContext());
				alert.setTitle("Suppression Utilisateur");//Set title dialog
				alert.setMessage("Etes vous sûr ?");	  //set message dialog
				alert.setPositiveButton("Ok", new DialogInterface.
				OnClickListener() {
					//If OK
				    public void onClick(DialogInterface dialog, int whichButton) 
				    {
				    	User user = (User) s.getSelectedItem();
				    	users.remove(user);
				    	useradapter.delete(user);
				    	spinnerArrayAdapter.notifyDataSetChanged();
						Toast toast = Toast.makeText(rootView.getContext(), 
								"Utilisateur supprimé",  Toast.LENGTH_LONG);
						toast.show();
				    }
				});
				// if cancel
				alert.setNegativeButton("Cancel", new DialogInterface.
						OnClickListener() {
				    public void onClick(DialogInterface dialog, int whichButton) 
				    {
				    //So sth here when "cancel" clicked.
				    }
				});
				alert.show();
			}
        });
        //listen button add user
        btnAdd.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
		       
		        //Get back text in login edit text
		        EditText login = (EditText) rootView.findViewById(
						R.id.editTextAddLogin);
		        //get back password in login edit password
		        EditText pwd = (EditText) rootView.findViewById(
						R.id.editTextAddPassword);
		        //put them in variable
		        String nameValue = login.getText().toString();
				String pwdValue = pwd.getText().toString();
				User user = useradapter.getByLogin(nameValue);
				
				//if input are not empty and user is null
				if( nameValue != null && pwdValue != null && user == null)
				{
					User useradd = new User(nameValue, pwdValue);
					//insert a user
					useradapter.insert(useradd);
					//Refresh spinner
					users.add(useradd);
					spinnerArrayAdapter.notifyDataSetChanged();
					//Display a toast success
					Toast toast = Toast.makeText(rootView.getContext(), 
							"Utilisateur enregistré",  Toast.LENGTH_LONG);
					toast.show();
				}else{
					//Display a toast warning
					Toast toast = Toast.makeText(rootView.getContext(), 
							"Donnée invalide",  Toast.LENGTH_LONG);
					toast.show();
				}
			}
        });
        return rootView;
    }
}
