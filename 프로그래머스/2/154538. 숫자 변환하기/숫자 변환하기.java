import java.util.*;

class Solution {
    public int solution(int x, int y, int n) {
        return calculate(x,y,n);
    }
    
    private static int calculate(int x, int y, int n) {
        int[] solution = new int[1_000_001];
        int[] dx = {n,2,3};

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(x,0));
        
        while(!q.isEmpty()) {
            Node now = q.poll();
            
            if(now.x == y) {
                return now.time;
            }

            for(int i = 0 ; i < 3 ; i++) {
                
                int nx;
                
                if(i == 0 ){
                    nx = dx[i] + now.x;
                } else {
                    nx = dx[i] * now.x;
                }
                
                if(nx<1_000_001&&solution[nx]==0) {
                    solution[nx] = now.time+1;
                    q.add(new Node(nx,now.time+1));
                }

            }
        }
        
        return -1;

    }

}

class Node {
    int x;
    int time;
    
    Node(int x, int time) {
        this.x = x;
        this.time = time;
        
    }
}