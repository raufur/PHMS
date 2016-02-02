package com.epsilon.coders.phms.model;

/**
 * Created by nilima on 10/6/2015.
 */

public class Profile {

    private String name;
    private String age;
    private String bloodGroup;
    private String weight;
    private String height;
    private String dateOfBirth;
    private String specialNotes;
    private byte[] image;

    int id;




    /*
     * set id of the profile
     */
    public void setID(int iID) {
        id = iID;
    }

    /*
     * get id of the profile
     */
    public int getID() {
        return id;
    }

    /*
     * Set a name for menu_profile_create profile. parameter iName return name
     */
    public void setName(String iName) {
        name = iName;
    }

    /*
     * get name of the menu_profile_create profile.
     */
    public String getName() {
        return name;
    }

    /*
     * set age of the person
     */
    public void setAge(String iAge) {
        age = iAge;
    }

    /*
     * get age of the person
     */
    public String getAge() {
        return age;
    }

    /*
     * set Blood Group of the person
     */
    public void setBloodGroup(String eBloodGroup) {
        bloodGroup = eBloodGroup;
    }

    /*
     * get Blood Group of the person
     */
    public String getBloodGroup() {
        return bloodGroup;
    }

    /*
     * set weight
     */
    public void setWeight(String iWeight) {
        weight = iWeight;
    }

    /*
     * get weight
     */
    public String getWeight() {
        return weight;
    }

    /*
     * set height
     */
    public void setHeight(String iHeight) {
        height = iHeight;
    }

    /*
     * get height
     */
    public String getHeight() {
        return height;
    }

    /*
     * set date of birth
     */
    public void setDateOfBirth(String iDateOfBirth) {
        dateOfBirth = iDateOfBirth;
    }

    /*
     * get date of birth
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /*
     * set special notes
     */
    public void setSpecialNotes(String iSpecialNotes) {
        specialNotes = iSpecialNotes;
    }

    /*
     * get special notes
     */
    public String getSpecialNotes() {
        return specialNotes;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    /*
             * set empty constructor of this class
             */
    public Profile() {

    }

    /*
     * constructor for set value
     */


    public Profile(String name, String age, String bloodGroup, String weight, String height, String dateOfBirth, String specialNotes, byte[] image, int id) {
        this.name = name;
        this.age = age;
        this.bloodGroup = bloodGroup;
        this.weight = weight;
        this.height = height;
        this.dateOfBirth = dateOfBirth;
        this.specialNotes = specialNotes;
        this.image = image;
        this.id = id;
    }

    public Profile(String name, String age, String bloodGroup, String weight, String height, String dateOfBirth, String specialNotes, byte[] image) {
        this.name = name;
        this.age = age;
        this.bloodGroup = bloodGroup;
        this.weight = weight;
        this.height = height;
        this.dateOfBirth = dateOfBirth;
        this.specialNotes = specialNotes;
        this.image = image;
    }
}
