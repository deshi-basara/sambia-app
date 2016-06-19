package org.hdm.app.sambia.data;

import org.joda.time.DateTime;

import java.util.Date;

/**
 * Created by Hannes on 03.06.2016.
 */
public class RecordedData {

    public Date startTime;
    public Date endTime;
    public String subCategoryName = null;



    public RecordedData(Date startTime, Date endTime, String subCategoryName) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.subCategoryName = subCategoryName;
    }
}
