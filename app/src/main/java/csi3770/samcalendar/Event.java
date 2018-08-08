package csi3770.samcalendar;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Event {
    private String key;
    private String date; // When manipulating date, make sure to convert to a date.
    private String location;
    private String details;
    //private String createdBy;

    public Event(){
        // Default constructor required for calls to DataSnapshot.getValue(Event.class)

    }

    public Event(String d, String loc, String det){
        this.key = null;
        this.date = d;
        this.location = loc;
        this.details = det;
        //this.createdBy = cb;
    }

    public Event(String d, String loc, String det, String key){
        this.key = key;
        this.date = d;
        this.location = loc;
        this.details = det;
        //this.createdBy = cb;
    }

    public String getDate() {
        return date;
    }

    public Date getDateAsDate() {
        Date tDate;
        try {
           tDate = new SimpleDateFormat().getDateInstance().parse(date);

        } catch (Exception e){
            e.getCause();
            tDate = new Date();
        }


        return tDate;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getKey(){ return this.key; }

    public void setKey(String key){ this.key = key; }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

//    public String getCreatedBy() {
//        return createdBy;
//    }

//    public void setCreatedBy(String createdBy) {
//        this.createdBy = createdBy;
//    }
}
