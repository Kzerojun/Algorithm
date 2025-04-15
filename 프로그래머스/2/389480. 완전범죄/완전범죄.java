import java.util.*;

class Solution {
    static int[][] info;
    static boolean[][][] dp;
    static int n;
    static int m;
    static int finalResult;
    
    public int solution(int[][] info, int n, int m) {
        init(info,n,m);
        backtracking(0,0,0);
        return finalResult == Integer.MAX_VALUE ? -1 : finalResult;
    }
    
    private static void init(int[][]info, int n, int m) {
        Solution.info = info;
        Solution.n = n;
        Solution.m = m;
        
        finalResult = Integer.MAX_VALUE;
        dp = new boolean[41][121][121];
    }
    
    private static void backtracking(int aEvidence, int bEvidence, int index) {
        if(index == info.length) {
            finalResult = Math.min(finalResult,aEvidence);
            return;
        }
        
        if(dp[index][aEvidence][bEvidence]) {
            return;
        }
        
        dp[index][aEvidence][bEvidence] = true;
        
        if(aEvidence+info[index][0]<n) {
            backtracking(aEvidence+info[index][0],bEvidence,index+1);
        }
        
        if(bEvidence+info[index][1]<m) {
            backtracking(aEvidence,bEvidence+info[index][1],index+1);
        }
        
    }
    
}

// 물건 i를 훔치면 도둑이 흔적 info[i][0] - A / info[i][1] 흔적을 남김
// 경찰에 붙잡히는 조건은 A의 흔적이 N개 이상 B의 흔적이 M개 이상일때
// 붙잡히지 않도록 모든 물건을 훔칠때 A 도둑이 남긴 누적개수의 최솟값
// 어떠한 방법도 안될시 return -1