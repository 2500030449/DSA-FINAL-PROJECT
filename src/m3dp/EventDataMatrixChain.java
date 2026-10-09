package m3dp;
public class EventDataMatrixChain {

    public static int minimumCost(int[] dimensions) {
        int n = dimensions.length - 1;
        int[][] dp = new int[n][n];

        for (int length = 2; length <= n; length++) {
            for (int i = 0; i <= n - length; i++) {
                int j = i + length - 1;
                dp[i][j] = Integer.MAX_VALUE;

                for (int k = i; k < j; k++) {
                    int cost = dp[i][k] + dp[k + 1][j]
                            + dimensions[i] * dimensions[k + 1]
                            * dimensions[j + 1];

                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }

        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        int[] dimensions = {10, 20, 30, 40};

        System.out.println("Minimum multiplication cost: "
                + minimumCost(dimensions));
    }
}


