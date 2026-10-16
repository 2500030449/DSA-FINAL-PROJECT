
package eventflow;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class RandomizedParallelEngine {

    // 1. Randomized QuickSort
    public static void randomizedQuickSort(int[] arr, int low, int high) {
        if (low < high) {
            Random random = new Random();
            int pivotIndex = low + random.nextInt(high - low + 1);

            int temp = arr[pivotIndex];
            arr[pivotIndex] = arr[high];
            arr[high] = temp;

            int pivot = arr[high];
            int i = low - 1;

            for (int j = low; j < high; j++) {
                if (arr[j] <= pivot) {
                    i++;
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }

            temp = arr[i + 1];
            arr[i + 1] = arr[high];
            arr[high] = temp;

            int p = i + 1;
            randomizedQuickSort(arr, low, p - 1);
            randomizedQuickSort(arr, p + 1, high);
        }
    }

    // 2. Miller-Rabin primality test
    public static boolean millerRabinTest(long number, int rounds) {
        if (number < 2) {
            return false;
        }
        return BigInteger.valueOf(number).isProbablePrime(rounds);
    }

    // 3. Reservoir Sampling
    public static List<String> reservoirSample(
            List<String> items, int sampleSize) {

        List<String> result = new ArrayList<>();

        if (sampleSize <= 0) {
            return result;
        }

        Random random = new Random();

        for (int i = 0; i < items.size(); i++) {
            if (i < sampleSize) {
                result.add(items.get(i));
            } else {
                int j = random.nextInt(i + 1);
                if (j < sampleSize) {
                    result.set(j, items.get(i));
                }
            }
        }

        return result;
    }

    // 4. Benchmark result
    public static class BenchmarkResult {
        public int cpuCores;
        public long sequentialTimeMs;
        public long parallelTimeMs;
        public double speedup;
    }

    // 5. Sequential vs parallel performance
    public static BenchmarkResult evaluatePerformance(int size) {
        BenchmarkResult result = new BenchmarkResult();

        result.cpuCores = Runtime.getRuntime().availableProcessors();

        long start = System.nanoTime();
        long sequentialSum = 0;

        for (int i = 1; i <= size; i++) {
            sequentialSum += i;
        }

        long sequentialEnd = System.nanoTime();

        long parallelStart = System.nanoTime();

        long parallelSum = IntStream.rangeClosed(1, size)
                .parallel()
                .asLongStream()
                .sum();

        long parallelEnd = System.nanoTime();

        result.sequentialTimeMs = TimeUnit.NANOSECONDS.toMillis(
                sequentialEnd - start);

        result.parallelTimeMs = TimeUnit.NANOSECONDS.toMillis(
                parallelEnd - parallelStart);

        if (result.parallelTimeMs > 0) {
            result.speedup = (double) result.sequentialTimeMs
                    / result.parallelTimeMs;
        } else {
            result.speedup = 0.0;
        }

        // Prevent unused-result issues and verify both calculations.
        if (sequentialSum != parallelSum) {
            throw new IllegalStateException("Benchmark results do not match");
        }

        return result;
    }
}