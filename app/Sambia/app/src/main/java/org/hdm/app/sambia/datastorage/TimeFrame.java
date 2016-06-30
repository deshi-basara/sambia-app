package org.hdm.app.sambia.datastorage;

import java.util.Date;

/**
 * Created by Hannes on 03.06.2016.
 */
public class TimeFrame {

    public Date startTime;
    public Date endTime;
    public String workingStyle = null;
    public float[] gpsPosition;



    public TimeFrame(Date startTime, Date endTime, String workingStyle) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.workingStyle = workingStyle;
    }
}
