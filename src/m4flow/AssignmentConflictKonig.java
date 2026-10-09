package m4flow;
import java.util.*;

public class AssignmentConflictKonig {

    static boolean match(int volunteer, boolean[][] eligible,
                         boolean[] visited, int[] owner) {
        for (int a = 0; a < eligible[volunteer].length; a++) {
            if (eligible[volunteer][a] && !visited[a]) {
                visited[a] = true;

                if (owner[a] == -1 ||
                    match(owner[a], eligible, visited, owner)) {
                    owner[a] = volunteer;
                    return true;
                }
            }
        }
        return false;
    }

    static void alternatingSearch(int v, boolean[][] eligible,
                                  int[] owner, boolean[] seenV,
                                  boolean[] seenA) {
        seenV[v] = true;

        for (int a = 0; a < eligible[v].length; a++) {
            if (eligible[v][a] && owner[a] != v && !seenA[a]) {
                seenA[a] = true;

                if (owner[a] != -1 && !seenV[owner[a]]) {
                    alternatingSearch(owner[a], eligible,
                            owner, seenV, seenA);
                }
            }
        }
    }

    public static int computeMinVertexCover(boolean[][] eligible) {
        int volunteers = eligible.length;
        int activities = eligible[0].length;
        int[] owner = new int[activities];
        Arrays.fill(owner, -1);

        int matching = 0;
        for (int v = 0; v < volunteers; v++) {
            if (match(v, eligible, new boolean[activities], owner)) {
                matching++;
            }
        }
        return matching;
    }

    public static void main(String[] args) {
        boolean[][] eligible = {
            {true, true, false},
            {true, false, true},
            {false, true, true}
        };

        int volunteers = eligible.length;
        int activities = eligible[0].length;
        int[] owner = new int[activities];
        Arrays.fill(owner, -1);

        int matching = 0;

        for (int v = 0; v < volunteers; v++) {
            if (match(v, eligible, new boolean[activities], owner)) {
                matching++;
            }
        }

        boolean[] seenV = new boolean[volunteers];
        boolean[] seenA = new boolean[activities];

        for (int v = 0; v < volunteers; v++) {
            boolean matched = false;

            for (int a = 0; a < activities; a++) {
                if (owner[a] == v) {
                    matched = true;
                    break;
                }
            }

            if (!matched && !seenV[v]) {
                alternatingSearch(v, eligible, owner, seenV, seenA);
            }
        }

        System.out.println("Maximum matching: " + matching);
        System.out.println("Minimum vertex cover:");

        for (int v = 0; v < volunteers; v++) {
            if (!seenV[v]) {
                System.out.println("Volunteer " + (v + 1));
            }
        }

        for (int a = 0; a < activities; a++) {
            if (seenA[a]) {
                System.out.println("Activity " + (a + 1));
            }
        }
    }
}


