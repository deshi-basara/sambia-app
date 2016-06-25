package org.hdm.app.sambia.main;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import org.apache.http.impl.cookie.DateUtils;
import org.hdm.app.sambia.R;
import org.hdm.app.sambia.data.Data;
import org.hdm.app.sambia.data.EventManager;
import org.hdm.app.sambia.util.FileLoader;
import org.hdm.app.sambia.util.MyJsonParser;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends Activity  {


    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initConfiguration();
        initCalenderMap();
        setFullScreen(true);
        setContentView(R.layout.activity_main);
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


    private void initConfiguration() {

        EventManager.init();

        EventManager.getInstance().createImageMap();


        FileLoader fl = new FileLoader(this);
        fl.initPropertyReader();
        fl.initFolder();


        // Load Content
        String jsonString = fl.readFromAssets(this, "activitys.json");
        MyJsonParser mJasonParser = new MyJsonParser();
        mJasonParser.createOjectFromJson("activitys", jsonString);






        // Copy Images to Image Folder For Testing
        int a = R.drawable.onfarmwork_bagging;
        int b = R.drawable.onfarmwork_weeding;
        int[] resources = new int[] {a, b};
        fl.CopyImagesFromResourceToStorage(resources);





        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ALPHA_8;
        options.inSampleSize = 4; //reduce quality


        int i= 0;

        while(i<=40) {




        String imgPath = fl.getEnvironment().toString() + "/" + "SambiaApp/Images/" + "onfarmwork_bagging.png";
        Bitmap bitmap = BitmapFactory.decodeFile(imgPath, options);
        EventManager.getInstance().putImage("onfarmwork_bagging", bitmap);


        Data data_01 = new Data();
        data_01.title = ""+i;
        data_01.group_activity = "On Farm Work";
        data_01.sub_activity = "Harvesting";
        data_01.subCategoryName = "Hand";
        data_01.id = i;
        data_01.image = bitmap;
        data_01.imageId = R.drawable.onfarmwork_bagging;
        data_01.sub_category = true;



        EventManager.getInstance().setActivityObject(data_01);
//        EventManager.getInstance().setActivityObject(data_02);
//        EventManager.getInstance().setActivityObject(data_03);
//        EventManager.getInstance().setActivityObject(data_04);

            i++;
        }




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
            EventManager.getInstance().setCalenderMapEntry(time, null);
            cal.add(Calendar.MINUTE, 15);
            i++;
            if(i== 5) {
                EventManager.getInstance().setCalenderMapEntry(time, "1");
                EventManager.getInstance().setCalenderMapEntry(time, "0");
            }

            if(i== 10) {
                EventManager.getInstance().setCalenderMapEntry(time, "0");
            }

            if(i== 15) {
                EventManager.getInstance().setCalenderMapEntry(time, "1");
                EventManager.getInstance().setCalenderMapEntry(time, "0");
            }
            if(i== 20) {
                EventManager.getInstance().setCalenderMapEntry(time, "0");
            }
        }
    }

}
