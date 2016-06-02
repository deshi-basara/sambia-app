package org.hdm.app.sambia.data;

/**
 * Created by Hannes on 27.05.2016.
 */
public class Data {
    public String title;
    public int imageId;
    public boolean active = false;

    public Data(String title , int imageId) {
        this.title = title;
        this.imageId = imageId;
    }

    public String getTitle(){
        return title;
    }



    public void setState() {
        if(active) {
            this.active = false;
        } else {
            this.active = true;
        }
    }



    public void setState(boolean state) {
        this.active = state;
    }


    public boolean getState() {
        return active;
    }

}