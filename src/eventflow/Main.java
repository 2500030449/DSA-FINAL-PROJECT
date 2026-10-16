package eventflow;

import java.util.*;

import model.Event;
import model.Activity;
import model.Participant;
import model.Registration;
import model.Venue;
import model.Volunteer;
import model.Ticket;
import model.CheckIn;

import eventflow.engine.*;
import m1string.*;
import m2suffix.*;
import m3dp.*;
import m4flow.*;

import service.TicketService;
import service.CheckInService;
import service.WaitlistService;

public class Main {

    static Scanner sc = new Scanner(System.in);

    // =========================================================
    // MAIN DATA COLLECTIONS
    // =========================================================

    static ArrayList<Event> events = new ArrayList<>();
    static ArrayList<Participant> participants = new ArrayList<>();
    static ArrayList<Registration> registrations = new ArrayList<>();
    static ArrayList<Venue> venues = new ArrayList<>();
    static ArrayList<Activity> activities = new ArrayList<>();
    static ArrayList<Volunteer> volunteers = new ArrayList<>();

    // New EventFlow components
    static TicketService ticketService = new TicketService();
    static CheckInService checkInService = new CheckInService();
    static WaitlistService waitlistService = new WaitlistService();

    // Module 1 & 2 pre-indexed data structures
    static EventKeywordAhoCorasick keywordMatcher =
            new EventKeywordAhoCorasick();

    // =========================================================
    // INITIAL DATA
    // =========================================================

    static {

        // -----------------------------------------------------
        // EVENTS
        // -----------------------------------------------------

        events.add(new Event(
                1,
                "AI Hackathon",
                "Annual Artificial Intelligence Hackathon & Innovation Contest",
                100
        ));

        events.add(new Event(
                2,
                "Web Development Workshop",
                "Hands-on Fullstack Web Development & Cloud Deployment",
                80
        ));

        events.add(new Event(
                3,
                "Robotics Challenge",
                "Autonomous Robotics Navigation and Combat Challenge",
                40
        ));

        events.add(new Event(
                4,
                "Data Science Meetup",
                "Big Data Analytics, Machine Learning & Networking Session",
                60
        ));

        // -----------------------------------------------------
        // VENUES
        // -----------------------------------------------------

        venues.add(new Venue(
                1,
                "Main Auditorium",
                "Academic Block A",
                120
        ));

        venues.add(new Venue(
                2,
                "Innovation Lab 1",
                "Tech Complex B",
                50
        ));

        venues.add(new Venue(
                3,
                "Robotics Arena",
                "Innovation Centre C",
                40
        ));

        venues.add(new Venue(
                4,
                "Seminar Hall",
                "Management Block D",
                70
        ));

        // -----------------------------------------------------
        // ACTIVITIES
        // -----------------------------------------------------

        activities.add(new Activity(
                1,
                "Opening Keynote & AI Trends",
                "09:00",
                "10:30",
                150
        ));

        activities.add(new Activity(
                2,
                "Fullstack Web App Sprint",
                "10:00",
                "12:00",
                80
        ));

        activities.add(new Activity(
                3,
                "Robotics Arena Combat",
                "11:00",
                "13:00",
                50
        ));

        activities.add(new Activity(
                4,
                "Data Science & ML Workshop",
                "13:30",
                "15:00",
                70
        ));

        activities.add(new Activity(
                5,
                "Algorithmic Speed Challenge",
                "14:30",
                "16:30",
                60
        ));

        activities.add(new Activity(
                6,
                "Grand Valedictory & Awards",
                "16:30",
                "18:00",
                200
        ));

        // -----------------------------------------------------
        // VOLUNTEERS
        // -----------------------------------------------------

        volunteers.add(new Volunteer(
                1,
                "Aarav Sharma",
                "Robotics & Hardware"
        ));

        volunteers.add(new Volunteer(
                2,
                "Diya Patel",
                "AI & Machine Learning"
        ));

        volunteers.add(new Volunteer(
                3,
                "Rohan Verma",
                "Web & Cloud"
        ));

        volunteers.add(new Volunteer(
                4,
                "Sneha Reddy",
                "Logistics & Stage"
        ));

        volunteers.add(new Volunteer(
                5,
                "Vikram Malhotra",
                "Coding Competitions"
        ));

        // -----------------------------------------------------
        // SAMPLE PARTICIPANTS
        // -----------------------------------------------------

        Participant p1 = new Participant(
                101,
                "Sproothi",
                "sproothi@college.edu",
                "9876543210"
        );

        participants.add(p1);

        registrations.add(
                new Registration(
                        "REG101",
                        101,
                        1
                )
        );

        Participant p2 = new Participant(
                102,
                "Rahul Sen",
                "rahul.sen@college.edu",
                "9876543211"
        );

        participants.add(p2);

        registrations.add(
                new Registration(
                        "REG102",
                        102,
                        2
                )
        );

        // -----------------------------------------------------
        // AHO-CORASICK KEYWORDS
        // -----------------------------------------------------

        keywordMatcher.addWord("ai");
        keywordMatcher.addWord("hackathon");
        keywordMatcher.addWord("web");
        keywordMatcher.addWord("robotics");
        keywordMatcher.addWord("data");

        keywordMatcher.buildFailureLinks();
    }

    // =========================================================
    // MAIN METHOD
    // =========================================================

    public static void main(String[] args) {

        int choice = 0;

        printMenu();

        do {

            System.out.print("Enter your choice: ");

            String input = sc.nextLine().trim();

            if (input.isEmpty()) {
                continue;
            }

            try {

                choice = Integer.parseInt(input);

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid input! Please enter a number between 1 and 18.\n"
                );

                continue;
            }

            switch (choice) {

                case 1:
                    viewEvents();
                    break;

                case 2:
                    registerParticipant();
                    break;

                case 3:
                    searchEvent();
                    break;

                case 4:
                    correctName();
                    break;

                case 5:
                    scheduleActivities();
                    break;

                case 6:
                    allocateVenue();
                    break;

                case 7:
                    assignVolunteer();
                    break;

                case 8:
                    analyzeData();
                    break;

                case 9:
                    manageVenues();
                    break;

                case 10:
                    manageActivities();
                    break;

                case 11:
                    viewRegistrations();
                    break;

                case 12:
                    generateTicket();
                    break;

                case 13:
                    verifyTicket();
                    break;

                case 14:
                    checkInParticipant();
                    break;

                case 15:
                    displayTicketAndCheckIns();
                    break;

                case 16:
                    manageWaitlist();
                    break;

                case 17:
                    showDashboard();
                    break;

                case 18:
                    System.out.println(
                            "\nExiting EventFlow. Thank you!"
                    );
                    break;

                default:
                    System.out.println(
                            "Invalid choice! Please choose between 1 and 18.\n"
                    );
            }

            if (choice != 18) {
                System.out.println();
            }

        } while (choice != 18);
    }

    // =========================================================
    // MENU
    // =========================================================

    private static void printMenu() {

        System.out.println();
        System.out.println("==============================================");
        System.out.println("       EVENTFLOW MANAGEMENT PLATFORM");
        System.out.println("   Large-Scale Event Management System");
        System.out.println("==============================================");

        System.out.println("1.  View Events");
        System.out.println("2.  Register Participant");
        System.out.println("3.  Search Event");
        System.out.println("4.  Correct Participant Name");
        System.out.println("5.  Schedule Activities");
        System.out.println("6.  Allocate Venue Capacity");
        System.out.println("7.  Assign Volunteers");
        System.out.println("8.  Analyze Event Data");
        System.out.println("9.  Manage Venues");
        System.out.println("10. Manage Activities");
        System.out.println("11. View Registrations");

        System.out.println("----------------------------------------------");

        System.out.println("12. Generate Ticket");
        System.out.println("13. Verify Ticket");
        System.out.println("14. Check-In Participant");
        System.out.println("15. View Tickets & Check-Ins");
        System.out.println("16. Manage Waitlist");
        System.out.println("17. Event Dashboard");

        System.out.println("----------------------------------------------");

        System.out.println("18. Exit");

        System.out.println("==============================================");
        System.out.println();
    }

    // =========================================================
    // OPTION 1
    // VIEW EVENTS
    // =========================================================

    static void viewEvents() {

        System.out.println(
                "\n---------- AVAILABLE EVENTS ----------"
        );

        if (events.isEmpty()) {

            System.out.println("No events available.");
            return;
        }

        for (Event e : events) {

            System.out.println(
                    "Event ID       : " + e.getEventId()
            );

            System.out.println(
                    "Event Name     : " + e.getEventName()
            );

            System.out.println(
                    "Description    : " + e.getDescription()
            );

            System.out.println(
                    "Available Seats: " + e.getAvailableSeats()
            );

            System.out.println("--------------------------------------");
        }
    }

    // =========================================================
    // OPTION 2
    // REGISTER PARTICIPANT
    // =========================================================

    static void registerParticipant() {

        System.out.println(
                "\n---------- REGISTER PARTICIPANT ----------"
        );

        System.out.print("Enter Participant ID: ");

        int id;

        try {

            id = Integer.parseInt(
                    sc.nextLine().trim()
            );

        } catch (NumberFormatException e) {

            System.out.println("Invalid Participant ID.");
            return;
        }

        System.out.print("Enter Name: ");
        String name = sc.nextLine().trim();

        System.out.print("Enter Email: ");
        String email = sc.nextLine().trim();

        System.out.print("Enter Phone: ");
        String phone = sc.nextLine().trim();

        System.out.print("Enter Event ID: ");

        int eventId;

        try {

            eventId = Integer.parseInt(
                    sc.nextLine().trim()
            );

        } catch (NumberFormatException e) {

            System.out.println("Invalid Event ID.");
            return;
        }

        Event selected = null;

        for (Event e : events) {

            if (e.getEventId() == eventId) {

                selected = e;
                break;
            }
        }

        if (selected == null) {

            System.out.println("Event not found!");
            return;
        }

        // Check duplicate participant ID
        for (Participant p : participants) {

            if (p.getParticipantId() == id) {

                System.out.println(
                        "Participant ID already exists."
                );

                return;
            }
        }

        String regCode = "REG" + id;

        // ----------------------------------------------
        // Rabin-Karp duplicate registration verification
        // ----------------------------------------------

        StringBuilder existingCodes =
                new StringBuilder();

        for (Registration reg : registrations) {

            existingCodes
                    .append(reg.getRegistrationCode())
                    .append(" ");
        }

        if (BookingCodeRabinKarp.contains(
                existingCodes.toString(),
                regCode)) {

            System.out.println(
                    "Duplicate registration code detected!"
            );

            return;
        }

        // ----------------------------------------------
        // Seat allocation
        // ----------------------------------------------

        if (selected.registerParticipant()) {

            Participant p = new Participant(
                    id,
                    name,
                    email,
                    phone
            );

            Registration r = new Registration(
                    regCode,
                    id,
                    eventId
            );

            participants.add(p);
            registrations.add(r);

            System.out.println(
                    "\nRegistration successful!"
            );

            System.out.println(
                    "Registration Code: " + regCode
            );

            System.out.println(
                    "Participant      : " + name
            );

            System.out.println(
                    "Event             : " +
                    selected.getEventName()
            );

            System.out.println(
                    "Status            : Confirmed"
            );

        } else {

            // ------------------------------------------
            // No seat → WAITLIST
            // ------------------------------------------

            System.out.println(
                    "\nNo seats available!"
            );

            System.out.println(
                    "Participant will be added to waitlist."
            );

            waitlistService.addToWaitlist(id);
        }
    }

    // =========================================================
    // OPTION 3
    // SEARCH EVENT
    // =========================================================

    static void searchEvent() {

        System.out.println(
                "\n---------- SEARCH EVENT ----------"
        );

        System.out.print(
                "Enter event name / keyword: "
        );

        String keyword =
                sc.nextLine().trim();

        if (keyword.isEmpty()) {

            System.out.println(
                    "Search term cannot be empty."
            );

            return;
        }

        boolean found = false;

        for (Event e : events) {

            String eventName =
                    e.getEventName().toLowerCase();

            String description =
                    e.getDescription().toLowerCase();

            String searchKeyword =
                    keyword.toLowerCase();

            // KMP
            int matchIndexName =
                    KMP.find(
                            eventName,
                            searchKeyword
                    );

            int matchIndexDesc =
                    KMP.find(
                            description,
                            searchKeyword
                    );

            // Suffix Automaton
            EventSearchSuffixAutomaton automaton =
                    new EventSearchSuffixAutomaton(
                            e.getEventName().length() + 5
                    );

            automaton.build(eventName);

            boolean saFound =
                    automaton.search(searchKeyword);

            // Suffix Tree
            EventDocumentSuffixTree suffixTree =
                    new EventDocumentSuffixTree();

            suffixTree.build(eventName);

            boolean treeFound =
                    suffixTree.search(searchKeyword);

            if (matchIndexName != -1 ||
                    matchIndexDesc != -1 ||
                    saFound ||
                    treeFound) {

                System.out.println(
                        "\nEvent found: " +
                        e.getEventName()
                );

                System.out.println(
                        "Description: " +
                        e.getDescription()
                );

                System.out.println(
                        "Available Seats: " +
                        e.getAvailableSeats()
                );

                found = true;
            }
        }

        if (!found) {

            System.out.println(
                    "Event not found."
            );
        }
    }

    // =========================================================
    // OPTION 4
    // CORRECT PARTICIPANT NAME
    // =========================================================

    static void correctName() {

        System.out.println(
                "\n---------- CORRECT PARTICIPANT NAME ----------"
        );

        System.out.println(
                "Using Damerau-Levenshtein Dynamic Programming"
        );

        System.out.print(
                "Enter participant ID (0 for distance test): "
        );

        int id;

        try {

            id = Integer.parseInt(
                    sc.nextLine().trim()
            );

        } catch (NumberFormatException e) {

            System.out.println("Invalid ID.");
            return;
        }

        // ----------------------------------------------
        // Standalone edit distance demonstration
        // ----------------------------------------------

        if (id == 0) {

            System.out.print(
                    "Enter misspelled name: "
            );

            String entered =
                    sc.nextLine().trim();

            System.out.print(
                    "Enter correct name: "
            );

            String correct =
                    sc.nextLine().trim();

            int dist =
                    ParticipantNameCorrection.distance(
                            entered,
                            correct
                    );

            System.out.println(
                    "\nDamerau-Levenshtein Distance: "
                    + dist
            );

            if (dist <= 2) {

                System.out.println(
                        "Correction Confidence: HIGH"
                );

            } else {

                System.out.println(
                        "Correction Confidence: LOW"
                );
            }

            return;
        }

        // ----------------------------------------------
        // Find participant
        // ----------------------------------------------

        Participant target = null;

        for (Participant p : participants) {

            if (p.getParticipantId() == id) {

                target = p;
                break;
            }
        }

        if (target == null) {

            System.out.println(
                    "Participant not found."
            );

            return;
        }

        System.out.println(
                "Current Name: " +
                target.getName()
        );

        System.out.print(
                "Enter corrected name: "
        );

        String correctedName =
                sc.nextLine().trim();

        int editDist =
                ParticipantNameCorrection.distance(
                        target.getName(),
                        correctedName
                );

        target.setName(correctedName);

        System.out.println(
                "\nEdit Distance: " + editDist
        );

        System.out.println(
                "Participant name updated successfully."
        );
    }

    // =========================================================
    // OPTION 5
    // SCHEDULE ACTIVITIES
    // =========================================================

    static void scheduleActivities() {

        System.out.println(
                "\n---------- SCHEDULE ACTIVITIES ----------"
        );

        if (activities.isEmpty()) {

            System.out.println(
                    "No activities available."
            );

            return;
        }

        int n = activities.size();

        int[] startMinutes =
                new int[n];

        int[] endMinutes =
                new int[n];

        int[] capacities =
                new int[n];

        boolean[][] conflicts =
                new boolean[n][n];

        // ----------------------------------------------
        // Convert time
        // ----------------------------------------------

        for (int i = 0; i < n; i++) {

            Activity a =
                    activities.get(i);

            startMinutes[i] =
                    parseTimeToMinutes(
                            a.getStartTime()
                    );

            endMinutes[i] =
                    parseTimeToMinutes(
                            a.getEndTime()
                    );

            capacities[i] =
                    a.getCapacity();
        }

        // ----------------------------------------------
        // Build conflict graph
        // ----------------------------------------------

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                if (i != j &&
                        !(endMinutes[i] <= startMinutes[j] ||
                                endMinutes[j] <= startMinutes[i])) {

                    conflicts[i][j] = true;
                }
            }
        }

        // ----------------------------------------------
        // Greedy scheduling
        // ----------------------------------------------

        int[] selectedIndices =
                GreedyScheduler.select(
                        startMinutes,
                        endMinutes
                );

        // ----------------------------------------------
        // Bitmask DP
        // ----------------------------------------------

        int maxBitmaskVal =
                ActivitySelectionBitmaskDP.selectActivities(
                        capacities,
                        conflicts
                );

        System.out.println(
                "\nOptimal Non-Conflicting Schedule:"
        );

        for (int i = 0;
             i < selectedIndices.length;
             i++) {

            Activity act =
                    activities.get(
                            selectedIndices[i]
                    );

            System.out.println(
                    "Slot " + (i + 1) +
                    ": " +
                    act.getStartTime() +
                    " - " +
                    act.getEndTime() +
                    " | " +
                    act.getActivityName()
            );
        }

        System.out.println(
                "\nTotal Scheduled Activities: " +
                selectedIndices.length +
                " / " + n
        );

        System.out.println(
                "Algorithm: Greedy Interval Scheduling"
        );

        System.out.println(
                "DP Maximum Capacity: " +
                maxBitmaskVal
        );
    }

    // =========================================================
    // TIME CONVERSION
    // =========================================================

    private static int parseTimeToMinutes(
            String timeStr) {

        try {

            String[] parts =
                    timeStr.trim().split(":");

            return Integer.parseInt(parts[0]) * 60
                    + Integer.parseInt(parts[1]);

        } catch (Exception e) {

            return 0;
        }
    }

    // =========================================================
    // OPTION 6
    // ALLOCATE VENUE CAPACITY
    // =========================================================

    static void allocateVenue() {

        System.out.println(
                "\n---------- VENUE CAPACITY ALLOCATION ----------"
        );

        if (venues.isEmpty()) {

            System.out.println(
                    "No venues available."
            );

            return;
        }

        int totalVenueCapacity = 0;

        System.out.println(
                "\nAvailable Venues:"
        );

        for (Venue v : venues) {

            System.out.println(
                    "- " +
                    v.getVenueName() +
                    " | Capacity: " +
                    v.getCapacity() +
                    " | Location: " +
                    v.getLocation()
            );

            totalVenueCapacity +=
                    v.getCapacity();
        }

        System.out.print(
                "\nEnter number of participants: "
        );

        int count;

        try {

            count = Integer.parseInt(
                    sc.nextLine().trim()
            );

        } catch (NumberFormatException e) {

            System.out.println(
                    "Invalid count."
            );

            return;
        }

        // ----------------------------------------------
        // Flow network
        // ----------------------------------------------

        int numVenues =
                venues.size();

        int totalNodes =
                numVenues + 3;

        int source = 0;

        int participantNode = 1;

        int sink =
                totalNodes - 1;

        int[][] capacityGraph =
                new int[totalNodes][totalNodes];

        capacityGraph[source]
                [participantNode] = count;

        FacilityFlowDinic dinic =
                new FacilityFlowDinic(
                        totalNodes
                );

        dinic.addEdge(
                source,
                participantNode,
                count
        );

        for (int i = 0;
             i < numVenues;
             i++) {

            int venueNode =
                    2 + i;

            int cap =
                    venues.get(i).getCapacity();

            capacityGraph[participantNode]
                    [venueNode] = cap;

            capacityGraph[venueNode]
                    [sink] = cap;

            dinic.addEdge(
                    participantNode,
                    venueNode,
                    cap
            );

            dinic.addEdge(
                    venueNode,
                    sink,
                    cap
            );
        }

        // ----------------------------------------------
        // Edmonds-Karp
        // ----------------------------------------------

        int maxAccommodated =
                VenueCapacityEdmondsKarp.maxFlow(
                        capacityGraph,
                        source,
                        sink
                );

        // ----------------------------------------------
        // Dinic
        // ----------------------------------------------

        int dinicMaxFlow =
                dinic.maxFlow(
                        source,
                        sink
                );

        System.out.println(
                "\n[Network Flow Analysis]"
        );

        System.out.println(
                "Requested Allocation : " +
                count
        );

        System.out.println(
                "Edmonds-Karp         : " +
                maxAccommodated
        );

        System.out.println(
                "Dinic                : " +
                dinicMaxFlow
        );

        System.out.println(
                "Total Venue Capacity : " +
                totalVenueCapacity
        );

        if (maxAccommodated >= count) {

            System.out.println(
                    "Capacity Status: Successfully accommodated."
            );

        } else {

            System.out.println(
                    "Capacity Status: Bottleneck detected."
            );

            System.out.println(
                    "Shortfall: " +
                    (count - maxAccommodated)
            );
        }

        // ----------------------------------------------
        // Knapsack
        // ----------------------------------------------

        int[] equipmentWeights =
                {10, 20, 30, 40};

        int[] equipmentValues =
                {60, 100, 120, 150};

        int maxEquipmentValue =
                Knapsack.maxValue(
                        equipmentWeights,
                        equipmentValues,
                        50
                );

        System.out.println(
                "[Knapsack DP] Equipment Value: " +
                maxEquipmentValue
        );

        // ----------------------------------------------
        // Single venue check
        // ----------------------------------------------

        boolean singleFound = false;

        System.out.println(
                "\nSuitable Single Venues:"
        );

        for (Venue v : venues) {

            if (v.getCapacity() >= count) {

                System.out.println(
                        "- " +
                        v.getVenueName() +
                        " (Capacity: " +
                        v.getCapacity() +
                        ")"
                );

                singleFound = true;
            }
        }

        if (!singleFound) {

            System.out.println(
                    "No single venue can accommodate everyone."
            );
        }
    }

    // =========================================================
    // OPTION 7
    // ASSIGN VOLUNTEERS
    // =========================================================

    static void assignVolunteer() {

        System.out.println(
                "\n---------- ASSIGN VOLUNTEERS ----------"
        );

        if (volunteers.isEmpty() ||
                activities.isEmpty()) {

            System.out.println(
                    "Insufficient volunteers or activities."
            );

            return;
        }

        int numV =
                volunteers.size();

        int numA =
                activities.size();

        boolean[][] eligible =
                new boolean[numV][numA];

        for (int i = 0;
             i < numV;
             i++) {

            Volunteer vol =
                    volunteers.get(i);

            String skill =
                    vol.getSkill().toLowerCase();

            for (int j = 0;
                 j < numA;
                 j++) {

                Activity act =
                        activities.get(j);

                String actName =
                        act.getActivityName()
                                .toLowerCase();

                if (
                        (skill.contains("robotics")
                                && actName.contains("robotics"))

                        ||

                        (skill.contains("ai")
                                && actName.contains("ai"))

                        ||

                        (skill.contains("web")
                                && actName.contains("web"))

                        ||

                        (skill.contains("logistics")
                                &&
                                (actName.contains("keynote")
                                || actName.contains("valedictory")))

                        ||

                        (skill.contains("coding")
                                && actName.contains("speed"))
                ) {

                    eligible[i][j] = true;
                }
            }

            // Ensure every volunteer has possible assignments
            eligible[i][i % numA] = true;

            eligible[i][(i + 1) % numA] = true;
        }

        int[] activityOwner =
                VolunteerActivityMatching.matchVolunteers(
                        eligible
                );

        int minVertexCover =
                AssignmentConflictKonig.computeMinVertexCover(
                        eligible
                );

        int assigned = 0;

        for (int v : activityOwner) {

            if (v != -1) {
                assigned++;
            }
        }

        System.out.println(
                "\nMaximum Bipartite Matching:"
        );

        System.out.println(
                "Total Volunteers Assigned: " +
                assigned +
                " / " +
                numV
        );

        for (int a = 0;
             a < numA;
             a++) {

            if (activityOwner[a] != -1) {

                int volIdx =
                        activityOwner[a];

                volunteers
                        .get(volIdx)
                        .assignActivity(
                                activities
                                        .get(a)
                                        .getActivityId()
                        );

                System.out.println(
                        "Activity '" +
                        activities
                                .get(a)
                                .getActivityName() +
                        "' <--- Volunteer ---> '" +
                        volunteers
                                .get(volIdx)
                                .getName() +
                        "'"
                );
            }
        }

        System.out.println(
                "\nKonig's Theorem:"
        );

        System.out.println(
                "Minimum Vertex Cover = " +
                minVertexCover
        );

        System.out.println(
                "Maximum Matching = " +
                assigned
        );
    }

    // =========================================================
    // OPTION 8
    // ANALYZE EVENT DATA
    // =========================================================

    static void analyzeData() {

        System.out.println(
                "\n---------- EVENT DATA ANALYSIS ----------"
        );

        // ----------------------------------------------
        // Core metrics
        // ----------------------------------------------

        System.out.println(
                "\n[1] CORE PLATFORM METRICS"
        );

        System.out.println(
                "Total Events       : " +
                events.size()
        );

        System.out.println(
                "Total Participants : " +
                participants.size()
        );

        System.out.println(
                "Total Registrations: " +
                registrations.size()
        );

        System.out.println(
                "Total Venues       : " +
                venues.size()
        );

        System.out.println(
                "Total Activities   : " +
                activities.size()
        );

        System.out.println(
                "Total Volunteers   : " +
                volunteers.size()
        );

        System.out.println(
                "Total Tickets      : " +
                ticketService.getTickets().size()
        );

        System.out.println(
                "Total Check-Ins    : " +
                checkInService.getCheckInCount()
        );

        System.out.println(
                "Waitlist Size      : " +
                waitlistService.getWaitlistSize()
        );

        // ----------------------------------------------
        // String Algorithms
        // ----------------------------------------------

        System.out.println(
                "\n[2] STRING ALGORITHM ANALYSIS"
        );

        String sampleText =
                "ai hackathon web development robotics challenge data science";

        int[] zArray =
                EventDescriptionZFunction.calculateZ(
                        sampleText
                );

        System.out.println(
                "Z-Algorithm array length: " +
                zArray.length
        );

        int[] sa =
                EventDescriptionSuffixArray.buildSuffixArray(
                        sampleText
                );

        int[] lcp =
                EventSimilarityLCP.buildLCP(
                        sampleText,
                        sa
                );

        System.out.println(
                "Suffix Array size: " +
                sa.length
        );

        System.out.println(
                "LCP size: " +
                lcp.length
        );

        // ----------------------------------------------
        // Dynamic Programming
        // ----------------------------------------------

        System.out.println(
                "\n[3] ADVANCED DP ANALYSIS"
        );

        int[] frequencies =
                {34, 8, 50, 20};

        int bstCost =
                EventAccessOptimalBST.minimumCost(
                        frequencies
                );

        System.out.println(
                "Optimal BST Cost: " +
                bstCost
        );

        int[] matrixDims =
                {10, 20, 30, 40};

        int mcmCost =
                EventDataMatrixChain.minimumCost(
                        matrixDims
                );

        System.out.println(
                "Matrix Chain Multiplication Cost: " +
                mcmCost
        );

        // ----------------------------------------------
        // Randomized Algorithms
        // ----------------------------------------------

        System.out.println(
                "\n[4] RANDOMIZED ALGORITHMS"
        );

        int[] participantIds =
                new int[participants.size()];

        for (int i = 0;
             i < participants.size();
             i++) {

            participantIds[i] =
                    participants
                            .get(i)
                            .getParticipantId();
        }

        if (participantIds.length > 0) {

            RandomizedParallelEngine.randomizedQuickSort(
                    participantIds,
                    0,
                    participantIds.length - 1
            );
        }

        System.out.print(
                "Randomized QuickSort IDs: "
        );

        for (int id : participantIds) {

            System.out.print(
                    id + " "
            );
        }

        System.out.println();

        // Miller-Rabin as primality testing
        long testNumber = 104729L;

        boolean prime =
                RandomizedParallelEngine.millerRabinTest(
                        testNumber,
                        7
                );

        System.out.println(
                "Miller-Rabin for " +
                testNumber +
                ": " +
                (prime ? "PRIME" : "COMPOSITE")
        );

        // ----------------------------------------------
        // Reservoir Sampling
        // ----------------------------------------------

        List<String> attendeeNames =
                new ArrayList<>();

        for (Participant p : participants) {

            attendeeNames.add(
                    p.getName()
            );
        }

        if (!attendeeNames.isEmpty()) {

            List<String> sampled =
                    RandomizedParallelEngine.reservoirSample(
                            attendeeNames,
                            1
                    );

            if (!sampled.isEmpty()) {

                System.out.println(
                        "Reservoir Sampling Winner: " +
                        sampled.get(0)
                );
            }
        }

        // ----------------------------------------------
        // Performance Benchmark
        // ----------------------------------------------

        System.out.println(
                "\n[5] PARALLEL PERFORMANCE"
        );

        int benchmarkSize = 100000;

        System.out.println(
                "Benchmark size: " +
                benchmarkSize
        );

        RandomizedParallelEngine.BenchmarkResult bench =
                RandomizedParallelEngine.evaluatePerformance(
                        benchmarkSize
                );

        System.out.println(
                "CPU Cores: " +
                bench.cpuCores
        );

        System.out.println(
                "Sequential Time: " +
                bench.sequentialTimeMs +
                " ms"
        );

        System.out.println(
                "Parallel Time: " +
                bench.parallelTimeMs +
                " ms"
        );

        System.out.printf(
                "Speedup: %.2fx%n",
                bench.speedup
        );

        // ----------------------------------------------
        // Set Cover
        // ----------------------------------------------

        demonstrateSetCoverApproximation();
    }

    // =========================================================
    // OPTION 9
    // MANAGE VENUES
    // =========================================================

    static void manageVenues() {

        System.out.println(
                "\n---------- MANAGE VENUES ----------"
        );

        System.out.println("1. View All Venues");
        System.out.println("2. Add New Venue");

        System.out.print(
                "Enter choice: "
        );

        String choice =
                sc.nextLine().trim();

        if (choice.equals("1")) {

            if (venues.isEmpty()) {

                System.out.println(
                        "No venues available."
                );

                return;
            }

            for (Venue v : venues) {

                v.displayVenue();

                System.out.println();
            }

            return;
        }

        if (!choice.equals("2")) {

            System.out.println(
                    "Invalid choice."
            );

            return;
        }

        System.out.print(
                "Enter Venue ID: "
        );

        int id;

        try {

            id = Integer.parseInt(
                    sc.nextLine().trim()
            );

        } catch (NumberFormatException e) {

            System.out.println(
                    "Invalid ID."
            );

            return;
        }

        System.out.print(
                "Enter Venue Name: "
        );

        String name =
                sc.nextLine().trim();

        System.out.print(
                "Enter Location: "
        );

        String location =
                sc.nextLine().trim();

        System.out.print(
                "Enter Capacity: "
        );

        int capacity;

        try {

            capacity =
                    Integer.parseInt(
                            sc.nextLine().trim()
                    );

        } catch (NumberFormatException e) {

            System.out.println(
                    "Invalid Capacity."
            );

            return;
        }

        Venue v =
                new Venue(
                        id,
                        name,
                        location,
                        capacity
                );

        venues.add(v);

        System.out.println(
                "\nVenue added successfully."
        );

        v.displayVenue();
    }

    // =========================================================
    // OPTION 10
    // MANAGE ACTIVITIES
    // =========================================================

    static void manageActivities() {

        System.out.println(
                "\n---------- MANAGE ACTIVITIES ----------"
        );

        System.out.println(
                "1. View All Activities"
        );

        System.out.println(
                "2. Add New Activity"
        );

        System.out.print(
                "Enter choice: "
        );

        String choice =
                sc.nextLine().trim();

        if (choice.equals("1")) {

            if (activities.isEmpty()) {

                System.out.println(
                        "No activities available."
                );

                return;
            }

            for (Activity a : activities) {

                a.displayActivity();

                System.out.println();
            }

            return;
        }

        if (!choice.equals("2")) {

            System.out.println(
                    "Invalid choice."
            );

            return;
        }

        System.out.print(
                "Enter Activity ID: "
        );

        int id;

        try {

            id =
                    Integer.parseInt(
                            sc.nextLine().trim()
                    );

        } catch (NumberFormatException e) {

            System.out.println(
                    "Invalid ID."
            );

            return;
        }

        System.out.print(
                "Enter Activity Name: "
        );

        String name =
                sc.nextLine().trim();

        System.out.print(
                "Enter Start Time (HH:mm): "
        );

        String start =
                sc.nextLine().trim();

        System.out.print(
                "Enter End Time (HH:mm): "
        );

        String end =
                sc.nextLine().trim();

        System.out.print(
                "Enter Capacity: "
        );

        int capacity;

        try {

            capacity =
                    Integer.parseInt(
                            sc.nextLine().trim()
                    );

        } catch (NumberFormatException e) {

            System.out.println(
                    "Invalid Capacity."
            );

            return;
        }

        Activity a =
                new Activity(
                        id,
                        name,
                        start,
                        end,
                        capacity
                );

        activities.add(a);

        System.out.println(
                "\nActivity added successfully."
        );

        a.displayActivity();
    }

    // =========================================================
    // OPTION 11
    // VIEW REGISTRATIONS
    // =========================================================

    static void viewRegistrations() {

        System.out.println(
                "\n---------- REGISTRATIONS ----------"
        );

        if (registrations.isEmpty()) {

            System.out.println(
                    "No registrations available."
            );

            return;
        }

        for (Registration r :
                registrations) {

            r.displayRegistration();

            System.out.println();
        }
    }

    // =========================================================
    // OPTION 12
    // GENERATE TICKET
    // =========================================================

    static void generateTicket() {

        System.out.println(
                "\n---------- GENERATE TICKET ----------"
        );

        System.out.print(
                "Enter Registration Code: "
        );

        String registrationCode =
                sc.nextLine().trim();

        Registration selected =
                null;

        for (Registration r :
                registrations) {

            if (r.getRegistrationCode()
                    .equalsIgnoreCase(
                            registrationCode
                    )) {

                selected = r;
                break;
            }
        }

        if (selected == null) {

            System.out.println(
                    "Registration not found."
            );

            return;
        }

        // Prevent duplicate ticket
        if (ticketService.findTicketByRegistrationCode(
                registrationCode) != null) {

            System.out.println(
                    "Ticket already generated for this registration."
            );

            return;
        }

        ticketService.generateTicket(
                registrationCode,
                selected.getParticipantId(),
                selected.getEventId()
        );

        System.out.println(
                "\nTicket generation completed."
        );
    }

    // =========================================================
    // OPTION 13
    // VERIFY TICKET
    // =========================================================

    static void verifyTicket() {

        System.out.println(
                "\n---------- VERIFY TICKET ----------"
        );

        System.out.print(
                "Enter Ticket ID: "
        );

        String ticketId =
                sc.nextLine().trim();

        ticketService.verifyTicket(
                ticketId
        );
    }

    // =========================================================
    // OPTION 14
    // CHECK-IN
    // =========================================================

    static void checkInParticipant() {

        System.out.println(
                "\n---------- PARTICIPANT CHECK-IN ----------"
        );

        System.out.print(
                "Enter Ticket ID: "
        );

        String ticketId =
                sc.nextLine().trim();

        checkInService.checkInParticipant(
                ticketId,
                ticketService
        );
    }

    // =========================================================
    // OPTION 15
    // TICKETS & CHECK-INS
    // =========================================================

    static void displayTicketAndCheckIns() {

        System.out.println(
                "\n========== EVENTFLOW TICKET DASHBOARD =========="
        );

        ticketService.displayAllTickets();

        checkInService.displayAllCheckIns();

        System.out.println(
                "\nTotal Tickets: " +
                ticketService.getTickets().size()
        );

        System.out.println(
                "Total Check-Ins: " +
                checkInService.getCheckInCount()
        );
    }

    // =========================================================
    // OPTION 16
    // WAITLIST
    // =========================================================

    static void manageWaitlist() {

        System.out.println(
                "\n---------- WAITLIST MANAGEMENT ----------"
        );

        System.out.println(
                "1. Add Participant"
        );

        System.out.println(
                "2. Promote Next Participant"
        );

        System.out.println(
                "3. View Waitlist"
        );

        System.out.print(
                "Enter choice: "
        );

        String choice =
                sc.nextLine().trim();

        switch (choice) {

            case "1":

                System.out.print(
                        "Enter Participant ID: "
                );

                try {

                    int id =
                            Integer.parseInt(
                                    sc.nextLine().trim()
                            );

                    waitlistService.addToWaitlist(
                            id
                    );

                } catch (NumberFormatException e) {

                    System.out.println(
                            "Invalid Participant ID."
                    );
                }

                break;

            case "2":

                Integer promoted =
                        waitlistService
                                .promoteNextParticipant();

                if (promoted != null) {

                    System.out.println(
                            "Participant " +
                            promoted +
                            " is ready for registration."
                    );
                }

                break;

            case "3":

                waitlistService.displayWaitlist();

                break;

            default:

                System.out.println(
                        "Invalid choice."
                );
        }
    }

    // =========================================================
    // OPTION 17
    // DASHBOARD
    // =========================================================

    static void showDashboard() {

        System.out.println();
        System.out.println(
                "================================================"
        );

        System.out.println(
                "             EVENTFLOW DASHBOARD"
        );

        System.out.println(
                "================================================"
        );

        System.out.println(
                "Events              : " +
                events.size()
        );

        System.out.println(
                "Participants        : " +
                participants.size()
        );

        System.out.println(
                "Registrations       : " +
                registrations.size()
        );

        System.out.println(
                "Venues              : " +
                venues.size()
        );

        System.out.println(
                "Activities          : " +
                activities.size()
        );

        System.out.println(
                "Volunteers          : " +
                volunteers.size()
        );

        System.out.println(
                "Tickets             : " +
                ticketService.getTickets().size()
        );

        System.out.println(
                "Check-Ins           : " +
                checkInService.getCheckInCount()
        );

        System.out.println(
                "Waitlisted          : " +
                waitlistService.getWaitlistSize()
        );

        // ----------------------------------------------
        // Registration percentage
        // ----------------------------------------------

        int totalCapacity = 0;

        int availableSeats = 0;

        for (Event e : events) {

            totalCapacity +=
                    e.getTotalSeats();

            availableSeats +=
                    e.getAvailableSeats();
        }

        int registered =
                totalCapacity - availableSeats;

        double occupancy = 0;

        if (totalCapacity > 0) {

            occupancy =
                    ((double) registered /
                    totalCapacity) * 100;
        }

        System.out.printf(
                "Overall Occupancy   : %.2f%%%n",
                occupancy
        );

        System.out.println(
                "================================================"
        );
    }

    // =========================================================
    // SET COVER APPROXIMATION
    // =========================================================

    static void demonstrateSetCoverApproximation() {

        System.out.println(
                "\n[6] NP-COMPLETENESS & APPROXIMATION"
        );

        System.out.println(
                "Problem: Cover all event tasks using fewer volunteer teams."
        );

        String[] tasks = {

                "Registration",
                "Technical Support",
                "Crowd Management",
                "Stage Management"
        };

        String[] teamNames = {

                "Registration Team",
                "Technical Team",
                "Event Operations Team",
                "Safety Team"
        };

        String[][] teamTasks = {

                {
                        "Registration",
                        "Crowd Management"
                },

                {
                        "Technical Support",
                        "Stage Management"
                },

                {
                        "Registration",
                        "Stage Management"
                },

                {
                        "Crowd Management",
                        "Technical Support"
                }
        };

        Set<String> uncovered =
                new LinkedHashSet<>(
                        Arrays.asList(tasks)
                );

        List<String> selectedTeams =
                new ArrayList<>();

        boolean[] chosen =
                new boolean[teamNames.length];

        while (!uncovered.isEmpty()) {

            int bestTeam = -1;

            int bestCount = 0;

            for (int i = 0;
                 i < teamTasks.length;
                 i++) {

                if (chosen[i]) {
                    continue;
                }

                int count = 0;

                for (String task :
                        teamTasks[i]) {

                    if (uncovered.contains(task)) {

                        count++;
                    }
                }

                if (count > bestCount) {

                    bestCount = count;

                    bestTeam = i;
                }
            }

            if (bestTeam == -1) {

                System.out.println(
                        "Some tasks cannot be covered."
                );

                return;
            }

            chosen[bestTeam] = true;

            selectedTeams.add(
                    teamNames[bestTeam]
            );

            for (String task :
                    teamTasks[bestTeam]) {

                uncovered.remove(task);
            }
        }

        System.out.println(
                "\nSelected Volunteer Teams:"
        );

        for (String team :
                selectedTeams) {

            System.out.println(
                    "- " + team
            );
        }

        System.out.println(
                "Total teams selected: " +
                selectedTeams.size()
        );

        System.out.println(
                "All required tasks are covered."
        );

        System.out.println(
                "Algorithm: Greedy Set Cover Approximation"
        );

        System.out.println(
                "Theory: Set Cover is NP-hard."
        );
    }
}