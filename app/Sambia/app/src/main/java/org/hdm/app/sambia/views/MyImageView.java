package org.hdm.app.sambia.views;



import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import org.hdm.app.sambia.R;
import org.hdm.app.sambia.main.MainActivityListener;
import org.hdm.app.sambia.main.ViewInteractionListener;


public class MyImageView extends RelativeLayout implements View.OnClickListener{

	
	/* ############################  Init Variabals/Classes- ################################### */

	// private static final String TAG = SliderView.class.getName();



	private Context context;
	private View view;
	private View iv_flip;
	private Context parentContext;


	private ViewInteractionListener mListener;

	
	
	
	
	/* ############################  Constructor  ################################### */
	
	/**
	 * Constructor called if View is created dynamic in ContentFragemnt
	 * 
	 * @param context of ContentFragment
	 * 
	 */
	public MyImageView(Context context) {
		super(context);
		this.context = context;
		initView();
	}




	/**
	 * Constructor called if View is created in XML
	 * 
	 * @param context of ContentFragment
	 * @param attr
	 * 
	 */
	public MyImageView(Context context, AttributeSet attr) {
		super(context, attr);
		this.context = context;
		initView();		
	}
	
	
	
	
	
	
	/* ############################ inti View  ################################### */

	
	/**
	 * init View
	 */
	private void initView() {
		LayoutInflater inflater = (LayoutInflater)
				context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		view = inflater.inflate(R.layout.view_slider, this, true);

		iv_flip = view.findViewById(R.id.iv_flip);
		view.setOnClickListener(this);
	}


	public void setOnCountReachedListener(ViewInteractionListener listener) {
		mListener = listener;
	}



	@Override
	public void onClick(View v) {
		mListener.mClickInteraction(v);
	}


	public void setParentContext() {
		parentContext = null;
	}
}
