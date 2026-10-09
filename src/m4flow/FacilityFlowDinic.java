package m4flow;
import java.util.*;

public class FacilityFlowDinic {

    static class Edge {
        int to, reverse, capacity;

        Edge(int to, int reverse, int capacity) {
            this.to = to;
            this.reverse = reverse;
            this.capacity = capacity;
        }
    }

    private final List<Edge>[] graph;
    private final int[] level;
    private final int[] next;

    @SuppressWarnings("unchecked")
    public FacilityFlowDinic(int n) {
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        level = new int[n];
        next = new int[n];
    }

    public void addEdge(int from, int to, int capacity) {
        Edge forward = new Edge(to, graph[to].size(), capacity);
        Edge backward = new Edge(from, graph[from].size(), 0);

        graph[from].add(forward);
        graph[to].add(backward);
    }

    private boolean buildLevels(int source, int sink) {
        Arrays.fill(level, -1);
        Queue<Integer> queue = new LinkedList<>();

        level[source] = 0;
        queue.add(source);

        while (!queue.isEmpty()) {
            int u = queue.poll();

            for (Edge edge : graph[u]) {
                if (edge.capacity > 0 && level[edge.to] == -1) {
                    level[edge.to] = level[u] + 1;
                    queue.add(edge.to);
                }
            }
        }

        return level[sink] != -1;
    }

    private int sendFlow(int u, int sink, int flow) {
        if (u == sink) {
            return flow;
        }

        for (; next[u] < graph[u].size(); next[u]++) {
            Edge edge = graph[u].get(next[u]);

            if (edge.capacity > 0 &&
                level[edge.to] == level[u] + 1) {

                int pushed = sendFlow(edge.to, sink,
                        Math.min(flow, edge.capacity));

                if (pushed > 0) {
                    edge.capacity -= pushed;
                    graph[edge.to].get(edge.reverse).capacity += pushed;
                    return pushed;
                }
            }
        }

        return 0;
    }

    public int maxFlow(int source, int sink) {
        int total = 0;

        while (buildLevels(source, sink)) {
            Arrays.fill(next, 0);

            int pushed;
            while ((pushed = sendFlow(source, sink,
                    Integer.MAX_VALUE)) > 0) {
                total += pushed;
            }
        }

        return total;
    }

    public static void main(String[] args) {
        FacilityFlowDinic network = new FacilityFlowDinic(4);

        network.addEdge(0, 1, 10);
        network.addEdge(0, 2, 5);
        network.addEdge(1, 2, 4);
        network.addEdge(1, 3, 8);
        network.addEdge(2, 3, 6);

        System.out.println("Maximum facility flow: " +
                network.maxFlow(0, 3));
    }
}


