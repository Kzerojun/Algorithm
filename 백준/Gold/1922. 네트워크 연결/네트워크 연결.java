import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,M;

    static int[][] graph;
    static int[] parent;
    public static void main(String[]args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        parent = new int[N+1];
        graph = new int[M][3];

        for(int i = 0; i<M; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            graph[i][0] = Integer.parseInt(st.nextToken());
            graph[i][1] = Integer.parseInt(st.nextToken());
            graph[i][2] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(graph, ((o1, o2) -> o1[2]-o2[2]));

        for(int i = 0; i<parent.length; i++){
            parent[i] = i;
        }

        kruskal();
    }

    static void kruskal(){
        int cost = 0;
        for(int i = 0; i< graph.length; i++){
            if(find(graph[i][0])!=find(graph[i][1])){
                cost += graph[i][2];
                union(graph[i][0],graph[i][1]);
            }
        }

        System.out.println(cost);
    }

    static boolean union(int x, int y){
         x = find(x);
         y = find(y);

         if(x==y) return false;

         if(x<=y) parent[y] = x;
         else parent[x] = y;

         return true;
    }

    static int find(int x){
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);

    }
}
class Node1922{
    int index;
    int cost;
    Node1922(int index, int cost){
        this.index = index;
        this.cost = cost;
    }
}