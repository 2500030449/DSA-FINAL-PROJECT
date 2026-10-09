package m4flow;
public class ParticipantFlowFordFulkerson {

    static int V = 4;

    static boolean dfs(int[][] residual, int source,
                       int sink, boolean[] visited, int[] parent) {
        visited[source] = true;

        if (source == sink) {
            return true;
        }

        for (int v = 0; v < V; v++) {
            if (!visited[v] && residual[source][v] > 0) {
                parent[v] = source;

                if (dfs(residual, v, sink, visited, parent)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static int maxFlow(int[][] graph, int source, int sink) {
        int[][] residual = new int[V][V];

        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                residual[i][j] = graph[i][j];
            }
        }

        int[] parent = new int[V];
        int flow = 0;

        while (true) {
            boolean[] visited = new boolean[V];

            if (!dfs(residual, source, sink, visited, parent)) {
                break;
            }

            int pathFlow = Integer.MAX_VALUE;

            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                pathFlow = Math.min(pathFlow, residual[u][v]);
            }

            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                residual[u][v] -= pathFlow;
                residual[v][u] += pathFlow;
            }

            flow += pathFlow;
        }

        return flow;
    }

    public static void main(String[] args) {
        int[][] graph = {
            {0, 10, 5, 0},
            {0, 0,  4, 8},
            {0, 0,  0, 6},
            {0, 0,  0, 0}
        };

        System.out.println("Maximum participant flow: " +
                maxFlow(graph, 0, 3));
    }
}


