package com.imie.montpporte;



import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class GestionUserActivity extends Activity {

	private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;

    private CharSequence mTitle;
    private String[] mGestionTitles;
    @Override
    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_user);
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
	    
	    
	
	    // update selected item and title, then close the drawer
	    mDrawerList.setItemChecked(position, true);
	    //setTitle(mGestionTitles[position]);
	    mDrawerLayout.closeDrawer(mDrawerList);
	    this.startActivity(intent);
	}
	
	/**
	 * Change title name
	 */
	@Override
	public void setTitle(CharSequence title) {
	    mTitle = title;
	    getActionBar().setTitle(mTitle);
	}
}
