package eventflow.engine;

import java.util.*;
import java.util.concurrent.*;

public class RandomizedParallelEngine {

    private static final Random rand = new Random();

    // ==========================================
    // 1. RANDOMIZED ALGORITHMS
    // ==========================================

    /**
     * Randomized QuickSort: selects a random pivot to guarantee expected O(n log n) runtime.
     */
    public static void randomizedQuickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = low + rand.nextInt(high - low + 1);
            swap(arr, pivotIndex, high);
            int p = partition(arr, low, high);
            randomizedQuickSort(arr, low, p - 1);
            randomizedQuickSort(arr, p + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Miller-Rabin Randomized Primality Test: O(k log^3 n) probabilistic primality test.
     */
    public static boolean millerRabinTest(long n, int k) {
        if (n <= 1 || n == 4) return false;
        if (n <= 3) return true;
        if (n % 2 == 0) return false;

        long d = n - 1;
        while (d % 2 == 0) {
            d /= 2;
        }

        for (int i = 0; i < k; i++) {
            long a = 2 + (long) (rand.nextDouble() * (n - 4));
            long x = modularExponentiation(a, d, n);

            if (x == 1 || x == n - 1) continue;

            boolean composite = true;
            while (d != n - 1) {
                x = (x * x) % n;
                d *= 2;
                if (x == 1) return false;
                if (x == n - 1) {
                    composite = false;
                    break;
                }
            }
            if (composite) return false;
        }
        return true;
    }

    private static long modularExponentiation(long base, long exp, long mod) {
        long res = 1;
        base = base % mod;
        while (exp > 0) {
            if ((exp & 1) == 1) res = (res * base) % mod;
            exp >>= 1;
            base = (base * base) % mod;
        }
        return res;
    }

    /**
     * Reservoir Sampling: uniformly samples k random winners from an attendee stream of size n.
     */
    public static List<String> reservoirSample(List<String> stream, int k) {
        List<String> reservoir = new ArrayList<>(k);
        int n = stream.size();
        if (n == 0) return reservoir;

        for (int i = 0; i < Math.min(k, n); i++) {
            reservoir.add(stream.get(i));
        }

        for (int i = k; i < n; i++) {
            int j = rand.nextInt(i + 1);
            if (j < k) {
                reservoir.set(j, stream.get(i));
            }
        }
        return reservoir;
    }

    // ==========================================
    // 2. PARALLEL ALGORITHMS (ForkJoin Framework)
    // ==========================================

    private static class ParallelMergeSortTask extends RecursiveAction {
        private final int[] arr;
        private final int[] temp;
        private final int left;
        private final int right;
        private static final int THRESHOLD = 5000;

        ParallelMergeSortTask(int[] arr, int[] temp, int left, int right) {
            this.arr = arr;
            this.temp = temp;
            this.left = left;
            this.right = right;
        }

        @Override
        protected void compute() {
            if (right - left <= THRESHOLD) {
                Arrays.sort(arr, left, right + 1);
                return;
            }

            int mid = left + (right - left) / 2;
            ParallelMergeSortTask leftTask = new ParallelMergeSortTask(arr, temp, left, mid);
            ParallelMergeSortTask rightTask = new ParallelMergeSortTask(arr, temp, mid + 1, right);

            invokeAll(leftTask, rightTask);
            merge(left, mid, right);
        }

        private void merge(int left, int mid, int right) {
            System.arraycopy(arr, left, temp, left, right - left + 1);
            int i = left, j = mid + 1, k = left;
            while (i <= mid && j <= right) {
                if (temp[i] <= temp[j]) {
                    arr[k++] = temp[i++];
                } else {
                    arr[k++] = temp[j++];
                }
            }
            while (i <= mid) {
                arr[k++] = temp[i++];
            }
        }
    }

    public static void parallelMergeSort(int[] arr) {
        int[] temp = new int[arr.length];
        ForkJoinPool pool = ForkJoinPool.commonPool();
        pool.invoke(new ParallelMergeSortTask(arr, temp, 0, arr.length - 1));
    }

    // ==========================================
    // 3. PERFORMANCE EVALUATION BENCHMARK
    // ==========================================

    public static class BenchmarkResult {
        public int dataSize;
        public long sequentialTimeMs;
        public long parallelTimeMs;
        public double speedup;
        public int cpuCores;
    }

    public static void sequentialMergeSort(int[] arr) {
        int[] temp = new int[arr.length];
        seqMergeSort(arr, temp, 0, arr.length - 1);
    }

    private static void seqMergeSort(int[] arr, int[] temp, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        seqMergeSort(arr, temp, left, mid);
        seqMergeSort(arr, temp, mid + 1, right);

        System.arraycopy(arr, left, temp, left, right - left + 1);
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            if (temp[i] <= temp[j]) {
                arr[k++] = temp[i++];
            } else {
                arr[k++] = temp[j++];
            }
        }
        while (i <= mid) {
            arr[k++] = temp[i++];
        }
    }

    public static BenchmarkResult evaluatePerformance(int sampleSize) {
        Random r = new Random(42);
        int[] original = new int[sampleSize];
        for (int i = 0; i < sampleSize; i++) {
            original[i] = r.nextInt(sampleSize * 10);
        }

        // Warm-up JIT compiler and ForkJoinPool threads
        int[] warm = Arrays.copyOf(original, 20000);
        Arrays.sort(warm.clone());
        Arrays.parallelSort(warm.clone());

        int[] seqArr = original.clone();
        int[] parArr = original.clone();

        // Sequential sort (Single-threaded)
        long startSeq = System.nanoTime();
        Arrays.sort(seqArr);
        long endSeq = System.nanoTime();
        long seqTimeMs = (endSeq - startSeq) / 1_000_000;

        // Parallel sort (Multi-threaded ForkJoin framework)
        long startPar = System.nanoTime();
        Arrays.parallelSort(parArr);
        long endPar = System.nanoTime();
        long parTimeMs = (endPar - startPar) / 1_000_000;

        BenchmarkResult res = new BenchmarkResult();
        res.dataSize = sampleSize;
        res.sequentialTimeMs = Math.max(seqTimeMs, 1);
        res.parallelTimeMs = Math.max(parTimeMs, 1);
        res.speedup = (double) res.sequentialTimeMs / res.parallelTimeMs;
        res.cpuCores = Runtime.getRuntime().availableProcessors();
        return res;
    }
}
