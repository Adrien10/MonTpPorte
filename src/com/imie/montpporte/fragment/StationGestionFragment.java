package com.imie.montpporte.fragment;

import com.imie.montpporte.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class StationGestionFragment extends Fragment {
	
	public StationGestionFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_gestion_station, container, false);
         
        return rootView;
    }
}
