package m2suffix;
import java.util.Arrays;

public class EventDescriptionSuffixArray {

    public static int[] buildSuffixArray(String text) {
        int n = text.length();
        Integer[] suffixes = new Integer[n];

        for (int i = 0; i < n; i++) {
            suffixes[i] = i;
        }

        Arrays.sort(suffixes,
            (a, b) -> text.substring(a).compareTo(text.substring(b)));

        int[] sa = new int[n];
        for (int i = 0; i < n; i++) {
            sa[i] = suffixes[i];
        }
        return sa;
    }

    public static void main(String[] args) {
        String description = "banana";

        int[] result = buildSuffixArray(description);

        System.out.println("Event description: " + description);
        System.out.println("Suffix array:");

        for (int index : result) {
            System.out.println(index + " : " + description.substring(index));
        }
    }
}


