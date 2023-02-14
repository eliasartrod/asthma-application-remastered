package com.example.asthmaapplication.main.mainpage.subpages.learningpages;

import android.widget.ImageView;

public class AsthmaInfoModel {
    public String title;
    public String description;
    public Integer imageview;

    public AsthmaInfoModel(String title, String description, int imageview) {
        this.title = title;
        this.description = description;
        this.imageview = imageview;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImageview() {
        return imageview;
    }

    public void setImageview(int imageview) {
        this.imageview = imageview;
    }

}
