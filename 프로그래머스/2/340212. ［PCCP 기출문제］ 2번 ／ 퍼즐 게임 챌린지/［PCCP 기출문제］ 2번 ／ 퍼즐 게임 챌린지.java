class Solution {
    static int[] diffs;
    static int[] times;
    static long limit;
    
    public int solution(int[] diffs, int[] times, long limit) {
        init(diffs,times,limit);
        return binarySearch();
    }
    
    private static void init(int[] diffs, int [] times, long limit) {
        Solution.diffs = diffs;
        Solution.times = times;
        Solution.limit = limit;
    }
    
    private static int binarySearch() {
        int left = 1;
        int right = 100_000;
        
        while(left<right) {
            int mid = (left+right)/2;
        
            if(isCorrect(mid)) {
                right = mid;
            }else {
                left = mid+1;
            }
        }
        
        return left;
    }
    
    private static boolean isCorrect(int level) {
        long useTime = 0;
        for(int i = 0; i<diffs.length; i++) {
            int diff = diffs[i];
            int time_cur = times[i];
            
            if(diff<=level) {
                useTime+=time_cur;
            }else {
                int count = diff -level;
                int time_prev = times[i-1];
                int totalUseTime = (time_prev +time_cur)*count+time_cur;
                useTime += totalUseTime;
            }
        }
                
        if(limit>=useTime) {
            return true;
        }
        return false;
    }   
}