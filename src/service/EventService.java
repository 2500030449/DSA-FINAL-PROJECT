package service;

import java.util.ArrayList;
import java.util.List;

import model.Event;

public class EventService {

    private List<Event> events;

    public EventService(List<Event> events) {
        this.events = events;
    }

    public void addEvent(Event event) {

        if (event == null) {
            System.out.println("Event cannot be null.");
            return;
        }

        if (findById(event.getEventId()) != null) {
            System.out.println("Event ID already exists.");
            return;
        }

        events.add(event);

        System.out.println("Event added successfully.");
    }

    public Event findById(int eventId) {

        for (Event event : events) {

            if (event.getEventId() == eventId) {
                return event;
            }
        }

        return null;
    }

    public List<Event> getAllEvents() {
        return events;
    }

    public boolean deleteEvent(int eventId) {

        Event event = findById(eventId);

        if (event == null) {
            return false;
        }

        events.remove(event);

        return true;
    }

    public boolean updateEventName(int eventId,
                                   String newName) {

        Event event = findById(eventId);

        if (event == null) {
            return false;
        }

        event.setEventName(newName);

        return true;
    }

    public void displayAllEvents() {

        if (events.isEmpty()) {
            System.out.println("No events available.");
            return;
        }

        for (Event event : events) {
            event.displayEvent();
        }
    }
}