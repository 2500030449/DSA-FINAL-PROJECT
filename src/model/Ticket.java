package model;

import java.time.LocalDateTime;
import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;

public class Ticket {

    private String ticketId;
    private String registrationCode;
    private int participantId;
    private int eventId;
    private LocalDateTime issueTime;
    private String status;
    private String ticketHash;

    public Ticket(String ticketId,
                  String registrationCode,
                  int participantId,
                  int eventId) {

        this.ticketId = ticketId;
        this.registrationCode = registrationCode;
        this.participantId = participantId;
        this.eventId = eventId;
        this.issueTime = LocalDateTime.now();
        this.status = "ACTIVE";
        this.ticketHash = generateHash();
    }

    private String generateHash() {

        try {
            String data = ticketId
                    + registrationCode
                    + participantId
                    + eventId
                    + issueTime;

            MessageDigest md = MessageDigest.getInstance("SHA-256");

            byte[] hash = md.digest(
                    data.getBytes(StandardCharsets.UTF_8)
            );

            StringBuilder hex = new StringBuilder();

            for (byte b : hash) {
                hex.append(String.format("%02x", b));
            }

            return hex.toString();

        } catch (Exception e) {
            return "HASH_ERROR";
        }
    }

    public String getTicketId() {
        return ticketId;
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

    public LocalDateTime getIssueTime() {
        return issueTime;
    }

    public String getStatus() {
        return status;
    }

    public String getTicketHash() {
        return ticketHash;
    }

    public void cancelTicket() {
        status = "CANCELLED";
    }

    public boolean isActive() {
        return status.equals("ACTIVE");
    }

    public void displayTicket() {

        System.out.println("--------------------------------");
        System.out.println("Ticket ID       : " + ticketId);
        System.out.println("Registration    : " + registrationCode);
        System.out.println("Participant ID  : " + participantId);
        System.out.println("Event ID        : " + eventId);
        System.out.println("Issue Time      : " + issueTime);
        System.out.println("Status          : " + status);
        System.out.println("Security Hash   : " + ticketHash);
        System.out.println("--------------------------------");
    }
}