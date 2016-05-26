package org.hdm.app.sambia.screens;



import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.hdm.app.sambia.R;
import org.hdm.app.sambia.listener.MainListener;


public class FragmentContainer extends Fragment  implements
		FragmentManager.OnBackStackChangedListener,
		MainListener {


	private FragmentActivity activityFragemnt;
	private View view;
	private boolean mShowingBack = false;
	private FragmentDayView dayViewFragemnt;







	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		view = inflater.inflate(R.layout.fragment_main, container, false);


		initFragments();
		loadFragment(savedInstanceState);
		getFragmentManager().addOnBackStackChangedListener(this);

		return view;
	}





	private void initFragments() {
		dayViewFragemnt = new FragmentDayView(this);
		activityFragemnt = new FragmentActivity(this);
	}




	private void loadFragment(Bundle savedInstanceState) {
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction().add(R.id.container, activityFragemnt).commit();
		} else {
			mShowingBack = (getFragmentManager().getBackStackEntryCount() > 0);
		}
	}








	private void flipCard() {

		if (mShowingBack) {
			getFragmentManager().popBackStack();
			return;
		}

		// Flip to the back.
		mShowingBack = true;

		getFragmentManager().beginTransaction()
				.setCustomAnimations(
						R.animator.card_flip_right_in, R.animator.card_flip_right_out,
						R.animator.card_flip_left_in, R.animator.card_flip_left_out)
				.replace(R.id.container, dayViewFragemnt)
				.addToBackStack(null)
				.commit();
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







