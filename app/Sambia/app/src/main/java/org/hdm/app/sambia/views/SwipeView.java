package org.hdm.app.sambia.views;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import org.hdm.app.sambia.R;
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
public class SwipeView extends RelativeLayout {

	private LayoutParams mLayoutParams;
	private float mStartYPosition = -1.0f;
	private int mScreenWidth;
	private OnTouchListener mOnTouchListener;
	private SliderListener mSliderListener;

	private View view;
//	private Paint mPaint;


	public SwipeView(Context context) {
		this(context, null);
	}

	public SwipeView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public SwipeView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

//		mLayoutParams = new android.widget.RelativeLayout.LayoutParams(context, attrs);
		mLayoutParams = new android.widget.RelativeLayout.LayoutParams(context, attrs);


		initView(context);
	}




	/* ############################ inti View  ################################### */


	/**
	 * init View
	 */
	private void initView(Context context) {

		LayoutInflater inflater = (LayoutInflater)
				context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);



		view = inflater.inflate(R.layout.view_slider, this, true);



		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		Point size = getDisplaySize(display);
		mScreenWidth = size.x;

	}


	public void setSliderListener(SliderListener listener) {
		this.mSliderListener = listener;
	}



	public void initialize(Activity activity) {


		mOnTouchListener = new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {

				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					mStartYPosition = event.getY();
					mLayoutParams.height = 0;
					setLayoutParams(mLayoutParams);
				} else if (event.getAction() == MotionEvent.ACTION_MOVE) {

					float distance = event.getY() - mStartYPosition;
					if (mLayoutParams.height >= mScreenWidth) {
						if (mSliderListener != null) {
							mSliderListener.onEndReached();
						}
						return true;
					}

					if (distance > 0 && distance < mScreenWidth) {
						mLayoutParams.height = (int) (distance * distance / 100);
						setLayoutParams(mLayoutParams);

						if (mSliderListener != null) {
							mSliderListener.onSliding();
						}
					}

				} else if (event.getAction() == MotionEvent.ACTION_UP) {

					// only animate to initial position if we have not reached the end
					if (mLayoutParams.height < mScreenWidth) {

						if (mSliderListener != null) {
							mSliderListener.onEndNotReached();
						}
//						showRetrievalAnimation();
					} else {
						mStartYPosition = -1;
						setLayoutParams(mLayoutParams);
					}

				}

				return true;
			}
		};

		activity.getWindow().getDecorView().setOnTouchListener(mOnTouchListener);
	}



//	private void showRetrievalAnimation() {
//		ScaleAnimation animation = new ScaleAnimation(1.0f, 0.0f, 1.0f, 1.0f,
//				Animation.RELATIVE_TO_SELF, (float) 0.5, Animation.RELATIVE_TO_SELF, (float) 0.5);
//		animation.setDuration(250);
//		animation.setAnimationListener(new AnimationListener() {
//
//			@Override
//			public void onAnimationStart(Animation animation) {
//			}
//
//			@Override
//			public void onAnimationRepeat(Animation animation) {
//			}
//
//			@Override
//			public void onAnimationEnd(Animation animation) {
//				mStartYPosition = -1;
//				mLayoutParams.height = 10;
//				setLayoutParams(mLayoutParams);
//			}
//		});
//		startAnimation(animation);
//	}

	@SuppressLint("NewApi")
	private static Point getDisplaySize(final Display display) {
		final Point point = new Point();
		try {
			display.getSize(point);
		} catch (java.lang.NoSuchMethodError ignore) {
			point.x = display.getWidth();
			point.y = display.getHeight();
		}
		return point;
	}










	/**
	 * Shows the loading animation
	 */
	public void showLoading() {

//		AlphaAnimation animation = new AlphaAnimation(1.0f, 0.2f);
//		animation.setDuration(1000);
//		animation.setRepeatCount(Animation.INFINITE);
//		animation.setRepeatMode(Animation.REVERSE);
//		startAnimation(animation);

	}



	/**
	 * Hides the loading animation
	 */
	public void hideLoading() {
		mStartYPosition = -1;
		mLayoutParams.height = 0;
		setLayoutParams(mLayoutParams);
	}
}
