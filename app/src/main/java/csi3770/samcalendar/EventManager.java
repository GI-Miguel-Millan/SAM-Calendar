package csi3770.samcalendar;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

public class EventManager {
    HashSet<Event> events;
    private HashSet<Date> eventDates;

    public EventManager(){
        events = new HashSet<>();
        eventDates = new HashSet<>();
    }

    public EventManager(HashSet<Event> events){
        this.events = events;
        eventDates = new HashSet<>();
        for (Event e: events){
            eventDates.add(e.getDateAsDate());
        }
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
        this.eventDates.add(event.getDateAsDate());
    }

    public void removeEvent(Event event){
        this.events.remove(event);
        this.eventDates.remove(event.getDateAsDate());
    }

    public HashSet<Event> getEvents() {
        return events;
    }

    public HashSet<Date> getEventDates() {
        return eventDates;
    }

    public void setEvents(HashSet<Event> events) {
        this.events = events;
        eventDates = new HashSet<>();
        for (Event e: events){
            eventDates.add(e.getDateAsDate());
        }
    }
}
