package org.hdm.app.sambia.views;



import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.hdm.app.sambia.R;
import org.hdm.app.sambia.main.MainActivityListener;
import org.hdm.app.sambia.screens.ActivityFragemnt;
import org.hdm.app.sambia.screens.DayViewFragemnt;


public class BaseFragment extends Fragment  implements
		FragmentManager.OnBackStackChangedListener,
		MainActivityListener {




	private View view;


	private Handler mHandler = new Handler();
	private boolean mShowingBack = false;

	private DayViewFragemnt dayViewFragemnt = new DayViewFragemnt();
	private ActivityFragemnt activityFragemnt = new ActivityFragemnt();

	/* ############################  Init Variabals/Classes- ################################### */

	// private static final String TAG = SliderView.class.getName();







	/* ############################  Constructor  ################################### */



	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		view = inflater.inflate(R.layout.fragment_main, container, false);



		if (savedInstanceState == null) {
			// If there is no saved instance state, add a fragment representing the
			// front of the card to this activity. If there is saved instance state,
			// this fragment will have already been added to the activity.
			getFragmentManager()
					.beginTransaction()
					.add(R.id.container, activityFragemnt)
					.commit();

			activityFragemnt.setListener(this);
		} else {
			mShowingBack = (getFragmentManager().getBackStackEntryCount() > 0);
		}


		// Monitor back stack changes to ensure the action bar shows the appropriate
		// button (either "photo" or "info").
		getFragmentManager().addOnBackStackChangedListener(this);

		return view;
	}






	private void flipCard() {
		if (mShowingBack) {
			getFragmentManager().popBackStack();
			return;
		}

		// Flip to the back.
		mShowingBack = true;

		getFragmentManager()
				.beginTransaction()
				.setCustomAnimations(
						R.animator.card_flip_right_in, R.animator.card_flip_right_out,
						R.animator.card_flip_left_in, R.animator.card_flip_left_out)
				.replace(R.id.container, dayViewFragemnt)
				.addToBackStack(null)
				.commit();



				dayViewFragemnt.setListener(this);
		mHandler.post(new Runnable() {
			@Override
			public void run() {
//                invalidateOptionsMenu();
			}
		});
	}





	@Override
	public void onBackStackChanged() {
		mShowingBack = (getFragmentManager().getBackStackEntryCount() > 0);
	}






	@Override
	public void flip() {
		flipCard();
	}


	


}







