import java.util.*;

class Solution {
    static int[] answer;
    static int[][] arr;
    
    public int[] solution(int[][] arr) {
        init(arr);
        dfs(0,0,arr.length);
        return answer;
    }
    
    private static void init(int[][] arr) {
        Solution.arr = arr;
        answer = new int[2];
    }
    
    private static void dfs(int start, int end , int size) {
        if(isAllSame(start,end,size)) {
            return;
        }
        
        dfs(start,end,size/2);
        dfs(start,end+size/2,size/2);
        dfs(start+size/2,end,size/2);
        dfs(start+size/2,end+size/2,size/2);
        
    }
    
    private static boolean isAllSame(int x, int y, int size) {
        
        Set<Integer> set = new HashSet<>();
        int value = -1;
        
        for(int i = x; i<x+size; i++) {
            for(int j = y; j<y+size; j++) {
                set.add(arr[i][j]);
                value = arr[i][j];
            }
        }
        
        if(set.size()==1) {
            if(value == 0 ) {
                answer[0]++;
            }else {
                answer[1]++;
            }
            return true;
        }
        
        return false;
        
    }
}