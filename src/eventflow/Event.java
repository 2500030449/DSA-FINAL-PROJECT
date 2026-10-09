package eventflow;

public class Event {
    private int eventId;
    private String eventName;
    private String description;
    private int totalSeats;
    private int registeredSeats;

    public Event(int eventId, String eventName, String description, int totalSeats) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.description = description;
        this.totalSeats = totalSeats;
        this.registeredSeats = 0;
    }

    public int getEventId() {
        return eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public String getDescription() {
        return description;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getRegisteredSeats() {
        return registeredSeats;
    }

    public int getAvailableSeats() {
        return totalSeats - registeredSeats;
    }

    public boolean registerParticipant() {
        if (registeredSeats < totalSeats) {
            registeredSeats++;
            return true;
        }
        return false;
    }

    public void displayEvent() {
        System.out.println("Event ID: " + eventId);
        System.out.println("Event Name: " + eventName);
        System.out.println("Available Seats: " + getAvailableSeats());
    }

    @Override
    public String toString() {
        return eventId + ". " + eventName + " | seats left: " + getAvailableSeats() + " | " + description;
    }
}