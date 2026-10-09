package engine;

public class RegistrationPrimeTest {
    public static boolean isPrime(long n) {
        if (n < 2) return false;
        if (n == 2 || n == 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;

        for (long d = 5; d * d <= n; d += 6) {
            if (n % d == 0 || n % (d + 2) == 0) {
                return false;
            }
        }
        return true;
    }
}
