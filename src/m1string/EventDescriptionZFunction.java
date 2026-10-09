package m1string;
public class EventDescriptionZFunction {
  public static int[] calculateZ(String text) {
        int n = text.length();
        int[] z = new int[n];

        int left = 0, right = 0;

        for (int i = 1; i < n; i++) {
            if (i <= right) {
                z[i] = Math.min(right - i + 1, z[i - left]);
            }

            while (i + z[i] < n &&
                   text.charAt(z[i]) == text.charAt(i + z[i])) {
                z[i]++;
            }

            if (i + z[i] - 1 > right) {
                left = i;
                right = i + z[i] - 1;
            }
        }
        return z;
    }

    public static void main(String[] args) {
        String description = "ababab";

        int[] result = calculateZ(description);

        System.out.print("Z-array: ");
        for (int value : result) {
            System.out.print(value + " ");
        }
    }
}

