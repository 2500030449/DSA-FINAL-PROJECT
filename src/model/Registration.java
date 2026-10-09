package model;
public class Registration {
    private String registrationCode;
    private int participantId;
    private int eventId;
    private String status;

    public Registration(String registrationCode,
                        int participantId, int eventId) {
        this.registrationCode = registrationCode;
        this.participantId = participantId;
        this.eventId = eventId;
        this.status = "Confirmed";
    }

    public String getRegistrationCode() {
        return registrationCode;
    }

    public int getParticipantId() {
        return participantId;
    }

    public int getEventId() {
        return eventId;
    }

    public String getStatus() {
        return status;
    }

    public void cancelRegistration() {
        status = "Cancelled";
    }

    public void displayRegistration() {
        System.out.println("Registration Code: " + registrationCode);
        System.out.println("Participant ID: " + participantId);
        System.out.println("Event ID: " + eventId);
        System.out.println("Status: " + status);
    }
}

