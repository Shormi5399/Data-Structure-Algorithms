import java.util.*;

class PrimsMST {
    static class Edge {
        int node, weight;
        Edge(int n, int w) { node = n; weight = w; }
    }

    public static int primMST(int V, List<List<Edge>> graph) {
        boolean[] inMST = new boolean[V];
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
        pq.add(new Edge(0, 0));
        int mstCost = 0;

        while (!pq.isEmpty()) {
            Edge curr = pq.poll();
            int u = curr.node, w = curr.weight;

            if (inMST[u]) continue;  // Skip if already in MST
            inMST[u] = true;
            mstCost += w;

            for (Edge neighbor : graph.get(u)) {
                if (!inMST[neighbor.node]) pq.add(neighbor);
            }
        }
        return mstCost;
    }

    public static void main(String[] args) {
        int V = 5;
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < V; i++) graph.add(new ArrayList<>());

        graph.get(0).add(new Edge(1, 2));
        graph.get(0).add(new Edge(3, 6));
        graph.get(1).add(new Edge(0, 2));
        graph.get(1).add(new Edge(2, 3));
        graph.get(1).add(new Edge(3, 8));
        graph.get(1).add(new Edge(4, 5));
        graph.get(2).add(new Edge(1, 3));
        graph.get(2).add(new Edge(4, 7));
        graph.get(3).add(new Edge(0, 6));
        graph.get(3).add(new Edge(1, 8));
        graph.get(4).add(new Edge(1, 5));
        graph.get(4).add(new Edge(2, 7));

        System.out.println("Minimum Spanning Tree Cost: " + primMST(V, graph));
    }
}

