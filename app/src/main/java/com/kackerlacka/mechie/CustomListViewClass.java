package com.kackerlacka.mechie;

public class CustomListViewClass {

    // Store the id of the  movie poster
    private String mEquation;
    // Store the name of the movie
    private String mName;
    public int mID;

    // Constructor that is used to create an instance of the CustomListViewClass object
    CustomListViewClass(String mName, String mEquation, int mID) {
        this.mEquation = mEquation;
        this.mName = mName;
        this.mID = mID;
    }

    public String getmEquation() {
        return mEquation;
    }

    public String getmName() {
        return mName;
    }

}
