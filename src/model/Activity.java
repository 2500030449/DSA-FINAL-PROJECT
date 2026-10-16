package model;

import java.util.ArrayList;
import java.util.List;

public class Activity {

    private int activityId;
    private String activityName;
    private String description;

    private String startTime;
    private String endTime;

    private int capacity;
    private int venueId;

    private int requiredVolunteers;
    private List<String> requiredSkills;

    public Activity(int activityId,
                    String activityName,
                    String startTime,
                    String endTime,
                    int capacity) {

        this.activityId = activityId;
        this.activityName = activityName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.capacity = capacity;

        this.description = "";
        this.venueId = -1;
        this.requiredVolunteers = 1;
        this.requiredSkills = new ArrayList<>();
    }

    public int getActivityId() {
        return activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public String getDescription() {
        return description;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getVenueId() {
        return venueId;
    }

    public int getRequiredVolunteers() {
        return requiredVolunteers;
    }

    public List<String> getRequiredSkills() {
        return requiredSkills;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setVenueId(int venueId) {
        this.venueId = venueId;
    }

    public void setRequiredVolunteers(int requiredVolunteers) {
        this.requiredVolunteers = requiredVolunteers;
    }

    public void addRequiredSkill(String skill) {

        if (skill != null && !skill.trim().isEmpty()) {
            requiredSkills.add(skill.trim());
        }
    }

    public void displayActivity() {

        System.out.println("----------------------------------------");
        System.out.println("Activity ID          : " + activityId);
        System.out.println("Activity             : " + activityName);
        System.out.println("Description          : " + description);
        System.out.println("Time                 : " +
                startTime + " - " + endTime);
        System.out.println("Capacity             : " + capacity);
        System.out.println("Venue ID             : " + venueId);
        System.out.println("Required Volunteers  : " +
                requiredVolunteers);
        System.out.println("Required Skills      : " +
                requiredSkills);
        System.out.println("----------------------------------------");
    }
}