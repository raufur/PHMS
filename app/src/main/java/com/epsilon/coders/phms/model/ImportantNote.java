package com.epsilon.coders.phms.model;

/**
 * Created by nilima on 10/6/2015.
 */
public class ImportantNote {
    private int key;
    private String Title;
    private String Date;
    private String Description;

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    @Override
    public String toString() {
        return "Title: "+Title +"  Date:"+ Date+". Description: "+ Description;
    }
}
