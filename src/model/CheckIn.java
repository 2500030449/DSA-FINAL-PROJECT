package model;

import java.time.LocalDateTime;

public class CheckIn {

    private String checkInId;
    private String ticketId;
    private int participantId;
    private int eventId;
    private LocalDateTime checkInTime;
    private String status;

    public CheckIn(String checkInId,
                   String ticketId,
                   int participantId,
                   int eventId) {

        this.checkInId = checkInId;
        this.ticketId = ticketId;
        this.participantId = participantId;
        this.eventId = eventId;
        this.checkInTime = LocalDateTime.now();
        this.status = "CHECKED_IN";
    }

    public String getCheckInId() {
        return checkInId;
    }

    public String getTicketId() {
        return ticketId;
    }

    public int getParticipantId() {
        return participantId;
    }

    public int getEventId() {
        return eventId;
    }

    public LocalDateTime getCheckInTime() {
        return checkInTime;
    }

    public String getStatus() {
        return status;
    }

    public void displayCheckIn() {

        System.out.println("--------------------------------");
        System.out.println("Check-In ID     : " + checkInId);
        System.out.println("Ticket ID       : " + ticketId);
        System.out.println("Participant ID  : " + participantId);
        System.out.println("Event ID        : " + eventId);
        System.out.println("Check-In Time   : " + checkInTime);
        System.out.println("Status          : " + status);
        System.out.println("--------------------------------");
    }
}