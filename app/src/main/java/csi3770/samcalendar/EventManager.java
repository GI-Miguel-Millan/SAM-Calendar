package csi3770.samcalendar;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

public class EventManager {
    HashSet<Event> events;

    public EventManager(){
        events = new HashSet<>();
    }

    public EventManager(HashSet<Event> events){
        this.events = events;
    }

    // Gets the events that occur the date specified.
    public HashSet<Event> getEventsForDate(Date date){
        HashSet<Event> eventsOnDate = new HashSet<Event>();
        for (Event e : this.events) {
            if (e.getDate().equals(date)) {
                eventsOnDate.add(e);
            }
        }
        return eventsOnDate;
    }

    public void addEvent(Event event){
        this.events.add(event);
    }

    public void removeEvent(Event event){
        this.events.remove(event);
    }

    public HashSet<Event> getEvents() {
        return events;
    }

    public void setEvents(HashSet<Event> events) {
        this.events = events;
    }
}
