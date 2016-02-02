package com.epsilon.coders.phms.model;

/**
 * Created by nilima on 10/6/2015.
 */
public class CareInfo {
    private String careName;
    private  String careAddress;
    private String contactNumber;
    private  String careEmail;

    public String getCareName() {
        return careName;
    }

    public void setCareName(String careName) {
        this.careName = careName;

    }

    public String getCareEmail() {
        return careEmail;
    }

    public void setCareEmail(String careEmail) {
        this.careEmail = careEmail;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getCareAddress() {
        return careAddress;
    }

    public void setCareAddress(String careAddress) {
        this.careAddress = careAddress;
    }

    @Override
    public String toString() {

        return "Name: "+ careName+" Number: "+ contactNumber;

    }
}
