class Solution {
    public int solution(int[] stones, int k) {
        return calculate(stones, k);
    }
    
    private static int calculate(int[] stones, int k) {
        int left = 1;
        int right = 200_000_000;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (isCanMove(mid, stones, k)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        return left - 1;
    }
    
    private static boolean isCanMove(int count, int[] stones, int k) {
        int link = 0;
        for (int stone : stones) {
            if (stone - count < 0) {
                link++;
            } else {
                link = 0;
            }
            
            if (link == k) {
                return false;
            }
        }
        
        return true;
    }
}