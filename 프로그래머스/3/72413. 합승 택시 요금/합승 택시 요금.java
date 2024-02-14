import java.util.*;

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        List<Node>[] list = new ArrayList[n+1];
        int[][] dist = new int[n+1][n+1];
        int result = Integer.MAX_VALUE;
        
        for(int i = 1 ; i<=n; i++) {
            list[i] = new ArrayList<>();
        }
        
        for(int[] fare: fares) {
            int from = fare[0];
            int to = fare[1];
            int price = fare[2];
            list[from].add(new Node(to,price));
            list[to].add(new Node(from,price));
        }
        
        for(int i = 1; i<=n; i++) {
            Arrays.fill(dist[i],Integer.MAX_VALUE);
        }
        
        for(int i = 1; i<=n; i++) {
             move(i,dist,list);
        }
        
        for(int i = 1; i<=n; i++) {
            if(dist[s][i] +dist[i][a]+dist[i][b]<result) {
                result = dist[s][i] + dist[i][a] + dist[i][b];
            }
        }
    
        

        return result;
    }
    
    static void move(int i,int[][] dist,List<Node>[]list) {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1,o2)->{
            return o1.price - o2.price;
        });
        
        pq.offer(new Node(i,0));
        dist[i][i] = 0;
        
        while(!pq.isEmpty()) {
            Node now = pq.poll();
            
            if(now.price > dist[i][now.to]) continue;
            
            for(Node next : list[now.to]) {
                int nextPrice = now.price + next.price;
                if(dist[i][next.to]>nextPrice) {
                    dist[i][next.to] = nextPrice;
                    pq.offer(new Node(next.to,nextPrice));
                }
            }
        }
        
    }
}

class Node{
    int to;
    int price;
    
    Node(int to , int price) {
        this.to = to;
        this.price = price;
    }
}