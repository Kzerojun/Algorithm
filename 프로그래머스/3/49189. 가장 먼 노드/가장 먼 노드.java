import java.util.*;

class Solution {
    static List<List<Integer>>graph;
    
    public int solution(int n, int[][] edge) {
        graph = init(n,edge);
        
        int[]dist = new int[n+1];
        Arrays.fill(dist,50001);
        
        bfs(n,dist);
        int maxCount = Integer.MIN_VALUE;
        
        for(int i = 1; i<=n; i++) {
            maxCount = Math.max(dist[i],maxCount);
            System.out.println(dist[i]);
        }
        
        int solution = 0;
        
        for(int i = 1; i<=n; i++) {
            if(maxCount == dist[i]) {
                solution++;   
            }
        }
        
        return solution;
    }
    
    static List<List<Integer>> init(int n, int[][]edges) {
        List<List<Integer>> graph = new ArrayList<>();
        
        for(int i = 1 ; i<=n+1; i++) {
            graph.add(new ArrayList<>());
        }
        
        for(int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        
        return graph;
    }
    
    static void bfs(int n,int[] dist) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(1,0));
        dist[1] = 0;
        
        while(!q.isEmpty()) {
            Node now = q.poll();
            if(now.moveCount>dist[now.number]) continue;
            
            for(int nextNode : graph.get(now.number)) {
                if(dist[nextNode] > now.moveCount +1 ){
                    dist[nextNode] = now.moveCount+1;
                    q.offer(new Node(nextNode,now.moveCount+1));
                }

            }
        }
        
    }
}

class Node{
    int number;
    int moveCount;
    
    Node(int number, int moveCount) {
        this.number = number;
        this.moveCount = moveCount;
    }
}