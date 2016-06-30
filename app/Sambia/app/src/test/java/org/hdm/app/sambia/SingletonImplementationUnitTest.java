package org.hdm.app.sambia;

import org.hdm.app.sambia.datastorage.ActivityObject;
import org.hdm.app.sambia.datastorage.ActivityManager;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class SingletonImplementationUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }


//    @Test
//    public void sizeOfHashMap() throws Exception {
//
//
//        for(int i = 0; i<10; i++) {
//            ActivityObject activityObject = new ActivityObject();
//            activityObject.title = "Hello";
//            ActivityManager.getInstance().setActivityObject(activityObject);
//        }
//
//        int actualSize = ActivityManager.getInstance().getActivityMap().size();
//        int expectedSize = 1;
//
//        assertEquals(actualSize, expectedSize);
//
//    }

    // ToDo Methode für richtiges parsen des Json Activity files Überprüfen anhand Daten auslesen der ActivityMap im eEventManager

}