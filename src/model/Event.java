package model;

public class Event {

    private int eventId;
    private String eventName;
    private String description;
    private String category;
    private String date;
    private String startTime;
    private String endTime;
    private int venueId;
    private String organizer;
    private int totalSeats;
    private int registeredSeats;
    private double registrationFee;
    private String status;

    // Existing constructor - keeps your old code working
    public Event(int eventId, String eventName,
                 String description, int totalSeats) {

        this.eventId = eventId;
        this.eventName = eventName;
        this.description = description;
        this.totalSeats = totalSeats;

        this.category = "General";
        this.date = "Not Set";
        this.startTime = "09:00";
        this.endTime = "18:00";
        this.venueId = -1;
        this.organizer = "EventFlow Team";
        this.registrationFee = 0.0;
        this.status = "UPCOMING";

        this.registeredSeats = 0;
    }

    // New complete constructor
    public Event(int eventId,
                 String eventName,
                 String description,
                 String category,
                 String date,
                 String startTime,
                 String endTime,
                 int venueId,
                 String organizer,
                 int totalSeats,
                 double registrationFee) {

        this.eventId = eventId;
        this.eventName = eventName;
        this.description = description;
        this.category = category;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.venueId = venueId;
        this.organizer = organizer;
        this.totalSeats = totalSeats;
        this.registrationFee = registrationFee;

        this.registeredSeats = 0;
        this.status = "UPCOMING";
    }

    // Getters

    public int getEventId() {
        return eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getDate() {
        return date;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public int getVenueId() {
        return venueId;
    }

    public String getOrganizer() {
        return organizer;
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

    public double getRegistrationFee() {
        return registrationFee;
    }

    public String getStatus() {
        return status;
    }

    // Setters

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setVenueId(int venueId) {
        this.venueId = venueId;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public void setRegistrationFee(double registrationFee) {
        this.registrationFee = registrationFee;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Register participant

    public boolean registerParticipant() {

        if (registeredSeats < totalSeats) {
            registeredSeats++;
            return true;
        }

        return false;
    }

    // Cancel participant registration

    public boolean cancelParticipant() {

        if (registeredSeats > 0) {
            registeredSeats--;
            return true;
        }

        return false;
    }

    public void displayEvent() {

        System.out.println("----------------------------------------");
        System.out.println("Event ID       : " + eventId);
        System.out.println("Event Name     : " + eventName);
        System.out.println("Description    : " + description);
        System.out.println("Category       : " + category);
        System.out.println("Date           : " + date);
        System.out.println("Time           : " + startTime + " - " + endTime);
        System.out.println("Venue ID       : " + venueId);
        System.out.println("Organizer      : " + organizer);
        System.out.println("Total Seats    : " + totalSeats);
        System.out.println("Registered     : " + registeredSeats);
        System.out.println("Available      : " + getAvailableSeats());
        System.out.println("Registration Fee: ₹" + registrationFee);
        System.out.println("Status         : " + status);
        System.out.println("----------------------------------------");
    }
}