package model;

import java.util.ArrayList;
import java.util.List;

public class Volunteer {

    private int volunteerId;
    private String name;
    private String skill;

    private String phone;
    private String email;

    private int assignedActivityId;

    private List<String> skills;
    private boolean available;

    public Volunteer(int volunteerId,
                     String name,
                     String skill) {

        this.volunteerId = volunteerId;
        this.name = name;
        this.skill = skill;

        this.assignedActivityId = -1;

        this.phone = "";
        this.email = "";

        this.skills = new ArrayList<>();
        this.skills.add(skill);

        this.available = true;
    }

    public int getVolunteerId() {
        return volunteerId;
    }

    public String getName() {
        return name;
    }

    public String getSkill() {
        return skill;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public int getAssignedActivityId() {
        return assignedActivityId;
    }

    public List<String> getSkills() {
        return skills;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void addSkill(String skill) {

        if (skill != null && !skill.trim().isEmpty()) {
            skills.add(skill.trim());
        }
    }

    public void assignActivity(int activityId) {

        assignedActivityId = activityId;
        available = false;
    }

    public void removeAssignment() {

        assignedActivityId = -1;
        available = true;
    }

    public void displayVolunteer() {

        System.out.println("----------------------------------------");
        System.out.println("Volunteer ID     : " + volunteerId);
        System.out.println("Name             : " + name);
        System.out.println("Primary Skill    : " + skill);
        System.out.println("All Skills       : " + skills);
        System.out.println("Phone            : " + phone);
        System.out.println("Email            : " + email);
        System.out.println("Available        : " + available);

        if (assignedActivityId == -1) {
            System.out.println("Activity         : Not assigned");
        } else {
            System.out.println("Assigned Activity: " +
                    assignedActivityId);
        }

        System.out.println("----------------------------------------");
    }
}