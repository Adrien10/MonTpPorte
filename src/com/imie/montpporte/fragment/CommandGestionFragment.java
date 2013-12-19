package com.imie.montpporte.fragment;

import com.imie.montpporte.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CommandGestionFragment extends Fragment {
	
	public CommandGestionFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_gestion_commande, container, false);
         
        return rootView;
    }
}
