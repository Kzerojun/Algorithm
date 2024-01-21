import java.util.*;

class Solution {
    static int[] nx = {1,0,-1};
    static int[] ny = {0,1,-1};
    
    public int[] solution(int n) {
        int[][] result = new int[n][n];
        int number = 1;
        int direction = 0;
        int count = 0;
        int x = -1;
        int y = 0;
        int tmp= n;
        
        while(true) {
            if(n==0) break;
            
            x = x + nx[direction%3];
            y = y + ny[direction%3];
            result[x][y] = number++;
            count++;
            
            if(count == n) {
                count = 0;
                n--;
                direction++;
            }
            
        }
        
        
        List<Integer> resultFinal = new ArrayList<>();
        
        for(int i = 0 ; i<tmp; i++ ) {
            for(int j = 0 ; j<tmp; j++) {
                if(result[i][j]!=0) {
                resultFinal.add(result[i][j]);
                }
            }
        }
        
        
        
        return resultFinal.stream().mapToInt(i->i).toArray();
        
    }
}