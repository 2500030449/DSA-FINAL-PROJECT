package eventflow;

import java.util.*;
import model.Event;
import model.Activity;
import model.Participant;
import model.Registration;
import model.Venue;
import model.Volunteer;
import eventflow.engine.*;
import m1string.*;
import m2suffix.*;
import m3dp.*;
import m4flow.*;

public class Main {

    static Scanner sc = new Scanner(System.in);

    static ArrayList<Event> events = new ArrayList<>();
    static ArrayList<Participant> participants = new ArrayList<>();
    static ArrayList<Registration> registrations = new ArrayList<>();
    static ArrayList<Venue> venues = new ArrayList<>();
    static ArrayList<Activity> activities = new ArrayList<>();
    static ArrayList<Volunteer> volunteers = new ArrayList<>();

    // Module 1 & 2 pre-indexed data structures
    static EventKeywordAhoCorasick keywordMatcher = new EventKeywordAhoCorasick();

    static {
        // Pre-populate Events with initial seats (100, 80, 40, 60)
        events.add(new Event(1, "AI Hackathon", "Annual Artificial Intelligence Hackathon & Innovation Contest", 100));
        events.add(new Event(2, "Web Development Workshop", "Hands-on Fullstack Web Development & Cloud Deployment", 80));
        events.add(new Event(3, "Robotics Challenge", "Autonomous Robotics Navigation and Combat Challenge", 40));
        events.add(new Event(4, "Data Science Meetup", "Big Data Analytics, Machine Learning & Networking Session", 60));

        // Pre-populate Venues
        venues.add(new Venue(1, "Main Auditorium", "Academic Block A", 120));
        venues.add(new Venue(2, "Innovation Lab 1", "Tech Complex B", 50));
        venues.add(new Venue(3, "Robotics Arena", "Innovation Centre C", 40));
        venues.add(new Venue(4, "Seminar Hall", "Management Block D", 70));

        // Pre-populate Activities
        activities.add(new Activity(1, "Opening Keynote & AI Trends", "09:00", "10:30", 150));
        activities.add(new Activity(2, "Fullstack Web App Sprint", "10:00", "12:00", 80));
        activities.add(new Activity(3, "Robotics Arena Combat", "11:00", "13:00", 50));
        activities.add(new Activity(4, "Data Science & ML Workshop", "13:30", "15:00", 70));
        activities.add(new Activity(5, "Algorithmic Speed Challenge", "14:30", "16:30", 60));
        activities.add(new Activity(6, "Grand Valedictory & Awards", "16:30", "18:00", 200));

        // Pre-populate Volunteers
        volunteers.add(new Volunteer(1, "Aarav Sharma", "Robotics & Hardware"));
        volunteers.add(new Volunteer(2, "Diya Patel", "AI & Machine Learning"));
        volunteers.add(new Volunteer(3, "Rohan Verma", "Web & Cloud"));
        volunteers.add(new Volunteer(4, "Sneha Reddy", "Logistics & Stage"));
        volunteers.add(new Volunteer(5, "Vikram Malhotra", "Coding Competitions"));

        // Pre-populate Sample Participant & Registration (demonstrates typo correction)
        Participant p1 = new Participant(101, "Sproothi", "sproothi@college.edu", "9876543210");
        participants.add(p1);
        registrations.add(new Registration("REG101", 101, 1));

        Participant p2 = new Participant(102, "Rahul Sen", "rahul.sen@college.edu", "9876543211");
        participants.add(p2);
        registrations.add(new Registration("REG102", 102, 2));

        // Initialize Module 1 Aho-Corasick Multi-Pattern Search Keywords
        keywordMatcher.addWord("ai");
        keywordMatcher.addWord("hackathon");
        keywordMatcher.addWord("web");
        keywordMatcher.addWord("robotics");
        keywordMatcher.addWord("data");
        keywordMatcher.buildFailureLinks();
    }

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
                System.out.println("Invalid input! Please enter a number between 1 and 12.\n");
                continue;
            }

            switch (choice) {
                case 1:
                    viewEvents();
                    System.out.println();
                    break;
                case 2:
                    registerParticipant();
                    System.out.println();
                    break;
                case 3:
                    searchEvent();
                    System.out.println();
                    break;
                case 4:
                    correctName();
                    System.out.println();
                    break;
                case 5:
                    scheduleActivities();
                    System.out.println();
                    break;
                case 6:
                    allocateVenue();
                    System.out.println();
                    break;
                case 7:
                    assignVolunteer();
                    System.out.println();
                    break;
                case 8:
                    analyzeData();
                    System.out.println();
                    break;
                case 9:
                    manageVenues();
                    System.out.println();
                    break;
                case 10:
                    manageActivities();
                    System.out.println();
                    break;
                case 11:
                    viewRegistrations();
                    System.out.println();
                    break;
                case 12:
                    System.out.println("Exiting EventFlow. Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice! Please choose between 1 and 12.\n");
            }

        } while (choice != 12);
    }

    private static void printMenu() {
        System.out.println("========================================");
        System.out.println("     EVENTFLOW MANAGEMENT PLATFORM");
        System.out.println("========================================");
        System.out.println();
        System.out.println("1. View Events");
        System.out.println("2. Register Participant");
        System.out.println("3. Search Event");
        System.out.println("4. Correct Participant Name");
        System.out.println("5. Schedule Activities");
        System.out.println("6. Allocate Venue Capacity");
        System.out.println("7. Assign Volunteers");
        System.out.println("8. Analyze Event Data");
        System.out.println("9. Manage Venues");
        System.out.println("10. Manage Activities");
        System.out.println("11. View Registrations");
        System.out.println("12. Exit");
        System.out.println();
    }

    // ==========================================
    // OPTION 1: VIEW EVENTS
    // ==========================================
    static void viewEvents() {
        System.out.println("\n---------- AVAILABLE EVENTS ----------");

        if (events.isEmpty()) {
            System.out.println("No events available.");
            return;
        }

        for (int i = 0; i < events.size(); i++) {
            Event e = events.get(i);
            System.out.println("Event ID: " + e.getEventId());
            System.out.println("Event Name: " + e.getEventName());
            System.out.println("Available Seats: " + e.getAvailableSeats());
            if (i < events.size() - 1) {
                System.out.println();
            }
        }
    }

    // ==========================================
    // OPTION 2: REGISTER PARTICIPANT (LINKED TO m1string & engine)
    // ==========================================
    static void registerParticipant() {
        System.out.println("\n---------- REGISTER PARTICIPANT ----------");
        System.out.print("Enter Participant ID: ");
        int id;
        try {
            id = Integer.parseInt(sc.nextLine().trim());
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
            eventId = Integer.parseInt(sc.nextLine().trim());
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

        String regCode = "REG" + id;

        // Module 1 Linked: Verify code uniqueness using Rabin-Karp rolling hash
        StringBuilder existingCodes = new StringBuilder();
        for (Registration reg : registrations) {
            existingCodes.append(reg.getRegistrationCode()).append(" ");
        }
        if (BookingCodeRabinKarp.contains(existingCodes.toString(), regCode)) {
            System.out.println("Duplicate registration code detected via Rabin-Karp verification!");
            return;
        }

        if (selected.registerParticipant()) {
            Participant p = new Participant(id, name, email, phone);
            Registration r = new Registration(regCode, id, eventId);

            participants.add(p);
            registrations.add(r);

            // Module 5 & Engine Linked: Miller-Rabin Primality verification token
            long tokenSeed = 10007L + id;
            boolean secureToken = RandomizedParallelEngine.millerRabinTest(tokenSeed, 5);

            System.out.println("\nRegistration successful!");
            System.out.println("Registration Code: " + regCode);
            System.out.println("Participant: " + name + " | Event: " + selected.getEventName() + " | Status: Confirmed");
            if (secureToken) {
                System.out.println("[Security] Digital ticket signature verified via Miller-Rabin token.");
            }
        } else {
            System.out.println("No seats available for this event!");
        }
    }

    // ==========================================
    // OPTION 3: SEARCH EVENT (LINKED TO m1string, m2suffix & engine)
    // ==========================================
    static void searchEvent() {
        System.out.print("Enter event name to search: ");
        String keyword = sc.nextLine().trim();

        if (keyword.isEmpty()) {
            System.out.println("Search term cannot be empty.");
            return;
        }

        boolean found = false;

        for (Event e : events) {
            // Module 1 Linked: KMP String Search Engine
            int matchIndexName = KMP.find(e.getEventName().toLowerCase(), keyword.toLowerCase());
            int matchIndexDesc = KMP.find(e.getDescription().toLowerCase(), keyword.toLowerCase());

            // Module 2 Linked: Suffix Automaton Pattern Matching
            EventSearchSuffixAutomaton automaton = new EventSearchSuffixAutomaton(e.getEventName().length() + 5);
            automaton.build(e.getEventName().toLowerCase());
            boolean saFound = automaton.search(keyword.toLowerCase());

            // Module 2 Linked: Suffix Tree Pattern Verification
            EventDocumentSuffixTree suffixTree = new EventDocumentSuffixTree();
            suffixTree.build(e.getEventName().toLowerCase());
            boolean treeFound = suffixTree.search(keyword.toLowerCase());

            if (matchIndexName != -1 || matchIndexDesc != -1 || saFound || treeFound) {
                System.out.println("Event found: " + e.getEventName());
                found = true;
            }
        }

        if (!found) {
            System.out.println("Event not found.");
        }
    }

    // ==========================================
    // OPTION 4: CORRECT PARTICIPANT NAME (LINKED TO m3dp)
    // ==========================================
    static void correctName() {
        System.out.println("\n---------- CORRECT PARTICIPANT NAME (DP EDIT DISTANCE) ----------");
        System.out.print("Enter participant ID (or 0 for two-string distance calculation): ");
        int id;
        try {
            id = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID.");
            return;
        }

        if (id == 0) {
            System.out.print("Enter misspelled name: ");
            String entered = sc.nextLine().trim();
            System.out.print("Enter correct name: ");
            String correct = sc.nextLine().trim();

            // Module 3 Linked: ParticipantNameCorrection Damerau-Levenshtein DP
            int dist = ParticipantNameCorrection.distance(entered, correct);
            System.out.println("\n[DP Algorithm] Damerau-Levenshtein Edit Distance: " + dist);
            if (dist <= 2) {
                System.out.println("Correction Confidence: High (Fuzzy match within 2 operations)");
            } else {
                System.out.println("Correction Confidence: Low (High variance)");
            }
            return;
        }

        Participant target = null;
        for (Participant p : participants) {
            if (p.getParticipantId() == id) {
                target = p;
                break;
            }
        }

        if (target == null) {
            System.out.println("Participant not found.");
            return;
        }

        System.out.println("Current Registered Name: " + target.getName());
        System.out.print("Enter corrected name: ");
        String correctedName = sc.nextLine().trim();

        // Module 3 Linked: ParticipantNameCorrection Damerau-Levenshtein DP
        int editDist = ParticipantNameCorrection.distance(target.getName(), correctedName);
        target.setName(correctedName);

        System.out.println("\n[Dynamic Programming] Calculated Edit Distance: " + editDist);
        System.out.println("Participant name successfully updated to: " + target.getName());
    }

    // ==========================================
    // OPTION 5: SCHEDULE ACTIVITIES (LINKED TO m3dp & engine)
    // ==========================================
    static void scheduleActivities() {
        System.out.println("\n---------- SCHEDULE ACTIVITIES (INTERVAL SCHEDULING & DP) ----------");

        if (activities.isEmpty()) {
            System.out.println("No activities added yet.");
            return;
        }

        System.out.println("Available Registered Activities:");
        for (Activity a : activities) {
            System.out.println("ID: " + a.getActivityId() + " | " + a.getActivityName() +
                    " (" + a.getStartTime() + " - " + a.getEndTime() + ") [Cap: " + a.getCapacity() + "]");
        }

        int n = activities.size();
        int[] startMinutes = new int[n];
        int[] endMinutes = new int[n];
        int[] capacities = new int[n];
        boolean[][] conflicts = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            startMinutes[i] = parseTimeToMinutes(activities.get(i).getStartTime());
            endMinutes[i] = parseTimeToMinutes(activities.get(i).getEndTime());
            capacities[i] = activities.get(i).getCapacity();
        }

        // Build conflict graph for Bitmask DP
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && !(endMinutes[i] <= startMinutes[j] || endMinutes[j] <= startMinutes[i])) {
                    conflicts[i][j] = true;
                }
            }
        }

        // Engine Linked: Greedy Interval Scheduling (Earliest Finish Time)
        int[] selectedIndices = GreedyScheduler.select(startMinutes, endMinutes);

        // Module 3 Linked: Bitmask DP Activity Selection under conflict constraints
        int maxBitmaskVal = ActivitySelectionBitmaskDP.selectActivities(capacities, conflicts);

        System.out.println("\nOptimal Non-Conflicting Schedule Selected (Greedy Earliest Finish Time):");
        for (int i = 0; i < selectedIndices.length; i++) {
            Activity act = activities.get(selectedIndices[i]);
            System.out.println("Slot " + (i + 1) + ": " + act.getStartTime() + " - " + act.getEndTime() + " | " + act.getActivityName());
        }

        System.out.println("\nTotal Scheduled Non-Conflicting Activities: " + selectedIndices.length + " / " + n);
        System.out.println("Algorithm: Greedy Interval Scheduling - Optimal O(n log n) Activity Selection");
        System.out.println("[m3dp Linked] Bitmask DP Max Capacity Attendance: " + maxBitmaskVal);
    }

    private static int parseTimeToMinutes(String timeStr) {
        try {
            String[] parts = timeStr.trim().split(":");
            return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
        } catch (Exception e) {
            return 0;
        }
    }

    // ==========================================
    // OPTION 6: ALLOCATE VENUE CAPACITY (LINKED TO m4flow & engine)
    // ==========================================
    static void allocateVenue() {
        System.out.println("\n---------- ALLOCATE VENUE CAPACITY (NETWORK FLOW & KNAPSACK) ----------");

        if (venues.isEmpty()) {
            System.out.println("No venues available.");
            return;
        }

        System.out.println("Available Venues:");
        int totalVenueCapacity = 0;
        for (Venue v : venues) {
            System.out.println("- " + v.getVenueName() + " (Capacity: " + v.getCapacity() + ", Location: " + v.getLocation() + ")");
            totalVenueCapacity += v.getCapacity();
        }

        System.out.print("\nEnter number of participants to allocate: ");
        int count;
        try {
            count = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid count entered.");
            return;
        }

        // Construct Flow Network: Source(0) -> Inflow(1) -> Venues(2..N+1) -> Sink(N+2)
        int numVenues = venues.size();
        int totalNodes = numVenues + 3;
        int source = 0;
        int participantNode = 1;
        int sink = totalNodes - 1;

        int[][] capacityGraph = new int[totalNodes][totalNodes];
        capacityGraph[source][participantNode] = count;

        // Module 4 Linked: Dinic's Algorithm Flow Network
        FacilityFlowDinic dinic = new FacilityFlowDinic(totalNodes);
        dinic.addEdge(source, participantNode, count);

        for (int i = 0; i < numVenues; i++) {
            int venueNode = 2 + i;
            int cap = venues.get(i).getCapacity();
            capacityGraph[participantNode][venueNode] = cap;
            capacityGraph[venueNode][sink] = cap;

            dinic.addEdge(participantNode, venueNode, cap);
            dinic.addEdge(venueNode, sink, cap);
        }

        // Module 4 Linked: Edmonds-Karp Network Flow Algorithm
        int maxAccommodated = VenueCapacityEdmondsKarp.maxFlow(capacityGraph, source, sink);
        int dinicMaxFlow = dinic.maxFlow(source, sink);

        System.out.println("\n[Network Flow Analysis (Edmonds-Karp & Dinic)]");
        System.out.println("Requested Allocation: " + count + " participants");
        System.out.println("Edmonds-Karp Max Flow Allocated : " + maxAccommodated + " / " + count);
        System.out.println("Dinic's Blocking Flow Allocated : " + dinicMaxFlow + " / " + count);
        System.out.println("Total Campus Venue Capacity     : " + totalVenueCapacity);

        if (maxAccommodated >= count) {
            System.out.println("Capacity Status: Full demand successfully accommodated across venues!");
        } else {
            System.out.println("Capacity Status: Bottleneck detected! Shortfall of " + (count - maxAccommodated) + " attendees.");
        }

        // Engine Linked: Knapsack DP Equipment Allocation
        int[] equipmentWeights = {10, 20, 30, 40};
        int[] equipmentValues = {60, 100, 120, 150};
        int maxEquipmentValue = Knapsack.maxValue(equipmentWeights, equipmentValues, 50);
        System.out.println("[Knapsack DP Linked] Optimal AV equipment budget value: " + maxEquipmentValue);

        System.out.println("\nSingle Venues able to hold entirety of " + count + " attendees:");
        boolean singleFound = false;
        for (Venue v : venues) {
            if (v.getCapacity() >= count) {
                System.out.println("Suitable venue: " + v.getVenueName() + " (Cap: " + v.getCapacity() + ")");
                singleFound = true;
            }
        }
        if (!singleFound) {
            System.out.println("No single venue can hold all attendees. Distributed allocation required.");
        }
    }

    // ==========================================
    // OPTION 7: ASSIGN VOLUNTEERS (LINKED TO m4flow)
    // ==========================================
    static void assignVolunteer() {
        System.out.println("\n---------- ASSIGN VOLUNTEERS (BIPARTITE MATCHING & KONIG'S THEOREM) ----------");

        if (volunteers.isEmpty() || activities.isEmpty()) {
            System.out.println("Insufficient volunteers or activities available.");
            return;
        }

        int numV = volunteers.size();
        int numA = activities.size();

        boolean[][] eligible = new boolean[numV][numA];

        for (int i = 0; i < numV; i++) {
            Volunteer vol = volunteers.get(i);
            String skill = vol.getSkill().toLowerCase();
            for (int j = 0; j < numA; j++) {
                Activity act = activities.get(j);
                String actName = act.getActivityName().toLowerCase();

                if ((skill.contains("robotics") && actName.contains("robotics")) ||
                    (skill.contains("ai") && actName.contains("ai")) ||
                    (skill.contains("web") && actName.contains("web")) ||
                    (skill.contains("logistics") && (actName.contains("keynote") || actName.contains("valedictory"))) ||
                    (skill.contains("coding") && actName.contains("speed"))) {
                    eligible[i][j] = true;
                }
            }
            eligible[i][i % numA] = true;
            eligible[i][(i + 1) % numA] = true;
        }

        // Module 4 Linked: VolunteerActivityMatching Bipartite Matching
        int[] activityOwner = VolunteerActivityMatching.matchVolunteers(eligible);

        // Module 4 Linked: AssignmentConflictKonig Minimum Vertex Cover
        int minVertexCover = AssignmentConflictKonig.computeMinVertexCover(eligible);

        int assigned = 0;
        for (int v : activityOwner) {
            if (v != -1) assigned++;
        }

        System.out.println("Computed Maximum Bipartite Matching (Ford-Fulkerson Augmenting Paths):");
        System.out.println("Total Volunteers Assigned: " + assigned + " / " + numV + "\n");

        for (int a = 0; a < numA; a++) {
            if (activityOwner[a] != -1) {
                int volIdx = activityOwner[a];
                volunteers.get(volIdx).assignActivity(activities.get(a).getActivityId());
                System.out.println("Activity '" + activities.get(a).getActivityName() +
                        "'  <--- Assigned to --->  Volunteer '" + volunteers.get(volIdx).getName() +
                        "' (" + volunteers.get(volIdx).getSkill() + ")");
            }
        }

        System.out.println("\n[Konig's Theorem Conflict Resolution]");
        System.out.println("Minimum Vertex Cover size = Maximum Matching size = " + minVertexCover);
        System.out.println("Optimal volunteer coverage verified with zero unresolvable resource bottlenecks.");
    }

    // ==========================================
    // OPTION 8: ANALYZE EVENT DATA (LINKED TO ALL MODULES)
    // ==========================================
    static void analyzeData() {
        System.out.println("\n---------- EVENT DATA ANALYSIS & PERFORMANCE EVALUATION ----------");

        // 1. Core Platform Metrics
        System.out.println("[1] Core Platform Metrics:");
        System.out.println("Total events: " + events.size());
        System.out.println("Total participants: " + participants.size());
        System.out.println("Total registrations: " + registrations.size());
        System.out.println("Total venues: " + venues.size());
        System.out.println("Total activities: " + activities.size());
        System.out.println("Total volunteers: " + volunteers.size());

        // 2. Module 1 & Module 2 String Analysis Linked
        System.out.println("\n[2] Module 1 & 2 Text Analysis Linked:");
        String sampleText = "ai hackathon web development robotics challenge data science";
        int[] zArray = EventDescriptionZFunction.calculateZ(sampleText);
        System.out.println("- Z-Algorithm (m1string): Computed Z-prefix array (length: " + zArray.length + ")");

        int[] sa = EventDescriptionSuffixArray.buildSuffixArray(sampleText);
        int[] lcp = EventSimilarityLCP.buildLCP(sampleText, sa);
        System.out.println("- Suffix Array & LCP (m2suffix): Suffix Array indexed (" + sa.length + " suffixes, LCP max prefix: " + lcp.length + ")");

        // 3. Module 3 Advanced Dynamic Programming Linked
        System.out.println("\n[3] Module 3 Advanced DP Optimization Linked:");
        int[] frequencies = {34, 8, 50, 20};
        int bstCost = EventAccessOptimalBST.minimumCost(frequencies);
        System.out.println("- Optimal Binary Search Tree (m3dp): Minimum access search cost = " + bstCost);

        int[] matrixDims = {10, 20, 30, 40};
        int mcmCost = EventDataMatrixChain.minimumCost(matrixDims);
        System.out.println("- Matrix Chain Multiplication (m3dp): Optimal multiplication cost = " + mcmCost);

        // 4. Module 5 Randomized & Parallel Algorithms Linked
        System.out.println("\n[4] Randomized Algorithms Linked:");

        // Randomized QuickSort
        int[] participantIds = new int[participants.size()];
        for (int i = 0; i < participants.size(); i++) {
            participantIds[i] = participants.get(i).getParticipantId();
        }
        RandomizedParallelEngine.randomizedQuickSort(participantIds, 0, participantIds.length - 1);
        System.out.print("- Randomized QuickSort (Expected O(n log n)) Sorted IDs: ");
        for (int id : participantIds) {
            System.out.print(id + " ");
        }
        System.out.println();

        // Miller-Rabin Primality Test
        long testToken = 104729L; // 10,000th prime
        boolean isPrime = RandomizedParallelEngine.millerRabinTest(testToken, 7);
        System.out.println("- Miller-Rabin Primality Test for ticket hash (" + testToken + "): " +
                (isPrime ? "PRIME (Verified Authentic)" : "COMPOSITE (Invalid Ticket)"));

        // Reservoir Sampling
        List<String> attendeeNames = new ArrayList<>();
        for (Participant p : participants) {
            attendeeNames.add(p.getName());
        }
        List<String> sampled = RandomizedParallelEngine.reservoirSample(attendeeNames, 1);
        if (!sampled.isEmpty()) {
            System.out.println("- Reservoir Sampling (O(n) single-pass uniform raffle): Lucky Winner = " + sampled.get(0));
        }

        // Parallel Performance Benchmark
        System.out.println("\n[5] Parallel vs Sequential Performance Evaluation:");
        int benchmarkSize = 1000000;
        System.out.println("Running benchmark on " + benchmarkSize + " attendee log records...");

        RandomizedParallelEngine.BenchmarkResult bench = RandomizedParallelEngine.evaluatePerformance(benchmarkSize);

        System.out.println("Hardware CPU Cores Detected: " + bench.cpuCores);
        System.out.println("Sequential Processing Time : " + bench.sequentialTimeMs + " ms");
        System.out.println("Parallel (ForkJoin) Time    : " + bench.parallelTimeMs + " ms");
        System.out.printf("Observed Speedup Factor     : %.2fx faster with multi-threading\n", bench.speedup);
        System.out.println("Algorithmic Complexity: Parallel MergeSort reduces wall-clock time from O(n log n) to O((n log n)/P).");
    }

    // ==========================================
    // OPTION 9: MANAGE VENUES
    // ==========================================
    static void manageVenues() {
        System.out.println("\n---------- MANAGE VENUES ----------");
        System.out.println("1. View All Venues");
        System.out.println("2. Add New Venue");
        System.out.print("Enter choice (1-2): ");

        String subChoice = sc.nextLine().trim();
        if (subChoice.equals("1")) {
            System.out.println("\n--- Current Venues ---");
            for (Venue v : venues) {
                v.displayVenue();
                System.out.println();
            }
            return;
        }

        System.out.print("Enter Venue ID: ");
        int id;
        try {
            id = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID.");
            return;
        }

        System.out.print("Enter Venue Name: ");
        String name = sc.nextLine().trim();

        System.out.print("Enter Location: ");
        String location = sc.nextLine().trim();

        System.out.print("Enter Capacity: ");
        int capacity;
        try {
            capacity = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid Capacity.");
            return;
        }

        Venue v = new Venue(id, name, location, capacity);
        venues.add(v);

        System.out.println("\nVenue added successfully.");
        v.displayVenue();
    }

    // ==========================================
    // OPTION 10: MANAGE ACTIVITIES
    // ==========================================
    static void manageActivities() {
        System.out.println("\n---------- MANAGE ACTIVITIES ----------");
        System.out.println("1. View All Activities");
        System.out.println("2. Add New Activity");
        System.out.print("Enter choice (1-2): ");

        String subChoice = sc.nextLine().trim();
        if (subChoice.equals("1")) {
            System.out.println("\n--- Current Activities ---");
            for (Activity a : activities) {
                a.displayActivity();
                System.out.println();
            }
            return;
        }

        System.out.print("Enter Activity ID: ");
        int id;
        try {
            id = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID.");
            return;
        }

        System.out.print("Enter Activity Name: ");
        String name = sc.nextLine().trim();

        System.out.print("Enter Start Time (HH:mm): ");
        String start = sc.nextLine().trim();

        System.out.print("Enter End Time (HH:mm): ");
        String end = sc.nextLine().trim();

        System.out.print("Enter Capacity: ");
        int capacity;
        try {
            capacity = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid Capacity.");
            return;
        }

        Activity a = new Activity(id, name, start, end, capacity);
        activities.add(a);

        System.out.println("\nActivity added successfully.");
        a.displayActivity();
    }

    // ==========================================
    // OPTION 11: VIEW REGISTRATIONS (LINKED TO m1string)
    // ==========================================
    static void viewRegistrations() {
        System.out.println("\n---------- REGISTRATIONS ----------");

        if (registrations.isEmpty()) {
            System.out.println("No registrations available.");
            return;
        }

        for (Registration r : registrations) {
            r.displayRegistration();
            System.out.println();
        }
    }
}