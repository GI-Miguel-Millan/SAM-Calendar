package csi3770.samcalendar;

public class MyCalendar {
    public String name;
    public String id;
    public MyCalendar(String _name, String _id){
        name = _name;
        id = _id;
    }

    @Override
    public String toString() {
        return name;
    }
}
