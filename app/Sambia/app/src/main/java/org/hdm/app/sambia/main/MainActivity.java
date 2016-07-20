package org.hdm.app.sambia.main;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import org.hdm.app.sambia.IntroActivity;
import org.hdm.app.sambia.R;
import org.hdm.app.sambia.datastorage.ActivityObject;
import org.hdm.app.sambia.datastorage.ActivityManager;
import org.hdm.app.sambia.tasks.PullUpdatesTask;
import org.hdm.app.sambia.util.FileLoader;
import org.hdm.app.sambia.util.MyJsonParser;
import org.hdm.app.sambia.util.Settings;

import java.util.Calendar;
import java.util.Date;


public class MainActivity extends Activity  {
    private final String TAG = "MainActivity";

    /**
     * Constants
     */
    public static final int NOTIFICATION_ID = 100;

    /**
     * Attributes
     */
    private boolean hasIcon = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initFirstStart();
        initConfiguration();
        initCalenderMap();
        setFullScreen(true);
        setContentView(R.layout.activity_main);

        checkForUpdates();
    }



    private void setFullScreen(boolean fullscreen) {

        if (fullscreen) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }








    /*****************************
     * Init
     *******************************************/

    /**
     * Checks if the user started the app for the first time. If true he is redirected to
     * the IntroActivity.
     */
    private void initFirstStart() {
        boolean firstStart = Settings.isFirstRun(getBaseContext());

        // did the user start the app for the first time?
        if(firstStart) {
            // true, show intro
            Intent intentIntro = new Intent(this, IntroActivity.class);
            startActivity(intentIntro);
        }
    }

    private void initConfiguration() {

        // One and only int of ActivityManager
        ActivityManager.init();


        FileLoader fl = new FileLoader(this);
        fl.initPropertyReader();
        fl.initFolder();
        fl.loadActivityObjects();









//        // Copy Images to Image Folder For Testing
//        int a = R.drawable.onfarmwork_bagging;
//        int b = R.drawable.onfarmwork_weeding;
//        int[] resources = new int[] {a, b};
//        fl.CopyImagesFromResourceToStorage(resources);

    }

    private void initCalenderMap() {

        Calendar cal = Calendar.getInstance();
        Date time = cal.getTime();
        int i = 0;

        // Reset Time to 00:00:00
        time.setHours(i);
        time.setMinutes(i);
        time.setSeconds(i);


        // Set Calendar with reseted time
        cal.setTime(time);


        Calendar calEndTime = Calendar.getInstance();
        Date endTime = calEndTime.getTime();
        endTime.setHours(i);
        endTime.setMinutes(i);
        endTime.setSeconds(i);
        calEndTime.setTime(endTime);
        calEndTime.add(Calendar.DAY_OF_WEEK, 1);
        calEndTime.add(Calendar.MINUTE, -15);
        endTime = calEndTime.getTime();


        while(time.before(endTime)) {
            time = cal.getTime();
            Log.d(TAG, "startTime "+  time);
            ActivityManager.getInstance().setCalenderMapEntry(time, null);
            cal.add(Calendar.MINUTE, 15);
            i++;
        }
    }

    private void checkForUpdates() {
        new PullUpdatesTask(this).execute();
    }

}
