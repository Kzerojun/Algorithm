import java.util.*;
class Solution {
    static int MOD = 10007;
    public int solution(int n, int[] tops) {
        return play(tops,n);
    }
    
    private static int play(int[] tops,int n) {
        int[] A = new int[n+1];
        int[] B = new int[n+1];
        
        A[0] = 0;
        B[0] = 1;
        
        for(int i = 1; i<=n; i++) {
            if(tops[i-1]==1) {
                A[i] = (A[i-1] + B[i-1]) %MOD;
                B[i] = (2* A[i-1] + 3 * B[i-1]) % MOD;
            }else {
                A[i] = (A[i-1] + B[i-1])%MOD;
                B[i] = (A[i-1] + B[i-1]*2) %MOD;
            }
        }
        
        return (A[n] + B[n]) % MOD;
        
    }
}