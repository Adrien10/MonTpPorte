package com.imie.montpporte;

import com.imie.montpporte.model.User;
import com.imie.montpporte.model.Zone;

import java.util.ArrayList;



import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class StationActivity extends Activity  {
	
	private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;

    private CharSequence mTitle;
    private String[] mGestionTitles;
    @Override
    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_donnees);
    mTitle = getTitle();
    
    mGestionTitles = getResources().getStringArray(R.array.action_array);
    mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
    mDrawerList = (ListView) findViewById(R.id.left_drawer);

    
    // set up the drawer's list view with items and click listener
    mDrawerList.setAdapter(new ArrayAdapter<String>(this,
            R.layout.drawer_list_item, mGestionTitles));
    mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

    // enable ActionBar app icon to behave as action to toggle nav drawer
    getActionBar().setDisplayHomeAsUpEnabled(true);
    getActionBar().setHomeButtonEnabled(true);

    // ActionBarDrawerToggle ties together the the proper interactions
    // between the sliding drawer and the action bar app icon
   
//    if (savedInstanceState != null) {
//    	int position = savedInstanceState.getInt("");
//    	if (position != 0)
//    		selectItem(position);
//    }
}
	
	    
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.menu_slide, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	/* The click listner for ListView in the navigation drawer */
	private class DrawerItemClickListener implements 
		ListView.OnItemClickListener {
	    @Override
	    public void onItemClick(AdapterView<?> parent, View view, int position,
	    		long id) {
	        selectItem(position);
	    }
	}
	
	private void selectItem(int position) {
	    // update the main content by replacing intent
		 //Switch activity 
		Intent intent = new Intent();
	    switch(position) {
	    case 0:
	    	intent.setClass(this,
	    			GestionUserActivity.class);
	        break;
	    case 1:
	    	intent.setClass(this, 
	    			GestionCommandActivity.class);
	        break;
	    case 2:
	    	intent.setClass(this, 
	    			GestionStationActivity.class);
	        break;
	        
	    default:
	    	intent.setClass(this, 
	    			GestionDonneesActivity.class);
	    }
	    
	    this.startActivity(intent);
	
	    // update selected item and title, then close the drawer
	    mDrawerList.setItemChecked(position, true);
	    setTitle(mGestionTitles[position]);
	    mDrawerLayout.closeDrawer(mDrawerList);
	}
	
	/**
	 * Change title name
	 */
	@Override
	public void setTitle(CharSequence title) {
	    mTitle = title;
	    getActionBar().setTitle(mTitle);
	}




	
	/**
	 * Fragment that appears in the "content_frame", shows a planet
	 */
	public static class actionFragment extends Fragment {
	    public static final String ARG_FRAGMENT_NUMBER = "fragment_number";
	
	    public actionFragment() {
	        // Empty constructor required for fragment subclasses
	    }
	
	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	        View rootView = inflater.inflate(R.layout.fragment_action,
	        		container, false);
	        int i = getArguments().getInt(ARG_FRAGMENT_NUMBER);
	        String action = getResources().getStringArray(
	        		R.array.action_array)[i];
	            
	
	        getActivity().setTitle(action);
	        
	        return rootView;
	    }
	}
}
