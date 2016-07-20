package org.hdm.app.sambia.tasks;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.util.Log;

import org.hdm.app.sambia.R;
import org.hdm.app.sambia.main.MainActivity;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Pulls new updates from the server.
 *
 * During the execution a Notification is shown by the Android NotificationManager.
 */
public class PullUpdatesTask extends AsyncTask<Void, Integer, Boolean> {
    private final String LOG_INDICATOR = "InitDeviceTask";


    /**
     * Attributes
     */
    private MainActivity activity;
    private Context context;
    private JSONObject activitiesJSON;
    private OkHttpClient client = new OkHttpClient();

    /**
     * Constructor
     * @param _context
     */
    public PullUpdatesTask(Context _context) {
        this.context = _context;
    }

    protected Boolean doInBackground(Void... subjects) {
        // show update notification
        showNotificationIcon();

        this.activitiesJSON = downloadActivities();

        return true;
    }

    protected void onProgressUpdate(Integer... progress) {
        //dialog.incrementProgress(progress[0]);
    }

    protected void onPostExecute(Boolean result) {
       //  this.listener.onFinished(this.subjectId, this.activitiesJSON);
    }

    /**
     * Downloads all enabled activities from the server.
     *
     * @return Activities as JSON
     */
    private JSONObject downloadActivities() {
        Request request = new Request.Builder()
                // TODO: Use real IP
                .url("http://192.168.1.158:8080/api/activities")
                .build();

        // make request and handle response
        JSONObject responseJSON = null;
        try {
            Response response = this.client.newCall(request).execute();

            // parse response to string
            String responseMessage = response.body().string();

            // parse string to json
            responseJSON = new JSONObject(responseMessage);
            Log.d(LOG_INDICATOR, responseJSON.toString());

            publishProgress(70);
        } catch(IOException error) {
            error.printStackTrace();
        } catch(JSONException error) {
            error.printStackTrace();
        }

        return responseJSON;
    }

    /**
     * Opens a status-indicator-notification when the updating-task was started.
     */
    private void showNotificationIcon() {

        // set MainActivity as notification action, which redirects to the last opened Activity
        /*Intent notificationIntent = new Intent(this, ChatActivity.class);
        notificationIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        notificationIntent.setAction(Intent.ACTION_MAIN);
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        notificationIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        // set a "closing-broadcast" as notification close action, which signals the broadcast-
        // listener to close the whole app
        Intent closingIntent = new Intent(this, MainActivity.class);
        closingIntent.putExtra("EXIT", true);
        closingIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent closePendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        closingIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );*/

        // set notification content and open it
        NotificationManager notificationManager = (NotificationManager)
                this.context.getSystemService(this.context.NOTIFICATION_SERVICE);
        Notification notification = new Notification.Builder(this.context)
                .setContentTitle("Sambia App")
                .setContentText("Checking for Updates")
                .setSmallIcon(R.drawable.settings)
                //.addAction(R.drawable.cancel_24,
                //        this.context.getString(R.string.notify_running_stop),
                //        closePendingIntent)
                //.setContentIntent(resultPendingIntent)
                .setOngoing(true)
                .build();
        notificationManager.notify(MainActivity.NOTIFICATION_ID, notification);

    }

}
