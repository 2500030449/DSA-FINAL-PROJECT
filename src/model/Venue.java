package model;

import java.util.ArrayList;
import java.util.List;

public class Venue {

    private int venueId;
    private String venueName;
    private String location;
    private int capacity;

    private String venueType;
    private boolean available;
    private List<String> equipment;

    public Venue(int venueId,
                 String venueName,
                 String location,
                 int capacity) {

        this.venueId = venueId;
        this.venueName = venueName;
        this.location = location;
        this.capacity = capacity;

        this.venueType = "General";
        this.available = true;
        this.equipment = new ArrayList<>();
    }

    public Venue(int venueId,
                 String venueName,
                 String location,
                 int capacity,
                 String venueType) {

        this.venueId = venueId;
        this.venueName = venueName;
        this.location = location;
        this.capacity = capacity;
        this.venueType = venueType;

        this.available = true;
        this.equipment = new ArrayList<>();
    }

    public int getVenueId() {
        return venueId;
    }

    public String getVenueName() {
        return venueName;
    }

    public String getLocation() {
        return location;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getVenueType() {
        return venueType;
    }

    public boolean isAvailable() {
        return available;
    }

    public List<String> getEquipment() {
        return equipment;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setVenueType(String venueType) {
        this.venueType = venueType;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void addEquipment(String item) {

        if (item != null && !item.trim().isEmpty()) {
            equipment.add(item.trim());
        }
    }

    public boolean hasEquipment(String requiredEquipment) {

        for (String item : equipment) {

            if (item.equalsIgnoreCase(requiredEquipment)) {
                return true;
            }
        }

        return false;
    }

    public void displayVenue() {

        System.out.println("----------------------------------------");
        System.out.println("Venue ID       : " + venueId);
        System.out.println("Venue Name     : " + venueName);
        System.out.println("Location       : " + location);
        System.out.println("Capacity       : " + capacity);
        System.out.println("Venue Type     : " + venueType);
        System.out.println("Available      : " + available);
        System.out.println("Equipment      : " + equipment);
        System.out.println("----------------------------------------");
    }
}