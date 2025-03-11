import java.util.*;

class Graph{
    private int V ;
    private List<List<Integer>> adj;

    public Graph{
        this.V = V;

        adj = new ArrayList<>();
        for(int i=0;i<V;i++){
            adj.add(new ArrayList<>());
        }
    }

    private boolean dfs(int node, int parent, boolean[] visited){

        visited[node] = true;

        for(int neighbour: adj.get(node)){
            if(!visited[neighbour]){
                if(dfs(neighbour, node, visited)){
                    return true;
                }
                else if(neighbour ! - parent)  return true;
                }
            }
        }
        return false;

    }
}