package com.hdm.mobileapplication.sambiaapp.activity;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created by Hannes on 09.05.2016.
 */
public class FileHandler {

    private static final String TAG = "FileHandler";

    String state = Environment.getExternalStorageState();
    File enviroment = Environment.getExternalStorageDirectory();
    private Context context;
    private Properties properties;

    /**************************
     * Constructor
     *************************/


    public FileHandler(MainActivity mainActivity) {
        context = mainActivity;
    }


    /**************************
     * Assets
     *************************/


    public String readFromAssets(Context context, String filename) {

        BufferedReader reader = null;
        StringBuilder sb = null;
        String mLine = null;

        try {
            reader = new BufferedReader(
                    new InputStreamReader(context.getAssets().open(filename)));
            sb = new StringBuilder();
            mLine = reader.readLine();
            while (mLine != null) {
                sb.append(mLine); // process line
                mLine = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return sb.toString();
    }



    public boolean CopyImagesFromResourceToStorage(int[] resources, String imageFolder) {
        if (resources.length != 0) {
            for (int i = 0; i < resources.length; i++) {
                String fileName = enviroment + "/" + imageFolder + "/" +
                        context.getResources().getResourceEntryName(resources[i]) +
                        ".png";
                Bitmap bm = BitmapFactory.decodeResource(context.getResources(), resources[i]);
                try {
                    FileOutputStream out = new FileOutputStream(fileName);
                    bm.compress(Bitmap.CompressFormat.PNG, 100, out);
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
            return true;
        }

        return false;
    }


    public Drawable getDrawableFromPath(String filePath) {
        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
        //Here you can make logic for decode bitmap for ignore oom error.
        return new BitmapDrawable(bitmap);
    }


    /**************************
     * File Permission Check
     *************************/

    public boolean isExternalStorageReadable() {
        return (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state));
    }

    public boolean isExternalStorageWritable() {
        return (Environment.MEDIA_MOUNTED.equals(state));
    }

    public boolean isExternalFileExists(String filePath) {
        File f = new File(filePath);
        if (f.exists()) {
            return true;
        }
        return false;
    }


    /**************************
     * Create External Folder
     *************************/

    public String createExternalFolder(String folderName) {
        File f = new File(enviroment, folderName);
        if (!f.exists()) {
            f.mkdirs();
            return f.toString();
        }
        Toast.makeText(context, folderName + " already exists", Toast.LENGTH_SHORT).show();
        return null;
    }


    /**************************
     * Property File
     *************************/

    public void initPropertyReader() {
        properties = new Properties();
    }


    public Properties getPropertiesFromAssets(String file) {
        try {
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open(file);
            properties.load(inputStream);

        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
            return null;
        }
        return properties;
    }
}
