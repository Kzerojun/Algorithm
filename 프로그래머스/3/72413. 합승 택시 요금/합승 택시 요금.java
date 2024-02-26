import java.util.*;

class Solution {
    static int INF = Integer.MAX_VALUE;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        List<Node>[]graph = new ArrayList[n+1];
        int[][] dist = new int[n+1][n+1];
        
        for(int i = 1; i<graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for(int i = 1; i< dist.length; i++) {
            for(int j = 1; j<dist.length; j++) {
                if(i==j) {
                    dist[i][j] = 0;
                }
                else {
                    dist[i][j] =INF;
                }
            }
        }
        
        for(int[] fare : fares) {
            int from = fare[0];
            int to = fare[1];
            int price = fare[2];
            graph[from].add(new Node(to,price));
            graph[to].add(new Node(from,price));
        }
        
        for(int i = 1; i<=n; i++) {
            move(i,dist,graph);
        }
        int result = INF;
        
        for(int i = 1; i<=n; i++) {
            if(dist[s][i] +dist[i][a]+dist[i][b]<result) {
                result = dist[s][i] +dist[i][a]+dist[i][b];
            }
        }
        
        return result;
        
    }
    
    static void move(int start,int[][]dist,List<Node>[]graph) {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1,o2)->{
            return o1.price - o2.price;
        });
        pq.add(new Node(start,0));
        
        while(!pq.isEmpty()) {
            Node now = pq.poll();
            
            if(dist[start][now.number]<now.price) continue;
            
            for(Node next : graph[now.number]) {
                if(dist[start][next.number] < now.price + next.price) continue;
                
                dist[start][next.number] = now.price+next.price;
                pq.offer(new Node(next.number,now.price+next.price));
            }
        }
        
    }
}

class Node{
    int number;
    int price;
    
    Node(int number, int price) {
        this.number = number;
        this.price = price;
    }
}