package model;

import java.util.ArrayList;
import java.util.List;

public class Participant {

    private int participantId;
    private String name;
    private String email;
    private String phone;

    private int age;
    private List<String> interests;

    public Participant(int participantId,
                       String name,
                       String email,
                       String phone) {

        this.participantId = participantId;
        this.name = name;
        this.email = email;
        this.phone = phone;

        this.age = 0;
        this.interests = new ArrayList<>();
    }

    public Participant(int participantId,
                       String name,
                       String email,
                       String phone,
                       int age) {

        this.participantId = participantId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.age = age;

        this.interests = new ArrayList<>();
    }

    public int getParticipantId() {
        return participantId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public int getAge() {
        return age;
    }

    public List<String> getInterests() {
        return interests;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void addInterest(String interest) {

        if (interest != null && !interest.trim().isEmpty()) {
            interests.add(interest.trim());
        }
    }

    public void displayParticipant() {

        System.out.println("----------------------------------------");
        System.out.println("Participant ID : " + participantId);
        System.out.println("Name           : " + name);
        System.out.println("Email          : " + email);
        System.out.println("Phone          : " + phone);
        System.out.println("Age            : " + age);
        System.out.println("Interests      : " + interests);
        System.out.println("----------------------------------------");
    }
}