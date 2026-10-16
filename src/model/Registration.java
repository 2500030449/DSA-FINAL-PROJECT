package model;

public class Registration {

    private String registrationCode;
    private int participantId;
    private int eventId;

    private String status;
    private String registrationDate;
    private String ticketType;
    private String paymentStatus;

    public Registration(String registrationCode,
                        int participantId,
                        int eventId) {

        this.registrationCode = registrationCode;
        this.participantId = participantId;
        this.eventId = eventId;

        this.status = "Confirmed";
        this.registrationDate = "Not Set";
        this.ticketType = "Regular";
        this.paymentStatus = "Pending";
    }

    public Registration(String registrationCode,
                        int participantId,
                        int eventId,
                        String registrationDate,
                        String ticketType,
                        String paymentStatus) {

        this.registrationCode = registrationCode;
        this.participantId = participantId;
        this.eventId = eventId;

        this.status = "Confirmed";
        this.registrationDate = registrationDate;
        this.ticketType = ticketType;
        this.paymentStatus = paymentStatus;
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

    public String getRegistrationDate() {
        return registrationDate;
    }

    public String getTicketType() {
        return ticketType;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void cancelRegistration() {
        status = "Cancelled";
    }

    public boolean isActive() {
        return status.equalsIgnoreCase("Confirmed");
    }

    public void displayRegistration() {

        System.out.println("----------------------------------------");
        System.out.println("Registration Code : " + registrationCode);
        System.out.println("Participant ID    : " + participantId);
        System.out.println("Event ID          : " + eventId);
        System.out.println("Status            : " + status);
        System.out.println("Registration Date : " + registrationDate);
        System.out.println("Ticket Type       : " + ticketType);
        System.out.println("Payment Status    : " + paymentStatus);
        System.out.println("----------------------------------------");
    }
}