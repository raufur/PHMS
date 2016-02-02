package com.epsilon.coders.phms.model;

/**
 * Created by nilima on 10/6/2015.
 */
public class DoctorProfile {
    private String dname;
    private String qualification;
    private String designation;
    private String expertise;
    private String organization;
    private String chamber;
    private String visitinghours;
    private String location;
    private String phone;
    private String email;
    long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public DoctorProfile(String dname, String qualification, String designation,
                         String expertise, String organization, String chamber, String visitinghours,
                         String location, String phone, String email, long id) {
        this.dname = dname;
        this.qualification = qualification;
        this.designation = designation;
        this.expertise = expertise;
        this.organization = organization;
        this.chamber = chamber;
        this.visitinghours = visitinghours;
        this.location = location;
        this.phone = phone;
        this.email = email;
        this.id = id;
    }
    public DoctorProfile() {

    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getChamber() {
        return chamber;
    }

    public void setChamber(String chamber) {
        this.chamber = chamber;
    }

    public String getVisitinghours() {
        return visitinghours;
    }

    public void setVisitinghours(String visitinghours) {
        this.visitinghours = visitinghours;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Name: "+dname +" Number:"+ phone;
    }
}

