import java.util.*;

class Solution {
    public int INF = Integer.MAX_VALUE;
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        List<Integer>[] graph = new ArrayList[n+1];
        int[] dist = new int[n+1];
        
        for(int i = 1; i< graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for(int i = 1; i< graph.length; i++) {
            dist[i] = INF;
        }
        dist[destination] = 0;
        
        
        
        for(int[] road : roads) {
            int to = road[0];
            int from = road[1];
            
            graph[to].add(from);
            graph[from].add(to);
        }
        
        int[] result = new int[sources.length];
        int index = 0;
        move(destination,dist,graph);
        
        for(int i = 0; i<result.length; i++) {
            if(dist[sources[i]]==INF) {
                dist[sources[i]] = -1;
            }
            result[i] = dist[sources[i]];
        }
        return result;
        
        
        
        
    }
    
    static void move(int start,int[]dist,List<Integer>[]graph){
        PriorityQueue<Node> pq = new PriorityQueue<>((o1,o2)->{
            return o1.weight - o2.weight;
        });

        pq.offer(new Node(start,0));
        
        while(!pq.isEmpty()) {
            Node now = pq.poll();
            
            if(dist[now.number]<now.weight) continue;
            
            for(int next : graph[now.number]) {
                if(dist[next]<now.weight+1) continue;
                
                dist[next] = now.weight+1;
                pq.offer(new Node(next,now.weight+1));
            }
             
        }
    }
    
    
}
class Node {
    int number;
    int weight;
    Node(int number, int weight) {
        this.number=number;
        this.weight=weight;
    }
}