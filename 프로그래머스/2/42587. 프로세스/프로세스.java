import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
       Queue<Node>q = new LinkedList<>();
        int[] result = new int[priorities.length];
        int count = 1;
        boolean[] visited = new boolean[priorities.length];
        
        for(int i = 0 ; i< priorities.length; i++) {
            q.offer(new Node(i,priorities[i]));
        }
        
        
        while(!q.isEmpty()) {
            Node now = q.poll();
            boolean check = false;
            
            for(int i = 0; i<q.size(); i++) {
                Node next = q.poll();
                if(next.count > now.count) {
                    check = true;
                }
                q.add(next);
            }

            
            if(check) {
                q.add(now);
            }
            
            if(!check) {
                result[now.index] = count;
                count++;
            }
        

            
            
        }      
        
        return result[location];
        
        
    }
}
class Node {
    int index;
    int count;
    
    Node(int index, int count) {
        this.index = index;
        this.count = count;
    }
}