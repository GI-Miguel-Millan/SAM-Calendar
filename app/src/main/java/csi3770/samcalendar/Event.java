package csi3770.samcalendar;

import java.util.Date;

public class Event {
    private Date date;
    private String location;
    private String details;
    private String createdBy;

    public Event(Date d, String loc, String det, String cb){
        this.date = d;
        this.location = loc;
        this.details = det;
        this.createdBy = cb;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
