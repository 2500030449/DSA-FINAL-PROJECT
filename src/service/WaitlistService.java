package service;

import java.util.LinkedList;
import java.util.Queue;

public class WaitlistService {

    private Queue<Integer> waitlist = new LinkedList<>();

    // Add participant to waitlist
    public void addToWaitlist(int participantId) {

        if (!waitlist.contains(participantId)) {
            waitlist.offer(participantId);

            System.out.println(
                "Participant " + participantId + " added to waitlist."
            );

        } else {
            System.out.println(
                "Participant " + participantId +
                " is already in the waitlist."
            );
        }
    }

    // Promote the next participant
    public Integer promoteNextParticipant() {

        if (waitlist.isEmpty()) {
            System.out.println("Waitlist is empty.");
            return null;
        }

        int participantId = waitlist.poll();

        System.out.println(
            "Participant " + participantId +
            " promoted from waitlist."
        );

        return participantId;
    }

    // Display all participants in waitlist
    public void displayWaitlist() {

        System.out.println("\n---------- WAITLIST ----------");

        if (waitlist.isEmpty()) {
            System.out.println("Waitlist is empty.");
            return;
        }

        for (Integer participantId : waitlist) {
            System.out.println("Participant ID: " + participantId);
        }
    }

    // Get waitlist size
    public int getWaitlistSize() {
        return waitlist.size();
    }

    // Check whether participant is waiting
    public boolean isWaiting(int participantId) {
        return waitlist.contains(participantId);
    }

    // Remove a particular participant
    public boolean removeFromWaitlist(int participantId) {

        if (waitlist.remove(participantId)) {
            System.out.println(
                "Participant " + participantId +
                " removed from waitlist."
            );
            return true;
        }

        System.out.println(
            "Participant " + participantId +
            " is not in the waitlist."
        );

        return false;
    }
}