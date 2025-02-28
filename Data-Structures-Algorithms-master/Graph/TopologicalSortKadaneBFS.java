import java.util.*;

class TopologicalSort {
    public static List<Integer> topologicalSort(int V, List<List<Integer>> graph) {
        int[] inDegree = new int[V];
        for (int u = 0; u < V; u++)
            for (int v : graph.get(u))
                inDegree[v]++;

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < V; i++)
            if (inDegree[i] == 0)
                queue.add(i);

        List<Integer> topoOrder = new ArrayList<>();
        while (!queue.isEmpty()) {
            int node = queue.poll();
            topoOrder.add(node);

            for (int neighbor : graph.get(node)) {
                if (--inDegree[neighbor] == 0)
                    queue.add(neighbor);
            }
        }
        return topoOrder.size() == V ? topoOrder : new ArrayList<>(); // Check for cycles
    }

    public static void main(String[] args) {
        int V = 6;
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < V; i++) graph.add(new ArrayList<>());

        graph.get(5).add(2);
        graph.get(5).add(0);
        graph.get(4).add(0);
        graph.get(4).add(1);
        graph.get(2).add(3);
        graph.get(3).add(1);

        System.out.println("Topological Sort: " + topologicalSort(V, graph));
    }
}
