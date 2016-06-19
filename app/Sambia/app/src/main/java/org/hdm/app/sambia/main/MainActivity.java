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

//        Data data_02 = new Data();
//        data_02.title = "Bagging" + i;
//        data_02.group_activity = "On Farm Work";
//        data_02.sub_activity = "Harvesting";
//        data_01.subCategoryName = "Animal";
//        data_02.id = 2 + i;
//        data_02.image = bitmap;
//        data_02.imageId = R.drawable.onfarmwork_bagging;
//        data_01.sub_category = true;
//
//
//
//        imgPath = fl.getEnvironment().toString() + "/" + "SambiaApp/Images/" + "onfarmwork_weeding.png";
//        bitmap = BitmapFactory.decodeFile(imgPath, options);
//
//        Data data_03 = new Data();
//        data_03.title = "Transport" + i;
//        data_03.group_activity = "Off Farm Work";
//        data_03.sub_activity = "Harvesting";
//        data_01.subCategoryName = "Machine";
//        data_03.id = 3 + i;
//        data_03.image = bitmap;
//        data_03.imageId = R.drawable.onfarmwork_weeding;
//
//
//        Data data_04 = new Data();
//        data_04.title = "Cooking" + i;
//        data_04.group_activity = "Home";
//        data_04.sub_activity = "Household";
//        data_04.id = 4+i;
//        data_04.image = bitmap;
//        data_04.imageId = R.drawable.onfarmwork_weeding;


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

        Log.d(TAG, "startTime " + time);

        time.setHours(0);
        time.setMinutes(0);
        time.setSeconds(0);

        cal.setTime(time);

        time = cal.getTime();
        Log.d(TAG, "startTime " + time);

        int i = 0;

        Calendar ende = Calendar.getInstance();
        Date endTime = ende.getTime();
        endTime.setHours(0);
        endTime.setMinutes(0);
        endTime.setSeconds(0);
        ende.setTime(endTime);
        ende.add(Calendar.DAY_OF_WEEK, 1);
        ende.add(Calendar.MINUTE, -15);
        endTime = ende.getTime();
        Log.d(TAG, "endTime " + endTime);

        while(time.before(endTime)) {

            time = cal.getTime(); //now
            Log.d(TAG, "startTime "+  time);
            EventManager.getInstance().setCalenderMapEntry(time, null);
            cal.add(Calendar.MINUTE, 15);
            i++;

        }
            Log.d(TAG, "iiii " + i);
    }

}
