package service;

import java.util.List;

import model.Event;
import model.Participant;
import model.Registration;

public class RegistrationService {

    private List<Event> events;
    private List<Participant> participants;
    private List<Registration> registrations;

    public RegistrationService(List<Event> events,
                               List<Participant> participants,
                               List<Registration> registrations) {

        this.events = events;
        this.participants = participants;
        this.registrations = registrations;
    }

    public boolean participantExists(int participantId) {

        for (Participant p : participants) {

            if (p.getParticipantId() == participantId) {
                return true;
            }
        }

        return false;
    }

    public boolean eventExists(int eventId) {

        for (Event e : events) {

            if (e.getEventId() == eventId) {
                return true;
            }
        }

        return false;
    }

    public Event findEvent(int eventId) {

        for (Event e : events) {

            if (e.getEventId() == eventId) {
                return e;
            }
        }

        return null;
    }

    public Registration findRegistration(String code) {

        for (Registration r : registrations) {

            if (r.getRegistrationCode()
                    .equalsIgnoreCase(code)) {

                return r;
            }
        }

        return null;
    }

    public boolean alreadyRegistered(int participantId,
                                     int eventId) {

        for (Registration r : registrations) {

            if (r.getParticipantId() == participantId
                    && r.getEventId() == eventId
                    && r.isActive()) {

                return true;
            }
        }

        return false;
    }

    public Registration register(int participantId,
                                 int eventId,
                                 String registrationCode) {

        if (!participantExists(participantId)) {

            System.out.println("Participant not found.");
            return null;
        }

        Event event = findEvent(eventId);

        if (event == null) {

            System.out.println("Event not found.");
            return null;
        }

        if (alreadyRegistered(participantId, eventId)) {

            System.out.println(
                    "Participant is already registered for this event.");

            return null;
        }

        if (!event.registerParticipant()) {

            System.out.println(
                    "Event is full.");

            return null;
        }

        Registration registration =
                new Registration(
                        registrationCode,
                        participantId,
                        eventId
                );

        registrations.add(registration);

        return registration;
    }

    public boolean cancelRegistration(String code) {

        Registration registration =
                findRegistration(code);

        if (registration == null) {
            return false;
        }

        if (!registration.isActive()) {
            return false;
        }

        registration.cancelRegistration();

        Event event = findEvent(
                registration.getEventId()
        );

        if (event != null) {
            event.cancelParticipant();
        }

        return true;
    }

    public void displayRegistrations() {

        if (registrations.isEmpty()) {

            System.out.println(
                    "No registrations available.");

            return;
        }

        for (Registration r : registrations) {
            r.displayRegistration();
        }
    }
}