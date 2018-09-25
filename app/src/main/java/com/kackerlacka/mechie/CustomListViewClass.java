package com.kackerlacka.mechie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CustomListViewClass {

    // Store the id of the  movie poster
    private String mEquation;
    // Store the name of the movie
    public String mName;
    public int mID;
    public int previousPosition;

    // Constructor that is used to create an instance of the CustomListViewClass object
    public CustomListViewClass(String mName, String mEquation, int mID) {
        this.mEquation = mEquation;
        this.mName = mName;
        this.mID = mID;
    }

    public String getmEquation() {
        return mEquation;
    }

    public void setmEquation(String mEquation) {
        this.mEquation = mEquation;
    }

    public String getmName() {
        return mName;
    }

    public int getmID() {
        return mID;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

}
