package m2suffix;
public class EventSimilarityLCP {

    public static int[] buildLCP(String text, int[] sa) {
        int n = text.length();
        int[] rank = new int[n];
        int[] lcp = new int[n];

        for (int i = 0; i < n; i++) {
            rank[sa[i]] = i;
        }

        int common = 0;

        for (int i = 0; i < n; i++) {
            int position = rank[i];

            if (position == n - 1) {
                common = 0;
                continue;
            }

            int j = sa[position + 1];

            while (i + common < n &&
                   j + common < n &&
                   text.charAt(i + common) ==
                   text.charAt(j + common)) {
                common++;
            }

            lcp[position] = common;

            if (common > 0) {
                common--;
            }
        }

        return lcp;
    }

    public static void main(String[] args) {
        String description = "banana";

        int[] sa = EventDescriptionSuffixArray
                .buildSuffixArray(description);

        int[] lcp = buildLCP(description, sa);

        System.out.println("LCP array:");

        for (int value : lcp) {
            System.out.print(value + " ");
        }
    }
}


