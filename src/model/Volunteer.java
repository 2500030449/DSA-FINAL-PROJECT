package model;
public class Volunteer {
    private int volunteerId;
    private String name;
    private String skill;
    private int assignedActivityId;

    public Volunteer(int volunteerId, String name, String skill) {
        this.volunteerId = volunteerId;
        this.name = name;
        this.skill = skill;
        this.assignedActivityId = -1;
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

    public int getAssignedActivityId() {
        return assignedActivityId;
    }

    public void assignActivity(int activityId) {
        assignedActivityId = activityId;
    }

    public void displayVolunteer() {
        System.out.println("Volunteer ID: " + volunteerId);
        System.out.println("Name: " + name);
        System.out.println("Skill: " + skill);

        if (assignedActivityId == -1) {
            System.out.println("Activity: Not assigned");
        } else {
            System.out.println("Assigned Activity: " +
                    assignedActivityId);
        }
    }
}


