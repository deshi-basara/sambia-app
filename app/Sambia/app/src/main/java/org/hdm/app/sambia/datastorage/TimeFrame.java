package org.hdm.app.sambia.datastorage;

import java.util.Date;

/**
 * Created by Hannes on 03.06.2016.
 */
public class TimeFrame {

    public Date startTime;
    public Date endTime;

    public TimeFrame(Date startTime, Date endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
