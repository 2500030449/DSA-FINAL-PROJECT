package m2suffix;
import java.util.Arrays;

public class LargeTextSAIS {

    public static int[] buildSuffixArray(String text) {
        int n = text.length();
        int[] sa = new int[n];
        int[] rank = new int[n];
        int[] nextRank = new int[n];

        for (int i = 0; i < n; i++) {
            sa[i] = i;
            rank[i] = text.charAt(i);
        }

        for (int k = 1; k < n; k *= 2) {
            final int step = k;

            Integer[] order = new Integer[n];
            for (int i = 0; i < n; i++) {
                order[i] = sa[i];
            }

            final int[] currentRank = rank;
            Arrays.sort(order, (a, b) -> {
                if (currentRank[a] != currentRank[b]) {
                    return Integer.compare(currentRank[a], currentRank[b]);
                }

                int ra = a + step < n ? currentRank[a + step] : -1;
                int rb = b + step < n ? currentRank[b + step] : -1;
                return Integer.compare(ra, rb);
            });

            for (int i = 0; i < n; i++) {
                sa[i] = order[i];
            }

            nextRank[sa[0]] = 0;

            for (int i = 1; i < n; i++) {
                int a = sa[i - 1];
                int b = sa[i];

                boolean different =
                    rank[a] != rank[b] ||
                    (a + step < n ? rank[a + step] : -1) !=
                    (b + step < n ? rank[b + step] : -1);

                nextRank[b] = nextRank[a] + (different ? 1 : 0);
            }

            int[] temp = rank;
            rank = nextRank;
            nextRank = temp;

            if (rank[sa[n - 1]] == n - 1) {
                break;
            }

            if (k > n / 2) {
                break;
            }
        }

        return sa;
    }

    public static void main(String[] args) {
        String description = "eventdata";

        int[] sa = buildSuffixArray(description);

        System.out.println("Suffix array:");
        for (int index : sa) {
            System.out.println(index + " : " +
                    description.substring(index));
        }
    }
}


