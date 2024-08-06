import java.util.*;

class Solution {
    static int[][] dp;
    static List<List<Integer>> lighthouses;
    
    
    public int solution(int n, int[][] lighthouse) {
        init(n,lighthouse);
        return play();
    }
    
    private static void init(int n, int[][] lighthouse) {
        //node의 개수, 끈 경우 / 킨 경우
        dp = new int[n+1][2];
        lighthouses = new ArrayList<>();
        
        for(int i = 0 ; i<=n; i++) {
            lighthouses.add(new ArrayList<>());
        }
        
        for(int[] bridge : lighthouse) {
            int from = bridge[0];
            int to = bridge[1];
            lighthouses.get(from).add(to);
            lighthouses.get(to).add(from);
        }
        
    }
    
    private static int play() {
        dfs(1,0);
        return Math.min(dp[1][0],dp[1][1]);
    }
    
    private static void dfs(int node,int parent) {
        dp[node][0] = 0;
        dp[node][1] = 1;
        
        for(int child : lighthouses.get(node)) {
            if(child != parent) {
                dfs(child,node);
                dp[node][0]  += dp[child][1];
                dp[node][1] += Math.min(dp[child][0],dp[child][1]);
            }
        }
    }
}