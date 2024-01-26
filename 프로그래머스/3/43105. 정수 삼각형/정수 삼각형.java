import java.util.*;

class Solution {
    static int[] dx = {1,1};
    static int[] dy = {0,1};
    
    public int solution(int[][] triangle) {
        int[][] dp = new int[triangle.length][triangle.length];
        int length = triangle.length;
        dp[0][0] = triangle[0][0];
        
        for(int x = 0 ; x<length-1; x++) {
            for(int y = 0; y<=x; y++) {
                
                for(int k = 0; k<2; k++) {
                 
                    int nx = x+dx[k];
                    int ny = y+dy[k];
                    
                    if(dp[nx][ny]>dp[x][y]+triangle[nx][ny]) continue;
                     dp[nx][ny] = dp[x][y] + triangle[nx][ny];
                }
            }
        }
        
        int result = Integer.MIN_VALUE;
        
        for(int i = 0 ; i<length; i++) {
            result = Math.max(dp[length-1][i],result);
        }
    
        return result;
            
           
            
        }
        
        
    }
    

