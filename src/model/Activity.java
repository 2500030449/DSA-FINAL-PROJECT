package model;
public class Activity {
    private int activityId;
    private String activityName;
    private String startTime;
    private String endTime;
    private int capacity;

    public Activity(int activityId, String activityName,
                    String startTime, String endTime,
                    int capacity) {
        this.activityId = activityId;
        this.activityName = activityName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.capacity = capacity;
    }

    public int getActivityId() {
        return activityId;
    }

    public String getActivityName() {
        return activityName;
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

    public void displayActivity() {
        System.out.println("Activity ID: " + activityId);
        System.out.println("Activity: " + activityName);
        System.out.println("Time: " + startTime + " - " + endTime);
        System.out.println("Capacity: " + capacity);
    }
}


