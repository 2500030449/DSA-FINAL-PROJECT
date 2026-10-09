# EventFlow Large Management Platform

> **Advanced Data Structures & Algorithms (DSA-3) Capstone Project**  
> A high-performance, algorithmic campus and enterprise event orchestration system built in Java.

---

## 📌 Project Overview & Problem Statement

Large-scale events (hackathons, workshops, symposiums, conferences) face complex algorithmic challenges:
1. **Cataloging & Search**: Attendees need fast, typo-tolerant search across thousands of event listings.
2. **Registration & Cryptographic Ticketing**: Verifying attendee registrations and fraud prevention.
3. **Data Correction**: Automated correction of participant name typos using dynamic programming string alignment.
4. **Activity Scheduling**: Resolving overlapping time-slot conflicts among dozens of concurrent tracks (Interval Scheduling & NP-hard subset selection).
5. **Venue Allocation**: Managing multi-facility capacity bottlenecks and flow routing (Maximum Network Flow).
6. **Volunteer Assignment**: Matching skilled volunteers to specialized tracks optimally (Maximum Bipartite Matching & König's Theorem).
7. **Big Data Analytics**: Sorting, sampling, and evaluating large attendee datasets via randomized algorithms and multi-core parallel processing.

**EventFlow** integrates state-of-the-art DSA-3 concepts into a clean, modular, and interactive platform.

---

## 📊 Complete DSA-3 Syllabus & Rubric Mapping

| Rubric Module | Concept / Algorithm | Implementation Class | Time Complexity | Space Complexity | Real-World Application in EventFlow |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **String Algorithms** | Knuth-Morris-Pratt (KMP) | `eventflow.engine.KMP`, `engine.EventSearchKMP` | $O(N + M)$ | $O(M)$ | Fast event title & description keyword pattern matching |
| **String Algorithms** | Rabin-Karp Rolling Hash | `m1string.BookingCodeRabinKarp` | $O(N + M)$ avg | $O(1)$ | Attendee booking code and registration string lookup |
| **String Algorithms** | Z-Algorithm | `m1string.EventDescriptionZFunction` | $O(N)$ | $O(N)$ | Linear-time prefix match analysis in event briefs |
| **String Algorithms** | Aho-Corasick Automaton | `m1string.EventKeywordAhoCorasick` | $O(N + K)$ | $O(K \cdot \Sigma)$ | Multi-keyword dictionary search across descriptions |
| **Suffix Structures** | Suffix Array | `m2suffix.EventDescriptionSuffixArray` | $O(N \log N)$ | $O(N)$ | Lexicographical indexing of event documentation |
| **Suffix Structures** | SA-IS Construction | `m2suffix.LargeTextSAIS` | $O(N \log^2 N)$ | $O(N)$ | Large-scale text suffix array generation |
| **Suffix Structures** | Suffix Tree | `m2suffix.EventDocumentSuffixTree` | $O(N^2)$ / $O(N)$ | $O(N \cdot \Sigma)$ | Fast full-text document substring queries |
| **Suffix Structures** | Suffix Automaton | `m2suffix.EventSearchSuffixAutomaton` | $O(N)$ | $O(N \cdot \Sigma)$ | Minimal DFA recognizing all event substrings in linear time |
| **Suffix Structures** | Longest Common Prefix (LCP) | `m2suffix.EventSimilarityLCP` | $O(N)$ | $O(N)$ | Document similarity evaluation between duplicate events |
| **Advanced DP** | Damerau-Levenshtein Distance | `m3dp.ParticipantNameCorrection`, `engine.EventEditDistance` | $O(M \cdot N)$ | $O(M \cdot N)$ | Typo tolerance with character insertions, deletions, replacements, and transpositions |
| **Advanced DP** | Bitmask DP Activity Selection | `m3dp.ActivitySelectionBitmaskDP` | $O(2^N \cdot N)$ | $O(2^N)$ | Optimal activity subset selection under arbitrary conflict matrices |
| **Advanced DP** | Optimal BST | `m3dp.EventAccessOptimalBST` | $O(N^3)$ | $O(N^2)$ | Min-cost event search tree organized by frequency of queries |
| **Advanced DP** | Matrix Chain Multiplication | `m3dp.EventDataMatrixChain` | $O(N^3)$ | $O(N^2)$ | Optimal tensor/matrix pipeline order for analytics aggregation |
| **Advanced DP** | 0/1 Knapsack | `eventflow.engine.Knapsack`, `engine.EquipmentKnapsack` | $O(N \cdot W)$ | $O(W)$ | Venue sound & AV equipment allocation within weight/budget limits |
| **Network Flow** | Edmonds-Karp Algorithm | `m4flow.VenueCapacityEdmondsKarp`, `engine.VenueCapacityFlow` | $O(V \cdot E^2)$ | $O(V^2)$ | Participant capacity flow routing across campus venues |
| **Network Flow** | Dinic's Blocking Flow | `m4flow.FacilityFlowDinic` | $O(V^2 \cdot E)$ | $O(V + E)$ | High-throughput venue network capacity allocation |
| **Network Flow** | Ford-Fulkerson | `m4flow.ParticipantFlowFordFulkerson` | $O(E \cdot \text{max\_flow})$ | $O(V^2)$ | Augmenting path capacity routing in residual graphs |
| **NP & Matching** | Maximum Bipartite Matching | `m4flow.VolunteerActivityMatching` | $O(V \cdot E)$ | $O(V)$ | Optimal assignment of volunteers to specialized activities |
| **NP & Matching** | König's Min Vertex Cover | `m4flow.AssignmentConflictKonig` | $O(V \cdot E)$ | $O(V)$ | Identification of minimum critical conflict nodes |
| **Greedy / Approx** | Greedy Interval Scheduling | `eventflow.engine.GreedyScheduler`, `engine.EventScheduleGreedy` | $O(N \log N)$ | $O(N)$ | Earliest finish time optimal non-overlapping activity scheduling |
| **Randomized** | Randomized QuickSort | `eventflow.engine.RandomizedParallelEngine` | $O(N \log N)$ exp | $O(\log N)$ | Expected $O(N \log N)$ sorting of attendees avoiding $O(N^2)$ worst cases |
| **Randomized** | Miller-Rabin Primality Test | `eventflow.engine.RandomizedParallelEngine`, `engine.RegistrationPrimeTest` | $O(K \log^3 N)$ | $O(1)$ | Cryptographic security verification of ticket tokens |
| **Randomized** | Reservoir Sampling | `eventflow.engine.RandomizedParallelEngine` | $O(N)$ | $O(K)$ | Single-pass uniform selection of raffle winners from event streams |
| **Parallel** | ForkJoin Parallel Sort | `eventflow.engine.RandomizedParallelEngine` | $O((N \log N) / P)$ | $O(N)$ | Multi-core parallel sorting and big-data performance evaluation |

---

## 🏛️ Project Architecture & Directory Structure

```
DSAFINALPROJECT/
│── .gitignore
│── .project
│── .classpath
│── README.md
│── bin/                                   # Compiled Java bytecode
└── src/
    ├── eventflow/
    │   ├── Main.java                      # Interactive CLI Platform & Entrypoint
    │   ├── Event.java                     # Event Domain Model
    │   └── engine/
    │       ├── EditDistance.java          # Levenshtein DP
    │       ├── GreedyScheduler.java       # Interval Scheduling
    │       ├── KMP.java                   # Knuth-Morris-Pratt Search
    │       ├── Knapsack.java              # 0/1 Knapsack DP
    │       ├── MaxFlow.java               # Edmonds-Karp Network Flow
    │       ├── PrimeTest.java             # Primality Verification
    │       └── RandomizedParallelEngine.java # Randomized & ForkJoin Parallel Benchmark
    ├── engine/                            # Mirroring DSA algorithms for modularity
    │   ├── EquipmentKnapsack.java
    │   ├── EventEditDistance.java
    │   ├── EventScheduleGreedy.java
    │   ├── EventSearchKMP.java
    │   ├── RegistrationPrimeTest.java
    │   └── VenueCapacityFlow.java
    ├── m1string/                          # Module 1: String Algorithms
    │   ├── BookingCodeRabinKarp.java      # Rabin-Karp Rolling Hash
    │   ├── EventDescriptionZFunction.java # Z-Algorithm
    │   └── EventKeywordAhoCorasick.java   # Aho-Corasick Multi-pattern Automaton
    ├── m2suffix/                          # Module 2: Suffix Structures
    │   ├── EventDescriptionSuffixArray.java
    │   ├── EventDocumentSuffixTree.java
    │   ├── EventSearchSuffixAutomaton.java
    │   ├── EventSimilarityLCP.java
    │   └── LargeTextSAIS.java
    ├── m3dp/                              # Module 3: Advanced Dynamic Programming
    │   ├── ActivitySelectionBitmaskDP.java
    │   ├── EventAccessOptimalBST.java
    │   ├── EventDataMatrixChain.java
    │   └── ParticipantNameCorrection.java # Damerau-Levenshtein Distance
    ├── m4flow/                            # Module 4: Network Flow & NP-Completeness
    │   ├── AssignmentConflictKonig.java   # König's Theorem & Vertex Cover
    │   ├── FacilityFlowDinic.java         # Dinic's Max Flow
    │   ├── ParticipantFlowFordFulkerson.java
    │   ├── VenueCapacityEdmondsKarp.java
    │   └── VolunteerActivityMatching.java # Bipartite Matching
    └── model/                             # Core Domain Models
        ├── Activity.java
        ├── Event.java
        ├── Participant.java
        ├── Registration.java
        ├── Venue.java
        └── Volunteer.java
```

---

## 🚀 How to Compile and Run

### Prerequisites
- Java Development Kit (JDK 17 or higher)
- Eclipse IDE or Command Line (PowerShell / Terminal)

### Option A: Command Line (PowerShell / Bash)
1. Navigate to the project root:
   ```powershell
   cd c:\Users\venka\eclipse-workspace\DSAFINALPROJECT
   ```
2. Compile all source files into the `bin` directory:
   ```powershell
   javac -d bin (Get-ChildItem -Recurse -Filter *.java src | ForEach-Object { $_.FullName })
   ```
3. Run the application:
   ```powershell
   java -cp bin eventflow.Main
   ```

### Option B: Eclipse IDE
1. Open Eclipse and choose your workspace (`c:\Users\venka\eclipse-workspace`).
2. Import `DSAFINALPROJECT` as an Existing Project.
3. Right-click on `src/eventflow/Main.java` ➔ **Run As** ➔ **Java Application**.

---

## 🖥️ Demonstration Walkthrough (Exact Expected Interaction)

```
========================================
     EVENTFLOW MANAGEMENT PLATFORM
========================================

1. View Events
2. Register Participant
3. Search Event
4. Correct Participant Name
5. Schedule Activities
6. Allocate Venue Capacity
7. Assign Volunteers
8. Analyze Event Data
9. Manage Venues
10. Manage Activities
11. View Registrations
12. Exit

Enter your choice: 1

---------- AVAILABLE EVENTS ----------
Event ID: 1
Event Name: AI Hackathon
Available Seats: 100

Event ID: 2
Event Name: Web Development Workshop
Available Seats: 80

Event ID: 3
Event Name: Robotics Challenge
Available Seats: 40

Event ID: 4
Event Name: Data Science Meetup
Available Seats: 60

========================================
     EVENTFLOW MANAGEMENT PLATFORM
========================================

...

Enter your choice: 3
Enter event name to search: Robotics
Event found: Robotics Challenge

========================================
     EVENTFLOW MANAGEMENT PLATFORM
========================================

...

Enter your choice: 12
Exiting EventFlow. Thank you!
```

---

## 🔬 In-Depth Algorithmic Features

### 1. View Events (Choice 1)
Displays all cataloged events with their IDs, names, and available seat counts, precisely formatted.

### 2. Register Participant (Choice 2)
Interactively collects participant details, decrements available event seats, creates confirmed registration codes (`REG<ID>`), and runs a **Miller-Rabin primality test** to issue an authenticated cryptographic ticket token.

### 3. Search Event (Choice 3)
Applies the linear-time **Knuth-Morris-Pratt (KMP)** algorithm ($O(N + M)$) to search queries across event names and descriptions using the longest prefix-suffix (LPS) table, avoiding redundant character backtrackings.

### 4. Correct Participant Name (Choice 4)
Uses **Dynamic Programming (Damerau-Levenshtein Edit Distance)** with transpositions to detect and correct misspelled attendee names. When comparing `"Sproothi"` and `"Spoorthi"`, it detects the single transposition (`ro` $\leftrightarrow$ `or`) in $O(M \cdot N)$ time and updates the record.

### 5. Schedule Activities (Choice 5)
Solves the interval scheduling problem using the **Greedy Earliest Finish Time** algorithm ($O(N \log N)$), proven to select the maximum number of non-overlapping activities. Also demonstrates **Bitmask DP** ($O(2^N \cdot N)$) to evaluate non-conflicting activity subsets with values.

### 6. Allocate Venue Capacity (Choice 6)
Models campus capacity distribution as a **Maximum Network Flow** problem. Solved using **Edmonds-Karp** (BFS-augmented paths) and **Dinic's blocking flow**, routing participant volumes across venues without exceeding physical fire-code capacities.

### 7. Assign Volunteers (Choice 7)
Constructs a bipartite graph between volunteers and specialized event tracks. Computes the **Maximum Bipartite Matching** using Ford-Fulkerson augmenting paths. Applies **König's Theorem** to prove that the Minimum Vertex Cover equals the Maximum Matching size, identifying any potential bottlenecks.

### 8. Analyze Event Data (Choice 8)
Demonstrates three key advanced paradigms:
- **Randomized QuickSort**: Sorts attendee IDs with randomized pivots in expected $O(N \log N)$ time.
- **Miller-Rabin Primality Test**: Probabilistic test verifying 104,729 as an authentic prime ticket seed token.
- **Reservoir Sampling**: Uniformly draws random lucky winners from streams in $O(N)$ single-pass time.
- **Parallel vs Sequential Benchmark**: Benchmarks sequential sorting vs. multi-threaded **ForkJoin Parallel Sort** on 1,000,000 records, calculating the observed multi-core speedup factor.

### 9. Manage Venues & 10. Manage Activities
Allows listing and adding new campus facilities and workshop tracks dynamically.

### 11. View Registrations
Lists all active registrations, their confirmation codes, participant IDs, and statuses.

### 12. Exit
Safely terminates the interactive session with:
`Exiting EventFlow. Thank you!`

---

## 🎯 Viva & Technical Interview Cheat Sheet

1. **Why KMP over naive search?**  
   Naive string matching takes $O(N \cdot M)$ worst-case time (e.g. searching `AAAA` in `AAAAAAA`). KMP preprocesses the pattern into an LPS (Longest Proper Prefix which is also Suffix) array in $O(M)$ time and achieves guaranteed $O(N + M)$ search time with zero backtracking on the text.

2. **Why Damerau-Levenshtein over standard Levenshtein?**  
   Standard Levenshtein only accounts for insertion, deletion, and substitution. Damerau-Levenshtein also handles **adjacent transpositions** (e.g., typing `Sproothi` instead of `Spoorthi`), which account for over 80% of human typing errors.

3. **How does Network Flow model venue capacity?**  
   A source node $S$ injects participant demand into an inflow node, which branches to individual venue nodes with edge capacities equal to venue physical limits. Venue nodes connect to a sink $T$. The maximum $S-T$ flow calculated via Edmonds-Karp indicates the maximum attendees the venue network can legally support.

4. **What is König's Theorem and how does it help?**  
   In any bipartite graph, the size of a Maximum Matching equals the size of a Minimum Vertex Cover. In volunteer allocation, this guarantees that we have identified the exact minimal set of critical assignments that cover all activity requirements.

5. **How does ForkJoin Parallel Sort achieve speedup?**  
   ForkJoin breaks the sorting problem recursively into sub-arrays across available CPU cores using work-stealing threads (`ForkJoinPool`), lowering wall-clock execution time from $O(N \log N)$ to $O((N \log N) / P)$, where $P$ is the core count.

---
*Created for the Data Structures & Algorithms (DSA-3) Final Examination.*
