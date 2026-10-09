package m3dp;

public class EventAccessOptimalBST {
    public static int minimumCost(int[] frequency) {
        int n = frequency.length;
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = frequency[i];
        }

        for (int length = 2; length <= n; length++) {
            for (int i = 0; i <= n - length; i++) {
                int j = i + length - 1;
                dp[i][j] = Integer.MAX_VALUE;

                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += frequency[k];
                }

                for (int root = i; root <= j; root++) {
                    int left = (root > i) ? dp[i][root - 1] : 0;
                    int right = (root < j) ? dp[root + 1][j] : 0;

                    int cost = left + right + sum;
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }

        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        int[] frequency = {34, 8, 50};

        System.out.println("Minimum search cost: " +
                minimumCost(frequency));
    }
}


