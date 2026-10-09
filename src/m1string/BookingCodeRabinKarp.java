package m1string;
public class BookingCodeRabinKarp {
    public static void search(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();

        int prime = 101;
        int d = 256;
        int patternHash = 0;
        int textHash = 0;
        int h = 1;

        if (m > n) {
            System.out.println("Booking code not found");
            return;
        }

        for (int i = 0; i < m - 1; i++) {
            h = (h * d) % prime;
        }

        for (int i = 0; i < m; i++) {
            patternHash = (d * patternHash + pattern.charAt(i)) % prime;
            textHash = (d * textHash + text.charAt(i)) % prime;
        }

        for (int i = 0; i <= n - m; i++) {
            if (patternHash == textHash) {
                int j;
                for (j = 0; j < m; j++) {
                    if (text.charAt(i + j) != pattern.charAt(j)) {
                        break;
                    }
                }

                if (j == m) {
                    System.out.println("Booking code found at index " + i);
                }
            }

            if (i < n - m) {
                textHash = (d * (textHash -
                        text.charAt(i) * h)
                        + text.charAt(i + m)) % prime;

                if (textHash < 0) {
                    textHash += prime;
                }
            }
        }
    }

    public static void main(String[] args) {
        String registrationData = "EVT101 EVT205 EVT310";
        String bookingCode = "EVT205";

        search(registrationData, bookingCode);
    }
}


