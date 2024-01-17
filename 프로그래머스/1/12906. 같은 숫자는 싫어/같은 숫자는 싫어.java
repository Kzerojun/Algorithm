import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        List<Integer> solution = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        q.offer(arr[0]);
        
        for(int i = 1; i<arr.length; i++) {
            if(q.peek()!= arr[i]){
                solution.add(q.poll());
                q.add(arr[i]);
            }
        }
        
        while(!q.isEmpty()) {
            solution.add( q.poll());
        }
        int[] result = new int[solution.size()];
        int index = 0;
        for(int x : solution) {
            result[index++] = x;
        }
        
        return result;
    }
}