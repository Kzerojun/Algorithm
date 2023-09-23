import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static ArrayList<Node1753>[] graph;
    static boolean[] visit;
    static int[] dist;
    static int V,E;

    public static void main(String[]args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        visit = new boolean[V+1];
        graph = new ArrayList[V+1];
        dist = new int[V+1];

        for(int i = 1; i<=V; i++){
            graph[i] = new ArrayList<>();
            dist[i] = Integer.MAX_VALUE;
        }

        int start = Integer.parseInt(br.readLine());


        for(int i = 0; i<E; i++){
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Node1753(v,w));
        }

        Dijkstra(start);
        for(int i = 1; i<=V; i++){
            System.out.println(dist[i]==Integer.MAX_VALUE ? "INF" :dist[i]);
        }

    }

    static void Dijkstra(int start){
        PriorityQueue<Node1753> pq = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);
        pq.offer(new Node1753(start,0));
        dist[start] = 0;

        while(!pq.isEmpty()){
            Node1753 current = pq.poll();

            
            if(!visit[current.end]){
                visit[current.end] = true;
            }
            for(Node1753 next : graph[current.end]){
                if(!visit[next.end] && dist[next.end]> current.weight + next.weight){
                    dist[next.end] = current.weight + next.weight;
                    pq.add(new Node1753(next.end,dist[next.end]));
                }
            }
        }
    }

}

class Node1753{

    int end;
    int weight;

    Node1753(int end, int weight){
        this.end = end;
        this.weight = weight;
    }
}
