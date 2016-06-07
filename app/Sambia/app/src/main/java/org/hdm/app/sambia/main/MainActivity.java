package org.hdm.app.sambia.main;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import org.hdm.app.sambia.R;
import org.hdm.app.sambia.data.EventManager;
import org.hdm.app.sambia.util.MyFileHandler;
import org.hdm.app.sambia.util.MyJsonParser;


public class MainActivity extends Activity  {


    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setFullScreen(true);
        setContentView(R.layout.activity_main);
        initConfiguration();
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


        MyFileHandler fl = new MyFileHandler(this);
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



        EventManager.getInstance().createImageMap();


        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ALPHA_8;
        options.inSampleSize = 4; //reduce quality
        String imgPath = fl.getEnvironment().toString() + "/" + "SambiaApp/Images/" + "onfarmwork_bagging.png";
        Bitmap bitmap = BitmapFactory.decodeFile(imgPath, options);
        EventManager.getInstance().putImage("onfarmwork_bagging", bitmap);
    }

}
