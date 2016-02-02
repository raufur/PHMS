package com.epsilon.coders.phms.model;

/**
 * Created by nilima on 10/6/2015.
 */
public class Note {
    private String notename;
    private String notedetail;
    private String notedate;
    private String notetime;
    private int id;


    public Note() {
    }

    public Note(String notename, String notedetail, String notedate, String notetime, int id) {
        this.notename = notename;
        this.notedetail = notedetail;
        this.notedate = notedate;
        this.notetime = notetime;
        this.id = id;
    }

    public String getNotename() {
        return notename;
    }

    public void setNotename(String notename) {
        this.notename = notename;
    }

    public String getNotedetail() {
        return notedetail;
    }

    public void setNotedetail(String notedetail) {
        this.notedetail = notedetail;
    }

    public String getNotedate() {
        return notedate;
    }

    public void setNotedate(String notedate) {
        this.notedate = notedate;
    }

    public String getNotetime() {
        return notetime;
    }

    public void setNotetime(String notetime) {
        this.notetime = notetime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}


