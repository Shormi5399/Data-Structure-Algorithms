import java.util.*;

class Dijkstra {
    static class Pair {
        int node, dist;
        Pair(int n, int d) { node = n; dist = d; }
    }

    public static int[] dijkstra(int V, List<List<Pair>> graph, int src) {
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.dist));
        pq.add(new Pair(src, 0));

        while (!pq.isEmpty()) {
            Pair curr = pq.poll();
            int u = curr.node;
            for (Pair neighbor : graph.get(u)) {
                int v = neighbor.node, weight = neighbor.dist;
                if (dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.add(new Pair(v, dist[v]));
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        int V = 5;
        List<List<Pair>> graph = new ArrayList<>();
        for (int i = 0; i < V; i++) graph.add(new ArrayList<>());

        graph.get(0).add(new Pair(1, 10));
        graph.get(0).add(new Pair(4, 5));
        graph.get(1).add(new Pair(2, 1));
        graph.get(2).add(new Pair(3, 4));
        graph.get(3).add(new Pair(0, 7));
        graph.get(4).add(new Pair(1, 3));
        graph.get(4).add(new Pair(2, 9));
        graph.get(4).add(new Pair(3, 2));

        int[] shortestPaths = dijkstra(V, graph, 0);
        System.out.println(Arrays.toString(shortestPaths));
    }
}
