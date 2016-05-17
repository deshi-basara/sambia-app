package org.hdm.app.sambia.views;



import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.hdm.app.sambia.R;
import org.hdm.app.sambia.main.MainActivityListener;
import org.hdm.app.sambia.screens.ActivityFragemnt;
import org.hdm.app.sambia.screens.DayViewFragemnt;


public class MenuFragment extends Fragment {

	private static final String TAG = "MenuFragment";

	private View view;




	/* ############################  Init Variabals/Classes- ################################### */








	/* ############################  Constructor  ################################### */



	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		view = inflater.inflate(R.layout.fragment_menu, container, false);

		return view;
	}


	public static void newInstance() {

	}
}







