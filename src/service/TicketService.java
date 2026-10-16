package service;

import java.util.ArrayList;
import model.Ticket;

public class TicketService {

    private ArrayList<Ticket> tickets = new ArrayList<>();

    public Ticket generateTicket(
            String registrationCode,
            int participantId,
            int eventId) {

        String ticketId =
                "TKT-" + participantId + "-" + eventId;

        Ticket ticket = new Ticket(
                ticketId,
                registrationCode,
                participantId,
                eventId
        );

        tickets.add(ticket);

        System.out.println("\nTicket generated successfully!");
        ticket.displayTicket();

        return ticket;
    }

    public Ticket findTicket(String ticketId) {

        for (Ticket ticket : tickets) {

            if (ticket.getTicketId().equals(ticketId)) {
                return ticket;
            }
        }

        return null;
    }

    public Ticket findTicketByRegistrationCode(String registrationCode) {

        for (Ticket ticket : tickets) {

            if (ticket.getRegistrationCode()
                    .equalsIgnoreCase(registrationCode)) {

                return ticket;
            }
        }

        return null;
    }

    public boolean verifyTicket(String ticketId) {

        Ticket ticket = findTicket(ticketId);

        if (ticket == null) {
            System.out.println("Ticket not found.");
            return false;
        }

        if (!ticket.isActive()) {
            System.out.println("Ticket is not active.");
            return false;
        }

        System.out.println("Ticket verified successfully.");
        return true;
    }

    public void displayAllTickets() {

        System.out.println("\n---------- ALL TICKETS ----------");

        if (tickets.isEmpty()) {
            System.out.println("No tickets generated.");
            return;
        }

        for (Ticket ticket : tickets) {
            ticket.displayTicket();
        }
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }
}