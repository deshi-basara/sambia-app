package org.hdm.app.sambia.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import org.hdm.app.sambia.R;
import org.hdm.app.sambia.datastorage.DataManager;
import org.hdm.app.sambia.datastorage.ActivityObject;
import org.hdm.app.sambia.main.MainActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by Hannes on 09.05.2016.
 */
public class FileLoader {

    private static final String TAG = "FileLoader";


    String state = Environment.getExternalStorageState();
    private File enviroment = Environment.getExternalStorageDirectory();
    private Context context;
    private Properties properties;


    /**************************
     * Constructor
     *************************/
    public FileLoader(MainActivity mainActivity) {
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





    private String readFromFolder(String fileName, String folderPath) {

        BufferedReader reader = null;
        StringBuilder sb = null;
        String mLine = null;
        File file = new File(enviroment+"/"+folderPath, fileName);

        try {

            FileInputStream fileInputStream = new FileInputStream(file);

            reader = new BufferedReader(
                    new InputStreamReader(fileInputStream));
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


    public boolean copyFileFromAssetToExternal(String fileName, String path) {

        if (fileName != null && path != null) {
            InputStream in = null;
            OutputStream out = null;
            try {
                in = context.getAssets().open(fileName);
                File outFile = new File(getEnvironment().toString()+ "/" + path, fileName);
                out = new FileOutputStream(outFile);
                copyFile(in, out);
            } catch (IOException e) {
                Log.e("tag", "Failed to copy asset file: " + fileName, e);
                return false;
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        return false;
                    }
                }
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        return false;
                    }
                }
            }
        }
        return true;
    }


    public boolean CopyImagesFromResourceToExternal(int[] resources) {

        if (resources.length != 0) {
            String imageFolder = getPropertiesFromAssets("configuration.properties")
                    .getProperty("imageFolder");
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
     * Create External Folder
     *************************/
    public String createExternalFolder(String folderName) {

        if (!isExternalStorageWritable()) {
            Toast.makeText(context, folderName + " External Storage is not writeble", Toast.LENGTH_SHORT).show();
            return null;
        }
        File f = new File(enviroment, folderName);
        if (!f.exists()) {
            f.mkdirs();
            if (f.exists()) return f.toString();
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






    /**************** Init process ****************/

    public void initFolder() {

        // Create MainFolder
        String mainFolder = getPropertiesFromAssets("configuration.properties")
                .getProperty("mainFolder");
        createExternalFolder(mainFolder);

        // Create ImageFolder
        String imageFolder = getPropertiesFromAssets("configuration.properties")
                .getProperty("imageFolder");
        createExternalFolder(imageFolder);

        // Create ConfigFolder
        String configFolder = getPropertiesFromAssets("configuration.properties")
                .getProperty("configFolder");
        createExternalFolder(configFolder);
    }



    public void initFiles(String fileName, String folderPath) {

        // Check if Json File is in External Folder
        // if not than copy Json file from Asset to external Folder
        String configFolder = getPropertiesFromAssets("configuration.properties")
                .getProperty(folderPath);
        if (!isExternalFileExists(configFolder + "/" + fileName)) {
            copyFileFromAssetToExternal(fileName, configFolder);
        }

        // Load ActivityObjects
        loadActivityObjects(fileName, configFolder);
    }





    // Load Content
    public void loadActivityObjects(String fileName, String folderPath) {

        // Load and Parse JsonFile
        String jsonString = readFromFolder(fileName+"77", folderPath);
        if(jsonString == null) {
            jsonString = readFromAssets(context, fileName);
        }
        Log.d(TAG, "jasonString " + jsonString);
        MyJsonParser jParser = new MyJsonParser();
        ArrayList<ActivityObject> list = jParser.createOjectFromJson("activitys", jsonString);


        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ALPHA_8;
        options.inSampleSize = 2; //reduce quality
        String imgPath = enviroment.toString() + "/" + getPropertiesFromAssets("configuration.properties")
                .getProperty("imageFolder")+ "/";



        for (int i = 0; i < list.size(); i++) {
            ActivityObject activityObject = list.get(i);
            //Log.d(TAG, "imageName " + activityObject.imageName);
            String objectImgPath =  imgPath + activityObject.imageName;
            activityObject.image = BitmapFactory.decodeFile(objectImgPath, options);
            DataManager.getInstance().setActivityObject(activityObject);
        }
    }






    /**************************
     * File Permission Check
     *************************/


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













    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
    }



    public File getEnvironment() {
        return enviroment;
    }


}
