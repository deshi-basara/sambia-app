package org.hdm.app.sambia.views;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import org.hdm.app.sambia.R;
import org.hdm.app.sambia.listener.MenuListener;
import org.hdm.app.sambia.listener.SliderListener;

/**
 * A simple view that will expand horizontally while the user drags their finger vertically across
 * the screen.
 * 
 * Limitations: this view will expand until the whole width of the device's screen has been
 * occupied. Override this behavior by setting your own mScreenWidth.
 * 
 * @author quiqueqs
 * 
 */
public class DisplayActivityView extends RelativeLayout implements View.OnClickListener{

	private LayoutParams mLayoutParams;
	private float mStartYPosition = -1.0f;
	private int mScreenWidth;
	private OnTouchListener mOnTouchListener;
	private SliderListener mSliderListener;
	private Context context;
	private MenuListener listener;


	private View view;
//	private Paint mPaint;


	public DisplayActivityView(Context context) {

		this(context, null);
		this.context = context;


	}

	public DisplayActivityView(Context context, AttributeSet attrs) {

		this(context, attrs, 0);
		this.context = context;

	}

	public DisplayActivityView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

//		mLayoutParams = new android.widget.RelativeLayout.LayoutParams(context, attrs);
		mLayoutParams = new LayoutParams(context, attrs);
		initView(context);
		this.context = context;
	}




	/* ############################ inti View  ################################### */


	/**
	 * init View
	 */
	private void initView(Context context) {

		LayoutInflater inflater = (LayoutInflater)
				context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		view = inflater.inflate(R.layout.view_dispaly_activity, this, true);
		view.setOnClickListener(this);

	}


	public void setSliderListener(SliderListener listener) {
		this.mSliderListener = listener;
	}



	public void initialize(Activity activity) {
		activity.getWindow().getDecorView().setOnTouchListener(mOnTouchListener);
	}


	@Override
	public void onClick(View v) {
		listener.mClickInteraction(view);
	}


	public void setListener(MenuListener listener) {
		this.listener = listener;
	}
}
