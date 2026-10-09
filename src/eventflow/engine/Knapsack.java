package eventflow.engine;

public class Knapsack {
public static int maxValue(int[] weight, int[] value,
                               int capacity) {
        int[] dp = new int[capacity + 1];

        for (int i = 0; i < weight.length; i++) {
            for (int c = capacity; c >= weight[i]; c--) {
                dp[c] = Math.max(dp[c],
                        dp[c - weight[i]] + value[i]);
            }
        }

        return dp[capacity];
    }
}


