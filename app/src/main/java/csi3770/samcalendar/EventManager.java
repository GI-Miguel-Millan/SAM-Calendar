package csi3770.samcalendar;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

public class EventManager {
    ArrayList<Event> events;
    private HashSet<Date> eventDates;

    public EventManager(){
        events = new ArrayList<>();
        eventDates = new HashSet<>();
    }

    public EventManager(ArrayList<Event> events){
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
            if (e.getDateAsDate().equals(date)) {
                eventsOnDate.add(e);
            }
        }
        return eventsOnDate;
    }

    public void addEvent(Event event){
        this.events.add(event);
        this.eventDates.add(event.getDateAsDate());
    }

    public Event getEvent(String key){
        for(Event e: this.events){
            if (e.getKey().equals(key)){
                return e;
            }
        }

        return null;
    }
    public void removeEvent(Event event){
        Date dateRemoved = event.getDateAsDate();
        this.events.remove(event);

        if (getEventsForDate(dateRemoved).isEmpty()){
            this.eventDates.remove(event.getDateAsDate());
        }


    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public HashSet<Date> getEventDates() {
        return eventDates;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
        eventDates = new HashSet<>();
        for (Event e: events){
            eventDates.add(e.getDateAsDate());
        }
    }

}
