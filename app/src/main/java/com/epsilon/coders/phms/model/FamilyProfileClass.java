package com.epsilon.coders.phms.model;

/**
 * Created by nilima on 10/20/2015.
 */
public class FamilyProfileClass {
    private String Fname;
    private String Frelationship;
    private String Fage;
    private String FbloodGroup;
    private String Fweight;
    private String Fheight;
    private String FdateOfBirth;
    private String FspecialNotes;
    private long Fid;
    private String FNumber;

    public String getFNumber() {
        return FNumber;
    }

    public void setFNumber(String FNumber) {
        this.FNumber = FNumber;
    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public String getFrelationship() {
        return Frelationship;
    }

    public void setFrelationship(String frelationship) {
        Frelationship = frelationship;
    }

    public String getFage() {
        return Fage;
    }

    public void setFage(String fage) {
        Fage = fage;
    }

    public String getFbloodGroup() {
        return FbloodGroup;
    }

    public void setFbloodGroup(String fbloodGroup) {
        FbloodGroup = fbloodGroup;
    }

    public String getFweight() {
        return Fweight;
    }

    public void setFweight(String fweight) {
        Fweight = fweight;
    }

    public String getFdateOfBirth() {
        return FdateOfBirth;
    }

    public void setFdateOfBirth(String fdateOfBirth) {
        FdateOfBirth = fdateOfBirth;
    }

    public String getFheight() {
        return Fheight;
    }

    public void setFheight(String fheight) {
        Fheight = fheight;
    }

    public String getFspecialNotes() {
        return FspecialNotes;
    }

    public void setFspecialNotes(String fspecialNotes) {
        FspecialNotes = fspecialNotes;
    }

    public long getFid() {
        return Fid;
    }

    public void setFid(long fid) {
        Fid = fid;
    }


    @Override
    public String toString() {
        return "Name:" + Fname;
    }
}
