package org.hdm.app.sambia.views;



import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import org.hdm.app.sambia.R;
import org.hdm.app.sambia.listener.MenuListener;


public class MyImageView extends RelativeLayout implements View.OnClickListener{

	
	/* ############################  Init Variabals/Classes- ################################### */

	// private static final String TAG = SliderView.class.getName();



	private Context context;
	private View view;
	private View iv_flip;
	private Context parentContext;


	private MenuListener mListener;

	
	
	
	
	/* ############################  Constructor  ################################### */

	public MyImageView(Context context) {
		super(context);
		this.context = context;
		initView();
	}


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
		view.setOnClickListener(this);
	}


	public void setOnCountReachedListener(MenuListener listener) {
		mListener = listener;
	}



	@Override
	public void onClick(View v) {
//		mListener.mClickInteraction(v);
	}


	public void setParentContext() {
		parentContext = null;
	}
}
