package com.epsilon.coders.phms.model;

/**
 * Created by nilima on 10/6/2015.
 */
public class FamilyDietChart {

    String mId = "";
    String mDate = "";
    String mTime = "";
    String mFamilyDietName = "";
    String mFamilyDietDetails = "";
    String mAlarm = "";

	/*
	 * set id of activity
	 */

    public void setId(String eId) {
        mId = eId;
    }

    /*
     * get id of activity
     */
    public String getId() {
        return mId;
    }

    /*
     * set Date of the activity
     */
    public void setDate(String eDate) {
        mDate = eDate;
    }

    /*
     * get Date of the activity
     */
    public String getDate() {
        return mDate;
    }

    /*
     * set time for the activity
     */
    public void setTime(String eTime) {
        mTime = eTime;
    }

    /*
     * get time of the activity
     */
    public String getTime() {
        return mTime;
    }

    /*
     * set food menu
     */
    public void setFamilyDietDetails(String FamilyDietDetails) {
        mFamilyDietDetails = FamilyDietDetails;
    }

    /*
     * get food menu
     */
    public String getFamilyDietDetails() {
        return mFamilyDietDetails;
    }

    /*
     * set the event name
     */
    public void setFamilyDietName(String eFamilyDietName) {
        mFamilyDietName = eFamilyDietName;
    }

    /*
     * get event name
     */
    public String getFamilyDietName() {
        return mFamilyDietName;
    }

    /*
     * set alarm for the activity
     */
    public void setAlarm(String eAlarm) {
        mAlarm = eAlarm;
    }

    /*
     * get alarm of the activity
     */
    public String getAlarm() {
        return mAlarm;
    }

    /*
     * empty constructor of this class
     */
    public FamilyDietChart() {

    }

    /*
     * constructor for set value
     */
    public FamilyDietChart(String eId, String eDate, String eTime,
                           String eFamilyDietDetails, String eFamilyDietName, String eAlarm) {
        mId = eId;
        mDate = eDate;
        mTime = eTime;
        mFamilyDietDetails = eFamilyDietDetails;
        mFamilyDietName = eFamilyDietName;
        mAlarm = eAlarm;
    }
}
