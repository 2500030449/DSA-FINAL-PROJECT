package service;

import java.util.ArrayList;
import model.CheckIn;
import model.Ticket;

public class CheckInService {

    private ArrayList<CheckIn> checkIns = new ArrayList<>();

    public CheckIn checkInParticipant(Ticket ticket) {

        if (ticket == null) {
            System.out.println("Invalid ticket.");
            return null;
        }

        if (!ticket.isActive()) {
            System.out.println("Ticket is not active.");
            return null;
        }

        // Prevent duplicate check-in
        for (CheckIn checkIn : checkIns) {
            if (checkIn.getTicketId().equals(ticket.getTicketId())) {
                System.out.println("Participant is already checked in.");
                return checkIn;
            }
        }

        String checkInId = "CHK-" + (checkIns.size() + 1);

        CheckIn checkIn = new CheckIn(
                checkInId,
                ticket.getTicketId(),
                ticket.getParticipantId(),
                ticket.getEventId()
        );

        checkIns.add(checkIn);

        System.out.println("Check-in successful!");
        checkIn.displayCheckIn();

        return checkIn;
    }

    public void displayAllCheckIns() {

        System.out.println("\n---------- ALL CHECK-INS ----------");

        if (checkIns.isEmpty()) {
            System.out.println("No check-ins available.");
            return;
        }

        for (CheckIn checkIn : checkIns) {
            checkIn.displayCheckIn();
        }
    }

    public int getCheckInCount() {
        return checkIns.size();
    }

    public ArrayList<CheckIn> getCheckIns() {
        return checkIns;
    }
}