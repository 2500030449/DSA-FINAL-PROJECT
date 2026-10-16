package model;

public class Notification {

    private String notificationId;
    private int participantId;
    private String message;
    private String date;
    private boolean read;

    public Notification(String notificationId,
                        int participantId,
                        String message,
                        String date) {

        this.notificationId = notificationId;
        this.participantId = participantId;
        this.message = message;
        this.date = date;
        this.read = false;
    }

    public String getNotificationId() {
        return notificationId;
    }

    public int getParticipantId() {
        return participantId;
    }

    public String getMessage() {
        return message;
    }

    public String getDate() {
        return date;
    }

    public boolean isRead() {
        return read;
    }

    public void markAsRead() {
        read = true;
    }

    public void displayNotification() {

        System.out.println("----------------------------------------");
        System.out.println("Notification ID: " + notificationId);
        System.out.println("Participant ID : " + participantId);
        System.out.println("Message        : " + message);
        System.out.println("Date           : " + date);
        System.out.println("Read           : " + read);
        System.out.println("----------------------------------------");
    }
}