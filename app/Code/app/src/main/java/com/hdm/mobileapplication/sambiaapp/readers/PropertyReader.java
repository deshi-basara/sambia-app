package com.hdm.mobileapplication.sambiaapp.readers;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Hannes on 08.05.2016.
 */
public class PropertyReader {

    private static final String TAG = "PropertyReader";
    private Context context;
    private Properties properties;

    public PropertyReader(Context context) {
        this.context = context;
        properties = new Properties();
    }



    public Properties getMyProperties(String file) {
        try {
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open(file);
            properties.load(inputStream);

        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
        }

        return properties;
    }
}