package m3dp;
public class ActivitySelectionBitmaskDP {

    public static int selectActivities(int[] value,
                                       boolean[][] conflict) {
        int n = value.length;
        int total = 1 << n;
        int[] dp = new int[total];

        for (int mask = 1; mask < total; mask++) {
            int lastBit = Integer.numberOfTrailingZeros(mask);
            int previous = mask & (mask - 1);

            boolean valid = true;

            for (int j = 0; j < n; j++) {
                if ((previous & (1 << j)) != 0 &&
                    conflict[lastBit][j]) {
                    valid = false;
                    break;
                }
            }

            if (valid) {
                dp[mask] = dp[previous] + value[lastBit];
            } else {
                dp[mask] = -1;
            }
        }

        int answer = 0;

        for (int mask = 0; mask < total; mask++) {
            answer = Math.max(answer, dp[mask]);
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] value = {5, 8, 6, 4};

        boolean[][] conflict = {
            {false, true,  false, false},
            {true,  false, true,  false},
            {false, true,  false, false},
            {false, false, false, false}
        };

        int result = selectActivities(value, conflict);

        System.out.println("Maximum activity value: " + result);
    }
}


