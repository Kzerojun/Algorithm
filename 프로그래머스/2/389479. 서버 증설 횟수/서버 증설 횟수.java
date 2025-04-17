class Solution {
    static int[] players;
    static int m;
    static int k;
    static int result;
    static int liveServers;
    static int[][] serverInfos;
    
    public int solution(int[] players, int m, int k) {
        init(players,m,k);
        simulate();
        return result;
    }
    
    private static void init(int[] players, int m, int k) {
        Solution.players = players;
        Solution.m = m;
        Solution.k = k;
        Solution.result = 0;
        Solution.liveServers = 0;
        
        // 0 : 몇개의 서버를 증설했는지, 1 : 몇시간 유지했는지
        Solution.serverInfos = new int[24][2];
        
    }
    
    private static void simulate() {
        //0 : 0~1
        for(int i = 0 ; i<24; i++) {
            increaseServerCount();
            int playerCount = players[i];
            
            int needServerCount = playerCount/m;
            
            if(liveServers<needServerCount) {
                int increaseServerCount = needServerCount - liveServers;
                
                result += increaseServerCount;
                liveServers +=increaseServerCount;                
                serverInfos[i][0] = increaseServerCount;
                serverInfos[i][1] = 0;
            }
        }
    }
    
    private static void increaseServerCount() {
        for(int i = 0; i<24; i++) {
            if(serverInfos[i][0]!=0) {
                //유지한 시간 더하기
                serverInfos[i][1]++;
            }
            
            // 유지한 시간이 다 되면 서버 종료하기
            if(serverInfos[i][1]==k) {
                liveServers-=serverInfos[i][0];
                serverInfos[i][0] = 0;
                serverInfos[i][1] = 0;
            }
        }
    }
    

}