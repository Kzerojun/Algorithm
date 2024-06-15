import java.util.*;

class Solution {
    
    public long solution(int[] sequence) {
       return calculate(sequence);
    }
    
    private static long calculate(int[] sequence) {
        long[][] dp = new long[sequence.length][2];
        dp[0][0] = sequence[0];
        dp[0][1] = -sequence[0];
        long max = Math.max(dp[0][0],dp[0][1]);
        
        for(int i = 1; i<sequence.length; i++) {
            dp[i][0] = Math.max(dp[i-1][1]+sequence[i],sequence[i]);
            dp[i][1] = Math.max(dp[i-1][0]-sequence[i],-sequence[i]);
            max = Math.max(dp[i][0],Math.max(dp[i][1],max));
        }
        
        return max;
    }
    
    
    
}