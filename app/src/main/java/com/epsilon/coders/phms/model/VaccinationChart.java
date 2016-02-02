package com.epsilon.coders.phms.model;

/**
 * Created by nilima on 10/6/2015.
 */
public class VaccinationChart {

    String mId = "";
    String mDate = "";
    String mTime = "";
    String mVaccinationName = "";
    String mVaccinationDetails = "";
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
    public void setVaccinationDetails(String vaccinationDetails) {
        mVaccinationDetails = vaccinationDetails;
    }

    /*
     * get food menu
     */
    public String getVaccinationDetails() {
        return mVaccinationDetails;
    }

    /*
     * set the event name
     */
    public void setVaccinationName(String eVaccinationName) {
        mVaccinationName = eVaccinationName;
    }

    /*
     * get event name
     */
    public String getVaccinationName() {
        return mVaccinationName;
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
    public VaccinationChart() {

    }

    /*
     * constructor for set value
     */
    public VaccinationChart(String eId, String eDate, String eTime,
                            String eVaccinationDetails, String eVaccinationName, String eAlarm) {
        mId = eId;
        mDate = eDate;
        mTime = eTime;
        mVaccinationDetails = eVaccinationDetails;
        mVaccinationName = eVaccinationName;
        mAlarm = eAlarm;
    }
}
