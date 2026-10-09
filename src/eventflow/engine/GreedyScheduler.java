package eventflow.engine;

public class GreedyScheduler {
 public static int[] select(int[] start, int[] end) {
        int n = start.length;
        boolean[] used = new boolean[n];
        int[] result = new int[n];

        int count = 0;
        int lastEnd = Integer.MIN_VALUE;

        for (int k = 0; k < n; k++) {
            int best = -1;

            for (int i = 0; i < n; i++) {
                if (!used[i] &&
                    (best == -1 || end[i] < end[best])) {
                    best = i;
                }
            }

            if (best == -1) {
                break;
            }

            used[best] = true;

            if (start[best] >= lastEnd) {
                result[count++] = best;
                lastEnd = end[best];
            }
        }

        int[] answer = new int[count];

        for (int i = 0; i < count; i++) {
            answer[i] = result[i];
        }

        return answer;
    }
}


