package engine;

import java.util.*;

public class VenueCapacityFlow {
    public static int edmondsKarp(int[][] capacity, int source, int sink) {
        int n = capacity.length;
        int[][] residual = new int[n][n];

        for (int i = 0; i < n; i++) {
            System.arraycopy(capacity[i], 0, residual[i], 0, n);
        }

        int total = 0;
        int[] parent = new int[n];

        while (bfs(residual, source, sink, parent)) {
            int flow = Integer.MAX_VALUE;

            for (int v = sink; v != source; v = parent[v]) {
                flow = Math.min(flow, residual[parent[v]][v]);
            }

            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                residual[u][v] -= flow;
                residual[v][u] += flow;
            }

            total += flow;
        }

        return total;
    }

    private static boolean bfs(int[][] r, int s, int t, int[] parent) {
        int n = r.length;
        boolean[] visited = new boolean[n];
        Queue<Integer> q = new LinkedList<>();

        q.add(s);
        visited[s] = true;
        parent[s] = -1;

        while (!q.isEmpty()) {
            int u = q.poll();

            for (int v = 0; v < n; v++) {
                if (!visited[v] && r[u][v] > 0) {
                    visited[v] = true;
                    parent[v] = u;
                    q.add(v);

                    if (v == t) {
                        return true;
                    }
                }
            }
        }

        return visited[t];
    }
}
