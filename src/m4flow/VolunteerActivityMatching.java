package m4flow;
import java.util.*;

public class VolunteerActivityMatching {

    static boolean assign(int volunteer, boolean[][] eligible,
                          boolean[] visited, int[] activityOwner) {

        for (int activity = 0;
             activity < eligible[volunteer].length; activity++) {

            if (eligible[volunteer][activity] && !visited[activity]) {
                visited[activity] = true;

                if (activityOwner[activity] == -1 ||
                    assign(activityOwner[activity], eligible,
                           visited, activityOwner)) {

                    activityOwner[activity] = volunteer;
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        // Rows: volunteers, columns: activities
        boolean[][] eligible = {
            {true, true, false},
            {true, false, true},
            {false, true, true}
        };

        int volunteers = eligible.length;
        int activities = eligible[0].length;
        int[] activityOwner = new int[activities];

        Arrays.fill(activityOwner, -1);

        int assigned = 0;

        for (int v = 0; v < volunteers; v++) {
            boolean[] visited = new boolean[activities];

            if (assign(v, eligible, visited, activityOwner)) {
                assigned++;
            }
        }

        System.out.println("Total volunteers assigned: " + assigned);

        for (int a = 0; a < activities; a++) {
            if (activityOwner[a] != -1) {
                System.out.println("Activity " + (a + 1) +
                        " assigned to Volunteer " +
                        (activityOwner[a] + 1));
            }
        }
    }
}


